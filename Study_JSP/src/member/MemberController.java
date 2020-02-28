package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
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
		String action = request.getPathInfo(); // 마지막 url
		System.out.println("action : " + action);
		
		if(action==null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/_Board/listMembers.jsp";
		}
		else if(action.equals("/addMember.do")){
			String id = (String)request.getParameter("id");
			String pw = (String)request.getParameter("pw");
			String name = (String)request.getParameter("name");
			String email = (String)request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pw, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member/listMembers.do";
		}
		else if(action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "delMember");
			nextPage = "/member/listMembers.do";
		}
		else if(action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			System.out.println("mod ID : " + id);
			MemberVO memberInfo = memberDAO.findMember(id);
			request.setAttribute("memberInfo", memberInfo);
			nextPage = "/_Board/modMemberForm.jsp";
		}
		else if(action.equals("/modMember.do")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			System.out.println(id+", "+pw+", "+name+", "+email);
			memberDAO.modMember(new MemberVO(id,pw,name,email));
			request.setAttribute("msg", "modMember");
			nextPage = "/member/listMembers.do";
		}
		else if(action.equals("/memberForm.do")) {
			nextPage = "/_Board/memberForm.jsp";
		}
		else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/_Board/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}








