package com.training.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;
import com.taining.web.entity.GalleryVo;

import JDBC.utill.JdbcUtil;

public class GalleryService {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<GalleryVo> getAllList(int page) {
		String sql = "SELECT * FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM GALLERY B,(SELECT @ROWNUM:=0) TMP ORDER BY REGDATE DESC) C WHERE NUM between ? AND ?";		
		List<GalleryVo> list = new ArrayList<>();
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1 + (page - 1) * 9);
			pstmt.setInt(2, page * 9);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String img = rs.getString("IMG");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getTimestamp("REGDATE");
				int hit = rs.getInt("HIT");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				
				GalleryVo vo = new GalleryVo(id,title,writer,img,content,regdate,hit,pub,password);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);

		}
		return list;
	}
	
	public int insertGallery(GalleryVo vo) {
		int result=0;
		String sql = "INSERT INTO GALLERY(TITLE,WRITER,IMG,CONTENT,PASSWORD) VALUES(?,?,?,?,?)";
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5,vo.getPassword());
			result=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getGalleryCount() {
		int count = 0;
		String sql = "SELECT COUNT(ID) COUNT FROM (SELECT @ROWNUM := @ROWNUM + 1 AS NUM, B.* FROM GALLERY B,(SELECT @ROWNUM:=0) TMP ORDER BY REGDATE DESC) C ;";
				

		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
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

	public GalleryVo getGallery(int id) {
		GalleryVo vo = null;
		String sql = "SELECT * FROM GALLERY WHERE ID=?";
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
				String img = rs.getString("IMG");
				boolean pub = rs.getBoolean("PUB");
				String password = rs.getString("PASSWORD");
				int hit=countHit(hit_,bid);
				
				vo = new GalleryVo(bid,title,writer,img,content,regdate,hit,pub,password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	public int countHit (int hit, int id) {
		int addhit = hit+1;
		System.out.println("조회전 hit: "+hit+" 조회후 : "+addhit);
		String SQL ="UPDATE GALLERY SET HIT = ? WHERE ID = ?";
		
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
