package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.Member;

public interface MemberService {
	public boolean addMem(Member member); // 회원가입
	public boolean modifyMem(Member member); // 회원정보수정(비밀번호)
	public boolean removeMem(String userId);// 회원탈퇴
	public Member select(String userId); // 단건조회
	public List<Member> selectAll(); // 회원전부조회
}
