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

@WebServlet("/updateBoard")
public class UpdateBoardController extends HttpServlet {
    private IBoardService boardService;
    
	// updateBoardForm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장할 session
		HttpSession session = request.getSession();
		// 접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login"); // 로그인Controller로 보내기
			return;
		}
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		request.setAttribute("boardNo", boardNo);
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/updateBoard.jsp");
		rd.forward(request, response);
		
	}
	// updateBoardAction
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
		// new
		boardService = new BoardService();
		
		int boardNo = 0;
		
		Board board = new Board();
		board.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		board.setBoardTitle(request.getParameter("boardTitle"));
		board.setMemberId(request.getParameter("memberId"));
		board.setBoardContents(request.getParameter("boardContents"));
		
		// 메서드 실행
		int row = boardService.modifyBoard(board);
		// 재요청 및 디버깅
		if(row == 0) {
			System.out.println("게시글 수정 실패");
			response.sendRedirect(request.getContextPath() + "/updateBoard");
		} else {
			System.out.println("게시글 수정 성공");
			response.sendRedirect(request.getContextPath() + "/boardOne?boardNo="+board.getBoardNo());
		}
	}
}
