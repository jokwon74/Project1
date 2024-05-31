package Admin;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeService {

	public static void select() {
		
		System.out.println("EmployeeserviceŬ������ select()����");
		
		ArrayList<EmployeeVO> list = EmployeeDao.select();
				
		for(EmployeeVO memo : list) {
			System.out.println(memo);
		}		
	}
	
	public static EmployeeVO search() {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�̸�:");
		String name = sc.nextLine();
		
		//���� �����ͺ��̽����� �˻��ϱ� 
		EmployeeVO vo =  EmployeeDao.search(name);
		System.out.println("Ȯ��: "+ vo);
		
		return vo;
		
	}
	
}
