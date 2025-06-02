package com.hotelreserv.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	// DB 접속
	String url = "jdbc:oracle:thin:@192.168.0.11:1521:xe";
	String id = "test99";
	String password = "test99";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url , id , password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnect() {
		
			try {
				if(conn != null) conn.close();
				if(pstmt != null)pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
