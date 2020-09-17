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
		System.out.println("\n▶ 게시글 등록");

		try {
			String date = sdp.format(cal.getTime());

			NoticeDTO dto = new NoticeDTO();

			dto.setNoticeMemId(id.getId());

			System.out.print("게시글 제목 >> ");
			dto.setNoticeTitle(br.readLine());

			System.out.print("게시글 내용 >> ");
			dto.setNoticePosts(br.readLine());

			dto.setNoticeDate(date);

			System.out.print("게시글 비밀번호 >> ");
			dto.setNoticePwd(br.readLine());
			int result = dao.insertWriting(dto);

			if (result == 1) {
				System.out.println("게시글이 등록되었습니다.");
			} else {
				System.out.println("게시글 등록을 실패하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateWriting() {
		System.out.println("\n▶ 게시글 수정");
		String date = sdp.format(cal.getTime());
		try {

			String num, pwd;

			System.out.print("게시글 번호 >> ");
			num = sc.next();

			NoticeDTO dto = dao.read(num);
			if (dto == null) {
				System.out.println("정보가 올바지르 않습니다.");
				return;
			}

			System.out.print("게시글 비밀번호 >> ");
			pwd = sc.next();

			if (!dto.getNoticePwd().equals(pwd)) {
				System.out.println("게시글 비밀번호가 일치하지 않습니다.");
				return;
			}

			System.out.print("\n수정할 게시글 제목 [수정안하시려면 N/n] >> ");
			String s = br.readLine();
			if (s.equals("n") || s.equals("N")) {
				dto.setNoticeTitle(dto.getNoticeTitle());
			} else {
				dto.setNoticeTitle(s);

			}

			System.out.print("수정 할 게시글 내용 [수정 안하시려면 N/n] >> ");
			String ss = br.readLine();
			if (ss.equals("n") || ss.equals("N")) {
				dto.setNoticePosts(dto.getNoticePosts());
			} else {
				dto.setNoticePosts(ss);
			}

			dto.setNoticeDate(date);

			int result = dao.updateWriting(dto);

			if (result == 1) {
				System.out.println("게시글이 수정되었습니다.");
			} else {
				System.out.println("게시글 수정 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteWriting() {
		System.out.println("\n▶ 게시글 삭제");

		String num, pwd;

		System.out.print("게시글 번호 >> ");
		num = sc.next();

		NoticeDTO dto = dao.read(num);
		if (dto == null) {
			System.out.println("정보가 올바르지 않습니다.");
			return;
		}

		System.out.print("게시글 비밀번호 >> ");
		pwd = sc.next();

		if (!dto.getNoticePwd().equals(pwd)) {
			System.out.println("게시글 비밀번호가 일치하지 않습니다.");
			return;
		}

		int result = dao.deleteWriting(num, pwd);

		if (result == 1) {
			System.out.println("게시글이 삭제되었습니다.");
		} else {
			System.out.println("게시글 삭제를 실패하였습니다.");
		}

	}

	public void listWriting() {
		System.out.println("\n▶ 게시글 전체목록 확인");

		try {
			System.out.print("번호\t작성자\t제목\t게시날짜\n");
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

			System.out.print("\n조회할 게시글 번호 [뒤로가시려면 2를 입력] >> ");
			String num = br.readLine();
			if (num.equals("2")) {
				return;
			}

			NoticeDTO dto = dao.read(num);

			System.out.println("\n게시글 제목    \t게시글 내용");
			System.out.println("================================");
			System.out.print(dto.getNoticeTitle() + "\t");
			System.out.println(dto.getNoticePosts());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void adminNList() {
		System.out.println("\n▶ 게시글 전체 목록(관리자용)");
		try {
			List<NoticeDTO> list = dao.adminList();
			System.out.print("번호\t작성자\t제목\t    게시날짜  \t게시글비밀번호\n");
			System.out.println("==================================================");
			for (NoticeDTO dto : list) {
				System.out.print(dto.getNoticeNum() + "\t");
				System.out.print(dto.getNoticeMemId() + "\t");
				System.out.print(dto.getNoticeTitle() + "\t");
				System.out.print(dto.getNoticeDate() + "\t");
				System.out.print(dto.getNoticePwd() + "\n");

			}
			System.out.print("\n조회 할 게시글 번호 [뒤로가시려면 2를 입력] >> ");
			String num = br.readLine();
			if (num.equals("2")) {
				return;
			}

			NoticeDTO ndto = dao.read(num);
			if (ndto == null) {
				System.out.println("해당번호의 게시글은 존재하지 않습니다.");
				return;
			}
			System.out.print("\n제목\t\t내용\n");
			System.out.print(ndto.getNoticeTitle() + "\t");
			System.out.println(ndto.getNoticePosts());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
