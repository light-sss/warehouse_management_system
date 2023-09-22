package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import com.until.DBUtil;

public class SupManDao {

	// 实现添加供应商和删除供应商
	// 实现添加旗下子产品

	static Connection con = DBUtil.conn;

	// 定义一个整形的返回值确定状态 0：失败 1：成功
	public static int writeSup(String name) {

		// 采用预处理

		PreparedStatement preSql;// 预处理语句
		int num;// 存放结果的
		String sqlStr = "insert into supplier(`name`) values (?)";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {

			return 3;

		}

	}

	// 删除供应商 为0删除失败 1删除成功
	public static int dellSup(String name) {

		// 采用预处理

		PreparedStatement preSql;// 预处理语句
		int num;// 存放结果的
		String sqlStr = "DELETE from supplier where `name`=?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {

			return 3;

		}

	}

	// 将运营商和子产品都写入数据
	// 同时写入父亲和孩子
	public static int writeSupSun(String supname, String sunname) {

		// 采用预处理

		PreparedStatement preSql;// 预处理语句
		int num;// 存放结果的
		String sqlStr = "insert into product(`name`,`supname`) values(?,?)";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, sunname);
			preSql.setString(2, supname);
			num = preSql.executeUpdate();// 将结果放到rs里
			return num; // 添加成功

		} catch (SQLException e) {

			return 3;

		}

	}

	// 读取全部
	public static void readSup(JComboBox cmb1) {
		// 移除传递过来的所有项目
		cmb1.removeAllItems();// 移除下拉框所有的项目
		cmb1.addItem("--请选择供应商--");

		int star = 0;// 为0表示没有数据 1有数据

		PreparedStatement preSql;// 预处理语句
		String sqlStr = "select * from supplier";
		ResultSet rs;
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();// 将结果放到rs里

			while (rs.next()) {
				// 执行到这即表明有数据
				if (star == 0) {
					star++;
				}

				// 获取数据
				String tempname = rs.getString("name");
				cmb1.addItem(tempname);
			}
			cmb1.repaint();

		} catch (SQLException e) {

		}

	}

	// 读取子产品 传递过来的是一个字符串和他的供应商
	public static void readSun(JComboBox cmb1, String sup) {
		// 移除传递过来的所有项目
		cmb1.removeAllItems();// 移除下拉框所有的项目
		cmb1.addItem("--请选择商品--");

		int star = 0;// 为0表示没有数据 1有数据

		PreparedStatement preSql;// 预处理语句
		String sqlStr = "select * from product where supname=?";
		ResultSet rs;
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();// 将结果放到rs里

			while (rs.next()) {
				// 执行到这即表明有数据
				if (star == 0) {
					star++;
				}

				// 获取数据
				String tempname = rs.getString("name");
				cmb1.addItem(tempname);
			}
			cmb1.repaint();

		} catch (SQLException e) {

		}

	}

	public static int delSunStock(String sup, String sun) {

		// 采用预处理

		PreparedStatement preSql;// 预处理语句
		int num;// 存放结果的
		String sqlStr = "delete from product WHERE `name`=? and supname=?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, sun);
			preSql.setString(2, sup);
			num = preSql.executeUpdate();// 将结果放到rs里
			return num;

		} catch (SQLException e) {

			return 3;

		}

	}

}
