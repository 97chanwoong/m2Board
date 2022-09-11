package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;
import vo.Member;

public class BoardService implements IBoardService {
	private DBUtil dbutil;
	private BoardDao boardDao;

	@Override
	public int addBoard(Board board) {
		// 리턴할 객체 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- addBoard conn");
			// 자동커밋해제
			conn.setAutoCommit(false);

			row = boardDao.insertBoard(conn, board);
			if (row == 0) {
				throw new Exception(); // 예외처리
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 5. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}

	@Override
	public int modifyBoard(Board board) {
		// 리턴할 객체 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- modifyBoard conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			row = boardDao.updateBoard(conn, board);
			if (row == 0) {
				throw new Exception(); // 예외처리
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 5. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}

	@Override
	public int removeBoard(int boardNo) {
		// 리턴할 객체 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- removeBoard conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			row = boardDao.deleteBoard(conn, boardNo);
			if (row == 0) {
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 5. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}

	// 반환 값 : List<Board>, lastPage 구하기
	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) {
		// 리턴할 객체 생성
		Map<String, Object> map = new HashMap<String, Object>();

		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();

		// 변수
		int beginRow = (currentPage - 1) * rowPerPage;
		int lastPage = 0;
		int Cnt = 0;

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- getBoardList conn");

			// 자동커밋해제
			conn.setAutoCommit(false);

			List<Map<String, Object>> list = boardDao.selectBoardListByPage(conn, rowPerPage, beginRow);
			if (list == null) {
				throw new Exception(); // 예외처리
			}
			map.put("list", list);

			Cnt = boardDao.selectBoardCnt(conn);
			if (Cnt == 0) {
				throw new Exception(); // 예외처리
			}
			// lastPage 구하기
			lastPage = (int) Math.ceil(Cnt / (double) rowPerPage);
			map.put("lastPage", lastPage);

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 5. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getBoardOneByBoardNo(int boardNo) {
		// 리턴할 객체 생성
		Map<String, Object> map = null;

		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();
		// 변수
		int row = 0;
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- getBoardOneByBoardNo conn");

			// 자동커밋해제
			conn.setAutoCommit(false);

			map = boardDao.selectBoardOneByBoardNo(conn, boardNo);
			if (map == null) {
				throw new Exception(); // 예외처리
			}
			row = boardDao.updateBoardViewCnt(conn, boardNo);
			if (row == 0) {
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 문제 있으면 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 4. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getBoardListByMemberId(String memberId) {
		// 리턴할 객체 생성
		List<Map<String, Object>> list = new ArrayList<>();
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		boardDao = new BoardDao();
		
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- getBoardListByMemberId conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			list = boardDao.selectBoardListByMemberId(conn, memberId);
			if(list == null) {
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 문제 있으면 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 4. 자원해제
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}