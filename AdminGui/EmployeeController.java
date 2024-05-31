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
	
	//자세히 보기
	public void detail(JTable table) {
		list = EmployeeDao.select();
		int selectRow = table.getSelectedRow();
		System.out.println("컨트롤러 detail 실행");
		if(selectRow != -1) {

			JFrame newFrame = new JFrame("사원 정보 자세히 보기");
				
			DefaultTableModel newModel = new DefaultTableModel()
			{
	      
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // 모든 셀에 대해 편집 불가
	            }
	        };
		
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
			
			// 새로운 테이블 생성 및 설정
			JTable newTable = new JTable(newModel);

			JScrollPane newSc = new JScrollPane(newTable);

			// 추가
			newFrame.add(newSc);
			newFrame.setSize(700, 200);
			newFrame.setVisible(true);
		
		}else {
			//선택한 행이 없으면
			JOptionPane.showMessageDialog(null,"사원을 선택해 주세요.");
		}
	}
	
	//사원 등록
	public void insert(DefaultTableModel model) {
		
		System.out.println("사원등록 함수 실행");
		// 새로운 창
		JFrame writerFrame = new JFrame("사원 등록");
		
		JPanel panel = new JPanel();
		
		//JLabel IdLabel = new JLabel("사번 : ");
		//JTextField IdField = new JTextField(20);
		
		JLabel NameLabel = new JLabel("이름 : ");
		JTextField NameField = new JTextField(20);
		
		JLabel BirthLabel = new JLabel("생일 : ");
		JTextField BirthField = new JTextField(20);
		
		JLabel EmailLabel = new JLabel("이메일 : ");
		JTextField EmailField = new JTextField(20);
		
		JLabel PhoneLabel = new JLabel("전화번호 : ");
		JTextField PhoneField = new JTextField(20);
		
		JTextField DeptField = new JTextField(20);
		DeptField.setEnabled(false);
		JMenuBar Deptmenubar = new JMenuBar();
		JMenu Deptmenu = new JMenu("부서명 : ");
		JMenuItem item01 = new JMenuItem("인사부");
		JMenuItem item02 = new JMenuItem("총무부");
		JMenuItem item03 = new JMenuItem("기획부");
		JMenuItem item04 = new JMenuItem("회계부");
		JMenuItem item05 = new JMenuItem("품질관리부");
		JMenuItem item06 = new JMenuItem("전산부");
		JMenuItem item07 = new JMenuItem("영업부");
		JMenuItem item08 = new JMenuItem("마케팅부");
		JMenuItem item09 = new JMenuItem("생산관리부");

		//setFont(font);

		//부서명 set
		item01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D1(인사부)");
			}
		});
		item02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D2(총무부)");
			}
		});
		item03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D3(기획부)");
			}
		});
		item04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D4(회계부)");
			}
		});
		item05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D5(품질관리부)");
			}
		});
		item06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D6(전산부)");
			}
		});
		item07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D7(영업부)");
			}
		});
		item08.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D8(마케팅부)");
			}
		});
		item09.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D9(생산관리부)");
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
		JMenu Jobmenu = new JMenu("직급명 : ");
		JMenuItem Jobitem01 = new JMenuItem("이사");
		JMenuItem Jobitem02 = new JMenuItem("부장");
		JMenuItem Jobitem03 = new JMenuItem("차장");
		JMenuItem Jobitem04 = new JMenuItem("과장");
		JMenuItem Jobitem05 = new JMenuItem("대리");
		JMenuItem Jobitem06 = new JMenuItem("주임");
		JMenuItem Jobitem07 = new JMenuItem("사원");


		//setFont(font);

		//부서명 set
		Jobitem01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J1(이사)");
			}
		});
		Jobitem02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J2(부장)");
			}
		});
		Jobitem03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J3(차장)");
			}
		});
		Jobitem04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J4(과장)");
			}
		});
		Jobitem05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J5(대리)");
			}
		});
		Jobitem06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J6(주임)");
			}
		});
		Jobitem07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J7(사원)");
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
		
		JLabel HireLabel = new JLabel("고용일 : ");
		JTextField HireField = new JTextField(20);
		
		JButton submitButton = new JButton("등록하기");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 텍스트 필드에 있는 모든 내용을 가져오기
				
				
				//int id = Integer.parseInt(IdField.getText());
			
				String name = NameField.getText(); 
				String birth = BirthField.getText(); 
				String email = EmailField.getText(); 
				String phone = PhoneField.getText(); 
				String dept = (DeptField.getText()).substring(0,2); 
				String job = (JobField.getText()).substring(0,2); 
				String hire_date = HireField.getText(); 
				
				// 데이터베이스에 추가하는 내용!
				EmployeeVO vo = new EmployeeVO(name,birth,email,
						phone, dept, job, hire_date);
				EmployeeDao.insert(vo);
				System.out.println("추가 성공");
				// 추가 후 새로고침
				list = EmployeeDao.select();
				
				// 모든 행이 삭제된다.
				model.setRowCount(0);
				
				for (EmployeeVO temp : list) {
					// 한 행씩 추가하기
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
		
		// 글쓰기 새창에 추가하기 
		writerFrame.add(panel);
		writerFrame.setSize(300,300);
		writerFrame.setVisible(true);
		
	}

	//사원 검색
	public void search() {
		System.out.println("EmployeeManage의 사원 검색 실행");
		
		JFrame SearchFrame = new JFrame();
		JPanel SearchPanel = new JPanel();
		JPanel FindPanel = new JPanel();
		
		JLabel NameLabel = new JLabel("이름 : ");
		JTextField NameFeild = new JTextField(20);
		
		JButton submitButton = new JButton("검색하기");
		
		JLabel FirstNameLabel = new JLabel("성으로 검색하기 : ");
		JTextField FirstNameFeild = new JTextField(10);
		
		JButton submitButton2 = new JButton("검색하기");
		
		//이름 전체로 찾기
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
					JFrame findFrame = new JFrame("검색한 사원 정보");
					
					DefaultTableModel newModel = new DefaultTableModel()
					{
			      
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false; // 모든 셀에 대해 편집 불가
			            }
			        };
					
					newModel.addColumn("사번");
					newModel.addColumn("이름");
					newModel.addColumn("생일");
					newModel.addColumn("이메일");
					newModel.addColumn("전화번호");
					newModel.addColumn("부서");
					newModel.addColumn("직업코드");
					newModel.addColumn("고용일");
					
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

					// 추가
					findFrame.add(newSc);
					findFrame.setSize(700, 200);
					findFrame.setVisible(true);
				}
			}
		});
		
		submitButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//찾은 검색창 만들기
				//JFrame FindFrame = new JFrame();
				//JPanel FindPanel = new JPanel();
				
				String Firstname = FirstNameFeild.getText();
				//찾는 이름이 있는지 검색하는 함수
				System.out.println("성씨: "+Firstname);
				
				boolean check = EmployeeDao.checkFirstName(Firstname);
				if(!check) {
					//찾는 이름이 없으면
					JOptionPane.showMessageDialog(submitButton2, "찾는 사원의 정보가 없습니다.!");
				}
				else {
					
					ArrayList<EmployeeVO> searchlist = EmployeeDao.searchFirstname(Firstname);
					//JFrame FindFrame = new JFrame("검색한 사원 정보");
					

					DefaultTableModel newModel = new DefaultTableModel()
					{
			      
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false; // 모든 셀에 대해 편집 불가
			            }
			        };

					// 열이름
					newModel.addColumn("사번");
					newModel.addColumn("이름");
					newModel.addColumn("생일");
					newModel.addColumn("이메일");
					newModel.addColumn("전화번호");
					newModel.addColumn("부서코드");
					newModel.addColumn("직업코드");
					newModel.addColumn("고용일");

					
					for (EmployeeVO temp : searchlist) {
						// 한 행씩 추가하기
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
					
					// 새로운 테이블 생성 및 설정
					JTable newTable = new JTable(newModel);

					JScrollPane newSc = new JScrollPane(newTable);
					//패널 갱신
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

	//부서 이동
	public void deptUpdate(DefaultTableModel model) {
		//새로운 프레임 생성
		JFrame UpdateFrame = new JFrame("부서 이동");
		JPanel panel = new JPanel();
		
		//이름입력
		JLabel nameLabel = new JLabel("이름 : ");
		JTextField nameField = new JTextField(20);
		
		JTextField DeptField = new JTextField(20);
		DeptField.setEnabled(false);
		JMenuBar Deptmenubar = new JMenuBar();
		JMenu Deptmenu = new JMenu("부서명 : ");
		JMenuItem item01 = new JMenuItem("인사부");
		JMenuItem item02 = new JMenuItem("총무부");
		JMenuItem item03 = new JMenuItem("기획부");
		JMenuItem item04 = new JMenuItem("회계부");
		JMenuItem item05 = new JMenuItem("품질관리부");
		JMenuItem item06 = new JMenuItem("전산부");
		JMenuItem item07 = new JMenuItem("영업부");
		JMenuItem item08 = new JMenuItem("마케팅부");
		JMenuItem item09 = new JMenuItem("생산관리부");

		//setFont(font);

		//부서명 set
		item01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D1(인사부)");
			}
		});
		item02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D2(총무부)");
			}
		});
		item03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D3(기획부)");
			}
		});
		item04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D4(회계부)");
			}
		});
		item05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D5(품질관리부)");
			}
		});
		item06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D6(전산부)");
			}
		});
		item07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D7(영업부)");
			}
		});
		item08.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D8(마케팅부)");
			}
		});
		item09.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeptField.setText("D9(생산관리부)");
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
		
		JButton submitButton = new JButton("부서 이동 하기");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("컨트롤러 deptupdate 제출버튼 클릭");
				String name = nameField.getText();
				String dept = (DeptField.getText()).substring(0,2); 
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

	//부서 이동
	public void jobUpdate(DefaultTableModel model) {
		//새로운 프레임 생성
		JFrame UpdateFrame = new JFrame("직급 승인");
		JPanel panel = new JPanel();
		
		//이름입력
		JLabel nameLabel = new JLabel("이름 : ");
		JTextField nameField = new JTextField(20);
		
		//부서 입력
		JTextField JobField = new JTextField(20);
		JobField.setEnabled(false);
		JMenuBar Jobmenubar = new JMenuBar();
		JMenu Jobmenu = new JMenu("직급명 : ");
		JMenuItem Jobitem01 = new JMenuItem("이사");
		JMenuItem Jobitem02 = new JMenuItem("부장");
		JMenuItem Jobitem03 = new JMenuItem("차장");
		JMenuItem Jobitem04 = new JMenuItem("과장");
		JMenuItem Jobitem05 = new JMenuItem("대리");
		JMenuItem Jobitem06 = new JMenuItem("주임");
		JMenuItem Jobitem07 = new JMenuItem("사원");


		//setFont(font);

		//부서명 set
		Jobitem01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J1(이사)");
			}
		});
		Jobitem02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J2(부장)");
			}
		});
		Jobitem03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J3(차장)");
			}
		});
		Jobitem04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J4(과장)");
			}
		});
		Jobitem05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J5(대리)");
			}
		});
		Jobitem06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J6(주임)");
			}
		});
		Jobitem07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JobField.setText("J7(사원)");
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
		
		JButton submitButton = new JButton("직급 변동 승인하기");
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String job = (JobField.getText()).substring(0,2); 
				
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
