package User;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AnnualPaidLeave.AnnualPGUI;


public class UserPage extends JFrame{

	static JPanel mMenuPanel, mPagePanel, mMenubarPanel;
	UserController controller = new UserController();
	private int inputId;
	
	static Font ft = new Font("함초롬돋움",Font.BOLD,15);

	public UserPage(int inputId, String title, int x, int y, int width, int height) {

		this.inputId = inputId;
		initContainer(title, x, y, width, height);
		initMenu();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}



	private void initContainer(String title, int x, int y, int width, int height) {

		setLayout(null);
		setTitle(title);
		setBounds(x, y, width, height);

		mPagePanel = new JPanel();
		mPagePanel.setLayout(null);
		mPagePanel.setBounds(0, 200, width, height);

		add(mPagePanel);

	}
	
	private void initMenu() {

		JPanel barpanel = new JPanel(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
	
		Font font = new Font("함초롬돋움",Font.BOLD,28);
		//1. 사원관리
		JMenu menu01 = new JMenu("사원관리");
		JMenuItem item01 = new JMenuItem("사원 정보");
		menu01.setFont(font);

		//사원정보 클릭 이벤트
		item01.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				controller.info(inputId);
				mPagePanel.add(UserController.findPanel);
				mPagePanel.add(UserController.AtPanel);
				mPagePanel.revalidate();
				mPagePanel.repaint();
			}
		});
		menu01.add(item01);
		
		//2. 급여 관리
		JMenu menu02 = new JMenu("급여 관리");
		JMenuItem item02 = new JMenuItem("월급명세서+보너스");
		JMenuItem item03 = new JMenuItem("출장비");
		JMenuItem item04 = new JMenuItem("교통비/식비 조회");
		
		menu02.setFont(font);
		//월급명세서+보너스 클릭 이벤트
		item02.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//출장비 클릭 이벤트
		item03.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//교통비/식비 조회 클릭 이벤트
		item04.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menu02.add(item02);
		menu02.add(item03);
		menu02.add(item04);
		
		//3. 근태관리
		JMenu menu03 = new JMenu("근태 관리");

		JMenuItem item05 = new JMenuItem("출퇴근 조회");
		JMenuItem item06 = new JMenuItem("연차,휴가,출장");
		JMenuItem item07 = new JMenuItem("휴일 근무/야근");
		
		menu03.setFont(font);
		//출퇴근 조회 클릭 이벤트
		item05.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//연차,휴가,출장 클릭 이벤트
		item06.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnnualPGUI();
				
			}
		});
		
		//휴일 근무/야근 클릭 이벤트
		item07.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menu03.add(item05);
		menu03.add(item06);
		menu03.add(item07);

		//4. 보고 관리
		JMenu menu04 = new JMenu("보고관리");
		JMenuItem item08 = new JMenuItem("업무 전달");
		JMenuItem item09 = new JMenuItem("공지사항(게시글) 조회");
		
		menu04.setFont(font);
		//업무 전달 클릭 이벤트
		item08.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//공지사항 조회 클릭 이벤트
		item09.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menu04.add(item08);
		menu04.add(item09);

		// 메뉴들을 메뉴 바에 추가
		menubar.add(menu01);
		menubar.add(menu02);
		menubar.add(menu03);
		menubar.add(menu04);
		

		mMenubarPanel = new JPanel();
		mMenubarPanel.setBounds(200, 80, 550, 200);
        mMenubarPanel.add(menubar);
  
        add(mMenubarPanel);
 

	}

	public static void main(String[] args) {
		//UserPage up = new UserPage("사원 모드", 0,0,1000,750);

	}
}
