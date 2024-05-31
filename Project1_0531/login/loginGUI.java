package login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Admin.AdminPage;
import Commute.Service;
import User.UserPage;

public class loginGUI extends JFrame {

	public loginGUI() {
		setTitle("�α���");
		setLayout(null);
		setBounds(0, 0, 1000, 750);

		Font ft = new Font("����", Font.BOLD, 15);

		
		// ���̵� �Է¶�
		JPanel idPanel = new JPanel();
		idPanel.setBounds(0, 350, 1000, 50);
		
		JLabel idLabel = new JLabel("���̵� ");
		idLabel.setFont(ft);
		idPanel.add(idLabel);

		JTextField idField = new JTextField(10);
		idField.setFont(ft);
		idPanel.add(idField);

		add(idPanel);

		// ��й�ȣ �Է¶�
		JPanel pwPanel = new JPanel();
		pwPanel.setBounds(0, 400, 1000, 50);

		JLabel pwLabel = new JLabel("��й�ȣ ");
		pwLabel.setFont(ft);
		pwPanel.add(pwLabel);

		JPasswordField pwField = new JPasswordField(14);
		pwField.setEchoChar('*');
		pwPanel.add(pwField);

		add(pwPanel);

		// ���̵� ã�� ��ư
		JPanel btnIdFindPanel = new JPanel();
		btnIdFindPanel.setBounds(350, 450, 150, 50);
		JButton btnIdFind = new JButton("���̵� ã��");
		btnIdFindPanel.add(btnIdFind);
		add(btnIdFindPanel);

		// ��й�ȣ ã�� ��ư
		JPanel btnPwFindPanel = new JPanel();
		btnPwFindPanel.setBounds(500, 450, 150, 50);
		JButton btnPwFind = new JButton("��й�ȣ ã��");
		btnPwFindPanel.add(btnPwFind);
		add(btnPwFindPanel);

		// �α��� ��ư
		JPanel btnLoginPanel = new JPanel();
		btnLoginPanel.setBounds(0, 500, 1000, 50);
		JButton btnLogin = new JButton("�α���");
		btnLoginPanel.add(btnLogin);
		add(btnLoginPanel);
		
		
		// ���̵� ã�� ��ư Ŭ��
		btnIdFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		
				new idFind();
			}
		});
		
		// ��й�ȣ ã�� ��ư Ŭ��
		btnPwFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new pwFind();
			}
		});
		
		// �α��� ��ư Ŭ��
		btnLogin.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(idField.getText().isEmpty()) { 				// ���̵� �Է� ������ ��
					JOptionPane.showMessageDialog(btnLogin, "���̵� �Է��ϼ���.");
				}else if (pwField.getText().isEmpty()) {		// ��й�ȣ �Է� ������ ��
					JOptionPane.showMessageDialog(btnLogin, "��й�ȣ�� �Է��ϼ���.");
				} else { // �� �� �Է����� ��
					try {
						Connection conn;
						PreparedStatement pt1, pt2;
						ResultSet rs1, rs2;

						String sql1 = "select EMP_BIRTH, AUTHORITY from EMPLOYEE order by EMP_ID";
						String sql2 = "select * from LOGIN";
						conn = DBUtil.getConnection();
						
						pt1 = conn.prepareStatement(sql1);
						rs1 = pt1.executeQuery();
						
						pt2 = conn.prepareStatement(sql2);
						rs2 = pt2.executeQuery();
						
						// �Է��� ���̵�� ��й�ȣ
						int inputId = Integer.parseInt(idField.getText());
						String inputPw = pwField.getText();

						
						
						while(rs2.next() && rs1.next()) {
							String birth = rs1.getString("EMP_BIRTH");
							// ���� ���̵�� ��й�ȣ, ����
							String authority = rs1.getString("AUTHORITY");
							int id = rs2.getInt("EMP_ID");
							String pw = rs2.getString("EMP_PW");

							//System.out.println(id);
							//System.out.println(inputId);
							//System.out.println(pw);
							//System.out.println(inputPw);
								
							// 1. ù �α��� : ��й�ȣ ���� �˾�
							if((id == inputId) && (pw.equals(inputPw)) && (pw.equals(birth))) {
								System.out.println("�α��� ����");
								ChangePw(id, pw);
								
								// �α��� ���� �� ��� �ð� ���
								Service ser = new Service();
								try {
									
									ser.service_login(id, pw);
									
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
								return;
							}
							// 2. Admin �α���
							else if((id == inputId) && (pw.equals(inputPw))){
							
								// Admin���� User���� Ȯ��
								while(rs1.next()) {
									// Admin Page�� �̵�
									if(authority.equals("ADMIN")) {
										System.out.println("Admin Login");
										new AdminPage("������ ���", 0,0,1000,750); // ������ ������ �̵�
										return;
									}
									// User Page�� �̵�
									else if(authority.equals("USER")) {
										System.out.println("User Login");
										new UserPage(inputId,"��� ���", 0,0,1000,750);
										return; // ���� ������ �̵�
									}
								} 
							}
						}
						// ���� ���� �� : ���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�. Ȯ�� �� �Է����ּ��� �˾� ���
						JOptionPane.showMessageDialog(btnLogin, "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�. Ȯ�� �� �Է����ּ���.");
		
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				
				}
			}
			
			
			
			// �ʱ� ��й�ȣ ����
			private void ChangePw(int id, String pw) {
				setTitle("��й�ȣ ����");
				setLayout(null);
				setBounds(200, 100, 500, 300);
				
				Font ft = new Font("����", Font.BOLD, 15);
				
				// ���� ��й�ȣ �Է¶�
				JPanel nowPwPanel = new JPanel();
				nowPwPanel.setBounds(0, 30, 500, 30);
				
				JLabel nowPwLabel = new JLabel("���� ��й�ȣ ");
				nowPwLabel.setFont(ft);
				nowPwPanel.add(nowPwLabel);

				JTextField nowPwField = new JTextField(15);
				nowPwField.setFont(ft);
				nowPwPanel.add(nowPwField);

				add(nowPwPanel);
				
				// �� ��й�ȣ �Է¶�
				JPanel newPwPanel1 = new JPanel();
				newPwPanel1.setBounds(0, 80, 500, 30);
				
				JLabel newPwLabel1 = new JLabel("�� ��й�ȣ");
				newPwLabel1.setFont(ft);
				newPwPanel1.add(newPwLabel1);

				JTextField newPwField1 = new JTextField(15);
				newPwField1.setFont(ft);
				newPwPanel1.add(newPwField1);

				add(newPwPanel1);
				
				// �� ��й�ȣ ���Է¶�
				JPanel newPwPanel2 = new JPanel();
				newPwPanel2.setBounds(0, 130, 500, 30);
				
				JLabel newPwLabel2 = new JLabel("�� ��й�ȣ Ȯ��");
				newPwLabel2.setFont(ft);
				newPwPanel2.add(newPwLabel2);
				
				JTextField newPwField2 = new JTextField(15);
				newPwField2.setFont(ft);
				newPwPanel2.add(newPwField2);
				
				add(newPwPanel2);

				
				// ���� ��ư
				JPanel btnPanel = new JPanel();
				btnPanel.setBounds(0, 200, 500, 30);
				JButton btn = new JButton("����");
				btnPanel.add(btn);
				add(btnPanel);
				
				// ��ư ���� ��
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// ��ĭ �Է� ������ ��
						if(nowPwField.getText().isEmpty() || newPwField1.getText().isEmpty() || newPwField2.getText().isEmpty()) { 
							JOptionPane.showMessageDialog(btn, "��й�ȣ�� �Է��ϼ���.");
						// ���� ��й�ȣ ���� �ٸ��ų�, ����й�ȣ�� ����й�ȣ ���Է� ���� �ٸ� ��
						}else if (!nowPwField.getText().equals(pw) || !newPwField1.getText().equals(newPwField2.getText())) {
							JOptionPane.showMessageDialog(btn, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						}else if(newPwField1.getText().equals(newPwField2.getText())) {
							// �� ��й�ȣ�� LOGIN ���̺� EMP_PW�� �����ؾߵ�
							Connection conn;
							PreparedStatement pt;
							ResultSet rs;
							conn = DBUtil.getConnection();
		
							try {
								String sql = "update LOGIN set EMP_PW = ? WHERE EMP_ID = ?";
								pt = conn.prepareStatement(sql);
								pt.setString(1, newPwField1.getText());
								pt.setInt(2, id);
								int result = pt.executeUpdate();
								
								System.out.println(result);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(btn, "��й�ȣ�� ����Ǿ����ϴ�.");
							
							new loginGUI();
						}
					}
				});
				
				
				setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}

		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	

}
