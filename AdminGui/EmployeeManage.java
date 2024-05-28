package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManage extends JPanel {

	EmployeeService service = new EmployeeService();
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<EmployeeVO> list;
	Scanner sc = new Scanner(System.in);
	
    public EmployeeManage(JPanel panel) {
      
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	//테이블 패널
    	JPanel tablePanel = new JPanel();
    	tablePanel.setBounds(300, 0, 500, 500);
		// 기존에 있는 데이터를 가져오기
		list = EmployeeDao.select();

		// 테이블 모델 생성
		model = new DefaultTableModel();

		// 열이름
		model.addColumn("사번");
		model.addColumn("이름");
		model.addColumn("부서");

		// MemoVO클래스를 한 행씩 추가하기

		for (EmployeeVO temp : list) {
			// 한 행씩 추가하기
			model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
					});
		}

		// 테이블 생성 및 모델 설정
		table = new JTable(model);

		// 스크롤 가능한 테이블 패널을 생성
		JScrollPane s = new JScrollPane(table);
		tablePanel.add(s);
    	
		add(tablePanel);
    	
    	
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1,10,40));
        buttonPanel.setBounds(50,50,170,300);
        
        JButton button1 = new JButton("자세히 보기");
        JButton button2 = new JButton("사원 등록");    
        JButton button3 = new JButton("사원 검색");
        JButton button4 = new JButton("부서 이동");
        JButton button5 = new JButton("직급 승인");
        
        
        //자세히 보기
        button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectRow = table.getSelectedRow();
				System.out.println("선택한 행:" + selectRow);
				if(selectRow != -1) {
					EmployeeDetail ed = new EmployeeDetail(selectRow);
				}else {
					//선택한 행이 없으면
					JOptionPane.showMessageDialog(button1, "사원을 선택해 주세요.");
				}
				
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeInsert ei = new EmployeeInsert(model);
			}
		});
		
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("EmployeeManage의 사원 검색 실행");
				
				JFrame SearchFrame = new JFrame();
				JPanel SearchPanel = new JPanel();
				
				JLabel NameLabel = new JLabel("이름 : ");
				JTextField NameFeild = new JTextField(20);
				
				JButton submitButton = new JButton("검색하기");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//찾은 검색창 만들기
						JFrame FindFrame = new JFrame();
						
						String name = NameFeild.getText();
						//찾는 이름이 있는지 검색하는 함수
						boolean check = EmployeeDao.checkName(name);
						if(!check) {
							//찾는 이름이 없으면
							JOptionPane.showMessageDialog(submitButton, "찾는 사원의 정보가 없습니다.!");
						}
						else {
							
							EmployeeVO vo = EmployeeDao.search(name);			
						}
					}
				});
				SearchPanel.add(NameLabel);
				SearchPanel.add(NameFeild);
				SearchPanel.add(submitButton);
				
				SearchFrame.add(SearchPanel);
				SearchFrame.setSize(400,300);
				SearchFrame.setVisible(true);
			
			}
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//새로운 프레임 생성
				JFrame UpdateFrame = new JFrame("부서 이동");
				JPanel panel = new JPanel();
				
				//이름입력
				JLabel nameLabel = new JLabel("이름 : ");
				JTextField nameField = new JTextField(20);
				
				//부서 입력
				JLabel deptLabel = new JLabel("부서 : ");
				JTextField deptField = new JTextField(20);
				
				JButton submitButton = new JButton("부서 이동 하기");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = nameField.getText();
						String dept = deptField.getText();
						boolean check = EmployeeDao.checkName(name);
						
						if(!check) {
							//찾는 이름이 없으면
							JOptionPane.showMessageDialog(submitButton, 
									"부서 이동할 사원이 존재하지 않습니다.!");
							
						}else {
							//업데이트 실행
							EmployeeDao.update(name,dept);
							
							// 업데이트 후 새로 고침
							list = EmployeeDao.select();

							// 모든 행을 삭제
							model.setRowCount(0);
							

							for (EmployeeVO temp : list) {
								// 한 행씩 추가하기
								model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
										});
							}
							
						}	
					}
				});
				panel.add(nameLabel);
				panel.add(nameField);
				
				panel.add(deptLabel);
				panel.add(deptField);
				
				panel.add(submitButton);
				
				UpdateFrame.add(panel);
				UpdateFrame.setSize(300,300);
				UpdateFrame.setVisible(true);
	
			}
			
			
		});
		
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//새로운 프레임 생성
				JFrame UpdateFrame = new JFrame("직급 승인");
				JPanel panel = new JPanel();
				
				//이름입력
				JLabel nameLabel = new JLabel("이름 : ");
				JTextField nameField = new JTextField(20);
				
				//부서 입력
				JLabel jobLabel = new JLabel("직급 : ");
				JTextField jobField = new JTextField(20);
				
				JButton submitButton = new JButton("직급 변동 승인하기");
				
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = nameField.getText();
						String job = jobField.getText();
						
						//찾는 이름 있는지 검사하는 함수
						boolean check = EmployeeDao.checkName(name);
						
						if(!check) {
							//찾는 이름이 없으면
							JOptionPane.showMessageDialog(submitButton, 
									"직급 승인할 사원이 존재하지 않습니다.!");
							
						}else {
							//찾는 이름 있음
							//업데이트 실행
							EmployeeDao.updateJob(name,job);
							
							// 업데이트 후 새로 고침
							list = EmployeeDao.select();

							// 모든 행이 삭제
							model.setRowCount(0);
							

							for (EmployeeVO temp : list) {
								// 한 행씩 추가하기
								model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDEPT_CODE(),
										});
							}
							
						}	
					}
				});
				panel.add(nameLabel);
				panel.add(nameField);
				
				panel.add(jobLabel);
				panel.add(jobField);
				
				panel.add(submitButton);
				
				UpdateFrame.add(panel);
				UpdateFrame.setSize(300,300);
				UpdateFrame.setVisible(true);
	
			}
			
			
		});

        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        
        add(buttonPanel);
        
        
        
        
    }

}