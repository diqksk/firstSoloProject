package com.training.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public int pubBoardAll(int[] ids) {
		
		return 0;
	}
	
	public int insertBoard(BoardVo vo) {
		return 0;
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
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				BoardView vo = new BoardView(id, title, writer, content, regdate, hit, files,cmtCount);
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
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files);
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
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files);
				System.out.println(vo);
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
				Date regdate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("files");
				
				vo = new BoardVo(bid, title, writer, content, regdate, hit, files);
				System.out.println(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
}
