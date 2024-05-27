package login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 비밀번호 찾기 버튼 누른 후
public class pwFind extends JFrame{
	
	public pwFind() {
		setTitle("비밀번호 찾기");
		setLayout(null);
		setBounds(200, 100, 500, 250);
		
		Font ft = new Font("굴림", Font.BOLD, 15);
		
		// 아이디 입력란
		JPanel idPanel = new JPanel();
		idPanel.setBounds(0, 30, 500, 30);
		
		JLabel idLabel = new JLabel("이름 ");
		idLabel.setFont(ft);
		idPanel.add(idLabel);

		JTextField idField = new JTextField(15);
		idField.setFont(ft);
		idPanel.add(idField);

		add(idPanel);
		
		// 전화번호 입력란
		JPanel phonePanel = new JPanel();
		phonePanel.setBounds(0, 80, 500, 30);
		
		JLabel phoneLabel = new JLabel("전화번호 ");
		phoneLabel.setFont(ft);
		phonePanel.add(phoneLabel);

		JTextField phoneField = new JTextField(15);
		phoneField.setFont(ft);
		phonePanel.add(phoneField);

		add(phonePanel);

		
		// 비밀번호 찾기 버튼
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(0, 140, 500, 40);
		JButton btn = new JButton("비밀번호 찾기");
		btnPanel.add(btn);
		add(btnPanel);
		
		// 버튼 누를 시
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idField.getText().isEmpty()) { 				// 이름 입력 안했을 시
					JOptionPane.showMessageDialog(btn, "아이디를 입력하세요.");
				}else if (phoneField.getText().isEmpty()) {		// 전화번호 입력 안했을 시
					JOptionPane.showMessageDialog(btn, "전화번호를 입력하세요.");
				} else { // 둘 다 입력했을 시
					// 1. 계정 없을 시 : 계정이 존재하지 않습니다.
					// 2. 정상 입력 : 비밀번호 팝업
					
				}
				
			}
		});
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
