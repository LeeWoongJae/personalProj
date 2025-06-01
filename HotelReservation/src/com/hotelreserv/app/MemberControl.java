package com.hotelreserv.app;

import java.util.Scanner;

import com.hotelreserv.service.MemberService;
import com.hotelreserv.service.MemberServiceDAO;
import com.hotelreserv.vo.Member;

public class MemberControl {
	MemberService svc = new MemberServiceDAO();
	Scanner scn = new Scanner(System.in);

	public void execute() {
		boolean inrRunMem = true; // 첫 while 문을 시작
		
		while (inrRunMem) {
			boolean inrRunMemSec = true; // 두번째 while문을 시작하게 만듬
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("｜ 1. 회원가입 ┃ 2. 회원조회 ┃ 3. 정보수정 ┃ 4. 회원탈퇴 ┃ 5. 이전으로 ｜");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("실행하실 메뉴를 입력 >");
			String inrFirstMenu = scn.nextLine();
			switch (inrFirstMenu) {
			case "1": // 회원 가입

				System.out.println("😊 환영합니다. 😊\n회원가입을 시작합니다.");
				System.out.println("가입하실 아이디를 입력해주세요 >");
				System.out.print("[아이디] ");
				String memberId = scn.nextLine();

				System.out.println("비밀번호를 입력해주세요 >");
				System.out.print("[비밀번호] ");
				String password = scn.nextLine();

				System.out.println("이름을 입력해주세요 >");
				System.out.print("[이름] ");
				String name = scn.nextLine();

				System.out.println("나이를 입력해주세요 >");
				System.out.print("[나이] ");
				int age = Integer.parseInt(scn.nextLine());

				System.out.println("성별을 입력해주세요 (m / f)>");
				System.out.print("[성별] ");
				String gender = scn.nextLine();
				if (gender.toLowerCase().equals("m"))
					gender = "male";
				if (gender.toLowerCase().equals("f"))
					gender = "female";

				System.out.println("전화번호를 입력해주세요 >");
				System.out.print("[전화번호] ");
				String phone = scn.nextLine();

				if (svc.addMem(new Member(memberId, password, name, age, gender, phone))) {
					System.err.println("😊 회원가입이 완료되었습니다. 😊");
				} else {
					System.err.println("😔 회원가입이 정상처리 되지못했습니다. 😔");
				}

				break;
			case "2": // 회원 단건 조회

				System.out.println("조회하실 아이디를 입력해주세요. >");
				String searchId = scn.nextLine();
				Member result = svc.select(searchId);
				if(result.getMemberId() != null) {
					System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("｜ 아이디  ┃  이름  ┃   성별   ┃   나이   ┃   연락처   ┃   회원등급  ┃  마일리지｜");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

					System.out.println(
							"┃" + result.getMemberId() + " ┃ " + result.getName() + " ┃  " + result.getGender() + "   ┃   "//
									+ result.getAge() + "  ┃  " + result.getPhone() + "  ┃  " + //
									result.getMemberGrade() + "  ┃  " + result.getMileage());

				}else {
					System.err.println("존재하지 않는 계정입니다.\n확인해 주시고 다시 시도해주세요");
				}
				
				break;
			case "3": // 회원정보 수정

				while (inrRunMemSec) {
					System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("｜ 1. 비밀번호 변경 | 2. 마일리지 변경 | 3. 돌아가기 ｜");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					System.out.println("실행하실 메뉴를 입력 >");
					String inrSecMenu = scn.nextLine();
					switch (inrSecMenu) {
					case "1": // 비밀번호 변경

						System.out.println("비밀번호의 수정만 가능합니다.");

						System.out.println("아이디를 입력해주세요.");
						String updateUserId = scn.nextLine();

						System.out.println("수정하실 비밀번호를 입력해주세요.");
						String updatePwd = scn.nextLine();

						Member member = new Member();
						member.setMemberId(updateUserId);
						member.setPassword(updatePwd);

						if (svc.modifyMemPwd(member)) {
							System.err.println("수정 완료되었습니다. 😀");
						} else {
							System.err.println("수정 취소되었습니다. 다시 확인해 주세요.");
						}
						break;
					case "2": // 마일리지 수정
						System.out.println("마일리지의 수정만 가능합니다.");
						System.out.println("아이디를 입력해주세요.");
						System.out.print("[아이디] ");
						String updateMilUserId = scn.nextLine();

						System.out.println("수정하실 마일리지를 입력해주세요.");
						System.out.print("[마일리지] ");
						int updateMile = Integer.parseInt(scn.nextLine());

						Member memberMile = new Member();
						memberMile.setMemberId(updateMilUserId);
						memberMile.setMileage(updateMile);

						if (svc.modifyMemMile(memberMile)) {

							System.err.println("수정 완료되었습니다. 😀");
						} else {
							System.err.println("수정 취소되었습니다. 다시 확인해 주세요.");
						}

						break;

					case "3": // 돌아가기
						inrRunMemSec = false;
						break;
					}
				}// end of 회원정보 수정
				
				break;
			case "4": // 회원탈퇴
				System.out.println("😔 회원탈퇴를 진행합니다. 😔\n" + "탈퇴하실 아이디와 비밀번호를 입력해주세요.");
				System.out.print("[아이디] ");
				String delUserId = scn.nextLine();
				
				System.out.print("[비밀번호] ");
				String delUserPwd = scn.nextLine();
				
				Member memberDel = new Member();
				memberDel.setMemberId(delUserId);
				memberDel.setPassword(delUserPwd);
				
				svc.removeMem(memberDel);
				System.err.println("이용해주셔서 감사합니다. 😀");
				
				break;
			case "5":
				inrRunMem = false;
				System.err.println("이전 페이지로 돌아갑니다.");
				break;
			}

		}
	}
}// end of class
