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
		System.out.print("구매 할 제품 번호 >> ");
		String ch = sc.next();

		try {
			pdto = pdu.readProductOne(ch);
			if (pdto == null) {
				System.out.println("존재하지 않는 제품 번호입니다.");
				return;
			}
			System.out.println("\n물품번호\t   물품이름\t   가격\t재고");
			System.out.println("===============================");
			System.out.print(pdto.getpNum() + "\t");
			System.out.print(pdto.getpName() + "\t");
			System.out.print(pdto.getpPrice() + "\t");
			System.out.println(pdto.getpCnt());
			System.out.println("===============================");

			System.out.print("\n구매할 갯수를 입력하세요 >> ");

			cnt = sc.nextInt();
			if (cnt < 1) {
				System.out.println("1개 이상의 수를 입력해주세요.");
				return;
			}

			pdto.setpCnt(cnt); // 구입한 갯수 set

			if (pdu2.woo(pdto) == 0) { // 전체갯수 - 구입갯수
				return;
			}

			String date = sdp.format(cal.getTime()); // 구입한 시간 출력

			result = his.insertHis(mdto, pdto, date);

			System.out.println(result + "행 성공");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() { // 관리모드 거래내역 삭제
		int result;
		System.out.print("삭제 할 거래 번호 >> ");
		int ch = sc.nextInt();

		try {
			result = his.deleteHis(ch);
			if (result == 0) {
				System.out.println("삭제할 번호가 존재하지 않습니다.");
				return;
			}

			System.out.println(result + "개 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewHisNumber() {
		HistoryDTO hdto;
		int ch;
		System.out.println("조회 할 거래번호 >> ");
		ch = sc.nextInt();

		try {
			hdto = his.viewNumber(ch);
			if (hdto == null) {
				System.out.println("등록된 자료가 없습니다.\n");
				return;
			}
			System.out.println("거래번호\t거래한회원\t거래회원이메일\t제품번호\t구매날짜\t운송장번호\t배송상태");
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

	public void viewHisId(String id) { // 아이디를 입력받아 해당 회원 리스트 조회
		System.out.println("\n▶ 회원님의 ID 조회");

		try {
			// 로그인 id가 들어오면 객체 주입
			List<HistoryDTO> list = his.viewMember(id);

			if (list.size() == 0) {
				System.out.println("등록된 자료가 없습니다.\n");
				return;
			}
			System.out.println("거래번호\t아이디\t이메일\t제품번호\t   구매날짜\t\t운송장번호\t  배송상태");
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
		System.out.println("\n▶ 거래내역\n");

		try {
			List<HistoryDTO> list = his.viewAll();

			if (list.size() == 0) {
				System.out.println("등록된 자료가 없습니다.\n");
				return;
			}
			System.out.println("거래번호\t아이디\t이메일\t제품번호\t구매날짜\t운송장번호\t배송상태");
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
