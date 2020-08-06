package shoesMart_END;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class NoticeImpl implements NoticeDAO {

	private Connection conn = DBConn.getConnection();

	@Override
	public int insertWriting(NoticeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO noticeboard(noticeNum, noticeMemId, noticePwd, noticePosts, noticeDate, noticetitle) ";
			sql += "VALUES(noticeboard_num.nextval, ?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getNoticeMemId());
			pstmt.setString(2, dto.getNoticePwd());
			pstmt.setString(3, dto.getNoticePosts());
			pstmt.setString(4, dto.getNoticeDate());
			pstmt.setString(5, dto.getNoticeTitle());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
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
	public List<NoticeDTO> listWriting() {
		List<NoticeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append(
					"SELECT noticeNum, noticeMemId, noticeTitle, TO_CHAR(noticeDate, 'YYYY-MM-DD') noticeDate FROM noticeboard");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNoticeNum(rs.getString("noticeNum"));
				dto.setNoticeMemId(rs.getString("noticeMemId"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				dto.setNoticeDate(rs.getString("noticeDate"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public int deleteWriting(String read, String pwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM noticeboard WHERE noticeNum =? AND noticePwd=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, read);
			pstmt.setString(2, pwd);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
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

	public int updateWriting(NoticeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE noticeboard SET noticePosts=?, noticeDate=?,  noticeTitle = ? WHERE noticeNum=? AND noticePwd=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getNoticePosts());
			pstmt.setString(2, dto.getNoticeDate());
			pstmt.setString(3, dto.getNoticeTitle());
			pstmt.setString(4, dto.getNoticeNum());
			pstmt.setString(5, dto.getNoticePwd());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
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
	public NoticeDTO read(String noticeNum) {
		NoticeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append(
					"SELECT noticeMemId, noticePwd, noticeNum, noticeTitle, TO_CHAR(noticeDate, 'YYYY-MM-DD') noticeDate, noticeposts");
			sb.append("  FROM noticeboard ");
			sb.append("  WHERE noticeNum=?");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, noticeNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new NoticeDTO();
				dto.setNoticeMemId(rs.getString("noticeMemId"));
				dto.setNoticePwd(rs.getString("noticePwd"));
				dto.setNoticeNum(rs.getString("noticeNum"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				dto.setNoticeDate(rs.getString("noticeDate"));
				dto.setNoticePosts(rs.getString("noticeposts"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<NoticeDTO> adminList() {
		List<NoticeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append(
					"SELECT noticeNum, noticeMemId, noticeTitle, TO_CHAR(noticeDate, 'YYYY-MM-DD') noticeDate, noticepwd, noticeposts FROM noticeboard");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNoticeNum(rs.getString("noticeNum"));
				dto.setNoticeMemId(rs.getString("noticeMemId"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				dto.setNoticeDate(rs.getString("noticeDate"));
				dto.setNoticePwd(rs.getString("noticepwd"));
				dto.setNoticePosts(rs.getString("noticeposts"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
