package Admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class EmployeeController {

	private ArrayList<EmployeeVO> list;
	private DefaultTableModel newModel;
	private JTable table;
	Scanner sc = new Scanner(System.in);
	
	//�ڼ��� ����
	public void detail(JTable table) {
		list = EmployeeDao.select();
		int selectRow = table.getSelectedRow();
		System.out.println("��Ʈ�ѷ� detail ����");
		if(selectRow != -1) {

			JFrame newFrame = new JFrame("��� ���� �ڼ��� ����");
				
			DefaultTableModel newModel = new DefaultTableModel()
			{
	      
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // ��� ���� ���� ���� �Ұ�
	            }
	        };
		
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
			
			// ���ο� ���̺� ���� �� ����
			JTable newTable = new JTable(newModel);

			JScrollPane newSc = new JScrollPane(newTable);

			// �߰�
			newFrame.add(newSc);
			newFrame.setSize(700, 200);
			newFrame.setVisible(true);
		
		}else {
			//������ ���� ������
			JOptionPane.showMessageDialog(null,"����� ������ �ּ���.");
		}
	}
	
	//��� ���
	public void insert(DefaultTableModel model) {
		
		System.out.println("������ �Լ� ����");
		// ���ο� â
		JFrame writerFrame = new JFrame("��� ���");
		
		JPanel panel = new JPanel();
		
		//JLabel IdLabel = new JLabel("��� : ");
		//JTextField IdField = new JTextField(20);
		
		JLabel NameLabel = new JLabel("�̸� : ");
		JTextField NameField = new JTextField(20);
		
		JLabel BirthLabel = new JLabel("���� : ");
		JTextField BirthField = new JTextField(20);
		
		JLabel EmailLabel = new JLabel("�̸��� : ");
		JTextField EmailField = new JTextField(20);
		
		JLabel PhoneLabel = new JLabel("��ȭ��ȣ : ");
		JTextField PhoneField = new JTextField(20);
		
		JTextField DeptField = new JTextField(20);
		DeptField.setEnabled(false);
		JMenuBar Deptmenubar = new JMenuBar();
		JMenu Deptmenu = new JMenu("�μ��� : ");
		JMenuItem item01 = new JMenuItem("�λ��");
		JMenuItem item02 = new JMenuItem("�ѹ���");
		JMenuItem item03 = new JMenuItem("��ȹ��");
		JMenuItem item04 = new JMenuItem("ȸ���");
		JMenuItem item05 = new JMenuItem("ǰ��������");
		JMenuItem item06 = new JMenuItem("�����");
		JMenuItem item07 = new JMenuItem("������");
		JMenuItem item08 = new JMenuItem("�����ú�");
		JMenuItem item09 = new JMenuItem("���������");

		//setFont(font);

		//�μ��� set
		item01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D1(�λ��)");
			}
		});
		item02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D2(�ѹ���)");
			}
		});
		item03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D3(��ȹ��)");
			}
		});
		item04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D4(ȸ���)");
			}
		});
		item05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D5(ǰ��������)");
			}
		});
		item06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D6(�����)");
			}
		});
		item07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D7(������)");
			}
		});
		item08.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D8(�����ú�)");
			}
		});
		item09.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D9(���������)");
			}
		});

				
		Deptmenu.add(item01);
		Deptmenu.add(item02);
		Deptmenu.add(item03);
		Deptmenu.add(item04);
		Deptmenu.add(item05);
		Deptmenu.add(item06);
		Deptmenu.add(item07);
		Deptmenu.add(item08);
		Deptmenu.add(item09);
		
		Deptmenubar.add(Deptmenu);
		
		JTextField JobField = new JTextField(20);
		JobField.setEnabled(false);
		JMenuBar Jobmenubar = new JMenuBar();
		JMenu Jobmenu = new JMenu("���޸� : ");
		JMenuItem Jobitem01 = new JMenuItem("�̻�");
		JMenuItem Jobitem02 = new JMenuItem("����");
		JMenuItem Jobitem03 = new JMenuItem("����");
		JMenuItem Jobitem04 = new JMenuItem("����");
		JMenuItem Jobitem05 = new JMenuItem("�븮");
		JMenuItem Jobitem06 = new JMenuItem("����");
		JMenuItem Jobitem07 = new JMenuItem("���");


		//setFont(font);

		//�μ��� set
		Jobitem01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J1(�̻�)");
			}
		});
		Jobitem02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J2(����)");
			}
		});
		Jobitem03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J3(����)");
			}
		});
		Jobitem04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J4(����)");
			}
		});
		Jobitem05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J5(�븮)");
			}
		});
		Jobitem06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J6(����)");
			}
		});
		Jobitem07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J7(���)");
			}
		});			
		Jobmenu.add(Jobitem01);
		Jobmenu.add(Jobitem02);
		Jobmenu.add(Jobitem03);
		Jobmenu.add(Jobitem04);
		Jobmenu.add(Jobitem05);
		Jobmenu.add(Jobitem06);
		Jobmenu.add(Jobitem07);
		Jobmenubar.add(Jobmenu);
		
		JLabel HireLabel = new JLabel("����� : ");
		JTextField HireField = new JTextField(20);
		
		JButton submitButton = new JButton("����ϱ�");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ؽ�Ʈ �ʵ忡 �ִ� ��� ������ ��������
				
				
				//int id = Integer.parseInt(IdField.getText());
			
				String name = NameField.getText(); 
				String birth = BirthField.getText(); 
				String email = EmailField.getText(); 
				String phone = PhoneField.getText(); 
				String dept = (DeptField.getText()).substring(0,2); 
				String job = (JobField.getText()).substring(0,2); 
				String hire_date = HireField.getText(); 
				
				// �����ͺ��̽��� �߰��ϴ� ����!
				EmployeeVO vo = new EmployeeVO(name,birth,email,
						phone, dept, job, hire_date);
				EmployeeDao.insert(vo);
				System.out.println("�߰� ����");
				// �߰� �� ���ΰ�ħ
				list = EmployeeDao.select();
				
				// ��� ���� �����ȴ�.
				model.setRowCount(0);
				
				for (EmployeeVO temp : list) {
					// �� �྿ �߰��ϱ�
					model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDept_name(),
					});
				}
			}
			
		});
		
		panel.add(NameLabel);
		panel.add(NameField);
		
		panel.add(BirthLabel);
		panel.add(BirthField);
		
		panel.add(EmailLabel);
		panel.add(EmailField);
		
		panel.add(PhoneLabel);
		panel.add(PhoneField);
		
		panel.add(Deptmenubar);
		panel.add(DeptField);
		
		panel.add(Jobmenubar);
		panel.add(JobField);
		
		panel.add(HireLabel);
		panel.add(HireField);
		
		
		panel.add(submitButton);
		
		// �۾��� ��â�� �߰��ϱ� 
		writerFrame.add(panel);
		writerFrame.setSize(300,300);
		writerFrame.setVisible(true);
		
	}

	//��� �˻�
	public void search() {
		System.out.println("EmployeeManage�� ��� �˻� ����");
		
		JFrame SearchFrame = new JFrame();
		JPanel SearchPanel = new JPanel();
		JPanel FindPanel = new JPanel();
		
		JLabel NameLabel = new JLabel("�̸� : ");
		JTextField NameFeild = new JTextField(20);
		
		JButton submitButton = new JButton("�˻��ϱ�");
		
		JLabel FirstNameLabel = new JLabel("������ �˻��ϱ� : ");
		JTextField FirstNameFeild = new JTextField(10);
		
		JButton submitButton2 = new JButton("�˻��ϱ�");
		
		//�̸� ��ü�� ã��
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//ã�� �˻�â �����
				JFrame FindFrame = new JFrame();
				
				String name = NameFeild.getText();
				//ã�� �̸��� �ִ��� �˻��ϴ� �Լ�
				boolean check = EmployeeDao.checkName(name);
				if(!check) {
					//ã�� �̸��� ������
					JOptionPane.showMessageDialog(submitButton, "ã�� ����� ������ �����ϴ�.!");
				}
				else {
					
					EmployeeVO vo = EmployeeDao.search(name);
					JFrame findFrame = new JFrame("�˻��� ��� ����");
					
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

					// �߰�
					findFrame.add(newSc);
					findFrame.setSize(700, 200);
					findFrame.setVisible(true);
				}
			}
		});
		
		submitButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ã�� �˻�â �����
				//JFrame FindFrame = new JFrame();
				//JPanel FindPanel = new JPanel();
				
				String Firstname = FirstNameFeild.getText();
				//ã�� �̸��� �ִ��� �˻��ϴ� �Լ�
				System.out.println("����: "+Firstname);
				
				boolean check = EmployeeDao.checkFirstName(Firstname);
				if(!check) {
					//ã�� �̸��� ������
					JOptionPane.showMessageDialog(submitButton2, "ã�� ����� ������ �����ϴ�.!");
				}
				else {
					
					ArrayList<EmployeeVO> searchlist = EmployeeDao.searchFirstname(Firstname);
					//JFrame FindFrame = new JFrame("�˻��� ��� ����");
					

					DefaultTableModel newModel = new DefaultTableModel()
					{
			      
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false; // ��� ���� ���� ���� �Ұ�
			            }
			        };

					// ���̸�
					newModel.addColumn("���");
					newModel.addColumn("�̸�");
					newModel.addColumn("����");
					newModel.addColumn("�̸���");
					newModel.addColumn("��ȭ��ȣ");
					newModel.addColumn("�μ��ڵ�");
					newModel.addColumn("�����ڵ�");
					newModel.addColumn("�����");

					
					for (EmployeeVO temp : searchlist) {
						// �� �྿ �߰��ϱ�
						newModel.addRow(new Object[] { temp.getEMP_ID(), 
								temp.getEMP_NAME(),
								temp.getEMP_BIRTH(),
								temp.getEMAIL(),
								temp.getPHONE(),
								temp.getDEPT_CODE(),
								temp.getJOB_CODE(),
								temp.getHIRE_DATE()
								});
					}
					
					// ���ο� ���̺� ���� �� ����
					JTable newTable = new JTable(newModel);

					JScrollPane newSc = new JScrollPane(newTable);
					//�г� ����
					FindPanel.removeAll();
					FindPanel.setBounds(0,100,700,600);
					FindPanel.add(newSc);
					FindPanel.setVisible(true);
					
					SearchPanel.add(FindPanel,BorderLayout.CENTER);
					
					SearchPanel.revalidate();
					SearchPanel.repaint();
					
				}
				
			}
		});
		SearchPanel.add(NameLabel);
		SearchPanel.add(NameFeild);
		SearchPanel.add(submitButton);
		
		SearchPanel.add(FirstNameLabel);
		SearchPanel.add(FirstNameFeild);
		SearchPanel.add(submitButton2);
		
		SearchFrame.add(SearchPanel);
		SearchFrame.setSize(700,700);
		SearchFrame.setVisible(true);
	
	}

	//�μ� �̵�
	public void deptUpdate(DefaultTableModel model) {
		//���ο� ������ ����
		JFrame UpdateFrame = new JFrame("�μ� �̵�");
		JPanel panel = new JPanel();
		
		//�̸��Է�
		JLabel nameLabel = new JLabel("�̸� : ");
		JTextField nameField = new JTextField(20);
		
		JTextField DeptField = new JTextField(20);
		DeptField.setEnabled(false);
		JMenuBar Deptmenubar = new JMenuBar();
		JMenu Deptmenu = new JMenu("�μ��� : ");
		JMenuItem item01 = new JMenuItem("�λ��");
		JMenuItem item02 = new JMenuItem("�ѹ���");
		JMenuItem item03 = new JMenuItem("��ȹ��");
		JMenuItem item04 = new JMenuItem("ȸ���");
		JMenuItem item05 = new JMenuItem("ǰ��������");
		JMenuItem item06 = new JMenuItem("�����");
		JMenuItem item07 = new JMenuItem("������");
		JMenuItem item08 = new JMenuItem("�����ú�");
		JMenuItem item09 = new JMenuItem("���������");

		//setFont(font);

		//�μ��� set
		item01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D1(�λ��)");
			}
		});
		item02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D2(�ѹ���)");
			}
		});
		item03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D3(��ȹ��)");
			}
		});
		item04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D4(ȸ���)");
			}
		});
		item05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D5(ǰ��������)");
			}
		});
		item06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D6(�����)");
			}
		});
		item07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D7(������)");
			}
		});
		item08.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D8(�����ú�)");
			}
		});
		item09.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D9(���������)");
			}
		});

		Deptmenu.add(item01);
		Deptmenu.add(item02);
		Deptmenu.add(item03);
		Deptmenu.add(item04);
		Deptmenu.add(item05);
		Deptmenu.add(item06);
		Deptmenu.add(item07);
		Deptmenu.add(item08);
		Deptmenu.add(item09);
		
		Deptmenubar.add(Deptmenu);
		
		JButton submitButton = new JButton("�μ� �̵� �ϱ�");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��Ʈ�ѷ� deptupdate �����ư Ŭ��");
				String name = nameField.getText();
				String dept = (DeptField.getText()).substring(0,2); 
				boolean check = EmployeeDao.checkName(name);
				
				if(!check) {
					//ã�� �̸��� ������
					JOptionPane.showMessageDialog(submitButton, 
							"�μ� �̵��� ����� �������� �ʽ��ϴ�.!");
					
				}else {
					//������Ʈ ����
					EmployeeDao.update(name,dept);
					
					// ������Ʈ �� ���� ��ħ
					list = EmployeeDao.select();
					// ��� ���� ����
					model.setRowCount(0);
					

					for (EmployeeVO temp : list) {
						// �� �྿ �߰��ϱ�
						model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDept_name(),
								});
					}
					
				}	
			}
		});
		panel.add(nameLabel);
		panel.add(nameField);
		
		panel.add(Deptmenubar);
		panel.add(DeptField);
		
		panel.add(submitButton);
		
		UpdateFrame.add(panel);
		UpdateFrame.setSize(300,300);
		UpdateFrame.setVisible(true);
		
		
	}

	//�μ� �̵�
	public void jobUpdate(DefaultTableModel model) {
		//���ο� ������ ����
		JFrame UpdateFrame = new JFrame("���� ����");
		JPanel panel = new JPanel();
		
		//�̸��Է�
		JLabel nameLabel = new JLabel("�̸� : ");
		JTextField nameField = new JTextField(20);
		
		//�μ� �Է�
		JTextField JobField = new JTextField(20);
		JobField.setEnabled(false);
		JMenuBar Jobmenubar = new JMenuBar();
		JMenu Jobmenu = new JMenu("���޸� : ");
		JMenuItem Jobitem01 = new JMenuItem("�̻�");
		JMenuItem Jobitem02 = new JMenuItem("����");
		JMenuItem Jobitem03 = new JMenuItem("����");
		JMenuItem Jobitem04 = new JMenuItem("����");
		JMenuItem Jobitem05 = new JMenuItem("�븮");
		JMenuItem Jobitem06 = new JMenuItem("����");
		JMenuItem Jobitem07 = new JMenuItem("���");


		//setFont(font);

		//�μ��� set
		Jobitem01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J1(�̻�)");
			}
		});
		Jobitem02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J2(����)");
			}
		});
		Jobitem03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J3(����)");
			}
		});
		Jobitem04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J4(����)");
			}
		});
		Jobitem05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J5(�븮)");
			}
		});
		Jobitem06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J6(����)");
			}
		});
		Jobitem07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J7(���)");
			}
		});			
		Jobmenu.add(Jobitem01);
		Jobmenu.add(Jobitem02);
		Jobmenu.add(Jobitem03);
		Jobmenu.add(Jobitem04);
		Jobmenu.add(Jobitem05);
		Jobmenu.add(Jobitem06);
		Jobmenu.add(Jobitem07);
		Jobmenubar.add(Jobmenu);
		
		JButton submitButton = new JButton("���� ���� �����ϱ�");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String job = (JobField.getText()).substring(0,2); 
				
				//ã�� �̸� �ִ��� �˻��ϴ� �Լ�
				boolean check = EmployeeDao.checkName(name);
				
				if(!check) {
					//ã�� �̸��� ������
					JOptionPane.showMessageDialog(submitButton, 
							"���� ������ ����� �������� �ʽ��ϴ�.!");
					
				}else {
					//ã�� �̸� ����
					//������Ʈ ����
					EmployeeDao.updateJob(name,job);
					
					// ������Ʈ �� ���� ��ħ
					list = EmployeeDao.select();

					// ��� ���� ����
					model.setRowCount(0);
					

					for (EmployeeVO temp : list) {
						// �� �྿ �߰��ϱ�
						model.addRow(new Object[] { temp.getEMP_ID(), temp.getEMP_NAME(), temp.getDept_name(),
								});
					}
					
				}	
			}
		});
		panel.add(nameLabel);
		panel.add(nameField);
		
		panel.add(Jobmenubar);
		panel.add(JobField);
		
		panel.add(submitButton);
		
		UpdateFrame.add(panel);
		UpdateFrame.setSize(300,300);
		UpdateFrame.setVisible(true);
		
	}
}
