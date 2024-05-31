package AnnualPaidLeave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AnnualP_AdminGUI extends JFrame{
	
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<appFormVO> list = new ArrayList<appFormVO>();
	private JButton btn1, btn2, btn3;
	
	public AnnualP_AdminGUI() {

		setBounds(500, 200, 1000, 750);

		// 기존에 있는 데이터 가져오기
		list = AnnualPDAO.Select();

		init();

		setTitle("[Admin] Table Select");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init() {
		// 테이블 모델 생성
				model = new DefaultTableModel();

				// 열 이름
				model.addColumn("문서명");
				model.addColumn("신청 유형");
				model.addColumn("신청 날짜");
				model.addColumn("사 번");
				model.addColumn("사원 이름");
				model.addColumn("작성일");
				model.addColumn("진행상태");

				// MemoVO 클래스를 한 행씩 추가하기
				for (appFormVO temp : list) {

					// 한 행씩 추가하기
					model.addRow(new Object[] { temp.getTitle(), temp.getType(), temp.getUseDate(), temp.getEmp_id(),
							temp.getEmp_name(), temp.getWriteDate(), temp.getProcessing() });
				}

				// 테이블 생성 및 모델 설정
				table = new JTable(model);

				// 스크롤 가능한 테이블 패널 생성
				JScrollPane scroll = new JScrollPane(table);

				add(scroll);

				JPanel panel = new JPanel();
				add("South", panel);

				btn1 = new JButton("조회");
				btn1.setSize(50, 50);

				btn2 = new JButton("결재승인");
				btn2.setSize(100, 50);

				btn3 = new JButton("반려");
				btn3.setSize(150, 50);

				panel.add(btn1);
				panel.add(btn2);
				panel.add(btn3);
				
				//조회
				btn1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						AnnualPDAO.Search();
						
					}
				});
				
				//결재 승인
				btn2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int index = table.getSelectedRow();
						
						AnnualPDAO.Approval(index);
						
					}
				});
				
				//반려
				btn3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
				
				
				
				
				
				
		
	}
	
	
	public static void main(String[] args) {
		new AnnualP_AdminGUI();
	}

}
