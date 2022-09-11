package repository;

import java.sql.Connection;

import vo.Nice;

public interface INiceDao {
	// 해당글 좋아요를 누른적이 있는지 조회기능
	int selectNice(Connection conn, Nice nice) throws Exception;
	// 좋아요 추가기능
	int insertNice(Connection conn, Nice nice) throws Exception;
	// 좋아요 취소기능
	int deleteNice(Connection conn, Nice nice) throws Exception;
	// 해당게시글 좋아요 전체 삭제
	int deleteNiceByboardNo(Connection conn, int boardNo) throws Exception;
	// 해당Member가 누른 좋아요 전체 삭제
	int deleteNiceByMemberId(Connection conn, String memberId) throws Exception;
}
