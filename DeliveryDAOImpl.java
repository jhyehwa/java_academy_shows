package shoesMart_END;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class DeliveryDAOImpl implements DeliveryDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insert(DeliveryDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("INSERT INTO delivery(dNum, dPNum, dHisNum, dSector, dManNum, dDate) ");
			sb.append("VALUES(?,?,?,?,?,?)");
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, dto.getdNum());
			pstmt.setString(2, dto.getdPNum());
			pstmt.setInt(3, dto.getdHisNum());
			pstmt.setString(4, dto.getdSector());
			pstmt.setString(5, dto.getdManNum());
			pstmt.setString(6, dto.getdDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

		return result;
	}

	@Override
	public int delete(String dNum) {
		String sql;
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			sql = "DELETE FROM delivery WHERE dNum = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dNum);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	@Override
	public int update(DeliveryDTO dto) {
		int result = 0;
		String sql;
		PreparedStatement pstmt = null;
		try {
			sql = "UPDATE delivery SET dState = ?, dEndDate = ? WHERE dNum = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getdState());
			pstmt.setString(2, dto.getdEndDate());
			pstmt.setString(3, dto.getdNum());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {

			}
		}
		return result;
	}

	@Override
	public DeliveryDTO readDNum(String dNum) {
		DeliveryDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("SELECT dNum, dPNum, dHisNum, dSector, DManNum, TO_CHAR(dDate, 'YYYY-MM-DD') dDate, dState,");
			sb.append(" NVL(TO_CHAR(dEndDate, 'YYYY-MM-DD'),'배송중인상품') dEndDate");
			sb.append(" FROM delivery ");
			sb.append("WHERE dNum = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new DeliveryDTO();
				dto.setdNum(rs.getString("dNum"));
				dto.setdPNum(rs.getString("dPNum"));
				dto.setdHisNum(rs.getInt("dHisNum"));
				dto.setdSector(rs.getString("dSector"));
				dto.setdManNum(rs.getString("DManNum"));
				dto.setdDate(rs.getString("dDate"));
				dto.setdState(rs.getString("dState"));
				dto.setdEndDate(rs.getString("dEndDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

		return dto;
	}

	@Override
	public List<DeliveryDTO> dAllList() {
		List<DeliveryDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "{CALL listView(?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				DeliveryDTO dto = new DeliveryDTO();
				dto.setdNum(rs.getString("dNum"));
				dto.setdPNum(rs.getString("dPNum"));
				dto.setdHisNum(rs.getInt("dHisNum"));
				dto.setdSector(rs.getString("dSector"));
				dto.setdManNum(rs.getString("DManNum"));
				dto.setdDate(rs.getString("dDate"));
				dto.setdState(rs.getString("dState"));
				dto.setdEndDate(rs.getString("dEndDate"));
				dto.setdGetPerson(rs.getString("hisMemberId"));
				list.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (cstmt != null) {
					try {
						cstmt.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<DeliveryDTO> dStateList(String state) {
		List<DeliveryDTO> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sb.append("SELECT dNum, dPNum, dHisNum, dSector, DManNum, ");
			sb.append("TO_CHAR(dDate, 'YYYY-MM-DD') dDate, dState, ");
			sb.append("NVL(TO_CHAR(dEndDate, 'YYYY-MM-DD'),'배송중인상품') dEndDate ");
			sb.append("FROM delivery ");
			sb.append("WHERE dState = ?");
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, state);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeliveryDTO dto = new DeliveryDTO();
				dto.setdNum(rs.getString("dNum"));
				dto.setdPNum(rs.getString("dPNum"));
				dto.setdHisNum(rs.getInt("dHisNum"));
				dto.setdSector(rs.getString("dSector"));
				dto.setdManNum(rs.getString("dManNum"));
				dto.setdDate(rs.getString("dDate"));
				dto.setdState(rs.getString("dState"));
				dto.setdEndDate(rs.getString("dEndDate"));
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public void create() {
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		try {
			sb.append("CREATE TABLE delivery(");
			sb.append(" dNum VARCHAR2(30) PRIMARY KEY, ");
			sb.append(" dPNum VARCHAR2(30), ");
			sb.append(" dHisNum NUMBER, ");
			sb.append(" dSector VARCHAR2(50) NOT NULL, ");
			sb.append(" dManNum VARCHAR2(50) NOT NULL, ");
			sb.append(" dDate DATE NOT NULL, ");
			sb.append(" dState VARCHAR2(30) DEFAULT '배송중', ");
			sb.append(" dEndDate Date, ");
			sb.append(" FOREIGN KEY (dPNum) REFERENCES product(pNum), ");
			sb.append(" FOREIGN KEY (dHisNum) REFERENCES history(hisNumber) ");
			sb.append(")");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	@Override
	public void drop() {
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		try {
			sb.append("DROP TABLE delivery PURGE");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

}
