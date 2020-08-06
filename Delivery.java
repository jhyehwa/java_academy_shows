package shoesMart_END;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Delivery {
	private DeliveryDAO dao = new DeliveryDAOImpl();
	private HistoryDAO his = new HistoryImpl();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private History history = new History();

	public void inputDelivery() {
		System.out.println("\n�� ��� ���� �߰�");
		DeliveryDTO dto = new DeliveryDTO();
		try {
			System.out.print("������ȣ >> ");
			String dNum = br.readLine();
			DeliveryDTO ddto = dao.readDNum(dNum);
			if (ddto != null) {
				System.out.println("�Է��Ͻ� ����� ��ȣ�� �̹� �����մϴ�.");
				return;
			}
			dto.setdNum(dNum);

			history.viewHisAll();

			System.out.print("\n�ŷ� ��ȣ >> ");
			int dPNum = Integer.parseInt(br.readLine());
			HistoryDTO hdto = his.viewNumber(dPNum);
			if (hdto == null) {
				System.out.println("�Է��Ͻ� �ŷ������� �������� �ʽ��ϴ�.");
				return;
			}

			if (hdto.getHisdstate().equals("�����")) {
				System.out.println("�ش� ��ǰ�� �̹� ������Դϴ�.");
				return;
			} else if (hdto.getHisdstate().equals("��ۿϷ�")) {
				System.out.println("�ش� ��ǰ�� �̹� ��ۿϷ� �Ǿ����ϴ�.");
				return;
			}
			dto.setdHisNum(dPNum);

			dto.setdPNum(hdto.getHisPNumber());

			System.out.print("����� >> ");
			dto.setdSector(br.readLine());

			System.out.print("��� ��� ��ȭ��ȣ >> ");
			dto.setdManNum(br.readLine());

			System.out.print("��� ���� ��¥ >> ");
			dto.setdDate(br.readLine());

			int result = dao.insert(dto);
			System.out.println(result + "���� ��������� �߰��Ǿ����ϴ�.");

		} catch (NumberFormatException e) {
			System.out.println("��Ȯ�� ���� �Է����ּ���.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDelivery() {
		String dNum;
		char c;
		try {
			System.out.print("����� ��ȣ >> ");
			dNum = br.readLine();
			DeliveryDTO dto = dao.readDNum(dNum);
			if (dto == null) {
				System.out.println("�˻��Ͻ� ����� ��ȣ�� �������� �ʽ��ϴ�.");
				return;
			}

			System.out.printf("%s�� ��ǰ�� ��� �Ϸ�Ǿ����ϱ�? [Y/N] : ", dNum);
			c = br.readLine().charAt(0);
			if (c == 'Y' || c == 'y') {
				if (dto.getdState().equals("��ۿϷ�")) {
					System.out.println("�̹� ��ۿϷ� ó���� ��ǰ�Դϴ�.");
					return;
				}
				dto.setdState("��ۿϷ�");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				String time1 = sdf.format(time);
				dto.setdEndDate(time1);
			} else if (c == 'N' || c == 'n') {
				return;
			} else {
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				return;
			}
			int result = dao.update(dto);
			System.out.println(result + "���� ��������� �����Ǿ����ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteDelivery() {
		System.out.println("\n�� ��� ���� ����");
		try {
			System.out.print("������ ����� ��ȣ >> ");
			String dNum = br.readLine();
			DeliveryDTO dto = dao.readDNum(dNum);
			if (dto.getdState().equals("��ۿϷ�")) {
				System.out.println("��ۿϷ�� ���� ������ �Ұ����մϴ�.");
				return;
			}
			int result = dao.delete(dNum);
			System.out.println(result + "���� ��������� �����Ǿ����ϴ�.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findDNum() {
		System.out.println("\n�� ����� ��ȣ �˻�");

		try {
			System.out.print("�˻� �� ����� ��ȣ >> ");
			DeliveryDTO dto = dao.readDNum(br.readLine());
			if (dto == null) {
				System.out.println("�˻��Ͻ� ������ȣ�� �������� �ʽ��ϴ�.");
				return;
			}
			System.out.print("\n������ȣ\t��ǰ��ȣ\t���Ź�ȣ\t�������\t   �޴�����ȣ\t    ��۽��۳�¥\t��ۻ���\t��ۿϷᳯ¥\n");
			System.out.println("======================================================================================");
			System.out.print(dto.getdNum() + "\t");
			System.out.print(dto.getdPNum() + "\t");
			System.out.print(dto.getdHisNum() + "\t");
			System.out.print(dto.getdSector() + "\t");
			System.out.print(dto.getdManNum() + "\t");
			System.out.print(dto.getdDate() + "\t");
			System.out.print(dto.getdState() + "\t");
			System.out.print(dto.getdEndDate() + "\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void showAllDelivery() {
		System.out.println("\n�� ����̷� ��ü ����Ʈ");
		List<DeliveryDTO> list = dao.dAllList();
		System.out.print("������ȣ\t��ǰ��ȣ\t���Ź�ȣ\t�������\t    �޴�����ȣ\t  ��۽��۳�¥\t��ۻ���\t��ۿϷᳯ¥\t�ֹ���\n");
		System.out.println("=============================================================================================");
		for (DeliveryDTO dto : list) {
			System.out.print(dto.getdNum() + "\t");
			System.out.print(dto.getdPNum() + "\t");
			System.out.print(dto.getdHisNum() + "\t");
			System.out.print(dto.getdSector() + "\t");
			System.out.print(dto.getdManNum() + "\t");
			System.out.print(dto.getdDate() + "\t");
			System.out.print(dto.getdState() + "\t");
			System.out.print(dto.getdEndDate() + "\t");
			System.out.print(dto.getdGetPerson() + "\n");
		}
	}

	public void showStateDelivery() {
		int n;
		String state;
		try {
			System.out.print("� ������ ����̷��� ���ðڽ��ϱ�? [1.����� 2.��ۿϷ�] >> ");
			n = Integer.parseInt(br.readLine());
			if (n == 1) {
				state = "�����";
			} else if (n == 2) {
				state = "��ۿϷ�";
			} else {
				System.out.println("�߸��Է��ϼ˽��ϴ�.");
				return;
			}

			List<DeliveryDTO> list = dao.dStateList(state);
			System.out.print("\n������ȣ\t��ǰ��ȣ\t���Ź�ȣ\t�������\\t    �޴�����ȣ\\t  ��۽��۳�¥\t��ۻ���\t��ۿϷᳯ¥\n");
			System.out.println("========================================================================");
			for (DeliveryDTO dto : list) {
				System.out.print(dto.getdNum() + "\t");
				System.out.print(dto.getdPNum() + "\t");
				System.out.print(dto.getdHisNum() + "\t");
				System.out.print(dto.getdSector() + "\t");
				System.out.print(dto.getdManNum() + "\t");
				System.out.print(dto.getdDate() + "\t");
				System.out.print(dto.getdState() + "\t");
				System.out.print(dto.getdEndDate() + "\n");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void createDelivery() {
		dao.create();
	}

	public void drop() {
		dao.drop();
	}
}
