package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.common.MemberDAO;
import com.hotelreserv.vo.Member;

public class MemberServiceDAO implements MemberService {

	MemberDAO dao = new MemberDAO();
	
	@Override
	public boolean addMem(Member member) {
		return dao.insert(member)==1;
	}

	@Override
	public boolean modifyMemPwd(Member member) {
		return dao.updatePwd(member)==1;
	}
	
	@Override
	public boolean modifyMemMile(Member member) {
		return dao.updateMile(member)==1;
	}
	

	@Override
	public boolean removeMem(String userId) {
		return dao.delete(userId)==1;
		
	}

	@Override
	public List<Member> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Member select(String userId) {
		return dao.select(userId);
	}

}
