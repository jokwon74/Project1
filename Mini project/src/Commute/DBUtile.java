package Commute;


import java.sql.Connection;
import java.sql.DriverManager;

// 오라클에  연결하는 클래스
public class DBUtile {

	public static Connection getConnection() {

		Connection conn = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "System";
			String password = "1234";

			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				System.out.println(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
