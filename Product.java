package shoesMart_END;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Product {
	private ProductDAO dao = new ProductDAOImpl();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void listProduct() {
		System.out.println("\n�� ��ü����Ʈ\n");
		System.out.println("��ǰ ��ȣ\t\t��ǰ �̸�\t\t��ǰ ����\t\t��ǰ ����");
		System.out.println("========================================================");

		List<ProductDTO> list = dao.listProduct();

		for (ProductDTO dto : list) {
			System.out.print(dto.getpNum() + "\t\t");
			System.out.print(dto.getpName() + " \t\t");
			System.out.print(dto.getpCnt() + "\t\t");
			System.out.print(dto.getpPrice() + "\n");
		}
		System.out.println();
	}

	public void insertProduct() {
		System.out.println("\n�� ��ǰ ���");

		ProductDTO dto = new ProductDTO();
		String pNum;

		try {
			System.out.print("��ǰ ��ȣ >> ");
			pNum = br.readLine();

			// ��ǰ��ȣ �ߺ�Ȯ��
			if (dao.readProduct(pNum) != null) {
				System.out.println("\n������ ��ǰ ��ȣ�� �����մϴ�.\n");
				return;
			}

			dto.setpNum(pNum);

			System.out.print("��ǰ �̸� >> ");
			dto.setpName(br.readLine());

			System.out.print("��ǰ ���� >> ");
			dto.setpCnt(Integer.parseInt(br.readLine()));

			System.out.print("��ǰ ���� >> ");
			dto.setpPrice(Integer.parseInt(br.readLine()));

			int result = dao.insertProduct(dto);

			if (result != 0) {
				System.out.println("\n��ǰ�� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("\n��ǰ ����� �����Ͽ����ϴ�.");
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProduct() {
		System.out.println("\n�� ��ǰ ����");

		String pNum;
		ProductDTO dto = new ProductDTO();

		try {
			System.out.print("���� �� ��ǰ ��ȣ >> ");
			pNum = br.readLine();
			ProductDTO pdto = dao.readProduct(pNum);
			if (pdto == null) {
				System.out.println("\n��ǰ ��ȣ�� �������� �ʽ��ϴ�.\n");
				return;
			}

			dto.setpNum(pNum);

			System.out.print("���� �� ��ǰ �̸� [�������Ͻ÷��� N/n �Է�] >> ");
			String name = br.readLine();
			if (name.equals("N") || name.equals("n")) {
				dto.setpName(pdto.getpName());
			} else {
				dto.setpName(name);
			}

			System.out.print("���� �� ��ǰ ���� [�������Ͻ÷��� N/n �Է�] >> ");
			String s = br.readLine();
			if (s.equals("N") || s.equals("n")) {
				dto.setpCnt(pdto.getpCnt());
			} else {
				int n1 = Integer.parseInt(s);
				dto.setpCnt(n1);
			}

			System.out.print("���� �� ��ǰ ���� [�������Ͻ÷��� N/n �Է�] >> ");
			String ss = br.readLine();
			if (ss.equals("N") || ss.equals("n")) {
				dto.setpPrice(pdto.getpPrice());
			} else {
				int n2 = Integer.parseInt(ss);
				dto.setpCnt(n2);
			}

			int result = dao.updateProduct(dto);

			if (result != 0) {
				System.out.println("\n��ǰ ������ �Ϸ� �Ǿ����ϴ�.\n");
			} else {
				System.out.println("\n�ش� ��ǰ ��ȣ�� �������� �ʽ��ϴ�.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("\n���ڸ� �Է°����մϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProduct() {
		System.out.println("\n�� ������ ����");

		String pNum;

		try {
			System.out.print("���� �� ��ǰ ��ȣ >> ");
			pNum = br.readLine();

			int result = dao.deleteProduct(pNum);

			if (result == 0) {
				System.out.println("\n��ǰ ��ȣ�� �������� �ʾ� ��ǰ ������ �����Ͽ����ϴ�.\n");
			} else {
				System.out.println("\n��ǰ ������ �Ϸ�Ǿ����ϴ�.\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findByName() {
		System.out.println("\n�� ��ǰ�̸��˻�");

		String pName;

		try {
			System.out.print("�˻� �� ��ǰ�̸� >> ");
			pName = br.readLine();

			List<ProductDTO> list = dao.listProduct(pName);

			if (list == null) {
				System.out.println("\n��ϵ� ��ǰ�� �����ϴ�.\n");
				return;
			}

			System.out.println("��ǰ��ȣ\t\t��ǰ�̸�\t\t��ǰ����\t\t��ǰ����");
			for (ProductDTO dto : list) {
				System.out.print(dto.getpNum() + "\t\t");
				System.out.print(dto.getpName() + "\t\t");
				System.out.print(dto.getpCnt() + "\t\t");
				System.out.print(dto.getpPrice() + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findById() {
		System.out.println("\n�� ��ǰ��ȣ�˻�");

		String pNum;

		try {
			System.out.print("�˻� �� ��ǰ��ȣ >> ");
			pNum = br.readLine();

			ProductDTO dto = dao.readProduct(pNum);

			if (dto == null) {
				System.out.println("\n��ϵ� ��ǰ�� �����ϴ�.\n");
				return;
			}

			System.out.print(dto.getpNum() + "\t\t");
			System.out.print(dto.getpName() + "\t\t");
			System.out.print(dto.getpCnt() + "\t\t");
			System.out.print(dto.getpPrice() + "\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int woo(ProductDTO dto) {
		ProductDTO dto2;
		int result = 0;

		dto2 = dao.readProduct(dto.getpNum());

		int cnt = dto2.getpCnt() - dto.getpCnt();

		if (dto2.getpCnt() == 0 || cnt < 0) {
			System.out.println("\n���� �� �� �����ϴ�.");
			return result;
		}

		dto2.setpCnt(cnt);

		result = dao.updateProduct(dto2);
		return result;
	}

}