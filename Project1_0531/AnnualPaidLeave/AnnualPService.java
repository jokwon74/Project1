package AnnualPaidLeave;

import java.util.ArrayList;

public class AnnualPService {

	private static ArrayList<appFormVO> list = new ArrayList<appFormVO>();

	public static void Select() {

		// ���� ���� ����ϴ� �޼���
		System.out.println("[ ServiceŬ������ Select()���� ]");

		list = AnnualPDAO.Select();

		for (appFormVO memo : list) {
			System.out.println();
		}

	}
	
	public static void Insert() {

		System.out.println("[ ServiceŬ������ Insert()���� ]");

		appFormVO vo = new appFormVO();

		if (AnnualPDAO.Insert(vo)) {
			System.out.println("���������� �߰��Ǿ����ϴ�.");
		} else {
			System.out.println("�߰� ����");
		}
	}
	
//	public static void Update() {
//
//		System.out.println("[ ServiceŬ������ Search()���� ]");
//
//		appFormVO vo = AnnualPDAO.Search();
//
//				vo.setName(name);
//				vo.setMemo(memo);
//				vo.setPassword(pw);
//
//				if (AnnualPDAO.Update(vo) > 0) {
//					System.out.println("���������� �����Ǿ����ϴ�.");
//				} else {
//					System.out.println("����� �������� �ʾҽ��ϴ�.");
//					System.out.println("������ Ȯ�����ּ���!");
//					return;
//				}
//			} 
//
//	public static appFormVO Search() {
//		
//		
//
//		// ���� �����ͺ��̽����� �˻��ϱ�
//		appFormVO vo = AnnualPDAO.Search(name);
//		System.out.println("Ȯ��: " + vo);
//
//		return vo;
//	}

	public static void Delete(int index) {
//		appFormVO vo = AnnualPDAO.Search();
		
		if(index != -1) {
			AnnualPDAO.Delete(index);
		} else {
			System.out.println("ã�� ���� �����ϴ�.");
		}

	}

}

