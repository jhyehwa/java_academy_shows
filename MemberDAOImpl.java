package shoesMart_END;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.util.DBConn;

public class MemberDAOImpl implements MemberDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO MEMBER(memberid,memberpwd,membernum, membername,memberemail,membersector,membertel,memberbirth) VALUES (?,?,membernum.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getSetor());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getBirth());

			result = pstmt.executeUpdate();

			System.out.println("가입 완료");

		} catch (SQLException e) {
			System.out.println("정확한값을 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result; // 리절트 '를' 반환한다.
	}

	@Override
	public int updateMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE MEMBER SET memberpwd =?, membername =?, memberemail =?, membersector =?, "
					+ "membertel =?, memberbirth=? WHERE memberid= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getSetor());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getBirth());
			pstmt.setString(7, dto.getId());

			result = pstmt.executeUpdate();

		} catch (InputMismatchException e) {
			System.out.println("정확한 값을 입력해주세요.");
		} catch (SQLException e) {
			System.out.println("정확한 값을 입력해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	@Override
	public int deleteMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM MEMBER WHERE memberid=?";
			pstmt = conn.prepareCall(sql);

			pstmt.setString(1, dto.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	@Override
	public MemberDTO readMember(String id) {
		MemberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT memberid,memberpwd,membername,memberemail,membersector,membertel,TO_CHAR(memberbirth, 'yyyy-MM-dd') memberbirth");
//		sb.append(" memberbirth, ");
		sb.append(" FROM member ");
		sb.append(" WHERE memberid = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDTO();

				dto.setId(rs.getString("memberid"));
				dto.setPwd(rs.getString("memberpwd"));
				dto.setName(rs.getString("membername"));
				dto.setEmail(rs.getString("memberemail"));
				dto.setSetor(rs.getString("membersector"));
				dto.setTel(rs.getString("membertel"));
				dto.setBirth(rs.getString("memberbirth"));
			}
		} catch (Exception e) {
			System.out.println("등록된 정보가 없습니다.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return dto;
	}

	@Override
	public List<MemberDTO> listMember() {
		List<MemberDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT memberid, membernum, membername, memberemail, membersector, membertel, ");
			sb.append(" TO_CHAR(memberbirth, 'YYYY-MM-DD') birth ");
			sb.append(" FROM member ");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("memberid"));
				dto.setName(rs.getString("membername"));
				dto.setEmail(rs.getString("memberemail"));
				dto.setSetor(rs.getString("membersector"));
				dto.setTel(rs.getString("membertel"));
				dto.setBirth(rs.getString("birth"));

				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("등록된 정보가 없습니다.");
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

	@Override
	public List<MemberDTO> listMember(String name) {
		List<MemberDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT memberid, membername, memberemail, membersector, membertel, ");
		sb.append(" TO_CHAR(memberbirth, 'YYYY-MM-DD') birth ");
		sb.append(" FROM member ");
		sb.append(" WHERE membername Like '%' || ? || '%'");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("memberid"));
				dto.setName(rs.getString("membername"));
				dto.setEmail(rs.getString("memberemail"));
				dto.setSetor(rs.getString("membersector"));
				dto.setTel(rs.getString("membertel"));
				dto.setBirth(rs.getString("birth"));

				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("등록된 정보가 없습니다.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

}
