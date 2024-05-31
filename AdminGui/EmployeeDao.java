package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EmployeeDao {

	static Connection conn;
	static PreparedStatement pt;
	static ResultSet rs;

	public static ArrayList<EmployeeVO> select() {
		
		System.out.println("Dao클래스 select()");
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();

			try {
			conn = DBUtil.getConnection();
			System.out.println(conn);
			String sql = "select  * from employee e\r\n" + 
					" join department d on e.dept_code = d.dept_code order by EMP_ID ";
					
			pt = conn.prepareStatement(sql);
			rs = pt.executeQuery();
	
			while(rs.next()) {
				
				EmployeeVO temp = new EmployeeVO();
				temp.setEMP_ID(rs.getInt("EMP_ID"));
				temp.setEMP_NAME(rs.getString("EMP_NAME"));
				temp.setEMP_BIRTH(rs.getString("EMP_BIRTH"));
				temp.setEMAIL(rs.getString("EMP_EMAIL"));
				temp.setPHONE(rs.getString("EMP_PHONE"));
				temp.setJOB_CODE(rs.getString("JOB_CODE"));
				temp.setDEPT_CODE(rs.getString("DEPT_CODE"));
				temp.setAUTHORITY(rs.getString("AUTHORITY"));
				temp.setHIRE_DATE(rs.getString("HIRE_DATE"));
				temp.setDept_name(rs.getString("DEPT_NAME"));
				list.add(temp);
				
			}
			
			
			}catch(Exception e) {
				e.printStackTrace();
			}		
			return list;
		}
	
	
	public static void insert(EmployeeVO vo) {
		 try {
			 //1. db연결
	            conn = DBUtil.getConnection();
	          //2. sql 명령문 
	          // 사원번ㄴ호는 자동으로 증가 후 정렬!
	          String sql = "\r\n" + 
	            		"INSERT INTO EMPLOYEE (\r\n" + 
	            		"    EMP_ID, EMP_NAME, EMP_PHONE, EMP_BIRTH, EMP_EMAIL, DEPT_CODE, JOB_CODE, AUTHORITY, HIRE_DATE\r\n" + 
	            		") VALUES (\r\n" + 
	            		"    (SELECT NVL(MAX(EMP_ID), 0) + 1 FROM EMPLOYEE),\r\n" + 
	            		"    ?, ?, ?, ?, ?, ?, ?, TO_DATE(?)) ";
	            
	            // 3. 임시 전송
	            pt = conn.prepareStatement(sql);
	           
	            pt.setString(1, vo.getEMP_NAME());
	            pt.setString(2, vo.getEMP_BIRTH());
	            pt.setString(3, vo.getEMAIL()); 
	            pt.setString(4, vo.getPHONE()); 
	            pt.setString(5, vo.getDEPT_CODE()); 
	            pt.setString(6, vo.getJOB_CODE());
	            pt.setString(7, "User");
	            pt.setString(8, vo.getHIRE_DATE()); 
	            
	            int result = pt.executeUpdate();
	            System.out.println("전송 :" + result);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (pt != null) pt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	public static boolean checkName(String name) {

		boolean result = false;
		
		try {
		conn = DBUtil.getConnection();
		String sql = "select EMP_NAME from employee";
		pt = conn.prepareStatement(sql);
		rs = pt.executeQuery();

		while(rs.next()) {
			
			if(rs.getString("EMP_NAME").equals(name)) {
				result = true;
				System.out.println("찾았습니다." + rs.getString("EMP_NAME"));
			}
		}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static boolean checkFirstName(String Firstname) {
		
		boolean result = false;
		
		try {
		conn = DBUtil.getConnection();
		String sql = "select EMP_NAME from employee WHERE emp_name LIKE ? || '%' ";
		pt = conn.prepareStatement(sql);
		pt.setString(1, Firstname);
		rs = pt.executeQuery();

		System.out.println(rs.next());
		while(rs.next()) {
			System.out.println((rs.getString("EMP_NAME")).substring(0));
			if((rs.getString("EMP_NAME")).substring(0,1).equals(Firstname)) {
				result = true;
				System.out.println("찾았습니다." + rs.getString("EMP_NAME"));
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static EmployeeVO search(String name) {
		
		EmployeeVO vo = null;
		
		try {
			
			System.out.println("EmployeeService의 search 함수 실행");
			conn = DBUtil.getConnection();
			
			String sql = "select * from employee ";
			sql += " where EMP_NAME = ?";
			
			pt = conn.prepareStatement(sql);
			pt.setString(1,name);
			
			rs = pt.executeQuery();
			
			if(rs.next()) {
				
				vo = new EmployeeVO(
					rs.getInt("EMP_ID"),
					rs.getString("EMP_NAME"),
					rs.getString("EMP_BIRTH"),
					rs.getString("EMP_EMAIL"),
					rs.getString("EMP_PHONE"),
					rs.getString("DEPT_CODE"),
					rs.getString("JOB_CODE"),
					rs.getString("HIRE_DATE")
					 
				);				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return vo;
	}
	public static ArrayList<EmployeeVO> searchFirstname(String Firstname) {
		
		ArrayList<EmployeeVO> searchlist = new ArrayList<EmployeeVO>();
		try {
			
			System.out.println("EmployeeService의 searchFirstname 함수 실행");
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM employee\r\n" + 
					"WHERE emp_name LIKE ? || '%' ";
			
			pt = conn.prepareStatement(sql);
			pt.setString(1,Firstname);
			
			rs = pt.executeQuery();
			
			while(rs.next()) {
				
				EmployeeVO temp = new EmployeeVO();
				temp.setEMP_ID(rs.getInt("EMP_ID"));
				temp.setEMP_NAME(rs.getString("EMP_NAME"));
				temp.setEMP_BIRTH(rs.getString("EMP_BIRTH"));
				temp.setEMAIL(rs.getString("EMP_EMAIL"));
				temp.setPHONE(rs.getString("EMP_PHONE"));
				temp.setJOB_CODE(rs.getString("JOB_CODE"));
				temp.setDEPT_CODE(rs.getString("DEPT_CODE"));
				temp.setAUTHORITY(rs.getString("AUTHORITY"));
				temp.setHIRE_DATE(rs.getString("HIRE_DATE"));
				//temp.setDept_name(rs.getString("DEPT_NAME"));
				searchlist.add(temp);
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return searchlist;
	}
	
	public static int update(String name,String dept) {
		
		conn = DBUtil.getConnection();
		int result = 0;
		
		try {
			System.out.println("EmployeeDao의 부서update함수 실행");
			String sql = "update employee ";
			sql += " set DEPT_CODE =  ? ";
			sql += " where EMP_NAME = ?";
			
			pt = conn.prepareStatement(sql);
			
			pt.setString(1, dept);
			pt.setString(2, name);
			
			result = pt.executeUpdate();
			
			System.out.println(result);
			System.out.println("업데이트 성공");
			
					
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	public static int updateJob(String name,String job) {
		
		conn = DBUtil.getConnection();
		int result = 0;
		
		try {
			System.out.println("EmployeeDao의 부서update함수 실행");
			String sql = "update employee ";
			sql += " set JOB_CODE =  ? ";
			sql += " where EMP_NAME = ?";
			
			pt = conn.prepareStatement(sql);
			
			pt.setString(1, job);
			pt.setString(2, name);
			
			result = pt.executeUpdate();
			
			System.out.println(result);
			System.out.println("업데이트 성공");
			
					
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	


}


