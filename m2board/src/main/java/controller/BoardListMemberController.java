package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.BoardDao;
import service.BoardService;
import vo.Board;
import vo.Member;

@WebServlet("/boardListMember")
public class BoardListMemberController extends HttpServlet {
	private BoardService boardService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 저장할 session 
		HttpSession session = request.getSession();
		//접근제한
		if (session.getAttribute("loginMember") == null) { // 로그인이 안된 상태라면
			response.sendRedirect(request.getContextPath() + "/login");	//로그인Controller로 보내기
			return;
		}
		boardService = new BoardService();
		// 해당 유저의 작성글 목록
		Member paramMember = (Member) session.getAttribute("loginMember");
		List<Map<String, Object>> list = new ArrayList<>(); 
		list = boardService.getBoardListByMemberId(paramMember.getMemberId());
		//디버깅
		System.out.println(list);
		request.setAttribute("list", list);
		//디버깅
		System.out.print(request.getAttribute("list"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardListMember.jsp");
		rd.forward(request, response);
	}
}