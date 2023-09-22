package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.until.DBUtil;

public class OutStockDao {

	static Connection con = DBUtil.conn;

	public static int writeStock(String sup, String sunname, String num1, String pri, String user) {
		PreparedStatement preSql;// 预处理语句

		// int num1=Integer.parseInt(num);//将字符串转换成整数

		String sqlStr = "insert into outstock(`supname`,`stockname`,`outtime`,`num`,`pric`,`user`) values (?,?,now(),?,?,?)";
		int num = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sunname);
			preSql.setString(3, num1);
			preSql.setString(4, pri);
			preSql.setString(5, user);

			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {
			if (e.getMessage().equals("仓库库存不足，不能进行出库")) {
				return 4;
			} else {
				return 3;
			}

		}

	}

	// 查找单个和查找全部方法
	public static ResultSet findStockoneData(String num) {
		PreparedStatement preSql;// 预处理语句

		// int num1=Integer.parseInt(num);//将字符串转换成整数

		String data[] = new String[6];
		String sqlStr = "select outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.num,outstock.pric,outstock.`user` ,product.stock from outstock,product where product.supname=outstock.supname and product.`name`=outstock.stockname and outstock.id=?";
		ResultSet rs = null;
		int count = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, num);

			rs = preSql.executeQuery();// 将结果放到rs

			return rs;

		} catch (SQLException e) {

			return rs;
		}

	}

	// 查找所有
	public static ResultSet findStockallData() {
		PreparedStatement preSql;// 预处理语句

		// int num1=Integer.parseInt(num);//将字符串转换成整数

		String sqlStr = "select outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.num,outstock.pric,outstock.`user` ,product.stock from outstock,product where product.supname=outstock.supname and product.`name`=outstock.stockname";
		ResultSet rs = null;
		int count = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();// 将结果放到rs

			return rs;

		} catch (SQLException e) {

			return rs;
		}

	}

	// 删除
	public static int dellStockData(String id) {

		PreparedStatement preSql;// 预处理语句

		String sqlStr = "delete from outstock where id=?";
		int num = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, id);
			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {
			return 3;

		}
	}

	// 更改数据
	public static int changeStockData(String sup, String sun, String num, String pric, String user, String id) {
		PreparedStatement preSql;// 预处理语句

		String sqlStr = "update outstock set supname=?,stockname=?,num=?,pric=?,`user`=? where outstock.id=?";
		int num1 = 0;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sun);
			preSql.setString(3, num);
			preSql.setString(4, pric);
			preSql.setString(5, user);
			preSql.setString(6, id);

			num1 = preSql.executeUpdate();// 将结果放到rs里
			return num1;

		} catch (SQLException e) {

			if (e.getMessage().equals("仓库库存不足，不能进行更改出库")) {
				return 4;
			} else {
				return 3;
			}

		}
	}

}
