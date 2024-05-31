import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import AnnualPaidLeave.appFormVO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class test2 extends JFrame{
	
	private JTable table;
	private DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleField, textField_1, nameField, contentField;
	private JLabel tiLabel, idLabel, nameLabel;
	private ArrayList<appFormVO> list = new ArrayList<appFormVO>();
	private JButton btn, btn1, btn2;

	public test2() {

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
		panel.add(titleLabel,BorderLayout.CENTER);
		
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(181, 138, 116, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//
		nameLabel = new JLabel("이 름");
		nameLabel.setBounds(113, 183, 57, 15);
		panel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(181, 180, 116, 21);
		panel.add(nameField);
		nameField.setColumns(10);
		
		//
		String[] box = {"연차", "오전 반차", "오후 반차", "병가"};
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
		
//		textField_3 = new JTextField();
//		textField_3.setBounds(181, 268, 116, 21);
//		panel.add(textField_3);
//		textField_3.setColumns(10);
		
		JLabel contentLabel = new JLabel("사용 사유");
		contentLabel.setBounds(113, 381, 57, 15);
		panel.add(contentLabel);
		
		contentField = new JTextField();
		contentField.setBounds(113, 410, 292, 59);
		panel.add(contentField);
		contentField.setColumns(10);
		
		JButton btn = new JButton("제출");
		btn.setBounds(147, 512, 76, 50);
		btn.setPreferredSize(new Dimension(90, 40));
		btn.setFont(ft);
		panel.add(btn);
		
		JButton btn2 = new JButton("취소");
		btn2.setBounds(275, 512, 76, 50);
		btn2.setPreferredSize(new Dimension(90, 40));
		btn2.setFont(ft);
		panel.add(btn2);
		
		panel.repaint();

		
	}
	
	public static void main(String[] args) {
		new test2();

		
	}

}
