package com.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.dao.LoginDao;
import com.dao.SupManDao;
import com.manage.panl.InStockPan;
import com.style.Style;
import com.tool.Tool;

public class Login {

	public static JTextField jtextfield;

	public static JPasswordField jtextfield1;
	final int WIDTH = 400;// 设置顶层框架的宽度
	final int HEIGHT = 215;// 设置顶层框架的高度

	String title;
	JFrame jframe = new JFrame();

	FlowLayout flowlayout;// 定义一个布局

	Login(String title) {
		this.title = title;
		init();// 一些组件操作
		jframe.setVisible(true);// 设置当前窗口是否可显示
		jframe.setResizable(false);// 设置当前窗口是否可变（这里设置不可变）
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置默认关闭方式
		jframe.validate();// 让组件生效

	}

	void init() {
		// 设置标题
		jframe.setTitle(title);
		// 设置窗口位置
		// 设置窗口宽度
		Tool.setWindowsPosCenter(WIDTH, HEIGHT, jframe);

		flowlayout = new FlowLayout(flowlayout.CENTER);// 居中对齐
		Style style = new Style();
		jframe.setLayout(null);

		// 先设置背景图
		ImageIcon img = new ImageIcon("src/img/login.jpg");// 将图片读取放到img变量里
		JLabel bgimg = new JLabel(img);// 标签会显示img图片
		bgimg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景位置 获取图片宽度与高度

		// 定义框架分别装账号、密码、标题
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(flowlayout);
		jpanel1.setBounds(0, 0, 400, 45);
		jpanel1.setBackground(Color.orange);

		// 添加标题注释
		JLabel jlabel1 = new JLabel("仓库管理系统登录");
		jlabel1.setFont(style.title);
		jpanel1.add(jlabel1);
		jpanel1.setOpaque(false);

		// 定义框架二分装账号
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(flowlayout);
		jpanel2.setBounds(100, 45, 200, 250);
		jpanel2.setOpaque(false);

		// 放账号标签
		JLabel jlabel2 = new JLabel("账号");
		jlabel2.setFont(style.account);
		jpanel2.add(jlabel2);
		// 添加账号框
		jtextfield = new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpanel2.add(jtextfield);
		// 放密码标签
		JLabel jlabel3 = new JLabel("密码");
		jlabel3.setFont(style.account);
		jpanel2.add(jlabel3);
		// 添加密码框
		jtextfield1 = new JPasswordField(15);
		jtextfield1.setFont(style.accounttext);
		jpanel2.add(jtextfield1);

		// 登录按钮
		JButton jbutton = new JButton("登录");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(180, 28));
		jbutton.setBackground(Color.LIGHT_GRAY);
		jbutton.setForeground(Color.black);
		jpanel2.add(jbutton);

		jframe.add(jpanel2);
		jframe.add(jpanel1);
		jframe.add(bgimg);

		// 一下监听事件
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将账号和密码获取到并做相应的提示
				// 将账号和密码跟数据库进行匹对，同时在匹对权限 跳转不同的界面

				String account = jtextfield.getText();// 获取账号

				// 获取密码
				char[] str = jtextfield1.getPassword();
				String password = new String(str);

				boolean star = LoginDao.loginStar(account, password);
				if (star == true) {
					System.out.println("Login Sussess");
					// 之后还要判断权限在哪个等级跳转到不同的界面
					int pow = LoginDao.loginpPow(account, password);
					if (pow == 2) {
						// 是管理员
						jframe.dispose();
						MangePeopleWindows B = new MangePeopleWindows();
						SupManDao.readSup(InStockPan.cmbSupName);

					} else if (pow == 1) {
						// 普通用户
						jframe.dispose();
						StaffWindows A = new StaffWindows();
					} else {
						// 报错
						JOptionPane.showMessageDialog(null, "System Wrong", "login message",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					System.out.println("Login Default");
					// 用信息框进行提示
					JOptionPane.showMessageDialog(null, "login default", "login message", JOptionPane.WARNING_MESSAGE);
				}
			}

		});

	}
}
