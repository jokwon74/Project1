import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Commute.DBUtile;

public class Ex {
	public static void main(String[] args) {
//		LocalDateTime now = LocalDateTime.now();
//
//		// LocalDateTime을 Timestamp으로 변환
//		Timestamp timestamp = Timestamp.valueOf(now.withNano(0));
//
//		System.out.println("현재 날짜와 시간(Timestamp 형식): " + timestamp);
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			System.out.println("[ DAO 안에 login() 메서드 실행 ]");

			conn = DBUtile.getConnection();

			String sql = " select * from LOGIN ";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getInt("EMP_ID"));
				System.out.println(rs.getString("EMP_PW"));
				System.out.println();
				
				}
					
	}catch(Exception e) {
		e.printStackTrace();
	}

}
}
