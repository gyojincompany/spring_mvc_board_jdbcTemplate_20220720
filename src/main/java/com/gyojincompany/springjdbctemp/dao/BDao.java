package com.gyojincompany.springjdbctemp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.gyojincompany.springjdbctemp.dto.BDto;
import com.gyojincompany.springjdbctemp.util.Constant;

public class BDao {
	
	DataSource dataSource;
	
	JdbcTemplate template;

	public BDao() {
		this.template = Constant.template;
	}
	
	public void write(final String bname, final String btitle, final String bcontent) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				return pstmt;
			}
		});
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) "
//					+ "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bname);
//			pstmt.setString(2, btitle);
//			pstmt.setString(3, bcontent);
//			pstmt.executeUpdate();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn !=null) {
//					conn.close();
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}			
//		}
	}
	
	public ArrayList<BDto> list() {
		
		String sql = "SELECT * FROM mvc_board ORDER BY bgroup desc, bstep asc";
		return (ArrayList<BDto>) template.query(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM mvc_board ORDER BY bgroup desc, bstep asc";
//			pstmt = conn.prepareStatement(sql);		
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {//글의 개수만큼 반복
//				int bid = rs.getInt("bid");
//				String bname = rs.getString("bname");
//				String btitle = rs.getString("btitle");
//				String bcontent = rs.getString("bcontent");
//				Timestamp bdate = rs.getTimestamp("bdate");
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//				
//				BDto dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
//				dtos.add(dto);				
//			}
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn !=null) {
//					conn.close();
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}			
//		}
//		return dtos;
	}
	
	public BDto contentView(String strbid) {
		
		this.upHit(strbid);
		
		String sql = "SELECT * FROM mvc_board WHERE bid=" + strbid;
		return template.queryForObject(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
		
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BDto dto = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM mvc_board WHERE bid=?";
//			pstmt = conn.prepareStatement(sql);	
//			pstmt.setInt(1, Integer.parseInt(strbid));
//			//문자열로 들어온 strbid를 int형으로 형변환
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {//글의 개수만큼 반복
//				int bid = rs.getInt("bid");
//				String bname = rs.getString("bname");
//				String btitle = rs.getString("btitle");
//				String bcontent = rs.getString("bcontent");
//				Timestamp bdate = rs.getTimestamp("bdate");
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//				
//				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
//							
//			}
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn !=null) {
//					conn.close();
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}			
//		}
//		return dto;
	}
	
	public void delete(String strbid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM mvc_board WHERE bid=?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, Integer.parseInt(strbid));
			//문자열로 들어온 strbid를 int형으로 형변환
			pstmt.executeUpdate();			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	public void modify(String bid, String bname, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bid));
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	private void upHit(String bid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, Integer.parseInt(bid));
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	public BDto replyView(String strbid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_board WHERE bid=?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, Integer.parseInt(strbid));
			//문자열로 들어온 strbid를 int형으로 형변환
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//글의 개수만큼 반복
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
							
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return dto;
	}
	
	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep, String bindent) {
		
		this.replyShape(bgroup, bstep);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bgroup,bstep,bindent) "
					+ "VALUES(mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bgroup));
			pstmt.setInt(5, Integer.parseInt(bstep)+1);
			pstmt.setInt(6, Integer.parseInt(bindent)+1);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	private void replyShape(String strGroup, String strStep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup = ? and bstep > ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
}
