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

// ��й�ȣ ã�� ��ư ���� ��
public class pwFind extends JFrame {

	public pwFind() {
		setTitle("��й�ȣ ã��");
		setLayout(null);
		setBounds(200, 100, 500, 250);

		Font ft = new Font("����", Font.BOLD, 15);

		// ���̵� �Է¶�
		JPanel idPanel = new JPanel();
		idPanel.setBounds(0, 30, 500, 30);

		JLabel idLabel = new JLabel("���̵� ");
		idLabel.setFont(ft);
		idPanel.add(idLabel);

		JTextField idField = new JTextField(15);
		idField.setFont(ft);
		idPanel.add(idField);

		add(idPanel);

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

		// ��й�ȣ ã�� ��ư
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(0, 140, 500, 40);
		JButton btn = new JButton("��й�ȣ ã��");
		btnPanel.add(btn);
		add(btnPanel);

		// ��ư ���� ��
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idField.getText().isEmpty()) { // �̸� �Է� ������ ��
					JOptionPane.showMessageDialog(btn, "���̵� �Է��ϼ���.");
				} else if (phoneField.getText().isEmpty()) { // ��ȭ��ȣ �Է� ������ ��
					JOptionPane.showMessageDialog(btn, "��ȭ��ȣ�� �Է��ϼ���.");
				} else { // �� �� �Է����� ��
					Connection conn;
					PreparedStatement pt1, pt2;
					ResultSet rs1, rs2;

					try {
						conn = DBUtil.getConnection();
						String sql1 = "select EMP_ID, EMP_PHONE from EMPLOYEE ";

						pt1 = conn.prepareStatement(sql1);
						rs1 = pt1.executeQuery();

						while (rs1.next()) {
							// ���� ���̵�, ��ȭ��ȣ
							String id = rs1.getString("EMP_ID");
							String phone = rs1.getString("EMP_PHONE");

							// �Է��� ���̵�, ��ȭ��ȣ
							String inputId = idField.getText();
							String inputPhone = phoneField.getText();

							// ���� ���̵�, ��ȭ��ȣ�� �Է��� ���̵�, ��ȭ��ȣ�� ���� �� ��й�ȣ �˾�
							if (id.equals(inputId) && phone.equals(inputPhone)) {
								String sql2 = "select EMP_PW from LOGIN WHERE EMP_ID = ? ";
								pt2 = conn.prepareStatement(sql2);
								pt2.setInt(1, Integer.parseInt(id));
								rs2 = pt2.executeQuery();

								while (rs2.next()) {
									String pw = rs2.getString("EMP_PW");
									JOptionPane.showMessageDialog(btn, "��й�ȣ : " + pw);
									return;
								}
							}
						}
						// ���� ���̵�, ��ȭ��ȣ�� �Է��� ���̵�, ��ȭ��ȣ�� �ٸ� ��
					  	JOptionPane.showMessageDialog(btn, "��ġ���� ���� �����Դϴ�.");

					} catch (Exception e2) { 
						e2.printStackTrace();
					}
					// 1. ���� ���� �� : ������ �������� �ʽ��ϴ�.
					// 2. ���� �Է� : ��й�ȣ �˾�

				}

			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
