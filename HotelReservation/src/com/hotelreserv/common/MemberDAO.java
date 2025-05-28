package com.hotelreserv.common;

import java.util.ArrayList;
import java.util.List;

import com.hotelreserv.vo.Member;

public class MemberDAO extends DAO{
	List<Member> list = new ArrayList<>();
	// insert , update , delete
	
	// Member_insert
	public int insert(Member member) {
		String sql = "INSERT INTO member(member_id , password , name, gender , age , phone)"+
	                 " VALUES(?,?,?,?,?,?)";
		
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getPhone());
			
			int r = pstmt.executeUpdate();
			return r;
			
			
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		return 0;
		
	}// end of member insert
	
	// Member_update(비밀번호 변경만)
	public int updatePwd(Member member) {
		String sql = "UPDATE TABLE member"+
	                 " SET password = ? "+
	                 "WHERE member_id = ?";
		
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			int r = pstmt.executeUpdate();
			return r;
			
			
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		return 0;
		
	}// end of member update
	
	
	public List<Member> selectAll(){
		String sql = "SELECT * FROM member";
		getConnect();
		Member searchMem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("member_id");
				String userName = rs.getString("name");
				String userGen = rs.getString("gender");
				int userAge = rs.getInt("age");
				String userPhone = rs.getString("phone");
				String userGrd = rs.getString("mem_grade");
				int userMil = rs.getInt("mileage");
				searchMem = new Member(userId, userName, userGen, userAge, userPhone, userGrd,userGrd, userMil);
				list.add(searchMem);
			}
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		
		return list;
		
	}
	
	
	
	// Member_select (단일 조회)
	public Member select(String memberId){
		String sql = "SELECT * FROM member "
				     + "WHERE member_id LIKE ?";
		//List<Member> list = new ArrayList<>();
		getConnect();
		Member searchMem = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				;
				String userId = rs.getString("member_id");
				String userName = rs.getString("name");
				String userGen = rs.getString("gender");
				int userAge = rs.getInt("age");
				String userPhone = rs.getString("phone");
				String userGrd = rs.getString("mem_grade");
				int userMil = rs.getInt("mileage");
				searchMem = new Member(userId, userName, userGen, userAge, userPhone, userGrd, userMil);
				//list.add(searchMem);
			}
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		
		
		return searchMem;
		
		
		
	}// end of select
	
	public int delete(String memberId) {
		String sql = "DELETE FROM member"+
	                 "WHERE member_id = ?";
		
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			int r = pstmt.executeUpdate();
			return r;
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		return 0;
		
	}// end of member delete
	
	
	
	
	
	
}
