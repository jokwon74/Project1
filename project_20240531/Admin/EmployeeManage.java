package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManage extends JPanel {

	EmployeeService service = new EmployeeService();
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<EmployeeVO> list;
	EmployeeVO Employee = new EmployeeVO();
	Scanner sc = new Scanner(System.in);
	EmployeeController controller = new EmployeeController();
	
    public EmployeeManage(JPanel panel) {
      
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	//���̺� �г�
    	JPanel tablePanel = new JPanel();
    	tablePanel.setBounds(300, 50, 500, 500);
		// ������ �ִ� �����͸� ��������
		list = EmployeeDao.select();

		model = new DefaultTableModel()
		{
      
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ��� ���� ���� ���� �Ұ�
            }
        };
		// ���̸�
		model.addColumn("���");
		model.addColumn("�̸�");
		model.addColumn("�μ���");


		for (EmployeeVO temp : list) {
			// �� �྿ �߰��ϱ�
			model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), 
					temp.getDept_name()
					});
		}

		// ���̺� ���� �� �� ����
		table = new JTable(model);

		// ��ũ�� ������ ���̺� �г��� ����
		JScrollPane s = new JScrollPane(table);
		tablePanel.add(s);
    	
		add(tablePanel);
    	
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBounds(300, 0, 400, 50);
		String[] searchitems = { "�̸�", "��"};
		JComboBox<String> SearchBox = new JComboBox<>(searchitems);
		
		JTextField SearchField = new JTextField(20);
		JButton SearchBtn = new JButton("�˻��ϱ�");
		SearchPanel.add(SearchBox);
		SearchPanel.add(SearchField);
		SearchPanel.add(SearchBtn);
		add(SearchPanel);
		
		SearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
    	
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1,10,40));
        buttonPanel.setBounds(50,50,170,300);
        
        JButton button1 = new JButton("�ڼ��� ����");
        JButton button2 = new JButton("��� ���");    
        JButton button3 = new JButton("��� �˻�");
        JButton button4 = new JButton("�μ� �̵�");
        JButton button5 = new JButton("���� ����");
        
        
        //�ڼ��� ����
        button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.detail(table);
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.insert(model);
			}
		});
		
		button3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.search();
			}	
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deptUpdate(model);
			}
			
		});
		
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.jobUpdate(model);
			}
			
			
		});

        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        
        add(buttonPanel);
        
        
        
        
    }

}