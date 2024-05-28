package GUI;

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
	Scanner sc = new Scanner(System.in);
	
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
				if(selectRow != -1) {
					EmployeeDetail ed = new EmployeeDetail(selectRow);
				}else {
					//������ ���� ������
					JOptionPane.showMessageDialog(button1, "����� ������ �ּ���.");
				}
				
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeInsert ei = new EmployeeInsert(model);
			}
		});
		
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("EmployeeManage�� ��� �˻� ����");
				
				JFrame SearchFrame = new JFrame();
				JPanel SearchPanel = new JPanel();
				
				JLabel NameLabel = new JLabel("�̸� : ");
				JTextField NameFeild = new JTextField(20);
				
				JButton submitButton = new JButton("�˻��ϱ�");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//ã�� �˻�â �����
						JFrame FindFrame = new JFrame();
						
						String name = NameFeild.getText();
						//ã�� �̸��� �ִ��� �˻��ϴ� �Լ�
						boolean check = EmployeeDao.checkName(name);
						if(!check) {
							//ã�� �̸��� ������
							JOptionPane.showMessageDialog(submitButton, "ã�� ����� ������ �����ϴ�.!");
						}
						else {
							
							EmployeeVO vo = EmployeeDao.search(name);			
						}
					}
				});
				SearchPanel.add(NameLabel);
				SearchPanel.add(NameFeild);
				SearchPanel.add(submitButton);
				
				SearchFrame.add(SearchPanel);
				SearchFrame.setSize(400,300);
				SearchFrame.setVisible(true);
			
			}
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//���ο� ������ ����
				JFrame UpdateFrame = new JFrame("�μ� �̵�");
				JPanel panel = new JPanel();
				
				//�̸��Է�
				JLabel nameLabel = new JLabel("�̸� : ");
				JTextField nameField = new JTextField(20);
				
				//�μ� �Է�
				JLabel deptLabel = new JLabel("�μ� : ");
				JTextField deptField = new JTextField(20);
				
				JButton submitButton = new JButton("�μ� �̵� �ϱ�");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = nameField.getText();
						String dept = deptField.getText();
						boolean check = EmployeeDao.checkName(name);
						
						if(!check) {
							//ã�� �̸��� ������
							JOptionPane.showMessageDialog(submitButton, 
									"�μ� �̵��� ����� �������� �ʽ��ϴ�.!");
							
						}else {
							//������Ʈ ����
							EmployeeDao.update(name,dept);
							
							// ������Ʈ �� ���� ��ħ
							list = EmployeeDao.select();

							// ��� ���� ����
							model.setRowCount(0);
							

							for (EmployeeVO temp : list) {
								// �� �྿ �߰��ϱ�
								model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
										});
							}
							
						}	
					}
				});
				panel.add(nameLabel);
				panel.add(nameField);
				
				panel.add(deptLabel);
				panel.add(deptField);
				
				panel.add(submitButton);
				
				UpdateFrame.add(panel);
				UpdateFrame.setSize(300,300);
				UpdateFrame.setVisible(true);
	
			}
			
			
		});
		
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//���ο� ������ ����
				JFrame UpdateFrame = new JFrame("���� ����");
				JPanel panel = new JPanel();
				
				//�̸��Է�
				JLabel nameLabel = new JLabel("�̸� : ");
				JTextField nameField = new JTextField(20);
				
				//�μ� �Է�
				JLabel jobLabel = new JLabel("���� : ");
				JTextField jobField = new JTextField(20);
				
				JButton submitButton = new JButton("���� ���� �����ϱ�");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = nameField.getText();
						String job = jobField.getText();
						
						//ã�� �̸� �ִ��� �˻��ϴ� �Լ�
						boolean check = EmployeeDao.checkName(name);
						
						if(!check) {
							//ã�� �̸��� ������
							JOptionPane.showMessageDialog(submitButton, 
									"���� ������ ����� �������� �ʽ��ϴ�.!");
							
						}else {
							//ã�� �̸� ����
							//������Ʈ ����
							EmployeeDao.updateJob(name,job);
							
							// ������Ʈ �� ���� ��ħ
							list = EmployeeDao.select();

							// ��� ���� ����
							model.setRowCount(0);
							

							for (EmployeeVO temp : list) {
								// �� �྿ �߰��ϱ�
								model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
										});
							}
							
						}	
					}
				});
				panel.add(nameLabel);
				panel.add(nameField);
				
				panel.add(jobLabel);
				panel.add(jobField);
				
				panel.add(submitButton);
				
				UpdateFrame.add(panel);
				UpdateFrame.setSize(300,300);
				UpdateFrame.setVisible(true);
	
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