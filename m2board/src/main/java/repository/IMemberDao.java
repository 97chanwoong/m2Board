package repository;

import java.sql.*;
import java.util.Map;

import vo.Member;

public interface IMemberDao {
	// 매개값 : id, pw
	// 반환값 : 세션에 저장될 Member의 정보 일부
	// 로그인
	Member selectMemberLogin(Connection conn, Member paramMember) throws Exception;
	// 회원가입 아이디 중복확인
	String selectIdCheck(Connection conn, String idck) throws Exception;
	// 회원가입
	int insertMember(Connection conn, Member paramMember) throws Exception;
	// 회원 정보 수정
	int updateMember(Connection conn, Member member) throws Exception;
	// 회원 비밀번호 수정
	int updateMemberPass(Connection conn, Map<String,Object> map) throws Exception;
	// 회원 탈퇴
	int deleteMember(Connection conn, Member member) throws Exception;
}
