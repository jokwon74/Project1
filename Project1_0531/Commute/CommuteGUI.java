package Commute;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AnnualPaidLeave.AnnualPDAO;
import AnnualPaidLeave.appFormVO;

// ��� �� �ް� ��� ��Ȳ
public class CommuteGUI extends JFrame{
	
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<appFormVO> list = new ArrayList<appFormVO>();
	
	void CommuteGUI() {

			setBounds(0, 0, 1000, 750);

			// ������ �ִ� ������ ��������
			list = AnnualPDAO.Select();

			init();

			setTitle("Table Select");
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	

	private void init() {
		// TODO Auto-generated method stub
		
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
