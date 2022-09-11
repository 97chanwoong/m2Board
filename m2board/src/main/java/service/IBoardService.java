package service;

import java.util.List;
import java.util.Map;

import vo.Board;
import vo.Member;

public interface IBoardService {
	// 게시글 작성
	int addBoard(Board board);
	// 게시글 수정
	int modifyBoard(Board board);
	// 게시글 삭제
	int removeBoard(int boardNo);
	// 반환값 : List<Board>, int lastPage
	Map<String, Object> getBoardList(int rowPerPage, int currentPage);
	// 반환값 :List<Map<String,Object>>, int BoardNo
	Map<String, Object> getBoardOneByBoardNo(int boardNo);
	// 해당Member가 작성한 boardNo 찾기
	List<Map<String,Object>> getBoardListByMemberId(String memberId);
}
