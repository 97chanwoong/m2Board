package repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import vo.Board;
import vo.Member;

public interface IBoardDao {
	// 게시글 작성
	int insertBoard(Connection conn, Board board) throws Exception;
	// 게시글 수정 
	int updateBoard(Connection conn, Board board) throws Exception;
	// 게시글 삭제 
	int deleteBoard(Connection conn, int boardNo) throws Exception;
	// 게시글 목록
	public List<Map<String,Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception;
	// 게시글 총 카운트
	int selectBoardCnt(Connection conn) throws Exception;
	// 게시글 상세보기
	Map<String,Object> selectBoardOneByBoardNo(Connection conn, int BoardNo) throws Exception;
	// 게시글 조회수 => 상세보기 누를 시 조회수 +1
	int updateBoardViewCnt(Connection conn, int BoardNo) throws Exception;
	// 해당Member가 작성한 boardNo 찾기
	public List<Map<String,Object>> selectBoardListByMemberId(Connection conn, String memberId) throws Exception;
}
