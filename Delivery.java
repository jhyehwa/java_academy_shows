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
		System.out.println("\n▶ 배송 정보 추가");
		DeliveryDTO dto = new DeliveryDTO();
		try {
			System.out.print("운송장번호 >> ");
			String dNum = br.readLine();
			DeliveryDTO ddto = dao.readDNum(dNum);
			if (ddto != null) {
				System.out.println("입력하신 운송장 번호는 이미 존재합니다.");
				return;
			}
			dto.setdNum(dNum);

			history.viewHisAll();

			System.out.print("\n거래 번호 >> ");
			int dPNum = Integer.parseInt(br.readLine());
			HistoryDTO hdto = his.viewNumber(dPNum);
			if (hdto == null) {
				System.out.println("입력하신 거래정보는 존재하지 않습니다.");
				return;
			}

			if (hdto.getHisdstate().equals("배송중")) {
				System.out.println("해당 상품은 이미 배송중입니다.");
				return;
			} else if (hdto.getHisdstate().equals("배송완료")) {
				System.out.println("해당 상품은 이미 배송완료 되었습니다.");
				return;
			}
			dto.setdHisNum(dPNum);

			dto.setdPNum(hdto.getHisPNumber());

			System.out.print("배송지 >> ");
			dto.setdSector(br.readLine());

			System.out.print("배송 기사 전화번호 >> ");
			dto.setdManNum(br.readLine());

			System.out.print("배송 보낸 날짜 >> ");
			dto.setdDate(br.readLine());

			int result = dao.insert(dto);
			System.out.println(result + "건의 배송정보가 추가되었습니다.");

		} catch (NumberFormatException e) {
			System.out.println("정확한 값을 입력해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDelivery() {
		String dNum;
		char c;
		try {
			System.out.print("운송장 번호 >> ");
			dNum = br.readLine();
			DeliveryDTO dto = dao.readDNum(dNum);
			if (dto == null) {
				System.out.println("검색하신 운송장 번호는 존재하지 않습니다.");
				return;
			}

			System.out.printf("%s의 물품이 배송 완료되었습니까? [Y/N] : ", dNum);
			c = br.readLine().charAt(0);
			if (c == 'Y' || c == 'y') {
				if (dto.getdState().equals("배송완료")) {
					System.out.println("이미 배송완료 처리된 상품입니다.");
					return;
				}
				dto.setdState("배송완료");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				String time1 = sdf.format(time);
				dto.setdEndDate(time1);
			} else if (c == 'N' || c == 'n') {
				return;
			} else {
				System.out.println("잘못입력하셨습니다.");
				return;
			}
			int result = dao.update(dto);
			System.out.println(result + "건의 배송정보가 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteDelivery() {
		System.out.println("\n▶ 배송 정보 삭제");
		try {
			System.out.print("삭제할 운송장 번호 >> ");
			String dNum = br.readLine();
			DeliveryDTO dto = dao.readDNum(dNum);
			if (dto.getdState().equals("배송완료")) {
				System.out.println("배송완료된 건은 삭제가 불가능합니다.");
				return;
			}
			int result = dao.delete(dNum);
			System.out.println(result + "건의 배송정보가 삭제되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findDNum() {
		System.out.println("\n▶ 운송장 번호 검색");

		try {
			System.out.print("검색 할 운송장 번호 >> ");
			DeliveryDTO dto = dao.readDNum(br.readLine());
			if (dto == null) {
				System.out.println("검색하신 운송장번호는 존재하지 않습니다.");
				return;
			}
			System.out.print("\n운송장번호\t물품번호\t구매번호\t배송지역\t   휴대폰번호\t    배송시작날짜\t배송상태\t배송완료날짜\n");
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
		System.out.println("\n▶ 배송이력 전체 리스트");
		List<DeliveryDTO> list = dao.dAllList();
		System.out.print("운송장번호\t물품번호\t구매번호\t배송지역\t    휴대폰번호\t  배송시작날짜\t배송상태\t배송완료날짜\t주문자\n");
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
			System.out.print("어떤 상태의 배송이력을 보시겠습니까? [1.배송중 2.배송완료] >> ");
			n = Integer.parseInt(br.readLine());
			if (n == 1) {
				state = "배송중";
			} else if (n == 2) {
				state = "배송완료";
			} else {
				System.out.println("잘못입력하셧습니다.");
				return;
			}

			List<DeliveryDTO> list = dao.dStateList(state);
			System.out.print("\n운송장번호\t물품번호\t구매번호\t배송지역\\t    휴대폰번호\\t  배송시작날짜\t배송상태\t배송완료날짜\n");
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
