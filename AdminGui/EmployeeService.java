package GUI;

import java.util.ArrayList;

public class EmployeeService {

	public static void select() {
		
		System.out.println("EmployeeserviceŬ������ select()����");
		
		ArrayList<EmployeeVO> list = EmployeeDao.select();
				
		for(EmployeeVO memo : list) {
			System.out.println(memo);
		}		
	}
}
