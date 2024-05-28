package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class AdminPage extends JFrame{

	static JPanel mMenuPanel, mPagePanel;
	
	static Font ft = new Font("함초롬돋움",Font.BOLD,15);

	public AdminPage(String title, int x, int y, int width, int height) {

		initContainer(title, x, y, width, height);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}



	private void initContainer(String title, int x, int y, int width, int height) {

		setTitle(title);
		setBounds(x, y, width, height);
		setLayout(null);
		
		mMenuPanel = new JPanel();
		mMenuPanel.setLayout(null);
		mMenuPanel.setBounds(140, 60, width, 130);
		
		menuBtn();

		mPagePanel = new JPanel();
		mPagePanel.setBounds(0, 200, width, height);

		add(mPagePanel);

	}

	private void menuBtn() {
		
		JButton btn1 = new JButton("사원 관리");
		btn1.setBounds(50, 50, 150, 40);
		btn1.setFont(ft);
		mMenuPanel.add(btn1);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				mPagePanel.removeAll();
				mPagePanel.add(new EmployeeManage(mPagePanel));
				mPagePanel.revalidate(); 
				mPagePanel.repaint();
				
			}
		});
		
		JButton btn2 = new JButton("급여 관리");
		
		btn2.setBounds(200, 50, 150, 40);
		btn2.setFont(ft);
		mMenuPanel.add(btn2);
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mPagePanel.removeAll();
				mPagePanel.add(new SalaryInfoManage(mPagePanel));
				mPagePanel.revalidate(); 
				mPagePanel.repaint();
				
			}
		});
		
		JButton btn3 = new JButton("근태 관리");
		btn3.setBounds(350, 50, 150, 40);
		btn3.setFont(ft);
		mMenuPanel.add(btn3);
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mPagePanel.removeAll();
				mPagePanel.add(new AttendanceManage(mPagePanel));
				mPagePanel.revalidate(); 
				mPagePanel.repaint();
				
			}
		});

		
		
		
		JButton btn4 = new JButton("보고 관리");
		btn4.setBounds(500, 50, 150, 40);
		btn4.setFont(ft);
		mMenuPanel.add(btn4);
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mPagePanel.removeAll();
				mPagePanel.add(new ReportManage(mPagePanel));
				mPagePanel.revalidate(); 
				mPagePanel.repaint();
				
			}
		});

		
		add(mMenuPanel);
	}

	public static void main(String[] args) {
		AdminPage ap = new AdminPage("관리자 모드", 0,0,1000,750);

	}
}
