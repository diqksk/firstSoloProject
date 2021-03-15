package com.training.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;

import JDBC.utill.JdbcUtil;

public class BoardService {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int removeBoardAll(int[] ids){
		
		return 0;
	}
	
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
		
		String sql = "INSERT INTO BOARD(TITLE,CONTENT,WRITER,PUB,FILES) VALUES(?,?,?,?,?)";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setBoolean(4, vo.getPub());
			pstmt.setString(5, vo.getFiles());
			
			result=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	public int deleteBoard(int id) {
		
		return 0;
	}
	
	public int updateBoard(BoardVo vo) {
		
		return 0;
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
				
				BoardView vo = new BoardView(id, title, writer, content, regdate, hit, files,pub,cmtCount);
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
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				BoardView vo = new BoardView(id, title, writer, content, regdate, hit, files,pub,cmtCount);
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
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				boolean pub = rs.getBoolean("PUB");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files, pub);
				System.out.println(vo);
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
					
					vo = new BoardVo(bid, title, writer, content, regdate, hit, files, pub);
					System.out.println(vo);
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
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files, pub);
				System.out.println(vo);
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
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files, pub);
				System.out.println(vo);
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
					
					vo = new BoardVo(bid, title, writer, content, regdate, hit, files, pub);
					System.out.println(vo);
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


}
