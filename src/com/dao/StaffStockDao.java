package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.until.DBUtil;

public class StaffStockDao {
	static Connection con = DBUtil.conn;

	// 获取订单的时间
	// 获取系统的时间
	// 进行对比 如果超过三分钟则不允许操作 删除和更改都需要在三分钟之内

	public static int showTime(String num) {
		// 预处理语句
		PreparedStatement preSql;
		// 定义一个结果集
		ResultSet rs;
		String sqlStr = "SELECT* from instock where ID=?";
		int a = 0;

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, num);
			String t = null;
			rs = preSql.executeQuery();// 将结果放到rs里
			while (rs.next()) {
				a++;
				t = rs.getString("intime");
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ac = df.format(day);
				Long between_dayInteger = between_days(t, df.format(day));

				if (between_dayInteger > 3) {

					return 0;
				} else {
					return 1;
				}
			}
			return 0;

		} catch (SQLException e) {
			return 3;
		}
	}

	public static int showTimeOut(String num) {
		// 预处理语句
		PreparedStatement preSql;
		// 定义一个结果集
		ResultSet rs;
		String sqlStr = "SELECT* from outstock where ID=?";
		int a = 0;

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, num);
			String t = null;
			rs = preSql.executeQuery();// 将结果放到rs里
			while (rs.next()) {
				a++;
				t = rs.getString("outtime");
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ac = df.format(day);
				Long between_dayInteger = between_days(t, df.format(day));
				
				if (between_dayInteger > 3) {

					return 0;
				} else {
					return 1;
				}
			}
			return 0;

		} catch (SQLException e) {
			return 3;
		}
	}

	public static Long between_days(String a, String b) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 自定义时间格式

		Calendar calendar_a = Calendar.getInstance();// 获取日历对象
		Calendar calendar_b = Calendar.getInstance();

		Date date_a = null;
		Date date_b = null;

		try {
			date_a = simpleDateFormat.parse(a);// 字符串转Date
			date_b = simpleDateFormat.parse(b);
			calendar_a.setTime(date_a);// 设置日历
			calendar_b.setTime(date_b);
		} catch (ParseException e) {
			e.printStackTrace();// 格式化异常
		}

		long time_a = calendar_a.getTimeInMillis();
		long time_b = calendar_b.getTimeInMillis();

		long between_days = (time_b - time_a) / (1000 * 60);// 计算相差天数

		return between_days;
	}

}
