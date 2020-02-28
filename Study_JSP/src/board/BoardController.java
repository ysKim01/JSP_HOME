package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Dispatch;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "D:\\eclipse-workspace\\article_image";
	BoardService boardService;
	
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		try {
			if(action==null || action.equals("/listArticles.do")) {
				nextPage = listArticles(request, response);
			}
			else if(action.equals("/articleForm.do")){
				nextPage = "/_Board/articleForm.jsp";
			}
			else if(action.equals("/addArticle.do")){
				addArticle(request, response);
				return;
			}
			else if(action.equals("/viewArticle.do")){
				nextPage = viewArticle(request, response);
			}
			else if(action.equals("/modArticle.do")){
				modArticle(request, response);
				return;
			}
			else if(action.equals("/removeArticle.do")){
				removeArticle(request, response);
				return;
			}
			else if(action.equals("/replyForm.do")){
				nextPage = replyForm(request, response);
			}
			else if(action.equals("/addReply.do")){
				addReply(request, response);
				return;
			}
			else if(action.equals("/addTest.do")){
				addTest(request, response);
				return;
			}
			else {
				nextPage = listArticles(request, response);
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String listArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section==null)?"1":_section));
		int pageNum = Integer.parseInt(((_pageNum==null)?"1":_pageNum));
		
		Map<String,Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map articlesMap = boardService.listArticles(pagingMap);
		calSectionPage(articlesMap);
		// ariclesMap 요소
		// section, pageNum, articlesList, totArticles, maxPage, secStart, secEnd
		
		request.setAttribute("articlesMap", articlesMap);
		return "/_Board/listArticles.jsp";
	}
	private void addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArticleVO articleVO = new ArticleVO();
		int articleNO = 0;
		Map<String, String> articleMap = upload(request,response); 
		// title, content, imageFileName 값을 저장하고, 이미지를 임시저장공간에 저장한다.
		
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String imageFileName = articleMap.get("imageFileName");
		articleVO.setParentNO(0);
		articleVO.setId("Hong");
		articleVO.setTitle(title);
		articleVO.setContent(content);
		articleVO.setImageFileName(imageFileName);
		articleNO = boardService.addArticle(articleVO);
		
		if(imageFileName!=null && imageFileName.length()!=0) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\temp\\"+imageFileName);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		PrintWriter pw = response.getWriter();
		pw.print("<script>alert('새 글을 추가했습니다.');"
				+"location.href='"+request.getContextPath()+"/board/listArticles.do';"
				+ "</script>");
	}
	private String viewArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String articleNO = request.getParameter("articleNO");
		ArticleVO articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
		request.setAttribute("article", articleVO);
		return "/_Board/viewArticle.jsp";
	}
	private void modArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArticleVO articleVO = new ArticleVO();
		Map<String, String> articleMap = upload(request, response);
		int articleNO = Integer.parseInt(articleMap.get("articleNO"));
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String imageFileName = articleMap.get("imageFileName");
		String originalFileName = articleMap.get("originalFileName");
		articleVO.setArticleNO(articleNO);
		articleVO.setParentNO(0);
		articleVO.setId("Hong");
		articleVO.setTitle(title);
		articleVO.setContent(content);
		if(imageFileName==null || imageFileName.length()==0) {
			articleVO.setImageFileName(originalFileName);
		}else {
			articleVO.setImageFileName(imageFileName);
		}
		boardService.modArticle(articleVO);
		
		if(imageFileName!=null && imageFileName.length()!=0) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\temp\\"+imageFileName);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			
			File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
			oldFile.delete();
		}
		PrintWriter pw = response.getWriter();
		pw.print("<script>alert('글을 수정했습니다.');"
				+"location.href='"+request.getContextPath()
				+"/board/viewArticle.do?articleNO="+ articleNO +"';</script>");
	}
	private void removeArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int articleNO = Integer.parseInt(request.getParameter("articleNO"));
		List<Integer> articleNOList = boardService.removeArticle(articleNO);
		for(int articleNumber : articleNOList) {
			 File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNumber);
			 if(imgDir.exists()) {
				 FileUtils.deleteDirectory(imgDir);
			 }
		}
		PrintWriter pw = response.getWriter();
		pw.print("<script>alert('글을 삭제했습니다.');"
				+"location.href='"+request.getContextPath()
				+"/board/listArticles.do';</script>");
	}
	private String replyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session;
		int parentNO = Integer.parseInt(request.getParameter("parentNO"));
		session = request.getSession();
		session.setAttribute("parentNO", parentNO);
		return "/_Board/replyForm.jsp";
	}
	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session;
		ArticleVO articleVO = new ArticleVO();
		
		session = request.getSession();
		int parentNO = (Integer)session.getAttribute("parentNO");
		session.removeAttribute("parentNO");
		Map<String, String> articleMap = upload(request,response);
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String imageFileName = articleMap.get("imageFileName");
		articleVO.setParentNO(parentNO);
		articleVO.setId("Lee");
		articleVO.setTitle(title);
		articleVO.setContent(content);
		articleVO.setImageFileName(imageFileName);
		int articleNO = boardService.addReply(articleVO);
		
		if(imageFileName!=null && imageFileName.length()!=0) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\temp\\"+imageFileName);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		
		PrintWriter pw = response.getWriter();
		pw.print("<script>alert('답글을 추가했습니다.');"
				+"location.href='"+request.getContextPath()
				+"/board/viewArticle.do?articleNO="+ articleNO +"';</script>");
	}
	private void addTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boardService.addArticleTest();
		
		PrintWriter pw = response.getWriter();
		pw.print("<script>alert('테스트 글을 추가했습니다.');"
				+"location.href='"+request.getContextPath()+"/board/listArticles.do';"
				+ "</script>");
	}
	
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024); // 1MB
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for(int i=0;i<items.size();i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) { // 파일 외의 form데이터 일 경우
					System.out.println(fileItem.getFieldName() + " = " + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {	// 파일 데이터 일 경우
					if(fileItem.getName()==null || fileItem.getName().length()==0) {
						continue; // 새로운 파일을 업로드하지 않았을 경우
					}
					System.out.println("파라미터명 : " + fileItem.getFieldName());
					System.out.println("파일명 : " + fileItem.getName());
					System.out.println("파일 크기 : " + fileItem.getSize() + "Bytes");
					if(fileItem.getSize()>0) {
						articleMap.put(fileItem.getFieldName(), fileItem.getName());
						File uploadFile = new File(currentDirPath+"\\temp\\"+fileItem.getName());
						fileItem.write(uploadFile);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}
	private void calSectionPage(Map articlesMap) {
		int section = (int)articlesMap.get("section");
		int totArticles = (int)articlesMap.get("totArticles");
		
		int maxPage = (totArticles/10) + ((totArticles%10)!=0?1:0);
		int secStart = (section-1)*10 +1;
		int secEnd = ((section*10)>(maxPage))?maxPage:(section*10);
		
		articlesMap.put("maxPage", maxPage);
		articlesMap.put("secStart", secStart);
		articlesMap.put("secEnd", secEnd);
	}
}









