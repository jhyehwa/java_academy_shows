package shoesMart_END;

import java.util.List;

public interface ProductDAO {
	public int insertProduct(ProductDTO dto);

	public int updateProduct(ProductDTO dto);

	public int deleteProduct(String pNum);

	public ProductDTO readProduct(String pNum);

	public ProductDTO readProductOne(String pNum);

	public List<ProductDTO> listProduct();

	public List<ProductDTO> listProduct(String pName);
}
