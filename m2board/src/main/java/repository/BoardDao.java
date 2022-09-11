package repository;

import java.sql.*;
import java.util.*;

import vo.Board;
import vo.Member;

public class BoardDao implements IBoardDao {
	
	// 게시글 작성
	@Override
	public int insertBoard(Connection conn, Board board) throws Exception {
		int row = 0;
		String sql = "INSERT INTO board(board_title, member_id, board_contents, create_date) VALUES (?, ?, ?, NOW())";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getBoardTitle());
			stmt.setString(2, board.getMemberId());
			stmt.setString(3, board.getBoardContents());
			// 디버깅
			System.out.println(stmt + "<-- insertBoard stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}
	
	// 게시글 수정 
	@Override
	public int updateBoard(Connection conn, Board board) throws Exception {
		int row = 0;
		String sql = "UPDATE board SET board_title = ?, board_contents = ? WHERE board_no = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getBoardTitle());
			stmt.setString(2, board.getBoardContents());
			stmt.setInt(3, board.getBoardNo());
			// 디버깅
			System.out.println(stmt + "<-- updateBoard stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}
	
	// 게시글 삭제
	@Override
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		int row = 0;
		String sql = "DELETE FROM board WHERE board_no = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			// 디버깅
			System.out.println(stmt + "<-- deleteBoard stmt");	
			row = stmt.executeUpdate();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
		return row;
	}
	
	// 게시글 목록
	@Override
	public List<Map<String, Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		String sql = "SELECT board_no boardNo, board_title boardTitle, member_id memberId, create_date createDate, board_views boardViews, IFNULL(n.cnt,0) boardNice FROM board LEFT JOIN (SELECT board_no, COUNT(*) cnt FROM nice GROUP BY board_no) n USING(board_no) ORDER BY create_date DESC LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			// 디버깅
			System.out.println(stmt + "<-- selectBoardListByPage stmt");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("boardNo", rs.getInt("boardNo"));
				map.put("boardTitle", rs.getString("boardTitle"));
				map.put("memberId", rs.getString("memberId"));
				map.put("createDate", rs.getString("createDate"));
				map.put("boardViews", rs.getInt("boardViews"));
				map.put("boardNice", rs.getInt("boardNice"));
				list.add(map);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
	
	// 게시글 총 카운트
	@Override
	public int selectBoardCnt(Connection conn) throws Exception {
		int Cnt = 0;
		String sql = "SELECT COUNT(*) count FROM board";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Cnt = rs.getInt("count");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return Cnt;
	}
		
	// 게시글 상세보기
	@Override
	public Map<String, Object> selectBoardOneByBoardNo(Connection conn, int BoardNo) throws Exception {
		Map<String, Object> map = null;
		String sql = "SELECT board_no boardNo, board_title boardTitle, member_id memberId, board_contents boardContents, create_date createDate, board_views boardViews, IFNULL(n.cnt,0) boardNice FROM board LEFT JOIN (SELECT board_no, COUNT(*) cnt FROM nice GROUP BY board_no) n USING(board_no) WHERE board_no = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BoardNo);
			// 디버깅
			System.out.println(stmt + "<-- selectBoardOneByBoardNo stmt");
			rs = stmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("boardNo", rs.getInt("boardNo"));
				map.put("boardTitle", rs.getString("boardTitle"));
				map.put("memberId", rs.getString("memberId"));
				map.put("boardContents", rs.getString("boardContents"));
				map.put("createDate", rs.getString("createDate"));
				map.put("boardViews", rs.getInt("boardViews"));
				map.put("boardNice", rs.getInt("boardNice"));

			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return map;
	}

	// 게시글 조회수 => 상세보기 누를 시 조회수 +1
	@Override
	public int updateBoardViewCnt(Connection conn, int BoardNo) throws Exception {
		int row = 0;
		String sql = "UPDATE board SET board_views = board_views + 1 WHERE board_no = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BoardNo);
			// 디버깅
			System.out.println(stmt + "<-- updateBoardViewCnt stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}
	
	// 해당Member가 작성한 boardNo 찾기
	@Override
	public List<Map<String, Object>> selectBoardListByMemberId(Connection conn, String memberId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		String sql = "SELECT board_no boardNo, board_title boardTitle, member_id memberId, create_date createDate, board_views boardViews, IFNULL(n.cnt,0) boardNice FROM board LEFT JOIN (SELECT board_no, COUNT(*) cnt FROM nice GROUP BY board_no) n USING(board_no) WHERE member_id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			// 디버깅
			System.out.println(stmt + "<-- selectBoardListByMemberId stmt");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("boardNo", rs.getInt("boardNo"));
				map.put("boardTitle", rs.getString("boardTitle"));
				map.put("memberId", rs.getString("memberId"));
				map.put("createDate", rs.getString("createDate"));
				map.put("boardViews", rs.getInt("boardViews"));
				map.put("boardNice", rs.getInt("boardNice"));
				list.add(map);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
	
	
}
