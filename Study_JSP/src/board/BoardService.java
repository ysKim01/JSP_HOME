package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.h;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles(){
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		articlesMap.put("section", pagingMap.get("section"));
		articlesMap.put("pageNum", pagingMap.get("pageNum"));
		
		return articlesMap;
	}

	public int addArticle(ArticleVO articleVO) {
		boardDAO.insertNewArticle(articleVO);
		return articleVO.getArticleNO();
	}
	public void addArticleTest() {
		boardDAO.insertTestArticles100();
	}
	
	public ArticleVO viewArticle(int articleNO) {
		return boardDAO.selectArticle(articleNO);
	}

	public void modArticle(ArticleVO articleVO) {
		boardDAO.updateArticle(articleVO);		
	}

	public List<Integer> removeArticle(int articleNO) {
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}

	public int addReply(ArticleVO articleVO) {
		boardDAO.insertNewArticle(articleVO);
		int num = articleVO.getArticleNO();
		return num;
	}

}
