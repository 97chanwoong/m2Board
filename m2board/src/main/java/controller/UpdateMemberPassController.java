package controller;

import java.io.IOException;
import java.util.*;

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

@WebServlet("/updateMemberPass")
public class UpdateMemberPassController extends HttpServlet {
	private IMemberService memberService;
	
	// 수정 Form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		// UpdateMember.jsp로 보내주기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/updateMemberPass.jsp");
		rd.forward(request, response);
	}

	// 수정 action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		// 인코딩
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberNewPw = request.getParameter("memberNewPw");
		String memberPw = request.getParameter("memberPw");
		// 객체생성
		Map<String,Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberNewPw", memberNewPw);
		map.put("memberPw", memberPw);
		// 메서드 실행
		memberService = new MemberService();
		int row = memberService.modifyMemberPass(map);
		if (row == 0) {
			System.out.println("회원비밀번호 수정 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/updateMemberPass.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("회원비밀번호 수정 성공");
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}

}
