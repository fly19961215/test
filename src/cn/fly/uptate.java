package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 更新数据
 * 
 * @author ASUS
 * 
 */
public class uptate {
	private static Logger logger = Logger.getLogger(uptate.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
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
			stat.executeUpdate("update dog set health=100,love=100 where id =1");
			logger.info("成功更新数据~~~");
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
