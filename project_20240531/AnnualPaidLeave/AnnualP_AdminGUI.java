package AnnualPaidLeave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AnnualP_AdminGUI extends JFrame{
	
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<appFormVO> list = new ArrayList<appFormVO>();
	private JButton btn1, btn2, btn3;
	
	public AnnualP_AdminGUI() {

		setBounds(500, 200, 1000, 750);

		// ������ �ִ� ������ ��������
		list = AnnualPDAO.Select();

		init();

		setTitle("[Admin] Table Select");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private void init() {
		// ���̺� �� ����
				model = new DefaultTableModel();

				// �� �̸�
				model.addColumn("������");
				model.addColumn("��û ����");
				model.addColumn("��û ��¥");
				model.addColumn("�� ��");
				model.addColumn("��� �̸�");
				model.addColumn("�ۼ���");
				model.addColumn("�������");

				// MemoVO Ŭ������ �� �྿ �߰��ϱ�
				for (appFormVO temp : list) {

					// �� �྿ �߰��ϱ�
					model.addRow(new Object[] { temp.getTitle(), temp.getType(), temp.getUseDate(), temp.getEmp_id(),
							temp.getEmp_name(), temp.getWriteDate(), temp.getProcessing() });
				}

				// ���̺� ���� �� �� ����
				table = new JTable(model);

				// ��ũ�� ������ ���̺� �г� ����
				JScrollPane scroll = new JScrollPane(table);

				add(scroll);

				JPanel panel = new JPanel();
				add("South", panel);

				btn1 = new JButton("��ȸ");
				btn1.setSize(50, 50);

				btn2 = new JButton("�������");
				btn2.setSize(100, 50);

				btn3 = new JButton("�ݷ�");
				btn3.setSize(150, 50);

				panel.add(btn1);
				panel.add(btn2);
				panel.add(btn3);
				
				//��ȸ
				btn1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						AnnualPDAO.Search();
						
					}
				});
				
				//���� ����
				btn2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int index = table.getSelectedRow();
						
						AnnualPDAO.Approval(index);
						
					}
				});
				
				//�ݷ�
				btn3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
					
		
	}
	
	
	public static void main(String[] args) {
		//new AnnualP_AdminGUI();
	}

}
