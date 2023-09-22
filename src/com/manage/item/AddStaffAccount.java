package com.manage.item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.dao.Addaccount;
import com.style.Style;
import com.tool.Tool;

public class AddStaffAccount {

	final int WIDTH = 200;// 设置顶层框架的宽度
	final int HEIGHT = 265;// 设置顶层框架的高度

	JFrame jframe = new JFrame();

	public AddStaffAccount() {
		init();// 一些组件操作
		jframe.setVisible(true);// 设置当前窗口是否可显示
		jframe.setResizable(false);// 设置当前窗口是否可变（这里设置不可变）
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置默认关闭方式
		jframe.validate();// 让组件生效
	}

	void init() {
		jframe.setTitle("添加员工账号");
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		Tool.setWindowsPosCenter(WIDTH, HEIGHT, jframe);
		// 5个标签 4个文本框
		JLabel JL = new JLabel("添加员工账号");// 大标题
		Style style = new Style();
		JL.setFont(style.title);
		jframe.add(JL);

		JLabel JL1 = new JLabel("员工工号");// 大标题
		jframe.add(JL1);
		JTextField JT1 = new JTextField(10);
		jframe.add(JT1);

		JLabel JL5 = new JLabel("账号密码");// 大标题
		jframe.add(JL5);
		JPasswordField JT5 = new JPasswordField(10);
		jframe.add(JT5);

		JLabel JL6 = new JLabel("确认密码");// 大标题
		jframe.add(JL6);
		JPasswordField JT6 = new JPasswordField(10);
		jframe.add(JT6);

		JLabel JL2 = new JLabel("员工姓名");// 大标题
		jframe.add(JL2);
		JTextField JT2 = new JTextField(10);
		jframe.add(JT2);

		JLabel JL3 = new JLabel("员工地址");// 大标题
		jframe.add(JL3);
		JTextField JT3 = new JTextField(10);
		jframe.add(JT3);

		JLabel JL4 = new JLabel("员工邮箱");// 大标题
		jframe.add(JL4);
		JTextField JT4 = new JTextField(10);
		jframe.add(JT4);

		// 一个按钮
		JButton JB = new JButton("添加员工");
		jframe.add(JB);

		JButton JB1 = new JButton("重置信息");
		jframe.add(JB1);

		// 重置所有内容
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JT1.setText("");
				JT2.setText("");
				JT3.setText("");
				JT4.setText("");
				JT5.setText("");
				JT6.setText("");

			}
		});

		// 添加数据库
		// 重置所有内容
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account = JT1.getText();
				// 获取密码
				String password = new String(JT5.getPassword());
				String okpassword = new String(JT6.getPassword());
				String name = JT2.getText();
				String address = JT3.getText();
				String email = JT4.getText();
				if (account.equals("")) {
					JOptionPane.showMessageDialog(null, "员工工号不能为空", "添加信息", JOptionPane.WARNING_MESSAGE);
				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "员工密码不能为空", "添加信息", JOptionPane.WARNING_MESSAGE);

				} else if (okpassword.equals("")) {
					JOptionPane.showMessageDialog(null, "员工确认密码不能为空", "添加信息", JOptionPane.WARNING_MESSAGE);
				} else if (!okpassword.equals(password)) {
					JOptionPane.showMessageDialog(null, "两次密码不一致", "添加信息", JOptionPane.WARNING_MESSAGE);
				} else {

					int a = Addaccount.writeAccount(account, password, name, address, email);
					if (a == 0) {
						JOptionPane.showMessageDialog(null, "添加失败", "添加信息", JOptionPane.WARNING_MESSAGE);
					}
					if (a == 1) {
						JOptionPane.showMessageDialog(null, "添加成功", "添加信息", JOptionPane.WARNING_MESSAGE);
					}
					if (a == 3) {
						JOptionPane.showMessageDialog(null, "请检查工号是否重复", "添加信息", JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});

	}
}
