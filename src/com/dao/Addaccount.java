package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.until.DBUtil;

public class Addaccount {
	static Connection con = DBUtil.conn;

	public static int writeAccount(String account, String password, String name, String address, String email) {
		PreparedStatement preSql;// 预处理语句

		// int num1=Integer.parseInt(num);//将字符串转换成整数

		String sqlStr = "insert into users(account,`password`,sname,saddress,semail) values (?,?,?,?,?)";
		int num = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			preSql.setString(3, name);
			preSql.setString(4, address);
			preSql.setString(5, email);

			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {

			return 3;

		}

	}

}
