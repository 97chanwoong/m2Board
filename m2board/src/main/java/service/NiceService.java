package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import repository.INiceDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService {
	private DBUtil dbutil;
	private INiceDao niceDao;

	// 좋아요 기능
	@Override
	public int modifyBoardNice(Nice nice) {
		// 리턴할 객체 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		niceDao = new NiceDao();

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- modifyBoardNice conn");
			// 자동커밋해제
			conn.setAutoCommit(false);

			if (niceDao.selectNice(conn, nice) == 0) {
				System.out.println("좋아요 누른 기록 없음");
				row = niceDao.insertNice(conn, nice);
			} else {
				System.out.println("좋아요 누른 기록 있음");
				row = niceDao.deleteNice(conn, nice);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}


}
