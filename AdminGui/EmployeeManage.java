package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeManage extends JPanel {

	EmployeeService service = new EmployeeService();
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<EmployeeVO> list;
	
    public EmployeeManage(JPanel panel) {
      
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	//���̺� �г�
    	JPanel tablePanel = new JPanel();
    	tablePanel.setBounds(300, 0, 500, 500);
		// ������ �ִ� �����͸� ��������
		list = EmployeeDao.select();

		// ���̺� �� ����
		model = new DefaultTableModel();

		// ���̸�
		model.addColumn("���");
		model.addColumn("�̸�");
		model.addColumn("�μ�");

		// MemoVOŬ������ �� �྿ �߰��ϱ�

		for (EmployeeVO temp : list) {
			// �� �྿ �߰��ϱ�
			model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
					});
		}

		// ���̺� ���� �� �� ����
		table = new JTable(model);

		// ��ũ�� ������ ���̺� �г��� ����
		JScrollPane s = new JScrollPane(table);
		tablePanel.add(s);
    	
		add(tablePanel);
    	
    	
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
				
				int selectRow = table.getSelectedRow();
				System.out.println("������ ��:" + selectRow);
				EmployeeDetail ed = new EmployeeDetail(selectRow);
				
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeInsert ei = new EmployeeInsert(model);
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