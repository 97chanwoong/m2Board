package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;

@WebServlet("/deleteBoard")
public class DeleteBoardController extends HttpServlet {
	private IBoardService boardService;

	// 게시글 삭제 Action
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		// 메서드 실행
		boardService = new BoardService();
		int row = boardService.removeBoard(boardNo);
		//결과 확인 후 포워딩
		if(row == 0) {	
			System.out.println("게시글 삭제 실패");
			response.sendRedirect(request.getContextPath() + "/boardOne"); 
		} else {	
			System.out.println("게시글 삭제 성공");
			// 세션 초기화
			response.sendRedirect(request.getContextPath() + "/boardListMember"); 
		}
	}

}