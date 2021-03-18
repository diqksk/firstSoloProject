package com.training.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.utill.JdbcUtil;

public class AccountService {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean LoginCheck(String id, String password) {
		boolean result = false;

		String sql = "SELECT * FROM ACCOUNT WHERE ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pwd = rs.getString("password");
				if (password.equals(pwd)) {
					System.out.println("비밀번호가 일치!");
					return true;}
				else {
					System.out.println("저장비번 : " + pwd);
					System.out.println("입력비번 : " + password);
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt, conn);
		}
		System.out.println("해당아이디 존재하지않음");
		return false;
	}

}
