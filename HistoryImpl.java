package shoesMart_END;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class HistoryImpl implements HistoryDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertHis(MemberDTO mdto, ProductDTO pdto, String date) { // 구매
		String sql;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			sql = "INSERT INTO history VALUES (hisnum_seq.nextVAL,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getEmail());
			pstmt.setString(3, pdto.getpNum());
			pstmt.setString(4, date);
			pstmt.setInt(5, pdto.getpCnt());

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
					e2.printStackTrace();
				}
			}

		}
		return result;

	}

	@Override
	public int deleteHis(int number) { // 거래내역 삭제
		String sql;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HistoryDTO hdto = null;
		int result = 0;

		try {
			sql = " SELECT h.hisnumber, nvl(dstate,'배송준비중') dstate\r\n" + "    from history h\r\n"
					+ "    left outer join delivery d on h.hisnumber = d.dhisnum\r\n" + "    where h.hisnumber = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				hdto = new HistoryDTO();

				hdto.setHisdstate(rs.getString("dstate"));
				if (hdto.getHisdstate().equals("배송준비중") == false) {
					System.out.println("배송중이거나 배송완료된 상품은 취소할 수 없습니다.");
					return 0;
				}
			}
			pstmt.close();

			sql = "DELETE FROM history WHERE hisnumber = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, number);

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
					e2.printStackTrace();
				}
			}

		}
		return result;

	}

	@Override
	public HistoryDTO viewNumber(int number) { // 거래번호로 리스트 조회
		String sql;
		HistoryDTO hdto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			sql = "{call pViewNumber(?,?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setInt(2, number);

			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(1);

			if (rs.next()) {
				hdto = new HistoryDTO();

				hdto.setHisNumber(rs.getInt("hisnumber"));
				hdto.setHisMemberId(rs.getString("hismemberid"));
				hdto.setHisMemberEmail(rs.getString("hismemberemail"));
				hdto.setHisPNumber(rs.getString("hispnumber"));
				hdto.setHisDate(rs.getString("hisdate"));
				hdto.setHisCnt(rs.getInt("hiscnt"));
				hdto.setHisdnum(rs.getString("dnum"));
				hdto.setHisdstate(rs.getString("dstate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		return hdto;

	}

	@Override
	public List<HistoryDTO> viewMember(String id) { // 회원 id로 리스트 조회
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		String sql;
		HistoryDTO hdto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			sql = "{call pviewID(?,?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2, id);

			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				hdto = new HistoryDTO();

				hdto.setHisNumber(rs.getInt("hisnumber"));
				hdto.setHisMemberId(rs.getString("hismemberid"));
				hdto.setHisMemberEmail(rs.getString("hismemberemail"));
				hdto.setHisPNumber(rs.getString("hispnumber"));
				hdto.setHisDate(rs.getString("hisdate"));
				hdto.setHisCnt(rs.getInt("hiscnt"));
				hdto.setHisdnum(rs.getString("dnum"));
				hdto.setHisdstate(rs.getString("dstate"));

				list.add(hdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		return list;
	}

	@Override
	public List<HistoryDTO> viewAll() { // 관리자 거래내역 조회
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		String sql;
		HistoryDTO hdto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			sql = "{call pviewAll(?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				hdto = new HistoryDTO();

				hdto.setHisNumber(rs.getInt("hisnumber"));
				hdto.setHisMemberId(rs.getString("hismemberid"));
				hdto.setHisMemberEmail(rs.getString("hismemberemail"));
				hdto.setHisPNumber(rs.getString("hispnumber"));
				hdto.setHisDate(rs.getString("hisdate"));
				hdto.setHisCnt(rs.getInt("hiscnt"));
				hdto.setHisdnum(rs.getString("dnum"));
				hdto.setHisdstate(rs.getString("dstate"));

				list.add(hdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		return list;
	}
}