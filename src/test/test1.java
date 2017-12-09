package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test1 {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		showFirstMenu();
	}

	/**
	 * 一级菜单
	 */
	public static void showFirstMenu() {
		System.out.println("欢迎来到**********");
		System.out.println("1注册 2.登陆3.退出");
		System.out.println("请选择----》");
		int choose = input.nextInt();
		switch (choose) {
		case 1:// 注册
			register();
			break;
		case 2:// 登陆
			login();
			break;
		case 3:// 退出
			System.out.println("~~~欢迎下次光临~~~");
			break;

		}
	}

	/**
	 * 登陆的方法
	 */
	private static void login() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");// 反射机制加载驱动包
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 建立连接
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("请输入登陆的昵称：");
			String loginname = input.next();
			System.out.println("请输入登陆的密码：");
			String password = input.next();
			stat = conn.createStatement();
			// 使用 "+变量+"拼接
			String sql = "select * from easybuy_user where loginName='"
					+ loginname + "'and password='" + password + "'";
			rs = stat.executeQuery(sql);
			System.out.println("\t\t你的信息为");
			System.out.println("编号\t昵称\t用户名\t密码\t性别");
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.print(rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 二级菜单
		 */
		secondary();

	}

	/**
	 * 二级菜单
	 */
	private static void secondary() {
		System.out.println("\n请输入你要进行的操作");
		System.out.println("1.删除 2.修改 3.返回上一级");
		int choose = input.nextInt();
		switch (choose) {
		case 1:// 删除
			delete();
			break;
		case 2:// 修改
			Update();
			break;
		case 3:// 返回上一级
			showFirstMenu();
			break;
		}
	}

	/**
	 * 修改
	 */
	private static void Update() {
		Connection conn = null;
		Statement stat = null;
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 建立连接
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("请输入要修改的的昵称：");
			String loginname = input.next();
			System.out.println("请输入要真实姓名：");
			String usename = input.next();
			System.out.println("请输入要修改的密码：");
			String password = input.next();
			System.out.println("请输入要修改的性别：(1男 --0女)");
			int sex = input.nextInt();
			String sql = "update easybuy_user set loginName='" + loginname
					+ "',userName='" + usename + "',password='" + password
					+ "',sex=" + sex + " where loginName='" + loginname + "'";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		secondary();// 修改完返回二级菜单
	}

	/**
	 * 删除
	 */
	private static void delete() {
		Connection conn = null;
		Statement stat = null;
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 建立连接
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("请输入要修改的的昵称：");
			String loginname = input.next();

			String sql = "delete from  easybuy_user where loginName='"
					+ loginname + "'";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("删除成功");
			} else {
				System.out.println("没有该用户，不能删除");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		secondary();// 删除完返回二级菜单
	}

	/**
	 * 注册的方法
	 */
	private static void register() {
		Connection conn = null;
		Statement stat = null;
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 建立连接
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("请输入注册的昵称：");
			String loginname = input.next();
			System.out.println("请输入注册的真实姓名：");
			String usename = input.next();
			System.out.println("请输入注册的密码：");
			String password = input.next();
			System.out.println("请输入注册的性别：(1男 --0女)");
			int sex = input.nextInt();
			String sql = "insert into easybuy_user(loginName,userName,password,sex)values('"
					+ loginname
					+ "','"
					+ usename
					+ "','"
					+ password
					+ "',"
					+ sex + ")";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("注册成功");
			} else {
				System.out.println("注册失败");
			}
			showFirstMenu();// 无论注册是否成功都要显示一级菜单
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
