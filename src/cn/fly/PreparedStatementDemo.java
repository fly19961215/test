package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * ʹ��preparedStatement��������
 * 
 * @author ASUS
 * 
 */
public class PreparedStatementDemo {
	private static Logger logger = Logger.getLogger(PreparedStatementDemo.class
			.getName());

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstat = null;
		// 1.��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		// 2.��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epet", "root", "root");
			// ���¹�����Ϣ�����ݿ�
			String sql = "update dog set health=?,love=? where id =?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, 88);
			pstat.setInt(2, 88);
			pstat.setInt(3, 1);
			pstat.executeUpdate();
			logger.info("�ɹ���������~~~");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			// 3.�ر�����
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
