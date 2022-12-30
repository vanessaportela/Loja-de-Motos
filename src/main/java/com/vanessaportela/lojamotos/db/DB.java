package com.vanessaportela.lojamotos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/lojamoto";
	private static String user = "vanessa";
	private static String password = "123456";

	public static Connection getConnection() {
		Connection conn = null;
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				throw new DbException(e.getMessage(), e.getCause());
			}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				throw new DbException(e.getMessage(), e.getCause());
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e.getCause());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				throw new DbException(e.getMessage(), e.getCause());
			}
		}
	}
}
