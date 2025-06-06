package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.Member;

public interface MemberService {
	public boolean addMem(Member member); // 회원가입
	public boolean modifyMemPwd(Member member); // 회원정보수정(비밀번호)
	public boolean modifyMemMile(Member member); // 회원정보수정(마일리지)
	public boolean removeMem(Member member);// 회원탈퇴
	public Member select(String userId); // 단건조회
	public List<Member> selectAll(); // 회원전부조회
	
}
