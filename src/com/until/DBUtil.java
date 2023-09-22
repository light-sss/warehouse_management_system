package com.until;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBUtil {
	public static Connection conn = null;

	public DBUtil(String account, String password, String database) {
		// 数据库账号 密码 数据库名称

		// 连接驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("drive SUCCESS");
		} catch (Exception e) {
			System.out.println("drive DEFAULT");
		}
		// 连接数据库
		try {
			String url = "jdbc:mysql://localhost:3306/" + database + "?characterEncoding=utf-8&useSSL=false";
			conn = DriverManager.getConnection(url, account, password);
			System.out.println("connect SUCCESS");
		} catch (SQLException e1) {
			System.out.println("---------------");
			System.out.println("connect DEFAULT");
			e1.printStackTrace();// 打印报错信息
			System.out.println("---------------");
		}
	}

	// 检测连接是否关闭：自动关闭 读取数据库的接口 预处理的接口
	public static void CloseDB(ResultSet rs, PreparedStatement stm) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
