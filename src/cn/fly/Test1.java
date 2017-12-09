package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * java��ʽ���Ӻ͹ر����ݿ�
 * 
 * @author ASUS
 * 
 */
public class Test1 {
	private static Logger logger = Logger.getLogger(Test1.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		// 1.��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		// 2.��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epet", "fly", "0000");
			System.out.println("�������ӳɹ�~~");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			// 3.�ر�����
			if (conn != null) {
				try {
					conn.close();
					System.out.println("�ر����ӳɹ�~~");
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
	}
}
