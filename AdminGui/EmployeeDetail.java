package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//��� ������ �ڼ��� ���� �ְ� �ϴ� �� ������
public class EmployeeDetail extends JFrame{

	private ArrayList<EmployeeVO> list = EmployeeDao.select();
	
	public EmployeeDetail(int selectRow){
		
		JFrame newFrame = new JFrame("��� ���� �ڼ��� ����");
		
		DefaultTableModel newModel = new DefaultTableModel();

		//selectList = list.get(selectRow);
		// ���̸�
		newModel.addColumn("���");
		newModel.addColumn("�̸�");
		newModel.addColumn("����");
		newModel.addColumn("�̸���");
		newModel.addColumn("��ȭ��ȣ");
		newModel.addColumn("�μ�");
		newModel.addColumn("�����ڵ�");
		newModel.addColumn("�����");


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
		// ���ο� ���̺� ���� �� ����
		JTable newTable = new JTable(newModel);

		JScrollPane newSc = new JScrollPane(newTable);

		// �߰�
		newFrame.add(newSc);
		newFrame.setSize(700, 200);
		newFrame.setVisible(true);
	}

}
	

