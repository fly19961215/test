package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 更新数据
 * 
 * @author ASUS
 * 
 */
public class Query {
	private static Logger logger = Logger.getLogger(Query.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		// 1.加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		// 2.建立连接
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epet", "root", "root");
			// 更新狗狗信息到数据库
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from dog");
			System.out.println("\t\t狗狗信息列表");
			System.out.println("编号\t姓名\t健康值\t亲密度\t品种");
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getInt(3) + "\t");
				System.out.print(rs.getInt(4) + "\t");
				System.out.println(rs.getString(5));
			}
			logger.info("查询成功~~~");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			// 3.关闭连接
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
	}
}
