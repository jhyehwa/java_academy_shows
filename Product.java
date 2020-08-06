package shoesMart_END;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Product {
	private ProductDAO dao = new ProductDAOImpl();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void listProduct() {
		System.out.println("\n▶ 전체리스트\n");
		System.out.println("상품 번호\t\t상품 이름\t\t상품 개수\t\t상품 가격");
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
		System.out.println("\n▶ 상품 등록");

		ProductDTO dto = new ProductDTO();
		String pNum;

		try {
			System.out.print("상품 번호 >> ");
			pNum = br.readLine();

			// 제품번호 중복확인
			if (dao.readProduct(pNum) != null) {
				System.out.println("\n동일한 상품 번호가 존재합니다.\n");
				return;
			}

			dto.setpNum(pNum);

			System.out.print("상품 이름 >> ");
			dto.setpName(br.readLine());

			System.out.print("상품 개수 >> ");
			dto.setpCnt(Integer.parseInt(br.readLine()));

			System.out.print("상품 가격 >> ");
			dto.setpPrice(Integer.parseInt(br.readLine()));

			int result = dao.insertProduct(dto);

			if (result != 0) {
				System.out.println("\n상품이 등록되었습니다.");
			} else {
				System.out.println("\n상품 등록을 실패하였습니다.");
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProduct() {
		System.out.println("\n▶ 상품 수정");

		String pNum;
		ProductDTO dto = new ProductDTO();

		try {
			System.out.print("수정 할 상품 번호 >> ");
			pNum = br.readLine();
			ProductDTO pdto = dao.readProduct(pNum);
			if (pdto == null) {
				System.out.println("\n상품 번호가 존재하지 않습니다.\n");
				return;
			}

			dto.setpNum(pNum);

			System.out.print("수정 할 상품 이름 [수정안하시려면 N/n 입력] >> ");
			String name = br.readLine();
			if (name.equals("N") || name.equals("n")) {
				dto.setpName(pdto.getpName());
			} else {
				dto.setpName(name);
			}

			System.out.print("수정 할 상품 개수 [수정안하시려면 N/n 입력] >> ");
			String s = br.readLine();
			if (s.equals("N") || s.equals("n")) {
				dto.setpCnt(pdto.getpCnt());
			} else {
				int n1 = Integer.parseInt(s);
				dto.setpCnt(n1);
			}

			System.out.print("수정 할 상품 가격 [수정안하시려면 N/n 입력] >> ");
			String ss = br.readLine();
			if (ss.equals("N") || ss.equals("n")) {
				dto.setpPrice(pdto.getpPrice());
			} else {
				int n2 = Integer.parseInt(ss);
				dto.setpCnt(n2);
			}

			int result = dao.updateProduct(dto);

			if (result != 0) {
				System.out.println("\n상품 수정이 완료 되었습니다.\n");
			} else {
				System.out.println("\n해당 상품 번호가 존재하지 않습니다.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("\n숫자만 입력가능합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProduct() {
		System.out.println("\n▶ 데이터 삭제");

		String pNum;

		try {
			System.out.print("삭제 할 상품 번호 >> ");
			pNum = br.readLine();

			int result = dao.deleteProduct(pNum);

			if (result == 0) {
				System.out.println("\n상품 번호가 존재하지 않아 상품 삭제를 실패하였습니다.\n");
			} else {
				System.out.println("\n상품 삭제가 완료되었습니다.\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findByName() {
		System.out.println("\n▶ 상품이름검색");

		String pName;

		try {
			System.out.print("검색 할 상품이름 >> ");
			pName = br.readLine();

			List<ProductDTO> list = dao.listProduct(pName);

			if (list == null) {
				System.out.println("\n등록된 상품이 없습니다.\n");
				return;
			}

			System.out.println("상품번호\t\t상품이름\t\t상품개수\t\t상품가격");
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
		System.out.println("\n▶ 상품번호검색");

		String pNum;

		try {
			System.out.print("검색 할 상품번호 >> ");
			pNum = br.readLine();

			ProductDTO dto = dao.readProduct(pNum);

			if (dto == null) {
				System.out.println("\n등록된 상품이 없습니다.\n");
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
			System.out.println("\n구매 할 수 없습니다.");
			return result;
		}

		dto2.setpCnt(cnt);

		result = dao.updateProduct(dto2);
		return result;
	}

}