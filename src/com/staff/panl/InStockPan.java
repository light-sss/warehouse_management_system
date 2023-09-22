package com.staff.panl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dao.InStockDao;
import com.dao.StaffStockDao;
import com.dao.SupManDao;
import com.tool.Tool;

public class InStockPan extends JPanel {

	final int WIDTH = 730;
	final int HEIGHT = 50;

	// 表格的数据
	// 标题信息
	Object colums[] = { "订单编号", "供应商", "商品名称", "入库时间", "商品数量", "商品价格", "商品总库存" };
	// 定义一个表格
	JTable table = null;
	// 定义一个滚动条
	JScrollPane jscrollpane;
	// 定义一个表格的控制权
	public static DefaultTableModel model;

	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static JComboBox cmbSupName;
	public static JComboBox cmbStockName;

	public InStockPan(int x, int y, int width, int height) {

		this.setBounds(x, y, width, height);

		init();// 初始化
	}

	void init() {
		// 设置为空布局
		this.setLayout(null);
		this.setBackground(Color.orange);

		// 商品名称、商品入库时间、商品入库价格、商品入库数量、商品库存、商品供应商等信息
		// 设置三个盘子
		// 盘子一
		JPanel jpan1 = new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 12));// 左对齐
		// 设置第一个盘子大小和位置
		jpan1.setBounds(0, 0, WIDTH, HEIGHT);
		// 设置第一个盘子背景颜色
		jpan1.setBackground(Color.pink);
		// 将第一个盘子加到布局中去
		this.add(jpan1);

		// 定义五个按钮
		JButton JB1 = new JButton("保存入库");
		jpan1.add(JB1);

		JButton JB2 = new JButton("删除入库");
		jpan1.add(JB2);

		JButton JB3 = new JButton("更改入库");
		jpan1.add(JB3);

		JButton JB4 = new JButton("查找入库");
		jpan1.add(JB4);

		// 盘子二
		JPanel jpan2 = new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 25));// 左对齐
		// 设置第二个盘子大小和位置
		jpan2.setBounds(0, 60, WIDTH, 100);
		// 设置第二个盘子背景颜色
		jpan2.setBackground(Color.yellow);
		// 将第二个盘子加到布局中去
		this.add(jpan2);

		// 添加四个标签 两个文本框 两个下拉框
		JLabel JL1 = new JLabel("商品供应商");
		jpan2.add(JL1);

		// 创建下拉框
		cmbSupName = new JComboBox();
		cmbSupName.addItem("--请选择供应商--");
		jpan2.add(cmbSupName);

		JLabel JL2 = new JLabel("商品名称");
		jpan2.add(JL2);
		// 创建下拉框
		cmbStockName = new JComboBox();
		cmbStockName.addItem("--请选择商品--");
		jpan2.add(cmbStockName);

		JLabel JL3 = new JLabel("商品数量");
		jpan2.add(JL3);
		stockNumIn = new JTextField(8);
		jpan2.add(stockNumIn);

		JLabel JL4 = new JLabel("商品价格");
		jpan2.add(JL4);

		stockPricIn = new JTextField(8);
		jpan2.add(stockPricIn);

		JLabel JL5 = new JLabel("订单编号");
		jpan2.add(JL5);

		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);

		table();
		this.add(jscrollpane);

		SupManDao.readSup(InStockPan.cmbSupName);

		// 放置一个表格
		// 表格的数据

		// --------------------------------
		// 添加监听
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将数据获取 写入到数据库中
				// InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY,
				// TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)

				if (cmbSupName.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择供应商", "消息", JOptionPane.WARNING_MESSAGE);
				} else if (cmbStockName.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择产品", "消息", JOptionPane.WARNING_MESSAGE);
				} else if (stockNumIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入数量", "消息", JOptionPane.WARNING_MESSAGE);
				} else if (stockPricIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入价格", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					String sup = (String) cmbSupName.getSelectedItem();
					String sun = (String) cmbStockName.getSelectedItem();
					String num = stockNumIn.getText();
					String pri = stockPricIn.getText();
					int a = InStockDao.writeStock(sup, sun, num, pri);
					if (a == 0) {
						JOptionPane.showMessageDialog(null, "添加失败", "消息", JOptionPane.WARNING_MESSAGE);
					}
					if (a == 3) {
						JOptionPane.showMessageDialog(null, "添加报错", "消息", JOptionPane.WARNING_MESSAGE);
					}
					if (a == 1) {
						JOptionPane.showMessageDialog(null, "添加成功", "消息", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		//
		// 下拉框的监听

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String) cmbSupName.getSelectedItem());

			}
		});

		// 查询所有
		// 鼠标选择哪个则删除哪个
		JB4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 有两种查询方法：查找全部 和 查找单个
				String num = stockNum.getText();
				ResultSet rs;
				if (num.equals("")) {
					// 则查找全部
					rs = InStockDao.findStockallData();
					// 传递一个存储数据的re 和一个表格 还需要一个表格的长度
					int a = Tool.addDataTable(rs, model, 7);

					if (a == 0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据", "消息", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					// 则查找单个
					rs = InStockDao.findStockoneData(num);
					ResultSet rs1 = InStockDao.findStockoneData(num);
					try {
						if (rs1.next()) {
							String sup = rs1.getString("supname");
							String sun = rs1.getString("stockname");
							String nu = rs1.getString("num");
							String pr = rs1.getString("pric");

							// 遍历两个下拉框

							for (int i = 0; i < cmbSupName.getItemCount(); i++) {
								String a = (String) cmbSupName.getItemAt(i);
								if (a.equals(sup)) {
									cmbSupName.setSelectedIndex(i);
									cmbSupName.repaint();

									for (int j = 0; j < cmbStockName.getItemCount(); j++) {
										String c = (String) cmbStockName.getItemAt(j);
										if (c.equals(sun)) {
											cmbStockName.setSelectedIndex(j);
											cmbStockName.repaint();
										}
									}
								}
							}

							stockPricIn.setText(pr);
							stockNumIn.setText(nu);

							myUpdateUI();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// 传递一个存储数据的re 和一个表格 还需要一个表格的长度
					int a = Tool.addDataTable(rs, model, 7);

					if (a == 0) {
						JOptionPane.showMessageDialog(null, "没有查到相关数据", "消息", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		// 删除按钮
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 直接输入ID进行删除
				String num = stockNum.getText();
				if (num.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入删除编号", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					int c = StaffStockDao.showTime(num);
					if (c == 1) {

						int a = InStockDao.dellStockData(num);

						if (a == 0) {
							JOptionPane.showMessageDialog(null, "请检查编号是否输入正确", "消息", JOptionPane.WARNING_MESSAGE);
						}
						if (a == 1) {
							JOptionPane.showMessageDialog(null, "删除成功", "消息", JOptionPane.WARNING_MESSAGE);
						}
						if (a == 3) {
							JOptionPane.showMessageDialog(null, "请检查输入编号是否为整数", "消息", JOptionPane.WARNING_MESSAGE);
						}
						if (a == 4) {
							JOptionPane.showMessageDialog(null, "仓库库存不足，不能删除此订单", "消息", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "时间已超出三分钟或编号格式不正确", "消息", JOptionPane.WARNING_MESSAGE);
					}
				}

			}

		});

		// 更改按钮
		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将数据获取 写入到数据库中
				String sup = null;// 供应商
				String sun = null;// 子产品
				String num = null;// 数量
				String pric = null;// 价格
				String ID = null;// 商品编号

				if (stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入ID号", "消息", JOptionPane.WARNING_MESSAGE);

				} else {

					if (cmbSupName.getSelectedIndex() == 0) {
						// 则说明没有选择
						JOptionPane.showMessageDialog(null, "请选择供应商", "消息", JOptionPane.WARNING_MESSAGE);
					} else if (cmbStockName.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "请选择子产品", "消息", JOptionPane.WARNING_MESSAGE);
					} else if (stockNumIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品数量不能为空", "消息", JOptionPane.WARNING_MESSAGE);
					} else if (stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品价格不能为空", "消息", JOptionPane.WARNING_MESSAGE);
					} else {

						int c = StaffStockDao.showTime(stockNum.getText());

						if (c == 1) {

							// 写入
							sup = (String) cmbSupName.getSelectedItem();
							sun = (String) cmbStockName.getSelectedItem();
							num = stockNumIn.getText();
							pric = stockPricIn.getText();
							ID = stockNum.getText();

							// 将五个值传入数据库
							int a = InStockDao.changeStockData(sup, sun, num, pric, ID);

							if (a == 0) {
								JOptionPane.showMessageDialog(null, "数据未变动", "消息", JOptionPane.WARNING_MESSAGE);
							}
							if (a == 1) {
								JOptionPane.showMessageDialog(null, "更改成功", "消息", JOptionPane.WARNING_MESSAGE);
							}
							if (a == 3) {
								JOptionPane.showMessageDialog(null, "请检查输入格式", "消息", JOptionPane.WARNING_MESSAGE);
							}
							if(a==4) {
								JOptionPane.showMessageDialog(null, "库存不足不能进行更改", "消息", JOptionPane.WARNING_MESSAGE);
							}

						} else {

							JOptionPane.showMessageDialog(null, "请检查编号格式或者已经超出三分钟无法操作", "消息",
									JOptionPane.WARNING_MESSAGE);

						}

					}
				}

			}
		});

	}

	// 定义一个表格方法
	void table() {
		// 初始化表格
		table = getTable();
		// 创建一个滚动条
		jscrollpane = new JScrollPane(table);

		// 设置表格大小
		table.setPreferredSize(new Dimension(WIDTH - 30, 10000));
		// 让滚动条在窗口中显示
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jscrollpane.setBounds(0, 170, WIDTH - 20, 360);

	}

	// 返回一个JTable类型的表格
	JTable getTable() {
		// 如果表格为空则创建表格
		if (table == null) {
			table = new JTable();// 创建表格

			model = new DefaultTableModel() {

				// 添加一些对表格的控制 列宽 和行数，并且让表格不可编辑
				public boolean isCellEditable(int row, int colum) {
					return false;
				}
			};

			// 设置表头
			model.setColumnIdentifiers(colums);
			// 设置表格模式
			table.setModel(model);
			// 设置表格为不可拖动
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
		}
		return table;

	}

	// 更新界面
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);// 添加或删除组件后，更新窗口
	}
}
