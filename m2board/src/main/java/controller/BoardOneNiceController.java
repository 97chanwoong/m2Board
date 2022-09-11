package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.INiceService;
import service.NiceService;
import vo.Member;
import vo.Nice;

@WebServlet("/boardOneNice")
public class BoardOneNiceController extends HttpServlet {
	private INiceService niceService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		
		// new
		niceService = new NiceService();
		Nice nice = new Nice();
		// boardNo 값 받기
		nice.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		nice.setMemberId(((Member) session.getAttribute("loginMember")).getMemberId());
		// 메서드실행
		int row = niceService.modifyBoardNice(nice); 
		// 재요청
		if(row == 0) {
			System.out.println("좋아요 실패");
		} else {
			System.out.println("좋아요 성공");
		}
		
		response.sendRedirect(request.getContextPath() + "/boardOne?boardNo="+nice.getBoardNo());
		
	}

}
