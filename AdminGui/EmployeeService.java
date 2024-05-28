package GUI;

import java.util.ArrayList;

public class EmployeeService {

	public static void select() {
		
		System.out.println("Employeeservice클래스의 select()실행");
		
		ArrayList<EmployeeVO> list = EmployeeDao.select();
				
		for(EmployeeVO memo : list) {
			System.out.println(memo);
		}		
	}
}
