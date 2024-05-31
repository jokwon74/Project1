package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Admin.DBUtil;
import Admin.EmployeeVO;
import login.loginGUI;

public class UserDao {

	static Connection conn;
	static PreparedStatement pt;
	static ResultSet rs;

	public static UserVO search(int inputId) {
		
		UserVO vo = null;
		
		try {
			
			System.out.println("UserDao의 search 함수 실행");
			conn = DBUtil.getConnection();
			
			String sql = "select * from employee ";
			sql += " where EMP_ID = ?";
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1,inputId);
			
			rs = pt.executeQuery();
			
			if(rs.next()) {
				
				vo = new UserVO(
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
	
}
