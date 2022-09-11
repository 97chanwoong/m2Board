package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private IMemberService memberService;
	// login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}	
	// login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		// 인코딩
		request.setCharacterEncoding("utf-8");
		// new
		memberService = new MemberService();
		Member paramMember = new Member();
		paramMember.setMemberId(request.getParameter("memberId"));
		paramMember.setMemberPw(request.getParameter("memberPw"));
		
		// new
		Member member = memberService.getMemberLogin(paramMember);
		System.out.println(member+"LoginController member");
		
		// 재요청 및 디버깅
		if(member == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath() + "/index");
	}
}
