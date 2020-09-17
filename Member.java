// ����
// �����ϱ� ���� ������ �ִ� Ŭ����
package shoesMart_END;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Member {
	private Scanner sc = new Scanner(System.in);
	private MemberDAO dao = new MemberDAOImpl(); // ���� �θ� ��
	private MemberDTO loginMember;

	public MemberDTO loginMember() {
		return loginMember;
	}

	public void login() {
		System.out.print("ID >> ");
		try {

			String id = sc.next();
			MemberDTO dto = dao.readMember(id); // id�� �ִ��� Ȯ���ϴ� �� �׸��� MemberDTO dto�� ������ �־���

			System.out.print("PW >> ");
			String pwd = sc.next();
			if (pwd.equals(dto.getPwd()) == false) {
				System.out.println("������ ��ġ���� �ʽ��ϴ�.");
				return;
			}
			loginMember = dto;
		} catch (Exception e) {
			System.out.println("��Ȯ�� ���� �Է����ּ���.");
		}

	}

	public void insert() {
		System.out.println("\n�� ȸ������");
		MemberDTO dto = new MemberDTO();
		try {
			System.out.print("ID >> ");
			dto.setId(sc.next());

			System.out.print("Pw >> ");
			dto.setPwd(sc.next());

			System.out.print("Name >> ");
			dto.setName(sc.next());

			System.out.print("Email >>");
			dto.setEmail(sc.next());

			System.out.print("Adress >> ");
			dto.setSetor(sc.next());

			System.out.print("Tel >> ");
			dto.setTel(sc.next());

			System.out.print("BirthDay >> ");
			dto.setBirth(sc.next());
			dao.insertMember(dto); // = result ���� �ȴ�.

		} catch (Exception e) {
			System.out.println("��Ȯ�� ���� �Է����ּ���.");
		}
	}

	public void update(MemberDTO up) {
		System.out.println("\n�� ȸ����������");

		MemberDTO dto = new MemberDTO();

		try {
			MemberDTO mdto = dao.readMember(up.getId());

			System.out.print("���� ��й�ȣ >> ");
			String pwd = sc.next();
			if (pwd.equals(mdto.getPwd()) == false) {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				return;
			}
			dto.setId(up.getId());
			System.out.print("Pw >> ");
			dto.setPwd(sc.next());

			System.out.print("Name >> ");
			dto.setName(sc.next());

			System.out.print("Email >> ");
			dto.setEmail(sc.next());

			System.out.print("BirthDay >> ");
			dto.setBirth(sc.next());

			System.out.print("Adress >> ");
			dto.setSetor(sc.next());

			System.out.print("Tel >> ");
			dto.setTel(sc.next());

			int result = dao.updateMember(dto);
			System.out.println(result + "���� �����Ǿ����ϴ�. \n");

		} catch (Exception e) {
			System.out.println("��Ȯ�� ���� �Է����ּ���.");
		}
	}

	public int delete(MemberDTO up) {
		int result = 0;
		MemberDTO mdto = dao.readMember(up.getId());
		System.out.print("���� ��й�ȣ >> ");
		String pwd = sc.next();
		if (pwd.equals(mdto.getPwd()) == false) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			return result;
		}
		result = dao.deleteMember(mdto);

		System.out.println(result + "���� ȸ��Ż�� �Ϸ�");

		return result;
	}

	public void findById() {
		System.out.println("\n�� ID Search");

		String id;
		System.out.print("Search ID >> ");
		id = sc.next();

		MemberDTO dto = dao.readMember(id);
		if (dto == null) {
			System.out.print("��ϵ� ������ �����ϴ�.");
			return;
		}

		System.out.println("ID\tPWD\tNAME\tEMAIL\tBIRTH\tSECTOR\tTLE");
		System.out.print(dto.getId() + "\t");
		System.out.print(dto.getPwd() + "\t");
		System.out.print(dto.getName() + "\t");
		System.out.print(dto.getEmail() + "\t");
		System.out.print(dto.getBirth() + "\t");
		System.out.print(dto.getSetor() + "\t");
		System.out.print(dto.getTel() + "\n");

		System.out.println();
	}

	public void findByMy(MemberDTO up) {

		MemberDTO dto = dao.readMember(up.getId());

		System.out.println("ID\tPWD\tNAME\tEMAIL\t   BIRTH\tSECTOR\tTLE");
		System.out.println("============================================================");
		System.out.print(dto.getId() + "\t");
		System.out.print(dto.getPwd() + "\t");
		System.out.print(dto.getName() + "\t");
		System.out.print(dto.getEmail() + "\t");
		System.out.print(dto.getBirth() + "\t");
		System.out.print(dto.getSetor() + "\t");
		System.out.print(dto.getTel() + "\n");

		System.out.println();
	}

	public void findByName() {

		String name;
		System.out.print("\n Search Name >> ");
		name = sc.next();

		System.out.println("ID\tPWD\tNAME\tEMAIL\tBIRTH\tSECTOR\tTLE");
		List<MemberDTO> list = dao.listMember(name);
		for (MemberDTO dto : list) {
			System.out.print(dto.getId() + "\t");
			System.out.print(dto.getPwd() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getEmail() + "\t");
			System.out.print(dto.getBirth() + "\t");
			System.out.print(dto.getSetor() + "\t");
			System.out.print(dto.getTel() + "\n");

		}
		System.out.println();

	}

	public void listAll() {
		System.out.println("\n�� ȸ�� ��ü ����Ʈ ��ȸ");

		List<MemberDTO> list = dao.listMember();
		Iterator<MemberDTO> it = list.iterator();

		System.out.println("ID\tNAME\tEMAIL\t   BIRTH\tSECTOR\t  TEL");
		System.out.println("===========================================================");
		while (it.hasNext()) {
			MemberDTO dto = it.next();

			System.out.print(dto.getId() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getEmail() + "\t");
			System.out.print(dto.getBirth() + "\t");
			System.out.print(dto.getSetor() + "\t");
			System.out.print(dto.getTel() + "\n");
		}
		System.out.println();
	}

	public void logout() {
		loginMember = null;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.\n");
	}

}
