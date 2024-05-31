package AnnualPaidLeave;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AnnualPGUI extends JFrame {

	private JTable table;
	private DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleField, idField, nameField, contentField;
	private JLabel tiLabel, idLabel, nameLabel;
	private ArrayList<appFormVO> list = new ArrayList<appFormVO>();
	private JButton btn, btn1, btn2;

	public AnnualPGUI() {

		setBounds(500, 200, 1000, 750);

		// 기존에 있는 데이터 가져오기
		list = AnnualPDAO.Select();

		init();

		setTitle("Table Select");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void init() {

		// 테이블 모델 생성
		model = new DefaultTableModel();

		// 열 이름
		model.addColumn("문서명");
		model.addColumn("신청 유형");
		model.addColumn("신청 날짜");
		model.addColumn("사 번");
		model.addColumn("사원 이름");
		model.addColumn("작성일");
		model.addColumn("진행상태");

		// MemoVO 클래스를 한 행씩 추가하기
		for (appFormVO temp : list) {

			// 한 행씩 추가하기
			model.addRow(new Object[] { temp.getTitle(), temp.getType(), temp.getUseDate(), temp.getEmp_id(),
					temp.getEmp_name(), temp.getWriteDate(), temp.getProcessing() });
		}

		// 테이블 생성 및 모델 설정
		table = new JTable(model);

		// 스크롤 가 능한 테이블 패널 생성
		JScrollPane scroll = new JScrollPane(table);

		add(scroll);

		JPanel panel = new JPanel();
		add("South", panel);

		btn = new JButton("새로고침");
		btn.setSize(50, 50);

		btn1 = new JButton("신청하기");
		btn1.setSize(100, 50);

		btn2 = new JButton("삭제하기");
		btn2.setSize(150, 50);

		panel.add(btn);
		panel.add(btn1);
		panel.add(btn2);

		// 새로고침 버튼 액션
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();


			}

		});

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(true);

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 1000, 745);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(contentPane);
				contentPane.setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(223, 10, 516, 686);
				panel.setBorder(new LineBorder(Color.BLACK));
				contentPane.add(panel);
				panel.setLayout(null);

				Font mainFt = new Font("굴림", Font.BOLD, 30);
				Font ft = new Font("굴림", Font.BOLD, 14);

				JLabel titleLabel = new JLabel("연차/휴가 신청서");
				titleLabel.setBounds(115, 25, 292, 44);
				titleLabel.setFont(mainFt);
				panel.add(titleLabel, BorderLayout.CENTER);

				titleField = new JTextField();
				titleField.setBounds(181, 93, 224, 21);
				panel.add(titleField);
				titleField.setColumns(10);

				tiLabel = new JLabel("제 목");
				tiLabel.setBounds(112, 96, 57, 15);
				panel.add(tiLabel);

				idLabel = new JLabel("사 번");
				idLabel.setBounds(112, 141, 57, 15);
				panel.add(idLabel);

				idField = new JTextField();
				idField.setBounds(181, 138, 116, 21);
				panel.add(idField);
				idField.setColumns(10);

				//
				nameLabel = new JLabel("이 름");
				nameLabel.setBounds(113, 183, 57, 15);
				panel.add(nameLabel);

				nameField = new JTextField();
				nameField.setBounds(181, 180, 116, 21);
				panel.add(nameField);
				nameField.setColumns(10);

				//
				String[] box = { "휴가", "연차", "오전 반차", "오후 반차", "병가" };
				JComboBox comboBox = new JComboBox(box);
				comboBox.setBounds(181, 222, 116, 23);
				panel.add(comboBox);

				JLabel comboLabel = new JLabel("사용 유형");
				comboLabel.setBounds(113, 226, 57, 15);
				panel.add(comboLabel);

				JLabel useDateLabel = new JLabel("사용 날짜");
				useDateLabel.setBounds(112, 271, 57, 15);
				UtilDateModel model = new UtilDateModel();
				JDatePanelImpl datePanel = new JDatePanelImpl(model);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
				panel.add(useDateLabel);
				panel.add(datePicker);

				JLabel contentLabel = new JLabel("사용 사유");
				contentLabel.setBounds(113, 381, 57, 15);
				panel.add(contentLabel);

				contentField = new JTextField();
				contentField.setBounds(113, 410, 292, 59);
				panel.add(contentField);
				contentField.setColumns(10);

				JButton btn1 = new JButton("제출");
				btn1.setBounds(147, 512, 76, 50);
				btn1.setPreferredSize(new Dimension(90, 30));
				btn1.setFont(ft);
				panel.add(btn1);

				JButton btn2 = new JButton("취소");
				btn2.setBounds(275, 512, 76, 50);
				btn2.setPreferredSize(new Dimension(90, 30));
				btn2.setFont(ft);
				panel.add(btn2);

				panel.repaint();

				// 확인
				btn1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						// int emp_id = Integer.parseInt(textField_1.getText());
						if (!idField.getText().isEmpty()) {
							int emp_id = Integer.parseInt(idField.getText());

							String title = titleField.getText();
							String usedate = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
							String type = (String) comboBox.getSelectedItem();
							String name = nameField.getText();
							String content = contentField.getText();

							appFormVO vo = new appFormVO(title, type, usedate, emp_id, name, content);
							AnnualPDAO.Insert(vo);
						} else {
							JOptionPane.showMessageDialog(btn, "다시 입력해주세요.");
						}

						// 입력 유효성 검사 및 정보 출력
						if (titleField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "제목을 입력하세요.");
						} else if (idField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "사번을 입력하세요.");
						} else if (nameField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "이름을 입력하세요.");
						} else if (contentField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "신청 사유를 입력하세요.");
						} else {
							JOptionPane.showMessageDialog(btn2, "제출되었습니다.");
						}

						// 제목
						System.out.println("제목 : " + titleField.getText());

						// 날짜
						System.out.println(
								"날짜 : " + model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());

						// 사번
						System.out.println("사번 : " + idField.getText());

						// 이름
						System.out.println("이름 : " + nameField.getText());

						// 신청유형 : 콤보박스 내용 String형으로 가져오기
						System.out.println("신청 유형 : " + comboBox.getSelectedItem().toString());

						// 신청 사유
						System.out.println("신청 사유 : " + contentField.getText());

						// 추가 후 새로고침
						refreshTable();

						JOptionPane.showMessageDialog(btn, "신청이 완료 되었습니다.");

						dispose();
						new AnnualPGUI();

					}

				});

				// 취소
				btn2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);

						new AnnualPGUI();

					}

				});

//				newFrame.setVisible(true);
			}

		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 행의 인덱스 가져오기
				int index = table.getSelectedRow();

				// 행이 선택된 경우
				if (index >= 0) {
					// 테이블 모델에서 행 제거
					model.removeRow(index);

					// 목록에서 해당 객체 제거
					list.remove(index);

					// 테이블 표시 갱신
					table.revalidate();
					table.repaint();

					// 선택된 행을 데이터베이스에서 삭제하려면 여기에 데이터베이스 업데이트 로직 추가 가능
					// 예를 들어, 테이블에서 행을 삭제하면 데이터베이스에서 해당 항목을 삭제하려면:
					// 해당하는 appFormVO 객체 가져오기

					// 데이터베이스에서 이 항목을 삭제하는 DAO 메서드 호출
					AnnualPDAO.Delete(index);

					refreshTable();

				} else {
					// 행이 선택되지 않은 경우 사용자에게 메시지 표시
					JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "선택된 행 없음", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void refreshTable() {
		// 데이터를 새로고침하고 테이블 모델에 새로운 데이터를 설정함.

		list = AnnualPDAO.Select();

		// 모든 행이 삭제됨.
		model.setRowCount(0);

		// 다시 가져옴
		for (appFormVO vo : list) {
			// 한 행씩 추가하기
			model.addRow(new Object[] { vo.getTitle(), vo.getType(), vo.getUseDate(), vo.getEmp_id(), vo.getEmp_name(),
					vo.getContent() });
		}

	}

}
