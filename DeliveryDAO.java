package shoesMart_END;

import java.util.List;

public interface DeliveryDAO {
	public int insert(DeliveryDTO dto);

	public int delete(String dNum);

	public int update(DeliveryDTO dto);

	public DeliveryDTO readDNum(String dNum);

	public List<DeliveryDTO> dAllList();

	public List<DeliveryDTO> dStateList(String state);

	public void create();

	public void drop();

}
