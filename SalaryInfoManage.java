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
	private JComboBox<String> bonusTypeCondition; // �߰��� ���ʽ� Ÿ�� üũ�ڽ�
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
				return false; // ��� ���� ���� ���� �Ұ�
			}
		};

		detailPanel = new JPanel();
		detailPanel.setBounds(140, 40, 300, 385);

		detailPanel.setVisible(false);
		add(detailPanel);

		detailLabel = new JLabel();
		detailLabel.setVerticalAlignment(SwingConstants.TOP);
		detailPanel.add(detailLabel); // detailPanel�� detailLabel �߰�

		model.addColumn("���");
		model.addColumn("�̸�");
		model.addColumn("����");
		model.addColumn("�����");
		model.addColumn("�����");
		model.addColumn("���ʽ�");
		model.addColumn("�Ǽ���");
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

		// �� ���� ������ �߰�
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

		String[] searchOptions = { "���", "�̸�", "����", "���ʽ�" }; // ����ڿ��� ǥ�õ� �ɼ�
		String[] columnNames = { "EMP_ID", "EMP_NAME", "MONTHLY_PAY", "BONUS" }; // �����ͺ��̽� �÷���
		searchCriteria = new JComboBox<>(searchOptions);
		searchPanel.add(searchCriteria);

		searchField = new JTextField(20);
		searchPanel.add(searchField);

		// ���� ���� üũ�ڽ�
		salaryCondition = new JComboBox<>(new String[] { "�̻�", "����" });
		salaryCondition.setVisible(false); // ó������ ������ �ʵ��� ����

		bonusTypeCondition = new JComboBox<>(new String[] { "%", "������" });
		bonusTypeCondition.setVisible(false); // ó������ ������ �ʵ��� ����

		// ���� �г�
		conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(salaryCondition);
		conditionPanel.add(bonusTypeCondition);
		conditionPanel.setVisible(false);

		// ���ʽ� ���� üũ�ڽ�
		searchPanel.add(conditionPanel);

		JButton searchButton = new JButton("�˻�");
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
				String criteria = columnNames[selectedIndex]; // ���õ� �ɼǿ� �����ϴ� �÷���
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

		if ("����".equals(selectedCriteria)) {
			salaryCondition.setVisible(true);
		} else if ("���ʽ�".equals(selectedCriteria)) {
			salaryCondition.setVisible(true);
			bonusTypeCondition.setVisible(true);
		} else {
			conditionPanel.setVisible(false);
		}

		conditionPanel.revalidate();
		conditionPanel.repaint();
	}

	private void filterTable(String criteria, String keyword, String salaryCond, String bonusTypeCond) {
		list = SalaryDAO.search(criteria, keyword, salaryCond, bonusTypeCond); // �˻� ��� ��������
		DefaultTableModel filteredModel = new DefaultTableModel();
		filteredModel.addColumn("���");
		filteredModel.addColumn("�̸�");
		filteredModel.addColumn("����");
		filteredModel.addColumn("�����");
		filteredModel.addColumn("�����");
		filteredModel.addColumn("���ʽ�");
		filteredModel.addColumn("�Ǽ���");

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
//	    DetailVO employee = SalaryDAO.detailSelect(empId); // ������ �κ�
//	    if (employee != null) {
//	        detailPanel.setVisible(true);
//	        detailLabel.setPreferredSize(new Dimension(detailPanel.getWidth(), detailPanel.getHeight())); // ������ �г� ũ�� ����
//	        StringBuilder details = new StringBuilder();
//	        details.append("<html><style>")
//	               .append("table { table-layout: fixed; width: 100%; }")
//	               .append("th, td { border: 1px solid black; padding: 5px; }")
//	               .append("td.money { text-align: right; }")
//	               .append("</style><table>");
//	        details.append("<tr><th colspan='4'>�������</th></tr>");
//	        details.append("<tr><th>���</th><td>").append(employee.getEMP_id()).append("</td>");
//	        details.append("<th>�̸�</th><td>").append(employee.getEMP_NAME()).append("</td></tr>");
//	        details.append("<tr><th>�μ�</th><td>").append(employee.getDEPT_CODE()).append("</td>");
//	        details.append("<th>����</th><td>").append(employee.getJOB_CODE()).append("</td></tr>");
//	        details.append("<tr><th>����</th><td colspan='3' class='money'>").append(df.format(employee.getSalary())).append("</td></tr>");
//	        details.append("<tr><th>�� �⺻��</th><td colspan='3' class='money'>").append(df.format(employee.getMonthly_pay())).append("</td></tr>");
//	        if (employee.getBonus() > 10) {
//	            details.append("<tr><th>���ʽ�</th><td colspan='3' class='money'>").append(df.format(employee.getBonus())).append("</td></tr>");
//	        } else {
//	            int bon = (int) (employee.getBonus() * employee.getMonthly_pay());
//	            int bon1 = (int) (employee.getBonus() * 100); // ������ �κ�
//	            details.append("<tr><th>���ʽ� ( ").append(bon1).append("% )</th><td colspan='3' class='money'>").append(df.format(bon)).append("</td></tr>");
//	        }
//	        details.append("<tr><th>�����</th><td colspan='3' class='money'> ").append(df.format(employee.getTravel_allowance())).append("</td></tr>");
//	        details.append("<tr><th>�����</th><td colspan='3' class='money'> ").append(df.format(employee.getTransport_allowance())).append("</td></tr>");
//	        // �ҵ漼, �ǰ������ ���� ���
//	        double incomeTax = employee.getMonthly_pay() * 0.03; // ���÷� 3% �ҵ漼
//	        double healthInsurance = employee.getMonthly_pay() * 0.035; // ���÷� 3.5% �ǰ������
//	        double netPay = employee.getMonthly_pay() - incomeTax - healthInsurance;
//	        details.append("<tr><th>�ҵ漼</th><td colspan='3' class='money'> ").append(df.format(incomeTax)).append("</td></tr>");
//	        details.append("<tr><th>�ǰ������</th><td colspan='3' class='money'> ").append(df.format(healthInsurance)).append("</td></tr>");
//	        details.append("<tr><th>�Ǽ��ɾ�</th><td colspan='3' class='money'> ").append(df.format(netPay)).append("</td></tr>");
//	        details.append("</table></html>");
//
//	        detailLabel.setText(details.toString());
//	    }
//	}
	private void showEmployeeDetails(int empId) {
	    DetailVO employee = SalaryDAO.detailSelect(empId); // ������ �κ�
	    if (employee != null) {
	        detailPanel.removeAll();
	        detailPanel.setLayout(new BorderLayout());
	        detailPanel.setVisible(true);

	        // �ҵ漼, �ǰ������ ���� ���
	        double incomeTax = employee.getMonthly_pay() * 0.03; // ���÷� 3% �ҵ漼
	        double healthInsurance = employee.getMonthly_pay() * 0.035; // ���÷� 3.5% �ǰ������
	        double netPay = employee.getMonthly_pay() - incomeTax - healthInsurance+employee.getBonus()+employee.getTransport_allowance()+employee.getTravel_allowance();

	        // �ݾ��� �������ϱ� ���� DecimalFormat ���
	        DecimalFormat df = new DecimalFormat("#,### \u00A4");

	        // ���̺� ������ ����
	        String[] columnNames = {"�׸�", "����"};
	        Object[][] data = {
	            {"���", employee.getEMP_id()},
	            {"�̸�", employee.getEMP_NAME()},
	            {"�μ�", employee.getDEPT_CODE()},
	            {"����", employee.getJOB_CODE()},
	            {"����", df.format(employee.getSalary())},
	            {"�� �⺻��", df.format(employee.getMonthly_pay())},
	            {"���ʽ�", employee.getBonus() > 10 ? df.format(employee.getBonus()) : df.format(employee.getBonus() * employee.getMonthly_pay()) + " (" + (int)(employee.getBonus() * 100) + "%)"},
	            {"�����", df.format(employee.getTravel_allowance())},
	            {"�����", df.format(employee.getTransport_allowance())},
	            {"�ҵ漼", "- " + df.format(incomeTax)},
	            {"�ǰ������", "- " + df.format(healthInsurance)},
	            {"�Ǽ��ɾ�", df.format(netPay)}
	        };

	        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // ��� ���� ���� ���� �Ұ�
	            }
	        };

	        JTable detailTable = new JTable(model);
	        detailTable.setRowHeight(30);
	        detailTable.getColumnModel().getColumn(0).setPreferredWidth(100); // �׸� �� �ʺ� ����
	        detailTable.getColumnModel().getColumn(1).setPreferredWidth(200); // ���� �� �ʺ� ����

	        // �߾� ����
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        detailTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

	        // ���� ����
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
