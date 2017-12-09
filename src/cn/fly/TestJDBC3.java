package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 使用preparedStatement插入记录
 * 
 * @author ASUS
 * 
 */
public class TestJDBC3 {
	private static Logger logger = Logger.getLogger(TestJDBC3.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstat = null;
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
			// 插入狗狗信息到数据库
			pstat = conn.prepareStatement("insert into dog"
					+ "(name,health,love,strain) values (?,?,?,?)");
			pstat.setString(1, "兰兰");
			pstat.setInt(2, 90);
			pstat.setInt(3, 88);
			pstat.setString(4, "酷酷的雪纳瑞");
			pstat.execute();
			pstat.setString(1, "奇奇");
			pstat.setInt(2, 100);
			pstat.setInt(3, 80);
			pstat.setString(4, "酷酷的雪纳瑞");
			pstat.execute();
			logger.info("插入成功~~~");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			// 3.关闭连接
			if (pstat != null) {
				try {
					pstat.close();
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
