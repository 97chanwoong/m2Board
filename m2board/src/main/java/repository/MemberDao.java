package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import vo.Member;

public class MemberDao implements IMemberDao {

	// 로그인
	@Override
	public Member selectMemberLogin(Connection conn, Member paramMember) throws Exception {
		// 리턴 객체
		Member member = null;
		// DB
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_name memberName, member_addr memberAddr, member_detailAddr memberDetailAddr, member_phone memberPhone FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			System.out.println(stmt + "<-- selectMemberLogin stmt");
			rs = stmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberAddr(rs.getString("memberAddr"));
				member.setmemberDetailAddr(rs.getString("memberDetailAddr"));
				member.setMemberPhone(rs.getString("memberPhone"));
				System.out.println("#로그인 정보" + member);
			}
		} finally {
			// DB자원 해제
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return member;
	}
	
	// 회원가입 아이디 중복확인
	@Override
	public String selectIdCheck(Connection conn, String idck) throws Exception {
		// 리턴값
		String id = null;
		// DB
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// customer_id, employee_id, out_id 중 중복된 값이 있는지
		String sql = "SELECT member_id memberId FROM member WHERE member_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, idck);
		rs = stmt.executeQuery();

		if (rs.next()) {
			id = rs.getString("memberId");
		}

		if (rs != null) {
			rs.close();
		}

		if (stmt != null) {
			stmt.close();
		}
		// 사용 가능한 아이디는 null
		return id;
	}

	// 회원가입
	@Override
	public int insertMember(Connection conn, Member member) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member (member_id, member_pw, member_name, member_addr, member_detailaddr, member_phone) VALUES (?, PASSWORD(?), ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getMemberName());
			stmt.setString(4, member.getMemberAddr());
			stmt.setString(5, member.getmemberDetailAddr());
			stmt.setString(6, member.getMemberPhone());
			System.out.println(stmt + "<-- stmt");
			row = stmt.executeUpdate();
			if (row == 0) {
				throw new Exception();
			}
		} finally {
			// DB 자원 해제
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 회원 정보 수정
	@Override
	public int updateMember(Connection conn, Member member) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		String sql = "UPDATE member SET member_phone = ?, member_addr = ?, member_detailaddr = ? WHERE member_id = ? AND member_pw = PASSWORD(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberPhone());
			stmt.setString(2, member.getMemberAddr());
			stmt.setString(3, member.getmemberDetailAddr());
			stmt.setString(4, member.getMemberId());
			stmt.setString(5, member.getMemberPw());
			// 디버깅
			System.out.println(stmt + "<-- updateMember stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 회원 비밀번호 수정
	@Override
	public int updateMemberPass(Connection conn, Map<String,Object> map) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_pw = PASSWORD(?) AND member_id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, (String)map.get("memberNewPw"));
			stmt.setString(2, (String)map.get("memberPw"));
			stmt.setString(3, (String)map.get("memberId"));
			// 디버깅
			System.out.println(stmt + "<-- updateMemberPass stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}

	// 회원 탈퇴
	@Override
	public int deleteMember(Connection conn, Member member) throws Exception {
		// 리턴값
		int row = 0;
		// DB
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			// 디버깅
			System.out.println(stmt + "<-- deleteMember stmt");
			row = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return row;
	}
}