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

		// ������ �ִ� ������ ��������
		list = AnnualPDAO.Select();

		init();

		setTitle("Table Select");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void init() {

		// ���̺� �� ����
		model = new DefaultTableModel();

		// �� �̸�
		model.addColumn("������");
		model.addColumn("��û ����");
		model.addColumn("��û ��¥");
		model.addColumn("�� ��");
		model.addColumn("��� �̸�");
		model.addColumn("�ۼ���");
		model.addColumn("�������");

		// MemoVO Ŭ������ �� �྿ �߰��ϱ�
		for (appFormVO temp : list) {

			// �� �྿ �߰��ϱ�
			model.addRow(new Object[] { temp.getTitle(), temp.getType(), temp.getUseDate(), temp.getEmp_id(),
					temp.getEmp_name(), temp.getWriteDate(), temp.getProcessing() });
		}

		// ���̺� ���� �� �� ����
		table = new JTable(model);

		// ��ũ�� �� ���� ���̺� �г� ����
		JScrollPane scroll = new JScrollPane(table);

		add(scroll);

		JPanel panel = new JPanel();
		add("South", panel);

		btn = new JButton("���ΰ�ħ");
		btn.setSize(50, 50);

		btn1 = new JButton("��û�ϱ�");
		btn1.setSize(100, 50);

		btn2 = new JButton("�����ϱ�");
		btn2.setSize(150, 50);

		panel.add(btn);
		panel.add(btn1);
		panel.add(btn2);

		// ���ΰ�ħ ��ư �׼�
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

				Font mainFt = new Font("����", Font.BOLD, 30);
				Font ft = new Font("����", Font.BOLD, 14);

				JLabel titleLabel = new JLabel("����/�ް� ��û��");
				titleLabel.setBounds(115, 25, 292, 44);
				titleLabel.setFont(mainFt);
				panel.add(titleLabel, BorderLayout.CENTER);

				titleField = new JTextField();
				titleField.setBounds(181, 93, 224, 21);
				panel.add(titleField);
				titleField.setColumns(10);

				tiLabel = new JLabel("�� ��");
				tiLabel.setBounds(112, 96, 57, 15);
				panel.add(tiLabel);

				idLabel = new JLabel("�� ��");
				idLabel.setBounds(112, 141, 57, 15);
				panel.add(idLabel);

				idField = new JTextField();
				idField.setBounds(181, 138, 116, 21);
				panel.add(idField);
				idField.setColumns(10);

				//
				nameLabel = new JLabel("�� ��");
				nameLabel.setBounds(113, 183, 57, 15);
				panel.add(nameLabel);

				nameField = new JTextField();
				nameField.setBounds(181, 180, 116, 21);
				panel.add(nameField);
				nameField.setColumns(10);

				//
				String[] box = { "�ް�", "����", "���� ����", "���� ����", "����" };
				JComboBox comboBox = new JComboBox(box);
				comboBox.setBounds(181, 222, 116, 23);
				panel.add(comboBox);

				JLabel comboLabel = new JLabel("��� ����");
				comboLabel.setBounds(113, 226, 57, 15);
				panel.add(comboLabel);

				JLabel useDateLabel = new JLabel("��� ��¥");
				useDateLabel.setBounds(112, 271, 57, 15);
				UtilDateModel model = new UtilDateModel();
				JDatePanelImpl datePanel = new JDatePanelImpl(model);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
				panel.add(useDateLabel);
				panel.add(datePicker);

				JLabel contentLabel = new JLabel("��� ����");
				contentLabel.setBounds(113, 381, 57, 15);
				panel.add(contentLabel);

				contentField = new JTextField();
				contentField.setBounds(113, 410, 292, 59);
				panel.add(contentField);
				contentField.setColumns(10);

				JButton btn1 = new JButton("����");
				btn1.setBounds(147, 512, 76, 50);
				btn1.setPreferredSize(new Dimension(90, 30));
				btn1.setFont(ft);
				panel.add(btn1);

				JButton btn2 = new JButton("���");
				btn2.setBounds(275, 512, 76, 50);
				btn2.setPreferredSize(new Dimension(90, 30));
				btn2.setFont(ft);
				panel.add(btn2);

				panel.repaint();

				// Ȯ��
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
							JOptionPane.showMessageDialog(btn, "�ٽ� �Է����ּ���.");
						}

						// �Է� ��ȿ�� �˻� �� ���� ���
						if (titleField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "������ �Է��ϼ���.");
						} else if (idField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "����� �Է��ϼ���.");
						} else if (nameField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "�̸��� �Է��ϼ���.");
						} else if (contentField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(btn2, "��û ������ �Է��ϼ���.");
						} else {
							JOptionPane.showMessageDialog(btn2, "����Ǿ����ϴ�.");
						}

						// ����
						System.out.println("���� : " + titleField.getText());

						// ��¥
						System.out.println(
								"��¥ : " + model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());

						// ���
						System.out.println("��� : " + idField.getText());

						// �̸�
						System.out.println("�̸� : " + nameField.getText());

						// ��û���� : �޺��ڽ� ���� String������ ��������
						System.out.println("��û ���� : " + comboBox.getSelectedItem().toString());

						// ��û ����
						System.out.println("��û ���� : " + contentField.getText());

						// �߰� �� ���ΰ�ħ
						refreshTable();

						JOptionPane.showMessageDialog(btn, "��û�� �Ϸ� �Ǿ����ϴ�.");

						dispose();
						new AnnualPGUI();

					}

				});

				// ���
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
				// ������ ���� �ε��� ��������
				int index = table.getSelectedRow();

				// ���� ���õ� ���
				if (index >= 0) {
					// ���̺� �𵨿��� �� ����
					model.removeRow(index);

					// ��Ͽ��� �ش� ��ü ����
					list.remove(index);

					// ���̺� ǥ�� ����
					table.revalidate();
					table.repaint();

					// ���õ� ���� �����ͺ��̽����� �����Ϸ��� ���⿡ �����ͺ��̽� ������Ʈ ���� �߰� ����
					// ���� ���, ���̺��� ���� �����ϸ� �����ͺ��̽����� �ش� �׸��� �����Ϸ���:
					// �ش��ϴ� appFormVO ��ü ��������

					// �����ͺ��̽����� �� �׸��� �����ϴ� DAO �޼��� ȣ��
					AnnualPDAO.Delete(index);

					refreshTable();

				} else {
					// ���� ���õ��� ���� ��� ����ڿ��� �޽��� ǥ��
					JOptionPane.showMessageDialog(null, "������ ���� �����ϼ���.", "���õ� �� ����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void refreshTable() {
		// �����͸� ���ΰ�ħ�ϰ� ���̺� �𵨿� ���ο� �����͸� ������.

		list = AnnualPDAO.Select();

		// ��� ���� ������.
		model.setRowCount(0);

		// �ٽ� ������
		for (appFormVO vo : list) {
			// �� �྿ �߰��ϱ�
			model.addRow(new Object[] { vo.getTitle(), vo.getType(), vo.getUseDate(), vo.getEmp_id(), vo.getEmp_name(),
					vo.getContent() });
		}

	}

}
