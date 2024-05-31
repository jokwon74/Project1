package AnnualPaidLeave;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.DBUtil;

public class AnnualPDAO {

	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static ArrayList<appFormVO> Select() {

		System.out.println("[ AnnualPDAO �ȿ� Select()�޼��� ���� ]");

		ArrayList<appFormVO> list = new ArrayList<appFormVO>();

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM TypeOfLeave";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				appFormVO temp = new appFormVO();

				
				temp.setTitle(rs.getString("title"));
				temp.setType(rs.getString("leave_type"));
				temp.setUseDate(rs.getString("leave_date"));
				temp.setEmp_id(rs.getInt("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setContext(rs.getString("content"));
				temp.setWriteDate(rs.getString("writeDate"));
				temp.setProcessing(rs.getString("processing"));

				list.add(temp);

			}

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));

			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public static boolean Insert(appFormVO vo) {
		System.out.println("[ DAO �ȿ� Insert() �޼��� ���� ]");

		boolean result = false;

		try {

			conn = DBUtil.getConnection();

			String sql = "INSERT INTO TypeOfLeave (title, leave_type, leave_date, emp_id, emp_name, content ) "
					+ "VALUES ( ? , ? , ? , ? , ? , ? ) ";

			ps = conn.prepareStatement(sql);

			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getType());
			ps.setString(3, vo.getUseDate().toString());
			ps.setInt(4, vo.getEmp_id());
			ps.setString(5, vo.getEmp_name());
			ps.setString(6, vo.getContent());

			int res = ps.executeUpdate();

			if (res > 0) {
				System.out.println("SQL ���̺� �߰��Ǿ����ϴ�.");
				System.out.println("���� : " + res);
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;

	}

	public static void Search() {

		System.out.println("[ AnnualPDAO �ȿ� Search()�޼��� ���� ]");

		ArrayList<appFormVO> list = new ArrayList<appFormVO>();

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM TypeOfLeave where num = ?";

			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rs.getString("num"));

			rs = ps.executeQuery();

			while (rs.next()) {
				appFormVO temp = new appFormVO();

				
				temp.setTitle(rs.getString("title"));
				temp.setType(rs.getString("leave_type"));
				temp.setUseDate(rs.getString("leave_date"));
				temp.setEmp_id(rs.getInt("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setContext(rs.getString("content"));
				temp.setWriteDate(rs.getString("writeDate"));
				temp.setProcessing(rs.getString("processing"));

				list.add(temp);

			}

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));

			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Delete(int index) {
		System.out.println("[ DAO �ȿ� Delete() �޼��� ���� ]");
		
		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM TypeOfLeave WHERE num = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			
			ps.executeUpdate();
			
			System.out.println("�����Ϸ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

	public static void Approval(int index) {
		// ���� ���� ��ư ���� �� �����ϴ� �ڵ�
		System.out.println("[ DAO �ȿ� Approval() �޼��� ���� ]");

		try {

			conn = DBUtil.getConnection();

			String sql = "UPDATE TypeOfLeave SET PROCESSING = '����Ϸ�' WHERE PROCESSING = '��û' AND NUM = ? ";

			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, index+1);

			int res = ps.executeUpdate();
			System.out.println(res);

			if (res > 0) {
				System.out.println("SQL ���̺� �����Ǿ����ϴ�.");
				Select();
			} else {
				System.out.println("�ٽ� �������ּ���");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

}
