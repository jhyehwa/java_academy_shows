package shoesMart_END;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private Member member = new Member();
	private Delivery delivery = new Delivery();
	private Notice notice = new Notice();
	private History history = new History();
	private Product product = new Product();

	public MemberDTO longinMember() {
		return member.loginMember();
	}

	public int main() {
		int ch = 0;
		try {
			do {
				System.out.println("\n▶ 메인 메뉴");
				System.out.print("1.로그인  2.회원가입  3.종료 → ");
				ch = sc.nextInt();

			} while (ch < 1 || ch > 3);

			switch (ch) {
			case 1:
				member.login();

				break;
			case 2:
				member.insert();
				break;

			}

		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ch;

	}

	public void memberMenu() {
		int ch;

		try {

			do {
				System.out.println("\n▶ 회원 메뉴");
				System.out.print("1.상품조회  2.구매  3.회원수정  4.회원탈퇴  5.구매내역조회  6.게시판  7.본인정보조회 8.로그아웃 → ");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 8);

			switch (ch) {
			case 1:
				product.listProduct();
				break;
			case 2:
				history.buy(longinMember());
				break;
			case 3:
				member.update(longinMember());
				break;
			case 4:
				if (member.delete(longinMember()) != 0) {
					member.logout();
				}
				break;
			case 5:
				history.viewHisId(longinMember().getId());
				break;
			case 6:
				noticeMenu();
				break;
			case 7:

				member.findByMy(longinMember());
				break;

			case 8:
				member.logout();
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void noticeMenu() {
		int ch;

		try {

			while (true) {

				do {
					System.out.println("\n▶ 게시판메뉴");
					System.out.print("1.글쓰기  2.글조회  3.글삭제  4.글수정  5.뒤로가기 → ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 5);

				switch (ch) {
				case 1:
					notice.insertWriting(longinMember());
					break;
				case 2:
					notice.listWriting();
					break;
				case 3:
					notice.deleteWriting();
					break;
				case 4:
					notice.updateWriting();
					break;

				}

				if (ch == 5) {
					break;
				}
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void adminMenu() {
		int ch;
		try {

			do {
				System.out.println("\n▶ 관리메뉴");
				System.out.print("1.상품관리  2.회원조회  3.게시판메뉴  4.배송메뉴  5.로그아웃 → ");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 5);

			switch (ch) {
			case 1:
				productMenu();
				break;
			case 2:
				member.listAll();
				break;
			case 3:
				noticeAdminMenu();
				break;
			case 4:
				deliveryMenu();
				break;
			case 5:
				member.logout();
				break;
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deliveryMenu() {
		int ch;
		try {

			while (true) {

				do {
					System.out.println("\n▶ 배송메뉴");
					System.out.print("1.배송추가  2.배송삭제  3.배송수정  4.운송장번호확인  5.배송리스트확인  6.배송중인리스트확인  7.뒤로가기 → ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 7);

				switch (ch) {
				case 1:
					delivery.inputDelivery();
					break;
				case 2:
					delivery.deleteDelivery();
					break;
				case 3:
					delivery.updateDelivery();
					break;
				case 4:
					delivery.findDNum();
					break;
				case 5:
					delivery.showAllDelivery();
					break;
				case 6:
					delivery.showStateDelivery();
					break;
				}

				if (ch == 7) {
					break;
				}
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void noticeAdminMenu() {
		int ch;
		try {

			while (true) {

				do {
					System.out.println("\n▶ 게시판메뉴(관리자용)");
					System.out.print("1.글쓰기  2.글조회  3.글삭제  4.글수정  5.뒤로가기 → ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 5);

				switch (ch) {
				case 1:
					notice.insertWriting(longinMember());
					break;
				case 2:
					notice.adminNList();
					break;
				case 3:
					notice.deleteWriting();
					break;
				case 4:
					notice.updateWriting();
					break;

				}

				if (ch == 5) {
					break;
				}
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void productMenu() {
		int ch;
		try {

			while (true) {

				System.out.println("\n▶ 상품 관리 메뉴");

				do {
					System.out.print("1.상품리스트 2.상품등록 3.상품수정 4.상품삭제 5.상품번호검색 6.상품이름검색 7.이전으로 → ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 7);

				if (ch == 7) {
					break;
				}

				switch (ch) {
				case 1:
					product.listProduct();
					break;

				case 2:
					product.insertProduct();
					break;

				case 3:
					product.updateProduct();
					break;

				case 4:
					product.deleteProduct();
					break;

				case 5:
					product.findById();
					break;

				case 6:
					product.findByName();
				}
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자만 입력가능 합니다 다시 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
