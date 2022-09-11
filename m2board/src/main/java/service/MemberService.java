package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import repository.IMemberDao;
import repository.INiceDao;
import repository.MemberDao;
import repository.NiceDao;
import vo.Member;

public class MemberService implements IMemberService {
	private DBUtil dbutil;
	private MemberDao memberDao;
	private BoardDao boardDao;
	private NiceDao niceDao;	
	
	// 로그인
	@Override
	public Member getMemberLogin(Member paramMember) {
		// 리턴할 객체 생성
		Member member = null;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- getMemberLogin conn");

			// 자동커밋해제
			conn.setAutoCommit(false);

			member = memberDao.selectMemberLogin(conn, paramMember);
			if (member == null) {
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
		return member;
	}

	// 회원가입 아이디 중복확인
	@Override
	public String getIdCheck(String idck) {
		// 리턴할 변수를 생성
		String id = null;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- getIdCheck conn");
			// 자동커밋해제
			conn.setAutoCommit(false);

			id = memberDao.selectIdCheck(conn, idck);

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
		return id;
	}

	// 회원가입
	@Override
	public int addMember(Member member) {
		// 리턴할 변수를 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();

		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- addMember conn");
			// 자동커밋해제
			conn.setAutoCommit(false);

			row = memberDao.insertMember(conn, member);
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

	// 회원 정보 수정
	@Override
	public int modifyMember(Member member) {
		// 리턴할 변수를 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- modifyMember conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			row = memberDao.updateMember(conn, member);
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

	// 회원 비밀번호 수정
	public int modifyMemberPass(Map<String, Object> map) {
		// 리턴할 변수를 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- modifyMemberPass conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			row = memberDao.updateMemberPass(conn, map);
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

	// 회원 탈퇴
	@Override
	public int removeMember(Member member) {
		// 리턴할 변수를 생성
		int row = 0;
		// 초기화
		Connection conn = null;
		dbutil = new DBUtil();
		memberDao = new MemberDao();
		boardDao = new BoardDao();
		niceDao = new NiceDao();
		
		try {
			conn = dbutil.getConnection();
			// 디버깅
			System.out.println(conn + "<-- removeMember conn");
			// 자동커밋해제
			conn.setAutoCommit(false);
			
			// 해당Member가 작성한 boardNo 찾기
			List<Map<String,Object>> list = boardDao.selectBoardListByMemberId(conn, member.getMemberId());
			for(Map<String,Object> m : list) {
				niceDao.deleteNiceByboardNo(conn, (int)m.get("boardNo"));
				boardDao.deleteBoard(conn, (int)m.get("boardNo"));
			}
			niceDao.deleteNiceByMemberId(conn, member.getMemberId());
			
			row = memberDao.deleteMember(conn, member);
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

}
