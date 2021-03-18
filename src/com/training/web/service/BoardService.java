package com.training.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;

import JDBC.utill.JdbcUtil;

public class BoardService {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int pubBoardAll(int[] oids,int[] cids) {
		
		List<String> oidsList = new ArrayList<>();		
		for(int i=0; i<oids.length;i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<>();		
		for(int i=0; i<oids.length;i++)
			cidsList.add(String.valueOf(cids[i]));
		
		return pubBoardAll(oidsList,cidsList);
	}
	
	public int pubBoardAll(List<String> oids,List<String> cids) {
		
		String oidsCSV =String.join(",",oids);
		String cidsCSV =String.join(",",cids);
		
		return pubBoardAll(oidsCSV,cidsCSV);
	}
	
	public int pubBoardAll(String oids,String cids) {
		int result = 0;
		String Opensql = String.format("UPDATE BOARD SET PUB=1 WHERE ID IN (%s)",oids);
		String Closesql = String.format("UPDATE BOARD SET PUB=0 WHERE ID IN (%s)",cids);
		try {
			conn = JdbcUtil.getConnection();
			Statement stmtOpen = conn.createStatement();
			result+=stmtOpen.executeUpdate(Opensql);
			
			Statement stmtcClose = conn.createStatement();
			result+=stmtcClose.executeUpdate(Closesql);
			
			stmtcClose.close();
			stmtOpen.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	public int insertBoard(BoardVo vo) {
		
		int result=0;
		
		String sql = "INSERT INTO BOARD(TITLE,CONTENT,WRITER,PUB,FILES,PASSWORD) VALUES(?,?,?,?,?,?)";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setBoolean(4, vo.getPub());
			pstmt.setString(5, vo.getFiles());
			pstmt.setString(6,vo.getPassword());
			
			result=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	public boolean checkPassword(int id, String pwd) {
		
		String sql = "SELECT * FROM BOARD WHERE ID=? ";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(pwd)) {
					return true;
				}
			}else {
				System.out.println("id가 없습니다.");
				return false;
			}
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("여기로 들어온다면 값이없는것");
		return false;
	}
	
	public void deleteBoard(int id) {
		
		
		String sql = "DELETE FROM BOARD WHERE ID=? ";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
			if(pstmt.executeUpdate()<=0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBoard(BoardVo vo) {
		
		String sql = "UPDATE BOARD SET TITLE=?, WRITER=?, CONTENT=? WHERE ID=?";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getWriter());
			pstmt.setString(3,vo.getContent());
			pstmt.setInt(4,	vo.getId());
			pstmt.executeUpdate();
			if(pstmt.executeUpdate()<=0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<BoardVo> getBoardNewList(){
		
		return null;
	}
	
	public List<BoardView> getBoardList() {

		return getBoardList("title", "", 1);
	}

	public List<BoardView> getBoardList(int page) {

		return getBoardList("title", "", page);
	}

	public List<BoardView> getBoardList(String field, String query, int page) {
		String sql = "SELECT * FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM BOARD_VIEW B,(SELECT @ROWNUM:=0) TMP WHERE "
				+ field + " LIKE ? ORDER BY REGDATE DESC) C WHERE NUM between ? AND ?;";
		List<BoardView> list = new ArrayList<>();
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, 1 + (page - 1) * 10);
			pstmt.setInt(3, page * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				
				BoardView vo = new BoardView(id, title, writer, content, regdate, hit, files,pub,password,cmtCount);
				System.out.println(vo);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);

		}
		return list;
	}

	public List<BoardView> getBoardPubList(String field, String query, int page) {
		String sql = "SELECT * FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM BOARD_VIEW B,(SELECT @ROWNUM:=0) TMP WHERE "
				+ field + " LIKE ? ORDER BY REGDATE DESC) C WHERE PUB=1 AND NUM between ? AND ?;";
		List<BoardView> list = new ArrayList<>();
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, 1 + (page - 1) * 10);
			pstmt.setInt(3, page * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				System.out.printf("id>>"+id+"  제목>>"+title+" 날짜>>>"+regdate+"\n");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				
				BoardView vo = new BoardView(id, title, writer, content, regdate, hit, files,pub,password,cmtCount);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);

		}
		return list;
	}
	
	public int getBoardCount() {
		return getBoardCount("title", "");
	}

	public int getBoardCount(String field, String query) {
		int count = 0;
		String sql = "SELECT COUNT(ID) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM BOARD B,(SELECT @ROWNUM:=0) TMP WHERE "+ field +" LIKE ? ORDER BY REGDATE DESC) C ;";
				

		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt("COUNT");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}

		return count;
	}
	
	public int getPubBoardCount(String field, String query) {
		int count = 0;
		String sql = "SELECT COUNT(ID) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM BOARD B,(SELECT @ROWNUM:=0) TMP WHERE "+ field +" LIKE ? AND PUB=1 ORDER BY REGDATE DESC) C ;";
		
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt("COUNT");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		
		return count;
	}
	public BoardVo getBoard(int id) {
		BoardVo vo = null;
		String sql = "SELECT * FROM BOARD WHERE ID=?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				int hit_ = rs.getInt("HIT");
				String files = rs.getString("files");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				int hit=countHit(hit_,bid);
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files,password,pub);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	public BoardVo getNextBoard(int id) {
		
		BoardVo vo = null;
		String sql = "SELECT * FROM BOARD WHERE ID >=(SELECT ID FROM (SELECT ID FROM BOARD WHERE ID>?) N LIMIT 1)";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("PUB")==true) {
					int bid = rs.getInt("ID");
					String title = rs.getString("TITLE");
					String writer = rs.getString("WRITER");
					String content = rs.getString("CONTENT");
					Date regdate = rs.getTimestamp("REGDATE");
					int hit = rs.getInt("HIT");
					String files = rs.getString("files");
					boolean pub = rs.getBoolean("PUB");
					String password = rs.getString("PASSWORD");
					
					vo = new BoardVo(bid, title, writer, content, regdate, hit, files,password,pub);
					break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	public BoardVo getPreBoard(int id) {	
		BoardVo vo = null;
		String sql = "SELECT * FROM BOARD WHERE ID <=(SELECT ID FROM (SELECT ID FROM BOARD WHERE ID<? ORDER BY ID DESC) N LIMIT 1) ORDER BY ID DESC";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getBoolean("PUB")==true) {
				int bid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				boolean pub = rs.getBoolean("PUB");
String password = rs.getString("PASSWORD");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files,password,pub);
				break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	
	public BoardVo getAdminNextBoard(int id) {
		
		BoardVo vo = null;
		String sql = "SELECT * FROM BOARD WHERE ID =(SELECT ID FROM (SELECT ID FROM BOARD WHERE ID>? ORDER BY ID ASC) N LIMIT 1)";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files,password,pub);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	public BoardVo getAdminPreBoard(int id) {	
		BoardVo vo = null;
		String sql = "SELECT * FROM BOARD WHERE ID =(SELECT ID FROM (SELECT ID FROM BOARD WHERE ID<? ORDER BY ID DESC) N LIMIT 1)";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
					int bid = rs.getInt("ID");
					String title = rs.getString("TITLE");
					String writer = rs.getString("WRITER");
					String content = rs.getString("CONTENT");
					Date regdate = rs.getTimestamp("REGDATE");
					int hit = rs.getInt("HIT");
					String files = rs.getString("files");
					boolean pub = rs.getBoolean("PUB");
					String password = rs.getString("PASSWORD");
					
					vo = new BoardVo(bid, title, writer, content, regdate, hit, files,password,pub);
					break;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}

	public int deleteBoardAll(int[] ids) {
		int result=0;
		String params="";
		for(int i=0;i<ids.length;i++) {
			params += ids[i];
			if(i<ids.length-1)
				params += ",";
		}
		String sql = "DELETE FROM BOARD WHERE ID IN ("+params+")";
		
		try {
			conn = JdbcUtil.getConnection();
			Statement stmt = conn.createStatement();
			result=stmt.executeUpdate(sql);
			
			conn.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return result;
	}
	public int countHit (int hit, int id) {
		int addhit = hit+1;
		System.out.println("조회전 hit: "+hit+" 조회후 : "+addhit);
		String SQL ="UPDATE BOARD SET HIT = ? WHERE ID = ?";
		
		try {
			conn=JdbcUtil.getConnection();
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, addhit);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addhit;
	}

}
