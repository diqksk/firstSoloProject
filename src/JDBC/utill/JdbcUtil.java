package JDBC.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
		public static String url ="jdbc:mysql://localhost/soloweb?serverTimezone=UTC";
		public static String id = "root";
		public static String pass = "1234";
		
		public static Connection getConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("드라이버 검색 성공!");
				DriverManager.getConnection(url,id,pass);
				System.out.println("로그인 성공!");
				return DriverManager.getConnection(url,id,pass);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
				
		}
		
		public static void close(ResultSet rs,Statement stmt, Connection conn) {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		private static void close(ResultSet rs) {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		private static void close(Statement stmt) {
			if(stmt!=null) {
			try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		private static void close(Connection conn) {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void main(String[] args) {
			Connection conn = getConnection();
			System.out.println(conn);
		}

	
	
}
