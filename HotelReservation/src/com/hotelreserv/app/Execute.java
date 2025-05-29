package com.hotelreserv.app;


import java.util.List;
import java.util.Scanner;

import com.hotelreserv.common.HotelDAO;
import com.hotelreserv.common.MemberDAO;
import com.hotelreserv.service.BusInfoService;
import com.hotelreserv.service.BusServiceDAO;
import com.hotelreserv.service.HotelService;
import com.hotelreserv.service.HotelServiceDAO;
import com.hotelreserv.service.MemberService;
import com.hotelreserv.service.MemberServiceDAO;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;
import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;
import com.hotelreserv.vo.Member;

import oracle.sql.DATE;

public class Execute {
	public void execute() {
		
		// 실제로 실행될 메인 페이지
		//MemberDAO dao = new MemberDAO();
		//HotelDAO hDao = new HotelDAO();
		MemberService svc = new MemberServiceDAO();
		HotelService hSvc = new HotelServiceDAO();
		BusInfoService bSvc = new BusServiceDAO();
		
		boolean run = true;
		
		boolean inrRunMem = true;
		boolean inrRunMemSec = true;
		
		boolean inrRunHr = true;
		boolean inrRunHrSec = true;
		
		Scanner scn = new Scanner(System.in);
		while(run) {
			System.out.println("-----------[ Welcome to YEDAM HOTEL ]---------------");
			System.out.println("----------------------------------------------------");
			System.out.println("| 1. 회원 | 2. 객실현황 | 3. 운행버스정보 | 4. 예약 | 5. 종료 |");
			System.out.println("====================================================");
			System.out.println("실행하실 메뉴를 입력 >");
			String menu = scn.nextLine();
			switch(menu) {
			case "1":
				
				
				while(inrRunMem) {
					System.out.println("-----------------------------------------------------");
					System.out.println("1. 회원가입 | 2. 회원조회 | 3. 정보수정 | 4. 회원탈퇴 | 5. 이전으로 ");
					System.out.println("=====================================================");
					System.out.println("실행하실 메뉴를 입력 >");
					String inrMenu = scn.nextLine();
					switch(inrMenu) {
					case "1" : // 회원 가입
						
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
						int age = Integer.parseInt( scn.nextLine());
						
						System.out.println("성별을 입력해주세요 (m / f)>");
						System.out.print("[성별] ");
						String gender = scn.nextLine();
						if(gender.toLowerCase().equals("m")) gender = "male";
						if(gender.toLowerCase().equals("f")) gender = "female";
						
						System.out.println("전화번호를 입력해주세요 >");
						System.out.print("[전화번호] ");
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
							
						System.out.println(result.getMemberId()+"  "+result.getName()+"    "+result.getGender()+"    "//
					                   + result.getAge()+"  "+result.getPhone()+"  "+ //
					                   result.getMemberGrade()+"  "+result.getMileage());
						
						break;
					case "3" : // 회원정보 수정
						
						while(inrRunMemSec) {
							System.out.println("------------------------------------------");
							System.out.println("1. 비밀번호 변경 | 2. 마일리지 변경 | 3. 돌아가기"); 
							System.out.println("==========================================");
							System.out.println("실행하실 메뉴를 입력 >");
							inrMenu = scn.nextLine();
							switch(inrMenu) {
							case "1":
								
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
								}else {
									System.err.println("수정 취소되었습니다. 다시 확인해 주세요.");
								}
								break;
							case "2":
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
								}else {
									System.err.println("수정 취소되었습니다. 다시 확인해 주세요.");
								}
								
								break;
								
							case "3":
								inrRunMemSec = false;
								break;
								}
							}
						break;
					case "4" : // 회원탈퇴
						System.out.println("😔 회원탈퇴를 진행합니다. 😔\n"
											+ "탈퇴하실 아이디를 입력해주세요.");
						System.out.print("[아이디] ");
						String delUserId = scn.nextLine();
						
						svc.removeMem(delUserId);
						
						System.out.println("이용해주셔서 감사합니다. 😀");
						break;
					
					case "5" : 
						inrRunMem = false;
						System.err.println("이전 페이지로 돌아갑니다.");
						break;
					}
					
				}
				break;
			case "2": // 객실현황
				int space = 0;
				List<HotelRoomInfo> roomList = hSvc.roomList();
				//List<HotelRoomInfo> hSlist = hSvc.roomList();
				//System.out.println(hSlist);
				System.out.println("----+----+----+----+----+----+----+----+----+----+");
				for(int i =0;i<roomList.size();i++) {
					space++;
					 System.out.print(roomList.get(i).getRoomNum()+" "+roomList.get(i).getRoomState()+" ");
					 if(space%7==0) {
						 if(roomList.get(i).getRoomNum()/9000 == 1) {System.out.print("3F");}
						 if(roomList.get(i).getRoomNum()/2000 == 1) {System.out.print("2F");}
						 if(roomList.get(i).getRoomNum()/1000 == 1) {System.out.print("1F");}
						 System.out.println("\n");
						 
					 }
					 if(space%14==0) {System.out.println("----+----+----+----+----+----+----+----+----+----+");}
				}
				System.out.println("----+----+----+----+----+----+----+----+----+----+");
				
				break;
			case "3": // 운행정보
				
				List<BusInfo> busList = bSvc.selectAllBusList();

				System.out.println("------------------------------------------------");
				System.out.println("버스등급        버스번호      운행시작      운행종료  탑승인원");
				for(int i =0;i<busList.size();i++) {

					System.out.print("["+busList.get(i).getBusGrd()+"]     ["+busList.get(i).getBusNum()+"]"+ //
				                       "     ["+busList.get(i).getBusOperStart()+"]  ~  [");
					
					if(busList.get(i).getBusGrd().equals("normal")) {
						System.out.print(busList.get(i).getBusOperNorEnd()+"]");
					}else {
						System.out.print(busList.get(i).getBusOperVipEnd()+"]");
					}
					
					System.out.println("    "+busList.get(i).getBusFlowChk());
					
				}
				System.out.println("================================================");
				
				break;
			case "4": // 호텔 예약
				System.out.println("😀 예약을 시작합니다.\n1.호텔 객실예약 2. 셔틀버스 예약\n원하는 메뉴를 입력해주세요.");
				String selectRsv = scn.nextLine();
				switch(selectRsv) {
				case "1": // 호텔 예약
					
					HotelReservation roomselect = new HotelReservation();
					
					System.out.println("😀 호텔 예약을 시작합니다.\n예약자 아이디를 입력해주세요.");
					String rsvUserId = scn.nextLine();
					
					System.out.println("예약하실 방 번호를 입력해주세요.");
					int rsvRoomNo = Integer.parseInt( scn.nextLine() );
					
					System.out.println("예약하실 날짜를 입력해주세요 (예 : 2026-01-01)");
					String rsvStart = scn.nextLine();
					
					
					System.out.println("숙박 기간을 입력해주세요 (예 : 3일 > 3)");
					int rsvEnd = Integer.parseInt( scn.nextLine());
					
					
					roomselect.setReservMem(rsvUserId);
					roomselect.setReservRoomNo(rsvRoomNo);
					roomselect.setReservRoomStart(rsvStart);
					roomselect.setReservRoomEnd(rsvEnd);
					
					if(hSvc.hotelRoomReserv(roomselect) == 1) {;
						System.out.println(" 😀 호텔 예약이 정상처리 되었습니다. 감사합니다! 😀");
					}else {
						System.out.println(" 😔 호텔 예약이 처리되지 못했습니다. 확인해주세요! 😔");
					}
					break;
				case "2":
					BusReservation busResrvSelect = new BusReservation();
					
					System.out.println("😀 버스 예약을 시작합니다.\n예약하실 버스 번호를 입력해주세요.");
					int busRsvNo = Integer.parseInt(scn.nextLine());
					
					
					System.out.println("예약자 아이디를 입력해주세요.");
					String busRsvMemId = scn.nextLine();
					
					busResrvSelect.setBusNo(busRsvNo);
					busResrvSelect.setBusReservMem(busRsvMemId);
					
					
					if(bSvc.busReserv(busResrvSelect) == 1) {;
						System.out.println(" 😀 버스 예약이 정상처리 되었습니다. 감사합니다! 😀");
					}else {
						System.out.println(" 😔 버스 예약이 처리되지 못했습니다. 확인해주세요! 😔");
					}
					
					break;
				}
				
				
				
				break;
			case "5": // 종료
				run = false;
				System.out.println("😊 이용해 주셔서 감사합니다. 😊");
				break;
			}
		}
	}	
	
}
