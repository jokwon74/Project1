package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//사원 정보를 자세히 볼수 있게 하는 새 프레임
public class EmployeeDetail extends JFrame{

	private ArrayList<EmployeeVO> list = EmployeeDao.select();
	
	public EmployeeDetail(int selectRow){
		
		JFrame newFrame = new JFrame("사원 정보 자세히 보기");
		
		DefaultTableModel newModel = new DefaultTableModel();

		//selectList = list.get(selectRow);
		// 열이름
		newModel.addColumn("사번");
		newModel.addColumn("이름");
		newModel.addColumn("생일");
		newModel.addColumn("이메일");
		newModel.addColumn("전화번호");
		newModel.addColumn("부서");
		newModel.addColumn("직업코드");
		newModel.addColumn("고용일");


		newModel.addRow(new Object[] { list.get(selectRow).getEMP_ID(),
									list.get(selectRow).getEMP_NAME(),
									list.get(selectRow).getEMP_BIRTH(),
									list.get(selectRow).getEMAIL(),
									list.get(selectRow).getPHONE(),
									list.get(selectRow).getDEPT_CODE(),
									list.get(selectRow).getJOB_CODE(),
									list.get(selectRow).getHIRE_DATE()
				
		});
		System.out.println(list.get(selectRow).getDEPT_CODE());
		// 새로운 테이블 생성 및 설정
		JTable newTable = new JTable(newModel);

		JScrollPane newSc = new JScrollPane(newTable);

		// 추가
		newFrame.add(newSc);
		newFrame.setSize(700, 200);
		newFrame.setVisible(true);
	}

}
	

