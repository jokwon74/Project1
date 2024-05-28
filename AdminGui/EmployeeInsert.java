package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class EmployeeInsert {

	private ArrayList<EmployeeVO> list;
	
	public EmployeeInsert(DefaultTableModel model) {
		
		System.out.println("������ �Լ� ����");
		// ���ο� â
		JFrame writerFrame = new JFrame("��� ���");

		JPanel panel = new JPanel();

		JLabel IdLabel = new JLabel("��� : ");
		JTextField IdField = new JTextField(20);
		
		JLabel NameLabel = new JLabel("�̸� : ");
		JTextField NameField = new JTextField(20);
		
		JLabel BirthLabel = new JLabel("���� : ");
		JTextField BirthField = new JTextField(20);
		
		JLabel EmailLabel = new JLabel("�̸��� : ");
		JTextField EmailField = new JTextField(20);
		
		JLabel PhoneLabel = new JLabel("��ȭ��ȣ : ");
		JTextField PhoneField = new JTextField(20);
		
		JLabel DeptLabel = new JLabel("�μ� : ");
		JTextField DeptField = new JTextField(20);
		
		JLabel JobLabel = new JLabel("�����ڵ� : ");
		JTextField JobField = new JTextField(20);
		
		JLabel HireLabel = new JLabel("����� : ");
		JTextField HireField = new JTextField(20);

		JButton submitButton = new JButton("����ϱ�");

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �ؽ�Ʈ �ʵ忡 �ִ� ��� ������ ��������
				

				int id = Integer.parseInt(IdField.getText());
				String name = NameField.getText(); 
				String birth = BirthField.getText(); 
				String email = EmailField.getText(); 
				String phone = PhoneField.getText(); 
				String dept = DeptField.getText(); 
				String job = JobField.getText(); 
				String hire_date = HireField.getText(); 
	
				// �����ͺ��̽��� �߰��ϴ� ����!
				EmployeeVO vo = new EmployeeVO(id, name,birth,email,
						phone, dept, job, hire_date);
				EmployeeDao.insert(vo);

				// �߰� �� ���ΰ�ħ
				list = EmployeeDao.select();

				// ��� ���� �����ȴ�.
				model.setRowCount(0);

				for (EmployeeVO temp : list) {
					// �� �྿ �߰��ϱ�
					model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
							});
				}
			}

		});

		// �гο� ������Ʈ �߰�
		//panel.add(titleField);
		
	
		panel.add(IdLabel);
		panel.add(IdField);
		
		panel.add(NameLabel);
		panel.add(NameField);
		
		panel.add(BirthLabel);
		panel.add(BirthField);
		
		panel.add(EmailLabel);
		panel.add(EmailField);
		
		panel.add(PhoneLabel);
		panel.add(PhoneField);
		
		panel.add(DeptLabel);
		panel.add(DeptField);
		
		panel.add(JobLabel);
		panel.add(JobField);
		
		panel.add(HireLabel);
		panel.add(HireField);

		
		panel.add(submitButton);
		
		// �۾��� ��â�� �߰��ϱ� 
		writerFrame.add(panel);
		writerFrame.setSize(300,300);
		writerFrame.setVisible(true);
	
	}

}
