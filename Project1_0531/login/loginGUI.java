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
		setTitle("로그인");
		setLayout(null);
		setBounds(0, 0, 1000, 750);

		Font ft = new Font("굴림", Font.BOLD, 15);

		
		// 아이디 입력란
		JPanel idPanel = new JPanel();
		idPanel.setBounds(0, 350, 1000, 50);
		
		JLabel idLabel = new JLabel("아이디 ");
		idLabel.setFont(ft);
		idPanel.add(idLabel);

		JTextField idField = new JTextField(10);
		idField.setFont(ft);
		idPanel.add(idField);

		add(idPanel);

		// 비밀번호 입력란
		JPanel pwPanel = new JPanel();
		pwPanel.setBounds(0, 400, 1000, 50);

		JLabel pwLabel = new JLabel("비밀번호 ");
		pwLabel.setFont(ft);
		pwPanel.add(pwLabel);

		JPasswordField pwField = new JPasswordField(14);
		pwField.setEchoChar('*');
		pwPanel.add(pwField);

		add(pwPanel);

		// 아이디 찾기 버튼
		JPanel btnIdFindPanel = new JPanel();
		btnIdFindPanel.setBounds(350, 450, 150, 50);
		JButton btnIdFind = new JButton("아이디 찾기");
		btnIdFindPanel.add(btnIdFind);
		add(btnIdFindPanel);

		// 비밀번호 찾기 버튼
		JPanel btnPwFindPanel = new JPanel();
		btnPwFindPanel.setBounds(500, 450, 150, 50);
		JButton btnPwFind = new JButton("비밀번호 찾기");
		btnPwFindPanel.add(btnPwFind);
		add(btnPwFindPanel);

		// 로그인 버튼
		JPanel btnLoginPanel = new JPanel();
		btnLoginPanel.setBounds(0, 500, 1000, 50);
		JButton btnLogin = new JButton("로그인");
		btnLoginPanel.add(btnLogin);
		add(btnLoginPanel);
		
		
		// 아이디 찾기 버튼 클릭
		btnIdFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		
				new idFind();
			}
		});
		
		// 비밀번호 찾기 버튼 클릭
		btnPwFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new pwFind();
			}
		});
		
		// 로그인 버튼 클릭
		btnLogin.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(idField.getText().isEmpty()) { 				// 아이디 입력 안했을 시
					JOptionPane.showMessageDialog(btnLogin, "아이디를 입력하세요.");
				}else if (pwField.getText().isEmpty()) {		// 비밀번호 입력 안했을 시
					JOptionPane.showMessageDialog(btnLogin, "비밀번호를 입력하세요.");
				} else { // 둘 다 입력했을 시
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
						
						// 입력한 아이디와 비밀번호
						int inputId = Integer.parseInt(idField.getText());
						String inputPw = pwField.getText();

						
						
						while(rs2.next() && rs1.next()) {
							String birth = rs1.getString("EMP_BIRTH");
							// 실제 아이디와 비밀번호, 권한
							String authority = rs1.getString("AUTHORITY");
							int id = rs2.getInt("EMP_ID");
							String pw = rs2.getString("EMP_PW");

							//System.out.println(id);
							//System.out.println(inputId);
							//System.out.println(pw);
							//System.out.println(inputPw);
								
							// 1. 첫 로그인 : 비밀번호 변경 팝업
							if((id == inputId) && (pw.equals(inputPw)) && (pw.equals(birth))) {
								System.out.println("로그인 성공");
								ChangePw(id, pw);
								
								// 로그인 성공 시 출근 시간 등록
								Service ser = new Service();
								try {
									
									ser.service_login(id, pw);
									
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
								return;
							}
							// 2. Admin 로그인
							else if((id == inputId) && (pw.equals(inputPw))){
							
								// Admin인지 User인지 확인
								while(rs1.next()) {
									// Admin Page로 이동
									if(authority.equals("ADMIN")) {
										System.out.println("Admin Login");
										new AdminPage("관리자 모드", 0,0,1000,750); // 관리자 페이지 이동
										return;
									}
									// User Page로 이동
									else if(authority.equals("USER")) {
										System.out.println("User Login");
										new UserPage(inputId,"사원 모드", 0,0,1000,750);
										return; // 유저 페이지 이동
									}
								} 
							}
						}
						// 계정 없을 시 : 아이디 또는 패스워드가 일치하지 않습니다. 확인 후 입력해주세요 팝업 출력
						JOptionPane.showMessageDialog(btnLogin, "아이디 또는 패스워드가 일치하지 않습니다. 확인 후 입력해주세요.");
		
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				
				}
			}
			
			
			
			// 초기 비밀번호 변경
			private void ChangePw(int id, String pw) {
				setTitle("비밀번호 변경");
				setLayout(null);
				setBounds(200, 100, 500, 300);
				
				Font ft = new Font("굴림", Font.BOLD, 15);
				
				// 현재 비밀번호 입력란
				JPanel nowPwPanel = new JPanel();
				nowPwPanel.setBounds(0, 30, 500, 30);
				
				JLabel nowPwLabel = new JLabel("현재 비밀번호 ");
				nowPwLabel.setFont(ft);
				nowPwPanel.add(nowPwLabel);

				JTextField nowPwField = new JTextField(15);
				nowPwField.setFont(ft);
				nowPwPanel.add(nowPwField);

				add(nowPwPanel);
				
				// 새 비밀번호 입력란
				JPanel newPwPanel1 = new JPanel();
				newPwPanel1.setBounds(0, 80, 500, 30);
				
				JLabel newPwLabel1 = new JLabel("새 비밀번호");
				newPwLabel1.setFont(ft);
				newPwPanel1.add(newPwLabel1);

				JTextField newPwField1 = new JTextField(15);
				newPwField1.setFont(ft);
				newPwPanel1.add(newPwField1);

				add(newPwPanel1);
				
				// 새 비밀번호 재입력란
				JPanel newPwPanel2 = new JPanel();
				newPwPanel2.setBounds(0, 130, 500, 30);
				
				JLabel newPwLabel2 = new JLabel("새 비밀번호 확인");
				newPwLabel2.setFont(ft);
				newPwPanel2.add(newPwLabel2);
				
				JTextField newPwField2 = new JTextField(15);
				newPwField2.setFont(ft);
				newPwPanel2.add(newPwField2);
				
				add(newPwPanel2);

				
				// 변경 버튼
				JPanel btnPanel = new JPanel();
				btnPanel.setBounds(0, 200, 500, 30);
				JButton btn = new JButton("변경");
				btnPanel.add(btn);
				add(btnPanel);
				
				// 버튼 누를 시
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// 빈칸 입력 안했을 시
						if(nowPwField.getText().isEmpty() || newPwField1.getText().isEmpty() || newPwField2.getText().isEmpty()) { 
							JOptionPane.showMessageDialog(btn, "비밀번호를 입력하세요.");
						// 현재 비밀번호 값이 다르거나, 새비밀번호와 새비밀번호 재입력 값이 다를 시
						}else if (!nowPwField.getText().equals(pw) || !newPwField1.getText().equals(newPwField2.getText())) {
							JOptionPane.showMessageDialog(btn, "비밀번호가 일치하지 않습니다.");
						}else if(newPwField1.getText().equals(newPwField2.getText())) {
							// 새 비밀번호를 LOGIN 테이블 EMP_PW에 저장해야됨
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
							
							JOptionPane.showMessageDialog(btn, "비밀번호가 변경되었습니다.");
							
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
