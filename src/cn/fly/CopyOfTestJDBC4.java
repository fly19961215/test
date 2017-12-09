package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * ʹ��preparedStatement�����¼
 * 
 * @author ASUS
 * 
 */
public class CopyOfTestJDBC4 {
	private static Logger logger = Logger.getLogger(CopyOfTestJDBC4.class
			.getName());

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
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
			// ���빷����Ϣ�����ݿ�
			pstat = conn.prepareStatement("select *  from dog");
			rs = pstat.executeQuery();
			System.out.println("\t\t������Ϣ�б�");
			System.out.println("���\t����\t����ֵ\t���ܶ�\tƷ��");
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getInt(3) + "\t");
				System.out.print(rs.getInt(4) + "\t");
				System.out.println(rs.getString(5));
			}
			logger.info("��ѯ�ɹ�~~~");
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
