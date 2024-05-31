package login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

// ���̵� ã�� ��ư Ŭ�� ��
public class idFind extends JFrame{
	
	public idFind() {
		setTitle("���̵� ã��");
		setLayout(null);
		setBounds(200, 100, 500, 250);
		
		Font ft = new Font("����", Font.BOLD, 15);
		
		// �̸� �Է¶�
		JPanel namePanel = new JPanel();
		namePanel.setBounds(0, 30, 500, 30);
		
		JLabel nameLabel = new JLabel("�̸� ");
		nameLabel.setFont(ft);
		namePanel.add(nameLabel);

		JTextField nameField = new JTextField(15);
		nameField.setFont(ft);
		namePanel.add(nameField);

		add(namePanel);
		
		// ��ȭ��ȣ �Է¶�
		JPanel phonePanel = new JPanel();
		phonePanel.setBounds(0, 80, 500, 30);
		
		JLabel phoneLabel = new JLabel("��ȭ��ȣ ");
		phoneLabel.setFont(ft);
		phonePanel.add(phoneLabel);

		JTextField phoneField = new JTextField(15);
		phoneField.setFont(ft);
		phonePanel.add(phoneField);

		add(phonePanel);

		
		// ���̵� ã�� ��ư
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(0, 140, 500, 40);
		JButton btn = new JButton("���̵� ã��");
		btnPanel.add(btn);
		add(btnPanel);
		
		// ��ư ���� ��
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().isEmpty()) { 				// �̸� �Է� ������ ��
					JOptionPane.showMessageDialog(btn, "�̸��� �Է��ϼ���.");
				}else if (phoneField.getText().isEmpty()) {		// ��ȭ��ȣ �Է� ������ ��
					JOptionPane.showMessageDialog(btn, "��ȭ��ȣ�� �Է��ϼ���.");
				} else { // �� �� �Է����� ��
					Connection conn;
					PreparedStatement pt;
					ResultSet rs;
					
					try {
						conn = DBUtil.getConnection();
						
						String sql = "select EMP_ID, EMP_NAME, EMP_PHONE from EMPLOYEE ";
						
						
						pt = conn.prepareStatement(sql);
						rs = pt.executeQuery();
						
						
						while(rs.next()) {
							// ���� ���̵�, �̸�, ��ȭ��ȣ
							String id = rs.getString("EMP_ID");
							String name = rs.getString("EMP_NAME");
							String phone = rs.getString("EMP_PHONE");
							
							// �Է��� �̸�, ��ȭ��ȣ
							String inputName = nameField.getText(); 
							String inputPhone = phoneField.getText();
							System.out.println(inputName);
							System.out.println(inputPhone);
							// 1. ���� �̸�, ��ȭ��ȣ�� �Է��� �̸�, ��ȭ��ȣ�� ���� �� ���̵� ���
							if(inputName.equals(name) && inputPhone.equals(phone)) {
								JOptionPane.showMessageDialog(btn, "���̵� : " + id);
								return;
							}

						}
						// 2. ���� �̸�, ��ȭ��ȣ�� �Է��� �̸�, ��ȭ��ȣ�� �ٸ� ��
						JOptionPane.showMessageDialog(btn, "���̵� �������� �ʽ��ϴ�.");
						
					} catch (Exception e2) {
						e2.printStackTrace(); 
					}
					
					
					
					}
					
				
			}
		});
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}