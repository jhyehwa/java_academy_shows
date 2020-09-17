package shoesMart_END;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class History {
	private Scanner sc = new Scanner(System.in);
	private ProductDAO pdu = new ProductDAOImpl();
	private Product pdu2 = new Product();
	private HistoryDAO his = new HistoryImpl();
	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");

	public void buy(MemberDTO mdto) {
		ProductDTO pdto = new ProductDTO();
		int result;
		int cnt;
		System.out.print("���� �� ��ǰ ��ȣ >> ");
		String ch = sc.next();

		try {
			pdto = pdu.readProductOne(ch);
			if (pdto == null) {
				System.out.println("�������� �ʴ� ��ǰ ��ȣ�Դϴ�.");
				return;
			}
			System.out.println("\n��ǰ��ȣ\t   ��ǰ�̸�\t   ����\t���");
			System.out.println("===============================");
			System.out.print(pdto.getpNum() + "\t");
			System.out.print(pdto.getpName() + "\t");
			System.out.print(pdto.getpPrice() + "\t");
			System.out.println(pdto.getpCnt());
			System.out.println("===============================");

			System.out.print("\n������ ������ �Է��ϼ��� >> ");

			cnt = sc.nextInt();
			if (cnt < 1) {
				System.out.println("1�� �̻��� ���� �Է����ּ���.");
				return;
			}

			pdto.setpCnt(cnt); // ������ ���� set

			if (pdu2.woo(pdto) == 0) { // ��ü���� - ���԰���
				return;
			}

			String date = sdp.format(cal.getTime()); // ������ �ð� ���

			result = his.insertHis(mdto, pdto, date);

			System.out.println(result + "�� ����");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() { // ������� �ŷ����� ����
		int result;
		System.out.print("���� �� �ŷ� ��ȣ >> ");
		int ch = sc.nextInt();

		try {
			result = his.deleteHis(ch);
			if (result == 0) {
				System.out.println("������ ��ȣ�� �������� �ʽ��ϴ�.");
				return;
			}

			System.out.println(result + "�� ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewHisNumber() {
		HistoryDTO hdto;
		int ch;
		System.out.println("��ȸ �� �ŷ���ȣ >> ");
		ch = sc.nextInt();

		try {
			hdto = his.viewNumber(ch);
			if (hdto == null) {
				System.out.println("��ϵ� �ڷᰡ �����ϴ�.\n");
				return;
			}
			System.out.println("�ŷ���ȣ\t�ŷ���ȸ��\t�ŷ�ȸ���̸���\t��ǰ��ȣ\t���ų�¥\t������ȣ\t��ۻ���");
			System.out.print(hdto.getHisNumber() + "\t");
			System.out.print(hdto.getHisMemberId() + "\t");
			System.out.print(hdto.getHisMemberEmail() + "\t");
			System.out.print(hdto.getHisPNumber() + "\t");
			System.out.print(hdto.getHisDate() + "\t");
			System.out.print(hdto.getHisdnum() + "\t");
			System.out.println(hdto.getHisdstate());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void viewHisId(String id) { // ���̵� �Է¹޾� �ش� ȸ�� ����Ʈ ��ȸ
		System.out.println("\n�� ȸ������ ID ��ȸ");

		try {
			// �α��� id�� ������ ��ü ����
			List<HistoryDTO> list = his.viewMember(id);

			if (list.size() == 0) {
				System.out.println("��ϵ� �ڷᰡ �����ϴ�.\n");
				return;
			}
			System.out.println("�ŷ���ȣ\t���̵�\t�̸���\t��ǰ��ȣ\t   ���ų�¥\t\t������ȣ\t  ��ۻ���");
			System.out.println("===============================================================");
			for (HistoryDTO hdto : list) {
				System.out.print(hdto.getHisNumber() + "\t");
				System.out.print(hdto.getHisMemberId() + "\t");
				System.out.print(hdto.getHisMemberEmail() + "\t");
				System.out.print(hdto.getHisPNumber() + "\t");
				System.out.print(hdto.getHisDate() + "\t");
				System.out.print(hdto.getHisdnum() + "\t");
				System.out.println(hdto.getHisdstate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewHisAll() {
		System.out.println("\n�� �ŷ�����\n");

		try {
			List<HistoryDTO> list = his.viewAll();

			if (list.size() == 0) {
				System.out.println("��ϵ� �ڷᰡ �����ϴ�.\n");
				return;
			}
			System.out.println("�ŷ���ȣ\t���̵�\t�̸���\t��ǰ��ȣ\t���ų�¥\t������ȣ\t��ۻ���");
			System.out.println("============================================================");
			for (HistoryDTO hdto : list) {
				System.out.print(hdto.getHisNumber() + "\t");
				System.out.print(hdto.getHisMemberId() + "\t");
				System.out.print(hdto.getHisMemberEmail() + "\t");
				System.out.print(hdto.getHisPNumber() + "\t");
				System.out.print(hdto.getHisDate() + "\t");
				System.out.print(hdto.getHisdnum() + "\t");
				System.out.println(hdto.getHisdstate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
