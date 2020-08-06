package shoesMart_END;

import java.util.List;

public interface HistoryDAO {
	public int insertHis(MemberDTO mdto, ProductDTO pdto, String date);

	public int deleteHis(int number);

	public HistoryDTO viewNumber(int number);

	public List<HistoryDTO> viewMember(String id);

	public List<HistoryDTO> viewAll();
}
