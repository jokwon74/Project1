package User;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Admin.EmployeeDao;
import Admin.EmployeeVO;

public class UserController {

	static JPanel findPanel,AtPanel;
	public void info(int inputId) {
		
		UserVO vo = UserDao.search(inputId);
		findPanel = new JPanel();
		//("�˻��� ��� ����");
		
		DefaultTableModel newModel = new DefaultTableModel()
		{
      
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ��� ���� ���� ���� �Ұ�
            }
        };
		
		newModel.addColumn("���");
		newModel.addColumn("�̸�");
		newModel.addColumn("����");
		newModel.addColumn("�̸���");
		newModel.addColumn("��ȭ��ȣ");
		newModel.addColumn("�μ�");
		newModel.addColumn("�����ڵ�");
		newModel.addColumn("�����");
		
		newModel.addRow(new Object[] {
				vo.getEMP_ID(),
				vo.getEMP_NAME(),
				vo.getEMP_BIRTH(),
				vo.getEMAIL(),
				vo.getPHONE(),
				vo.getDEPT_CODE(),
				vo.getJOB_CODE(),
				vo.getHIRE_DATE()
		});
		JTable newTable = new JTable(newModel);

		JScrollPane newSc = new JScrollPane(newTable);

		newSc.setSize(500,200);
		// �߰�
		findPanel.add(newSc);
		findPanel.setBounds(100, 0, 500, 100);
		findPanel.setVisible(true);
		
		
		//�⼮ üũ�� �� �г�
		AtPanel = new JPanel();
		
		JRadioButton  rb1 = new JRadioButton("���");
	
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb1);

		JButton button = new JButton("Submit");
		
		AtPanel.add(rb1);
		AtPanel.add(button);
		
		AtPanel.setBounds(600, 0, 100, 100);
		AtPanel.setVisible(true);
		
	}
}
