package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton Pattern
public class DBConn {
	private static Connection conn;

	private DBConn() {
	}

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@211.238.142.63:1521:xe"; // 11g
		// String url="jdbc:oracle:thin:@//127.0.0.1:1521/xe"; // 12c

		String user = "sh";
		String pwd = "java$!";

		if (conn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// JDK 6부터는 생략 가능. 자동 로딩

				conn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return conn;
	}

	public static void close() {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		conn = null;
	}
}
