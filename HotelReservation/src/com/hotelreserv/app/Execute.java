package com.hotelreserv.app;


import java.util.Scanner;
import com.hotelreserv.common.MemberDAO;
import com.hotelreserv.service.MemberService;
import com.hotelreserv.service.MemberServiceDAO;
import com.hotelreserv.vo.Member;

public class Execute {
	public void execute() {
		
		// 실제로 실행될 메인 페이지
		MemberDAO dao = new MemberDAO();
		MemberService svc = new MemberServiceDAO();
		boolean run = true;
		
		boolean inrRun = true;
		Scanner scn = new Scanner(System.in);
		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1. 회원 | 2. 객실현황 | 3. 운행버스정보 | 4. 예약 | 5. 종료");
			System.out.println("==================================================");
			System.out.println("실행하실 메뉴를 입력 >");
			String menu = scn.nextLine();
			switch(menu) {
			case "1":
				
				
				while(inrRun) {
					System.out.println("--------------------------------------------------------");
					System.out.println("1. 회원가입 | 2. 회원조회 | 3. 정보수정 | 4. 회원탈퇴 | 5. 이전으로 ");
					System.out.println("========================================================");
					System.out.println("실행하실 메뉴를 입력 >");
					String inrMenu = scn.nextLine();
					switch(inrMenu) {
					case "1" : // 회원 가입
						
						System.out.println("😊 환영합니다. 😊\n회원가입을 시작합니다.");
						System.out.println("가입하실 아이디를 입력해주세요 >");
						
						String memberId = scn.nextLine();
						
						System.out.println("비밀번호를 입력해주세요 >");
						String password = scn.nextLine();
						
						System.out.println("이름을 입력해주세요 >");
						String name = scn.nextLine();
						
						System.out.println("나이를 입력해주세요 >");
						int age = Integer.parseInt( scn.nextLine());
						
						System.out.println("성별을 입력해주세요 >");
						String gender = scn.nextLine();
						
						System.out.println("전화번호를 입력해주세요 >");
						String phone = scn.nextLine();
						
												
						if(svc.addMem(new Member(memberId, password, name, age, gender, phone ))) {
							System.err.println("😊 회원가입이 완료되었습니다. 😊");
						}else {
							System.err.println("😔 회원가입이 정상처리 되지못했습니다. 😔");
						}
						
						break;
					case "2" : // 회원 단건 조회
						
						System.out.println("조회하실 아이디를 입력해주세요. >");
						String searchId = scn.nextLine();
						Member result = svc.select(searchId);
						
						System.out.println("----------------------------------------------------------");
						System.out.println(" 아이디 |  이름  |  성별  |  나이  |  연락처  |  회원등급  | 마일리지");
						System.out.println("==========================================================");
							
						System.out.println(result.getMemberId()+" "+result.getName()+"  "+result.getGender()+"    "//
					                   + result.getAge()+"  "+result.getPhone()+"  "+ //
					                   result.getMemberGrade()+"  "+result.getMileage());
						
						break;
					case "3" : // 회원정보 수정
						System.out.println("비밀번호의 수정만 가능합니다.");
						
						System.out.println("아이디를 입력해주세요.");
						String updateUserId = scn.nextLine();
						
						System.out.println("수정하실 비밀번호를 입력해주세요.");
						String updatePwd = scn.nextLine();
						
						Member member = new Member();
						member.setMemberId(updateUserId);
						member.setPassword(updatePwd);
						
						if (svc.modifyMem(member)) {
							System.out.println("삭제가 완료되었습니다. \n 이용해주셔서 감사합니다 😀");
						}
						
						break;
					case "4" : // 회원탈퇴
						System.out.println("😔 회원탈퇴를 진행합니다. 😔");
						System.out.println("탈퇴하실 아이디를 입력해주세요 >");
						String delUserId = scn.nextLine();
						
						svc.removeMem(delUserId);
						
						System.out.println("이용해주셔서 감사합니다. 😀");
						break;
					
					case "5" : 
						inrRun = false;
						System.err.println("이전 페이지로 돌아갑니다.");
						break;
					}
					
				}
				break;
			case "2": // 객실현황
				
				break;
			case "3":
	
				break;
			case "4":
	
				break;
			case "5":
	
				break;
			case "6":
				run = false;
				System.out.println("😊 이용해 주셔서 감사합니다. 😊");
				break;
			}
			
			
		}
		
		
	}	
	
}
