package shoesMart_END;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class ProductDAOImpl implements ProductDAO {
	private Connection conn = DBConn.getConnection();

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 전체제품 조회
	@Override
	public List<ProductDTO> listProduct() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT pNum, pName, pCnt, pPrice ");
			sb.append("FROM product ");
			sb.append("ORDER BY pNum ASC ");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery(sb.toString());

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();

				dto.setpNum(rs.getString("pNum"));
				dto.setpName(rs.getString("pName"));
				dto.setpCnt(rs.getInt("pCnt"));
				dto.setpPrice(rs.getInt("pPrice"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {

				}

				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e2) {

					}
				}
			}
		}
		return list;
	}

	// 제품 추가
	@Override
	public int insertProduct(ProductDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "INSERT INTO product(pNum, pName, pCnt, pPrice) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getpNum());
			pstmt.setString(2, dto.getpName());
			pstmt.setInt(3, dto.getpCnt());
			pstmt.setInt(4, dto.getpPrice());

			pstmt.executeUpdate();

			conn.commit();

			result = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		return result;
	}

	// 제품 수정
	@Override
	public int updateProduct(ProductDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			sql = "UPDATE product SET pName = ?, pCnt = ?, pPrice = ? WHERE pNum = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getpName());
			pstmt.setInt(2, dto.getpCnt());
			pstmt.setInt(3, dto.getpPrice());
			pstmt.setString(4, dto.getpNum());

			pstmt.executeUpdate();

			conn.commit();

			result = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	// 제품 삭제
	@Override
	public int deleteProduct(String pNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "DELETE FROM product WHERE pNum = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pNum);

			result = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {

			}
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		return result;
	}

	// 제품이름조회
	@Override
	public List<ProductDTO> listProduct(String pName) {
		ProductDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		List<ProductDTO> list = null;
		sb.append("SELECT pNum, pName, pCnt, pPrice ");
		sb.append("FROM product ");
		sb.append("WHERE pName LIKE ? || '%' ");

		try {
			list = new ArrayList<ProductDTO>();
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, pName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new ProductDTO();

				dto.setpNum(rs.getString("pNum"));
				dto.setpName(rs.getString("pName"));
				dto.setpCnt(rs.getInt("pCnt"));
				dto.setpPrice(rs.getInt("pPrice"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return list;
	}

	// 제품번호로 조회
	@Override
	public ProductDTO readProduct(String pNum) {
		ProductDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT pNum, pName, pCnt, pPrice ");
		sb.append("FROM product ");
		sb.append("WHERE pNum LIKE ? || '%' ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, pNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ProductDTO();

				dto.setpNum(rs.getString("pNum"));
				dto.setpName(rs.getString("pName"));
				dto.setpCnt(rs.getInt("pCnt"));
				dto.setpPrice(rs.getInt("pPrice"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return dto;
	}

	@Override
	public ProductDTO readProductOne(String pNum) {
		ProductDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT pNum, pName, pCnt, pPrice ");
		sb.append("FROM product ");
		sb.append("WHERE pNum = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, pNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ProductDTO();

				dto.setpNum(rs.getString("pNum"));
				dto.setpName(rs.getString("pName"));
				dto.setpCnt(rs.getInt("pCnt"));
				dto.setpPrice(rs.getInt("pPrice"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return dto;
	}
}
