package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeManage extends JPanel {

	EmployeeService service = new EmployeeService();
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<EmployeeVO> list;
	
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
				EmployeeDetail ed = new EmployeeDetail(selectRow);
				
			}
		});
        
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeInsert ei = new EmployeeInsert(model);
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