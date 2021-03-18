package com.training.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taining.web.entity.CommentVo;

import JDBC.utill.JdbcUtil;

public class CommentService {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String SQL;
	
	public ArrayList<CommentVo> getComment(int Board_id){
		ArrayList<CommentVo> Commentlist = new ArrayList<CommentVo>();
		SQL="SELECT * FROM COMMENT WHERE BOARD_ID=? ORDER BY ID DESC ";
		
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,Board_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentVo vo= new CommentVo();
				vo.setId(rs.getInt("ID"));
				vo.setCwriter(rs.getString("WRITER"));
				vo.setCcontent(rs.getString("CONTENT"));
				vo.setCregdate(rs.getTimestamp("REGDATE"));
				vo.setBoardID(rs.getInt("BOARD_ID"));
				Commentlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		return Commentlist;
	}
	
	public void insertComment(CommentVo vo) {
		SQL="INSERT INTO COMMENT(WRITER,CONTENT,REGDATE,BOARD_ID) VALUES(?,?,NOW(),?)";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,vo.getCwriter());
			pstmt.setString(2,vo.getCcontent());
			pstmt.setInt(3,vo.getBoardID());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
}
