package shoesMart_END;

import java.util.List;

public interface NoticeDAO {
	public int insertWriting(NoticeDTO dto);

	public int deleteWriting(String read, String pwd);

	public int updateWriting(NoticeDTO dto);

	public List<NoticeDTO> listWriting();

	public List<NoticeDTO> adminList();

	public NoticeDTO read(String noticeNum);
}
