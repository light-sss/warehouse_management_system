package com.manage.panl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.dao.SupManDao;

public class SupplierPan extends JPanel {

	// 添加子产品次数
	static int num = 1;

	final int WIDTH = 730;// 宽
	final int HEIGHT = 50;// 高
	public JComboBox cmb1;
	public static JTextField jt1;

	public SupplierPan(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);

		init();// 初始化

	}

	void init() {
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		JPanel jpan1 = new JPanel();

		// 定义盘子一
		// 居中对齐，流布局
		jpan1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jpan1.setBounds(0, 0, WIDTH, 80);
		jpan1.setBackground(Color.gray);
		this.add(jpan1);

		JLabel jl1 = new JLabel("供应商");
		jt1 = new JTextField(8);
		JButton jb1 = new JButton("添加供应商");
		JButton jb5 = new JButton("删除供应商");

		jpan1.add(jl1);
		jpan1.add(jt1);
		jpan1.add(jb1);
		jpan1.add(jb5);

		// 一个标签

		JLabel JL1 = new JLabel("供应商");
		jpan1.add(JL1);
		// 创建下拉框
		JComboBox cmbSupName = new JComboBox();
		cmbSupName.addItem("--请选择供应商--");
		jpan1.add(cmbSupName);

		JLabel JL2 = new JLabel("商品名称");
		jpan1.add(JL2);
		// 创建下拉框
		JComboBox cmbStockName = new JComboBox();
		cmbStockName.addItem("--请选择商品--");
		jpan1.add(cmbStockName);

		JButton jb = new JButton("删除子产品");
		jpan1.add(jb);

		// 下拉框的监听

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String) cmbSupName.getSelectedItem());
				
			}
		});

		SupManDao.readSup(cmbSupName);

		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将满足条件的删除掉

				if (cmbSupName.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择供应商", "消息", JOptionPane.WARNING_MESSAGE);
				} else if (cmbStockName.getSelectedIndex() == 0) {

					JOptionPane.showMessageDialog(null, "请选择子产品", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					String sup = (String) cmbSupName.getSelectedItem();
					String sun = (String) cmbStockName.getSelectedItem();

					int a = SupManDao.delSunStock(sup, sun);
					if (a == 1) {
						JOptionPane.showMessageDialog(null, "删除成功", "消息", JOptionPane.WARNING_MESSAGE);
						SupManDao.readSun(cmbStockName, (String) cmbSupName.getSelectedItem());
					}
					if (a == 0) {
						JOptionPane.showMessageDialog(null, "删除失败", "消息", JOptionPane.WARNING_MESSAGE);
					}
					if (a == 3) {
						JOptionPane.showMessageDialog(null, "删除报错", "消息", JOptionPane.WARNING_MESSAGE);
					}

				}

			}
		});

		// 定义盘子二 总体的宽度是一样的

		JPanel jpan2 = new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		jpan2.setBackground(Color.green);
		jpan2.setBounds(0, 80, WIDTH, 470);
		this.add(jpan2);

		JLabel jl2 = new JLabel("供应商");
		cmb1 = new JComboBox();
		cmb1.addItem("--请选择供应商--");

		JButton jb2 = new JButton("添加旗下子产品");

		JButton jb3 = new JButton("保存数据");
		JButton jb4 = new JButton("重置");

		jpan2.add(jl2);
		jpan2.add(cmb1);
		jpan2.add(jb2);
		jpan2.add(jb3);
		jpan2.add(jb4);

		// 定义盘子三放置到盘子二中
		JPanel jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp3.setBackground(Color.yellow);
		jp3.setPreferredSize(new Dimension(200, 420));// 必须在流布局中使用
		JTextField A = new JTextField(12);
		JLabel B = new JLabel("产品名称");
		A.setName("sun");
		jp3.add(B);
		jp3.add(A);
		jpan2.add(jp3);

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (num < 5) {
					JTextField A = new JTextField(12);
					JLabel B = new JLabel("产品名称");
					A.setName("sun");// 孩子的意思
					jp3.add(B);
					jp3.add(A);
					myUpdateUI();
					num++;
				} else {
					JOptionPane.showMessageDialog(null, "最多添加5个", "消息", JOptionPane.WARNING_MESSAGE);

				}

			}
		});

		// 添加供应商
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将数据写入到数据库
				if (jt1.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "供应商不能为空", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					int star = SupManDao.writeSup(jt1.getText());
					if (star == 0) {
						JOptionPane.showMessageDialog(null, "供应商添加失败", "消息", JOptionPane.WARNING_MESSAGE);
					}
					if (star == 1) {
						JOptionPane.showMessageDialog(null, "供应商添加成功", "消息", JOptionPane.WARNING_MESSAGE);
						// 刷新下拉框
						SupManDao.readSup(cmb1);
						SupManDao.readSup(cmbSupName);
					}
					if (star == 3) {
						JOptionPane.showMessageDialog(null, "供应商名字重复，请重新输入", "消息", JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});

		// 添加删除按钮监听dellSup
		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 将数据写入到数据库
				if (jt1.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "供应商不能为空", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					int star = SupManDao.dellSup(jt1.getText());
					if (star == 0) {
						JOptionPane.showMessageDialog(null, "供应商删除失败", "消息", JOptionPane.WARNING_MESSAGE);
					}
					if (star == 1) {
						JOptionPane.showMessageDialog(null, "供应商删除成功", "消息", JOptionPane.WARNING_MESSAGE);
						// 刷新下拉框
						SupManDao.readSup(cmb1);
						SupManDao.readSup(cmbSupName);
					}
					if (star == 3) {
						JOptionPane.showMessageDialog(null, "报错：请检查输入", "消息", JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});

		// 把盘子里面的所有东西获取一下
		// 把下拉框选择的数据 和文本框里面的内容获取到写入数据库

		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int a = 0;
				Component[] tem = jp3.getComponents();
				for (int i = 0; i < tem.length; i++) {

					if (tem[i].getName() != null && tem[i].getName().equals("sun")) {
						// 证明这个组件文本框
						JTextField TEMP1 = (JTextField) tem[i];
						String text = TEMP1.getText();// 将文本框框的内容获取过来
						if (cmb1.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "请选择供应商", "消息", JOptionPane.WARNING_MESSAGE);
						} else {
							// 获取项目名字
							String sup = (String) cmb1.getSelectedItem();
							// text 这个是子产品 sup是运营商
							a = SupManDao.writeSupSun(sup, text);

						}

					}
				}
				// 在高级版本中会自动报错哪行有问题
				if (a == 3) {
					JOptionPane.showMessageDialog(null, "请检查子产品名字是否重复", "消息", JOptionPane.WARNING_MESSAGE);
				} else if (a == 0) {
					JOptionPane.showMessageDialog(null, "添加失败", "消息", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "添加成功", "消息", JOptionPane.WARNING_MESSAGE);
					SupManDao.readSun(cmbStockName, (String) cmbSupName.getSelectedItem());
				}

			}
		});

		// 重置按钮 将下面的子菜单清空并留一个
		jb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jp3.removeAll();
				JTextField A = new JTextField(12);
				JLabel B = new JLabel("产品名称");
				A.setName("sun");
				jp3.add(B);
				jp3.add(A);
				myUpdateUI();
				num = 1;
			}
		});

	}

	// 更新界面
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);// 添加或删除组件后，更新窗口
	}
}
