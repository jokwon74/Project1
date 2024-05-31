package Admin;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeService {

	public static void select() {
		
		System.out.println("Employeeservice클래스의 select()실행");
		
		ArrayList<EmployeeVO> list = EmployeeDao.select();
				
		for(EmployeeVO memo : list) {
			System.out.println(memo);
		}		
	}
	
	public static EmployeeVO search() {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름:");
		String name = sc.nextLine();
		
		//실제 데이터베이스에서 검색하기 
		EmployeeVO vo =  EmployeeDao.search(name);
		System.out.println("확인: "+ vo);
		
		return vo;
		
	}
	
}
