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
				System.out.println("\n�� ���� �޴�");
				System.out.print("1.�α���  2.ȸ������  3.���� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ch;

	}

	public void memberMenu() {
		int ch;

		try {

			do {
				System.out.println("\n�� ȸ�� �޴�");
				System.out.print("1.��ǰ��ȸ  2.����  3.ȸ������  4.ȸ��Ż��  5.���ų�����ȸ  6.�Խ���  7.����������ȸ 8.�α׾ƿ� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void noticeMenu() {
		int ch;

		try {

			while (true) {

				do {
					System.out.println("\n�� �Խ��Ǹ޴�");
					System.out.print("1.�۾���  2.����ȸ  3.�ۻ���  4.�ۼ���  5.�ڷΰ��� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void adminMenu() {
		int ch;
		try {

			do {
				System.out.println("\n�� �����޴�");
				System.out.print("1.��ǰ����  2.ȸ����ȸ  3.�Խ��Ǹ޴�  4.��۸޴�  5.�α׾ƿ� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deliveryMenu() {
		int ch;
		try {

			while (true) {

				do {
					System.out.println("\n�� ��۸޴�");
					System.out.print("1.����߰�  2.��ۻ���  3.��ۼ���  4.������ȣȮ��  5.��۸���ƮȮ��  6.������θ���ƮȮ��  7.�ڷΰ��� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void noticeAdminMenu() {
		int ch;
		try {

			while (true) {

				do {
					System.out.println("\n�� �Խ��Ǹ޴�(�����ڿ�)");
					System.out.print("1.�۾���  2.����ȸ  3.�ۻ���  4.�ۼ���  5.�ڷΰ��� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void productMenu() {
		int ch;
		try {

			while (true) {

				System.out.println("\n�� ��ǰ ���� �޴�");

				do {
					System.out.print("1.��ǰ����Ʈ 2.��ǰ��� 3.��ǰ���� 4.��ǰ���� 5.��ǰ��ȣ�˻� 6.��ǰ�̸��˻� 7.�������� �� ");
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
			System.out.println("���ڸ� �Է°��� �մϴ� �ٽ� �Է����ּ���");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
