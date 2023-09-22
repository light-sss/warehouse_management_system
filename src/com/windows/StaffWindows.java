package com.windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.function.Supplier;

import javax.swing.*;

import com.dao.StaffStockDao;
import com.dao.SupManDao;
import com.manage.item.AddStaffAccount;
import com.staff.panl.*;
import com.tool.Tool;

public class StaffWindows {

	final int WIDTH = 900;// 设置顶层框架的宽度
	final int HEIGHT = 600;// 设置顶层框架的高度

	// 定义两个数组
	String[] button = { "商品入库", "商品出库" };// 按钮名称
	String[] buttonName = { "stockin", "stockout" };// 名称不同区分不同按钮

	public JFrame jframe = new JFrame();

	public StaffWindows() {

		init();// 一些组件操作
		jframe.setVisible(true);// 设置当前窗口是否可显示
		jframe.setResizable(false);// 设置当前窗口是否可变（这里设置不可变）
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置默认关闭方式
		jframe.validate();// 让组件生效
	}

	void init() {

		jframe.setLayout(null);// 设置空布局
		jframe.setTitle("仓库管理系统");// 设置标题
		// 窗口居中
		Tool.setWindowsPosCenter(WIDTH, HEIGHT, jframe);

		JPanel jpanel1 = new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		// 暂时没有布局

		// 设计第一个盘子位置以及大小
		jpanel1.setBounds(5, 5, 150, HEIGHT - 10);
		jpanel1.setBackground(Color.orange);
		jpanel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		// 增加一个菜单栏放置账号管理和一个增加供应商操作

		// 创建一个菜单条
		// JMenuBar menubar=new JMenuBar();

		// 将菜单条添加到窗口布局中
		// jframe.setJMenuBar(menubar);

		// 创建一个菜单
		// JMenu menu=new JMenu("账号管理");
		// JMenu menu1=new JMenu("仓库管理");

		// 将菜单放到菜单条中
		// menubar.add(menu);
		// menubar.add(menu1);

		// 创建第一个菜单账号管理的子菜单
		// JMenuItem item1_1=new JMenuItem("添加员工账号");
		// JMenuItem item1_2=new JMenuItem("删除员工账号");

		// 将第一个菜单账号管理的子菜单添加到菜单中
		// menu.add(item1_1);
		// menu.add(item1_2);

		// 创建第二个菜单仓库管理的子菜单
		JMenuItem item2_1 = new JMenuItem("添加供货商信息");

		// 将第二个仓库管理的子菜单添加到菜单中
		// menu1.add(item2_1);

		// 打开账号添加员工账号界面完毕
		/*
		 * item1_1.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub AddStaffAccount a=new AddStaffAccount(); } });
		 */

		// 入库窗格 //位置大小是相对于jpanel1这个选项卡
		InStockPan inpan = new InStockPan(0, 0, 660 + 50, HEIGHT - 10);
		jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));

		// 入库窗格
		OutStockPan outpan = new OutStockPan(0, 0, 660 + 50, HEIGHT - 10);
		jpanel2.add(outpan, (Integer) (JLayeredPane.PALETTE_LAYER));

		/*
		 * //添加运营商窗格 SupplierPan suppan=new SupplierPan(0,0,665+50,HEIGHT-10);
		 * jpanel2.add(suppan,(Integer)(JLayeredPane.PALETTE_LAYER));
		 * 
		 */
		// StaffStockDao.showTime("3");

		jpanel2.setBounds(215 - 50, 5, 660 + 50, HEIGHT - 10);
		jpanel2.setBackground(Color.BLUE);

		jframe.add(jpanel2);
		jframe.add(jpanel1);

		// for循环-----------------------
		for (int i = 0; i < button.length; i++) {
			JButton bu = new JButton(button[i]);
			jpanel1.add(bu);
			bu.setName(buttonName[i]);
			bu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					JButton jb1 = (JButton) e.getSource();
					if (jb1.getName().equals(buttonName[0])) {
						// 将商品入库盘子移动到最上面
						jpanel2.moveToFront(inpan);
						SupManDao.readSup(InStockPan.cmbSupName);

						// public static JComboBox cmbSupName;
						// public static JComboBox cmbStockName;
					}
					if (jb1.getName().equals(buttonName[1])) {
						// 将商品出库那个盘子
						jpanel2.moveToFront(outpan);

						SupManDao.readSup(OutStockPan.cmbSupName);
					}
				}

			});
		}
	}
}
