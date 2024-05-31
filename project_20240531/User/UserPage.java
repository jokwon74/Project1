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
	
	static Font ft = new Font("���ʷҵ���",Font.BOLD,15);

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
	
		Font font = new Font("���ʷҵ���",Font.BOLD,28);
		//1. �������
		JMenu menu01 = new JMenu("�������");
		JMenuItem item01 = new JMenuItem("��� ����");
		menu01.setFont(font);

		//������� Ŭ�� �̺�Ʈ
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
		
		//2. �޿� ����
		JMenu menu02 = new JMenu("�޿� ����");
		JMenuItem item02 = new JMenuItem("���޸���+���ʽ�");
		JMenuItem item03 = new JMenuItem("�����");
		JMenuItem item04 = new JMenuItem("�����/�ĺ� ��ȸ");
		
		menu02.setFont(font);
		//���޸���+���ʽ� Ŭ�� �̺�Ʈ
		item02.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//����� Ŭ�� �̺�Ʈ
		item03.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//�����/�ĺ� ��ȸ Ŭ�� �̺�Ʈ
		item04.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menu02.add(item02);
		menu02.add(item03);
		menu02.add(item04);
		
		//3. ���°���
		JMenu menu03 = new JMenu("���� ����");

		JMenuItem item05 = new JMenuItem("����� ��ȸ");
		JMenuItem item06 = new JMenuItem("����,�ް�,����");
		JMenuItem item07 = new JMenuItem("���� �ٹ�/�߱�");
		
		menu03.setFont(font);
		//����� ��ȸ Ŭ�� �̺�Ʈ
		item05.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//����,�ް�,���� Ŭ�� �̺�Ʈ
		item06.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnnualPGUI();
				
			}
		});
		
		//���� �ٹ�/�߱� Ŭ�� �̺�Ʈ
		item07.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menu03.add(item05);
		menu03.add(item06);
		menu03.add(item07);

		//4. ���� ����
		JMenu menu04 = new JMenu("�������");
		JMenuItem item08 = new JMenuItem("���� ����");
		JMenuItem item09 = new JMenuItem("��������(�Խñ�) ��ȸ");
		
		menu04.setFont(font);
		//���� ���� Ŭ�� �̺�Ʈ
		item08.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//�������� ��ȸ Ŭ�� �̺�Ʈ
		item09.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menu04.add(item08);
		menu04.add(item09);

		// �޴����� �޴� �ٿ� �߰�
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
		//UserPage up = new UserPage("��� ���", 0,0,1000,750);

	}
}
