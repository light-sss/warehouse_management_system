package com.style;

import java.awt.Font;

public class Style {

	// 字体样式
	public static Font title;// 定义一个登录界面标题
	public static Font account;// 账号的样式
	public static Font accounttext;// 登录文本框的样式
	public static Font ok;// 登录按钮的字体

	public Style() {
		// 对程序的初始化
		title = new Font("宋体", Font.BOLD, 28);// 标题 字体 加粗 大小
		account = new Font("华文bai行楷", Font.BOLD, 18);// 标签
		accounttext = new Font("宋体", Font.PLAIN, 18);// 账号框
		ok = new Font("宋体", Font.BOLD, 18);// 登录按钮字体
	}
}
