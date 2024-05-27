package login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
					// 1. 계정 없을 시 : 아이디 또는 패스워드가 일치하지 않습니다. 확인 후 입력해주세요 팝업 출력
					// 2. 첫 로그인 : 비밀번호 변경 팝업
					// 3. 일반 로그인 : Admin 또는 User 페이지로 이동
					
				}
				
			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
