//package dao;
//
//import java.util.Scanner;
//
//import dto.MemberDTO;
//
//public class MainMenuDAO extends MemberDTO{
//	
//	 public MainMenuDAO(String id, String pw, String name, String gender, int age) {
//		super(id, pw, name, gender, age);
//	}



//	public int signUp(Scanner scanner) {
//	        System.out.print("id를 입력해주세요 >> ");
//	        String id = scanner.next();
//	        System.out.print("pw를 입력해주세요 >> ");
//	        String pw = scanner.next();
//	        System.out.print("name을 입력해주세요 >> ");
//	        String name = scanner.next();
//	        System.out.print("성별 남/여을 입력해주세요 >> ");
//	        String gender = scanner.next();
//	        System.out.print("age를 입력해주세요 >> ");
//	        int age = scanner.nextInt();
//	        MemberDTO dto = new MemberDTO(id, pw, name, gender, age);
//	        int row = join(dto);
//	        if (row > 0) {
//	            System.out.println("가입완료");
//	        }
//	        return row;
//	    }
//
//
//	
//	public int withdraw(Scanner scanner) {
//        System.out.print("id를 입력해주세요 >> ");
//        String id = scanner.next();
//        System.out.print("pw를 입력해주세요 >> ");
//        String pw = scanner.next();
//        MemberDTO dto = new MemberDTO(id, pw);
//        int row = delete(dto);
//        if (row > 0) {
//            System.out.println("회원탈퇴완료");
//        }
//        return row;
//    }
//
//}
