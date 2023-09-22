package com.windows;

import com.dao.StaffStockDao;
import com.manage.item.AddStaffAccount;
import com.until.DBUtil;

public class warehouse_management_system {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 打开登录界面 连接数据库

		Login login = new Login("仓库管理系统");
		DBUtil dbutil = new DBUtil("root", "", "warehouse_management_system");

		//

	}

}
