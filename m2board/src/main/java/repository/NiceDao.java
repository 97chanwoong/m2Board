package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Nice;

public class NiceDao implements INiceDao {

	// 해당글 좋아요를 누른적이 있는지 조회기능
	@Override
	public int selectNice(Connection conn, Nice nice) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM nice WHERE board_no = ? and member_id =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nice.getBoardNo());
			stmt.setString(2, nice.getMemberId());
			// 디버깅
			System.out.println(stmt + "<-- selectNice stmt");
			rs = stmt.executeQuery();
			if (rs.next()) { // 쿼리가 실행된다면
				row = rs.getInt("cnt");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 좋아요 추가기능
	@Override
	public int insertNice(Connection conn, Nice nice) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		String sql = "INSERT INTO nice (board_no, member_id, create_date) VALUES (?, ?, now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nice.getBoardNo());
			stmt.setString(2, nice.getMemberId());
			// 디버깅
			System.out.println(stmt + "<-- selectNice stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 좋아요 취소기능
	@Override
	public int deleteNice(Connection conn, Nice nice) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		String sql = "DELETE FROM nice WHERE  board_no=? AND member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nice.getBoardNo());
			stmt.setString(2, nice.getMemberId());
			// 디버깅
			System.out.println(stmt + "<-- deleteNice stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 해당게시글 좋아요 전체 삭제
	@Override
	public int deleteNiceByboardNo(Connection conn, int boardNo) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		String sql = "DELETE FROM nice WHERE  board_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			// 디버깅
			System.out.println(stmt + "<-- deleteNiceByboardNo stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		if(row == 1 ) {
			System.out.print(row + "!!!!!!!!!!!!!!!!!!!!");
		}else {
			System.out.print(row + "?????????????????????");

		}
		return row;
	}

	@Override
	public int deleteNiceByMemberId(Connection conn, String memberId) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		String sql = "DELETE FROM nice WHERE member_id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			// 디버깅
			System.out.println(stmt + "<-- deleteNiceByMemberId stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

}