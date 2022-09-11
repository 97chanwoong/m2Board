package service;

import java.util.*;

import vo.Member;

public interface IMemberService {
	// 로그인
	Member getMemberLogin(Member paramMember);
	// 회원가입 아이디 중복확인
	String getIdCheck(String idck);
	// 회원가입
	int addMember(Member member);
	// 회원 정보 수정
	int modifyMember(Member member);
	// 회원 비밀번호 수정
	int modifyMemberPass(Map<String,Object> map);
	// 회원 탈퇴
	int removeMember(Member member);
}

