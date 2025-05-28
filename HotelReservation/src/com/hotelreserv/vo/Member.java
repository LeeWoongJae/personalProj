package com.hotelreserv.vo;

public class Member {
	private String memberId;
	private String password;
	private String name;
	private int age;
	private String gender;
	private String phone;
	private String memberGrade;
	private int mileage;
	
	// Constructor
	public Member(){}
	
	public Member(String memberId,	String password,String name, //
			int age, String gender,String phone)
	{
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
	}
	

	public Member(String memberId, String name, String gender, //
			int age, String phone, String memberGrade, int mileage) {
		this.memberId = memberId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.memberGrade = memberGrade;
		this.mileage = mileage;

	}	
	
	public Member( String memberId,	String password,String name, //
					int age,String gender,String phone, //
					String memberGrade,	int mileage) 
	{
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.mileage = mileage;
		
	}
	
	
	// getter , setter
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + ", phone=" + phone + ", memberGrade=" + memberGrade + ", mileage=" + mileage
				+ "]";
	}
	
	
	
	
	
}
