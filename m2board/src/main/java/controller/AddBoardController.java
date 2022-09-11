package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/addBoard")
public class AddBoardController extends HttpServlet {
	private IBoardService boardService;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
	
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/addBoard.jsp");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		
		// new
		boardService = new BoardService();
		
		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
		board.setBoardTitle(request.getParameter("boardTitle"));
		board.setMemberId(request.getParameter("memberId"));
		board.setBoardContents(request.getParameter("boardContents"));
		
		// 메서드 실행
		int row = boardService.addBoard(board);
		// 재요청
		if(row == 0) {
			response.sendRedirect(request.getContextPath() + "/addBoard");
		} else {
			response.sendRedirect(request.getContextPath() + "/boardList");
		}
		
	}

}
