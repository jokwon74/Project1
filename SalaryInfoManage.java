package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalaryInfoManage extends JPanel implements ActionListener, MouseListener {
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<SalaryVO> list;
	DecimalFormat df = new DecimalFormat("\u00A4 ###,###");
	private JTextField searchField;
	private JComboBox<String> searchCriteria;
	private JComboBox<String> salaryCondition;
	private JComboBox<String> bonusTypeCondition; // 추가된 보너스 타입 체크박스
	private JPanel conditionPanel;
	private JPanel detailPanel;
	private JLabel detailLabel;

	public SalaryInfoManage(JPanel panel) {
		list = SalaryDAO.select();

		setPreferredSize(panel.getSize());
		setLayout(null);

		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(450, 45, 500, 400);
		tablePanel.setLayout(new BorderLayout());

		// list = service.select();

		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 모든 셀에 대해 편집 불가
			}
		};

		detailPanel = new JPanel();
		detailPanel.setBounds(140, 40, 300, 385);

		detailPanel.setVisible(false);
		add(detailPanel);

		detailLabel = new JLabel();
		detailLabel.setVerticalAlignment(SwingConstants.TOP);
		detailPanel.add(detailLabel); // detailPanel에 detailLabel 추가

		model.addColumn("사번");
		model.addColumn("이름");
		model.addColumn("월급");
		model.addColumn("교통비");
		model.addColumn("출장비");
		model.addColumn("보너스");
		model.addColumn("실수령");
		for (SalaryVO temp : list) {
			int bonus1 = temp.getBonus() < 1 ? (int) (temp.getBonus() * temp.getMonthly_pay()) : (int) temp.getBonus();
			String bonus;
			bonus = temp.getBonus() < 1 ? temp.getBonus() * 100 + " %" : df.format(temp.getBonus());
			model.addRow(new Object[] { temp.getEmp_id(), temp.getEmp_name(), df.format(temp.getMonthly_pay()),
					df.format(temp.getTransport_allowance()), df.format(temp.getTravel_allowance()), bonus,
					df.format(temp.getMonthly_pay() + bonus1 + temp.getTransport_allowance()
							+ temp.getTravel_allowance()) });
		}

		table = new JTable(model);

		JScrollPane sp = new JScrollPane(table);
		tablePanel.add(sp, BorderLayout.CENTER);

		// 행 선택 리스너 추가
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						int empId = (int) table.getValueAt(selectedRow, 0);
						showEmployeeDetails(empId);

					}
				}
			}
		});

		add(tablePanel);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		searchPanel.setBounds(450, 0, 500, 30);

		String[] searchOptions = { "사번", "이름", "월급", "보너스" }; // 사용자에게 표시될 옵션
		String[] columnNames = { "EMP_ID", "EMP_NAME", "MONTHLY_PAY", "BONUS" }; // 데이터베이스 컬럼명
		searchCriteria = new JComboBox<>(searchOptions);
		searchPanel.add(searchCriteria);

		searchField = new JTextField(20);
		searchPanel.add(searchField);

		// 월급 조건 체크박스
		salaryCondition = new JComboBox<>(new String[] { "이상", "이하" });
		salaryCondition.setVisible(false); // 처음에는 보이지 않도록 설정

		bonusTypeCondition = new JComboBox<>(new String[] { "%", "정액제" });
		bonusTypeCondition.setVisible(false); // 처음에는 보이지 않도록 설정

		// 조건 패널
		conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(salaryCondition);
		conditionPanel.add(bonusTypeCondition);
		conditionPanel.setVisible(false);

		// 보너스 조건 체크박스
		searchPanel.add(conditionPanel);

		JButton searchButton = new JButton("검색");
		searchPanel.add(searchButton);

		add(searchPanel);

		searchCriteria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateConditionPanel();
			}
		});

		JPanel tablepanel = new JPanel();
		tablepanel.setBounds(150, 30, 800, 400);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyword = searchField.getText();
				int selectedIndex = searchCriteria.getSelectedIndex();
				String criteria = columnNames[selectedIndex]; // 선택된 옵션에 대응하는 컬럼명
				String salaryCond = salaryCondition.isVisible() ? (String) salaryCondition.getSelectedItem() : null;
				String bonusTypeCond = bonusTypeCondition.isVisible() ? (String) bonusTypeCondition.getSelectedItem()
						: null;
				System.out.println(criteria);
				filterTable(criteria, keyword, salaryCond, bonusTypeCond);

			}
		});
	}

	private void updateConditionPanel() {
		String selectedCriteria = (String) searchCriteria.getSelectedItem();
		conditionPanel.setVisible(true);
		salaryCondition.setVisible(false);
		bonusTypeCondition.setVisible(false);

		if ("월급".equals(selectedCriteria)) {
			salaryCondition.setVisible(true);
		} else if ("보너스".equals(selectedCriteria)) {
			salaryCondition.setVisible(true);
			bonusTypeCondition.setVisible(true);
		} else {
			conditionPanel.setVisible(false);
		}

		conditionPanel.revalidate();
		conditionPanel.repaint();
	}

	private void filterTable(String criteria, String keyword, String salaryCond, String bonusTypeCond) {
		list = SalaryDAO.search(criteria, keyword, salaryCond, bonusTypeCond); // 검색 결과 가져오기
		DefaultTableModel filteredModel = new DefaultTableModel();
		filteredModel.addColumn("사번");
		filteredModel.addColumn("이름");
		filteredModel.addColumn("월급");
		filteredModel.addColumn("교통비");
		filteredModel.addColumn("출장비");
		filteredModel.addColumn("보너스");
		filteredModel.addColumn("실수령");

		for (SalaryVO temp : list) {
			int bonus1 = temp.getBonus() < 1 ? (int) (temp.getBonus() * temp.getMonthly_pay()) : (int) temp.getBonus();
			String bonus = temp.getBonus() < 1 ? temp.getBonus() * 100 + " %" : df.format(temp.getBonus());
			filteredModel.addRow(new Object[] { temp.getEmp_id(), temp.getEmp_name(), df.format(temp.getMonthly_pay()),
					df.format(temp.getTransport_allowance()), df.format(temp.getTravel_allowance()), bonus,
					df.format(temp.getMonthly_pay() + bonus1 + temp.getTransport_allowance()
							+ temp.getTravel_allowance()) });
		}

		table.setModel(filteredModel);
	}

//	private void showEmployeeDetails(int empId) {
//	    DetailVO employee = SalaryDAO.detailSelect(empId); // 수정된 부분
//	    if (employee != null) {
//	        detailPanel.setVisible(true);
//	        detailLabel.setPreferredSize(new Dimension(detailPanel.getWidth(), detailPanel.getHeight())); // 디테일 패널 크기 설정
//	        StringBuilder details = new StringBuilder();
//	        details.append("<html><style>")
//	               .append("table { table-layout: fixed; width: 100%; }")
//	               .append("th, td { border: 1px solid black; padding: 5px; }")
//	               .append("td.money { text-align: right; }")
//	               .append("</style><table>");
//	        details.append("<tr><th colspan='4'>공연요금</th></tr>");
//	        details.append("<tr><th>사번</th><td>").append(employee.getEMP_id()).append("</td>");
//	        details.append("<th>이름</th><td>").append(employee.getEMP_NAME()).append("</td></tr>");
//	        details.append("<tr><th>부서</th><td>").append(employee.getDEPT_CODE()).append("</td>");
//	        details.append("<th>직급</th><td>").append(employee.getJOB_CODE()).append("</td></tr>");
//	        details.append("<tr><th>연봉</th><td colspan='3' class='money'>").append(df.format(employee.getSalary())).append("</td></tr>");
//	        details.append("<tr><th>월 기본급</th><td colspan='3' class='money'>").append(df.format(employee.getMonthly_pay())).append("</td></tr>");
//	        if (employee.getBonus() > 10) {
//	            details.append("<tr><th>보너스</th><td colspan='3' class='money'>").append(df.format(employee.getBonus())).append("</td></tr>");
//	        } else {
//	            int bon = (int) (employee.getBonus() * employee.getMonthly_pay());
//	            int bon1 = (int) (employee.getBonus() * 100); // 수정된 부분
//	            details.append("<tr><th>보너스 ( ").append(bon1).append("% )</th><td colspan='3' class='money'>").append(df.format(bon)).append("</td></tr>");
//	        }
//	        details.append("<tr><th>출장비</th><td colspan='3' class='money'> ").append(df.format(employee.getTravel_allowance())).append("</td></tr>");
//	        details.append("<tr><th>교통비</th><td colspan='3' class='money'> ").append(df.format(employee.getTransport_allowance())).append("</td></tr>");
//	        // 소득세, 건강보험료 공제 계산
//	        double incomeTax = employee.getMonthly_pay() * 0.03; // 예시로 3% 소득세
//	        double healthInsurance = employee.getMonthly_pay() * 0.035; // 예시로 3.5% 건강보험료
//	        double netPay = employee.getMonthly_pay() - incomeTax - healthInsurance;
//	        details.append("<tr><th>소득세</th><td colspan='3' class='money'> ").append(df.format(incomeTax)).append("</td></tr>");
//	        details.append("<tr><th>건강보험료</th><td colspan='3' class='money'> ").append(df.format(healthInsurance)).append("</td></tr>");
//	        details.append("<tr><th>실수령액</th><td colspan='3' class='money'> ").append(df.format(netPay)).append("</td></tr>");
//	        details.append("</table></html>");
//
//	        detailLabel.setText(details.toString());
//	    }
//	}
	private void showEmployeeDetails(int empId) {
	    DetailVO employee = SalaryDAO.detailSelect(empId); // 수정된 부분
	    if (employee != null) {
	        detailPanel.removeAll();
	        detailPanel.setLayout(new BorderLayout());
	        detailPanel.setVisible(true);

	        // 소득세, 건강보험료 공제 계산
	        double incomeTax = employee.getMonthly_pay() * 0.03; // 예시로 3% 소득세
	        double healthInsurance = employee.getMonthly_pay() * 0.035; // 예시로 3.5% 건강보험료
	        double netPay = employee.getMonthly_pay() - incomeTax - healthInsurance+employee.getBonus()+employee.getTransport_allowance()+employee.getTravel_allowance();

	        // 금액을 포맷팅하기 위해 DecimalFormat 사용
	        DecimalFormat df = new DecimalFormat("#,### \u00A4");

	        // 테이블 데이터 설정
	        String[] columnNames = {"항목", "내용"};
	        Object[][] data = {
	            {"사번", employee.getEMP_id()},
	            {"이름", employee.getEMP_NAME()},
	            {"부서", employee.getDEPT_CODE()},
	            {"직급", employee.getJOB_CODE()},
	            {"연봉", df.format(employee.getSalary())},
	            {"월 기본급", df.format(employee.getMonthly_pay())},
	            {"보너스", employee.getBonus() > 10 ? df.format(employee.getBonus()) : df.format(employee.getBonus() * employee.getMonthly_pay()) + " (" + (int)(employee.getBonus() * 100) + "%)"},
	            {"출장비", df.format(employee.getTravel_allowance())},
	            {"교통비", df.format(employee.getTransport_allowance())},
	            {"소득세", "- " + df.format(incomeTax)},
	            {"건강보험료", "- " + df.format(healthInsurance)},
	            {"실수령액", df.format(netPay)}
	        };

	        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // 모든 셀에 대해 편집 불가
	            }
	        };

	        JTable detailTable = new JTable(model);
	        detailTable.setRowHeight(30);
	        detailTable.getColumnModel().getColumn(0).setPreferredWidth(100); // 항목 열 너비 조정
	        detailTable.getColumnModel().getColumn(1).setPreferredWidth(200); // 내용 열 너비 조정

	        // 중앙 정렬
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        detailTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

	        // 우측 정렬
	        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
	        detailTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

	        JScrollPane scrollPane = new JScrollPane(detailTable);
	        detailPanel.add(scrollPane, BorderLayout.CENTER);
	        detailPanel.revalidate();
	        detailPanel.repaint();
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
