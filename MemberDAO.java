// ¼¼¹Ì
package shoesMart_END;

import java.util.List;

public interface MemberDAO {
	public int insertMember(MemberDTO dto);
	public int updateMember(MemberDTO dto);
	public int deleteMember(MemberDTO dto);
	
	public MemberDTO readMember(String id);

	public List<MemberDTO> listMember();
	public List<MemberDTO> listMember(String name);

}
