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

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private IMemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") != null) { // 로그인이 된 상태라면
			response.sendRedirect(request.getContextPath() + "/index"); // indexController로 보내기
			return;
		}
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/addMember.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") != null) { // 로그인이 된 상태라면
			response.sendRedirect(request.getContextPath() + "/index"); // indexController로 보내기
			return;
		}
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// new
		memberService = new MemberService();
		// 값 객체에 넣기
		Member paramMember = new Member();
		paramMember.setMemberId(request.getParameter("memberId"));
		paramMember.setMemberPw(request.getParameter("memberPw"));
		paramMember.setMemberName(request.getParameter("memberName"));
		paramMember.setMemberAddr(request.getParameter("memberAddr"));
		paramMember.setmemberDetailAddr(request.getParameter("memberDetailAddr"));
		paramMember.setMemberPhone(request.getParameter("memberPhone"));
		
		// 메서드 실행
		int row = memberService.addMember(paramMember);
		//  재요청 및 콘솔 디버깅
		if(row == 0) {
			System.out.println("회원가입 실패");
			response.sendRedirect(request.getContextPath() +"/addMember");
		} else {
			System.out.println("회원가입 성공");
			response.sendRedirect(request.getContextPath() +"/login");
		}
		
	}	
}
