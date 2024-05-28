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
		
		System.out.println("사원등록 함수 실행");
		// 새로운 창
		JFrame writerFrame = new JFrame("사원 등록");

		JPanel panel = new JPanel();

		JLabel IdLabel = new JLabel("사번 : ");
		JTextField IdField = new JTextField(20);
		
		JLabel NameLabel = new JLabel("이름 : ");
		JTextField NameField = new JTextField(20);
		
		JLabel BirthLabel = new JLabel("생일 : ");
		JTextField BirthField = new JTextField(20);
		
		JLabel EmailLabel = new JLabel("이메일 : ");
		JTextField EmailField = new JTextField(20);
		
		JLabel PhoneLabel = new JLabel("전화번호 : ");
		JTextField PhoneField = new JTextField(20);
		
		JLabel DeptLabel = new JLabel("부서 : ");
		JTextField DeptField = new JTextField(20);
		
		JLabel JobLabel = new JLabel("직업코드 : ");
		JTextField JobField = new JTextField(20);
		
		JLabel HireLabel = new JLabel("고용일 : ");
		JTextField HireField = new JTextField(20);

		JButton submitButton = new JButton("등록하기");

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 텍스트 필드에 있는 모든 내용을 가져오기
				

				int id = Integer.parseInt(IdField.getText());
				String name = NameField.getText(); 
				String birth = BirthField.getText(); 
				String email = EmailField.getText(); 
				String phone = PhoneField.getText(); 
				String dept = DeptField.getText(); 
				String job = JobField.getText(); 
				String hire_date = HireField.getText(); 
	
				// 데이터베이스에 추가하는 내용!
				EmployeeVO vo = new EmployeeVO(id, name,birth,email,
						phone, dept, job, hire_date);
				EmployeeDao.insert(vo);

				// 추가 후 새로고침
				list = EmployeeDao.select();

				// 모든 행이 삭제된다.
				model.setRowCount(0);

				for (EmployeeVO temp : list) {
					// 한 행씩 추가하기
					model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
							});
				}
			}

		});

		// 패널에 컴포넌트 추가
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
		
		// 글쓰기 새창에 추가하기 
		writerFrame.add(panel);
		writerFrame.setSize(300,300);
		writerFrame.setVisible(true);
	
	}

}
