package Admin;

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
	EmployeeVO Employee = new EmployeeVO();
	Scanner sc = new Scanner(System.in);
	EmployeeController controller = new EmployeeController();
	
    public EmployeeManage(JPanel panel) {
      
    	setPreferredSize(panel.getSize());
    	setLayout(null);
    	
    	//테이블 패널
    	JPanel tablePanel = new JPanel();
    	tablePanel.setBounds(300, 50, 500, 500);
		// 기존에 있는 데이터를 가져오기
		list = EmployeeDao.select();

		model = new DefaultTableModel()
		{
      
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀에 대해 편집 불가
            }
        };
		// 열이름
		model.addColumn("사번");
		model.addColumn("이름");
		model.addColumn("부서명");


		for (EmployeeVO temp : list) {
			// 한 행씩 추가하기
			model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), 
					temp.getDept_name()
					});
		}

		// 테이블 생성 및 모델 설정
		table = new JTable(model);

		// 스크롤 가능한 테이블 패널을 생성
		JScrollPane s = new JScrollPane(table);
		tablePanel.add(s);
    	
		add(tablePanel);
    	
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBounds(300, 0, 400, 50);
		String[] searchitems = { "이름", "성"};
		JComboBox<String> SearchBox = new JComboBox<>(searchitems);
		
		JTextField SearchField = new JTextField(20);
		JButton SearchBtn = new JButton("검색하기");
		SearchPanel.add(SearchBox);
		SearchPanel.add(SearchField);
		SearchPanel.add(SearchBtn);
		add(SearchPanel);
		
		SearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
    	
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
				controller.detail(table);
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.insert(model);
			}
		});
		
		button3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.search();
			}	
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deptUpdate(model);
			}
			
		});
		
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.jobUpdate(model);
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