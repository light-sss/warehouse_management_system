package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import com.mysql.jdbc.Statement;
import com.until.DBUtil;

public class LoginDao {
	// 处理登录事件的类
	static Connection con = DBUtil.conn;// 将连接的con传过来
	// 登录状态 成功返回true 失败返回false

	public static boolean loginStar(String account, String password) {
		// 账号 密码 预处理
		// 预处理语句
		PreparedStatement preSql;
		// 定义一个结果集
		ResultSet rs;
		String sqlStr = "select * from users where account=? and password=?";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs = preSql.executeQuery();// 将结果放到rs里
			if (rs.next()) {
				return true;

			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}

	}
//------------------------------------

	// 检验账号权限
	public static int loginpPow(String account, String password) {
		// 账号 密码 预处理
		// 预处理语句
		PreparedStatement preSql;
		// 定义一个结果集
		ResultSet rs;
		String sqlStr = "select * from users where account=? and password=?";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs = preSql.executeQuery();// 将结果放到rs里
			if (rs.next()) {
				if (rs.getString("pow").equals("2")) {
					// 是管理员
					return 2;
				} else {
					// 是普通用户
					return 1;
				}

			} else {
				return 3;
			}

		} catch (SQLException e) {
			return 3;
		}

	}

}
