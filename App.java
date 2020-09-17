package shoesMart_END;

public class App {
	public static void main(String[] args) {

		Menu menu = new Menu();
		int ch;
		MemberDTO loginMember = null;

		System.out.println(">> .인터넷 쇼핑.  <<");
		try {
			while (true) {
				loginMember = menu.longinMember();
				if (loginMember == null) {
					ch = menu.main();
					if (ch == 3) {
						break;
					}

				} else if (loginMember.getId().equals("admin")) {
					menu.adminMenu();
				} else {
					menu.memberMenu();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
