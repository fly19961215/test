package zuoye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Stu3 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner input = new Scanner(System.in);
		System.out.println("\t宠物主人登陆");
		input = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String name = input.next();
		System.out.print("请输入密码：");
		String pwd = input.next();
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 2.建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epet", "root", "root");
			// 判断用户是否登陆成功
			pstmt = conn.prepareStatement("select * from master where name='"
					+ name + "'and password='" + pwd + "'");
			System.out.println("select * from master where name='" + name
					+ "'and password='" + pwd + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("登陆成功~~~欢迎您~~~");
			} else {
				System.out.println("登陆失败！！！下次注意!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.关闭连接
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
