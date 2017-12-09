package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 添加数据
 * 
 * @author ASUS
 * 
 */
public class add {
	private static Logger logger = Logger.getLogger(add.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		String name = "欧欧";
		int health = 100;
		int love = 66;
		String strain = "酷酷的雪纳瑞";
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
			// 向数据库中插入一条信息
			// insert into dog
			// (name,health,love,strain)values('欧欧',100,66,'酷酷的雪纳瑞')
			stat = conn.createStatement();
			StringBuffer sbsql = new StringBuffer(
					"insert into dog (name,health,love,strain)values('");
			sbsql.append(name + "',");
			sbsql.append(health + ",");
			sbsql.append(love + ",'");
			sbsql.append(strain + "')");
			stat.execute(sbsql.toString());
			logger.info("插入信息成功~~~");
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
