package shoesMart_END;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Notice {
	private Scanner sc = new Scanner(System.in);
	private NoticeDAO dao = new NoticeImpl();
	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void insertWriting(MemberDTO id) {
		System.out.println("\n�� �Խñ� ���");

		try {
			String date = sdp.format(cal.getTime());

			NoticeDTO dto = new NoticeDTO();

			dto.setNoticeMemId(id.getId());

			System.out.print("�Խñ� ���� >> ");
			dto.setNoticeTitle(br.readLine());

			System.out.print("�Խñ� ���� >> ");
			dto.setNoticePosts(br.readLine());

			dto.setNoticeDate(date);

			System.out.print("�Խñ� ��й�ȣ >> ");
			dto.setNoticePwd(br.readLine());
			int result = dao.insertWriting(dto);

			if (result == 1) {
				System.out.println("�Խñ��� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("�Խñ� ����� �����Ͽ����ϴ�.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateWriting() {
		System.out.println("\n�� �Խñ� ����");
		String date = sdp.format(cal.getTime());
		try {

			String num, pwd;

			System.out.print("�Խñ� ��ȣ >> ");
			num = sc.next();

			NoticeDTO dto = dao.read(num);
			if (dto == null) {
				System.out.println("������ �ù����� �ʽ��ϴ�.");
				return;
			}

			System.out.print("�Խñ� ��й�ȣ >> ");
			pwd = sc.next();

			if (!dto.getNoticePwd().equals(pwd)) {
				System.out.println("�Խñ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return;
			}

			System.out.print("\n������ �Խñ� ���� [�������Ͻ÷��� N/n] >> ");
			String s = br.readLine();
			if (s.equals("n") || s.equals("N")) {
				dto.setNoticeTitle(dto.getNoticeTitle());
			} else {
				dto.setNoticeTitle(s);

			}

			System.out.print("���� �� �Խñ� ���� [���� ���Ͻ÷��� N/n] >> ");
			String ss = br.readLine();
			if (ss.equals("n") || ss.equals("N")) {
				dto.setNoticePosts(dto.getNoticePosts());
			} else {
				dto.setNoticePosts(ss);
			}

			dto.setNoticeDate(date);

			int result = dao.updateWriting(dto);

			if (result == 1) {
				System.out.println("�Խñ��� �����Ǿ����ϴ�.");
			} else {
				System.out.println("�Խñ� ���� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteWriting() {
		System.out.println("\n�� �Խñ� ����");

		String num, pwd;

		System.out.print("�Խñ� ��ȣ >> ");
		num = sc.next();

		NoticeDTO dto = dao.read(num);
		if (dto == null) {
			System.out.println("������ �ùٸ��� �ʽ��ϴ�.");
			return;
		}

		System.out.print("�Խñ� ��й�ȣ >> ");
		pwd = sc.next();

		if (!dto.getNoticePwd().equals(pwd)) {
			System.out.println("�Խñ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return;
		}

		int result = dao.deleteWriting(num, pwd);

		if (result == 1) {
			System.out.println("�Խñ��� �����Ǿ����ϴ�.");
		} else {
			System.out.println("�Խñ� ������ �����Ͽ����ϴ�.");
		}

	}

	public void listWriting() {
		System.out.println("\n�� �Խñ� ��ü��� Ȯ��");

		try {
			System.out.print("��ȣ\t�ۼ���\t����\t�Խó�¥\n");
			System.out.println("======================================");
			List<NoticeDTO> list = dao.listWriting();
			Iterator<NoticeDTO> it = list.iterator();
			while (it.hasNext()) {
				NoticeDTO dto = it.next();
				System.out.print(dto.getNoticeNum() + "\t");
				System.out.print(dto.getNoticeMemId() + "\t");
				System.out.print(dto.getNoticeTitle() + "\t");
				System.out.print(dto.getNoticeDate() + "\n");
			}

			System.out.print("\n��ȸ�� �Խñ� ��ȣ [�ڷΰ��÷��� 2�� �Է�] >> ");
			String num = br.readLine();
			if (num.equals("2")) {
				return;
			}

			NoticeDTO dto = dao.read(num);

			System.out.println("\n�Խñ� ����    \t�Խñ� ����");
			System.out.println("================================");
			System.out.print(dto.getNoticeTitle() + "\t");
			System.out.println(dto.getNoticePosts());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void adminNList() {
		System.out.println("\n�� �Խñ� ��ü ���(�����ڿ�)");
		try {
			List<NoticeDTO> list = dao.adminList();
			System.out.print("��ȣ\t�ۼ���\t����\t    �Խó�¥  \t�Խñۺ�й�ȣ\n");
			System.out.println("==================================================");
			for (NoticeDTO dto : list) {
				System.out.print(dto.getNoticeNum() + "\t");
				System.out.print(dto.getNoticeMemId() + "\t");
				System.out.print(dto.getNoticeTitle() + "\t");
				System.out.print(dto.getNoticeDate() + "\t");
				System.out.print(dto.getNoticePwd() + "\n");

			}
			System.out.print("\n��ȸ �� �Խñ� ��ȣ [�ڷΰ��÷��� 2�� �Է�] >> ");
			String num = br.readLine();
			if (num.equals("2")) {
				return;
			}

			NoticeDTO ndto = dao.read(num);
			if (ndto == null) {
				System.out.println("�ش��ȣ�� �Խñ��� �������� �ʽ��ϴ�.");
				return;
			}
			System.out.print("\n����\t\t����\n");
			System.out.print(ndto.getNoticeTitle() + "\t");
			System.out.println(ndto.getNoticePosts());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
