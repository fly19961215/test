package cn.fly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * �������
 * 
 * @author ASUS
 * 
 */
public class add {
	private static Logger logger = Logger.getLogger(add.class.getName());

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		String name = "ŷŷ";
		int health = 100;
		int love = 66;
		String strain = "����ѩ����";
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
			// �����ݿ��в���һ����Ϣ
			// insert into dog
			// (name,health,love,strain)values('ŷŷ',100,66,'����ѩ����')
			stat = conn.createStatement();
			StringBuffer sbsql = new StringBuffer(
					"insert into dog (name,health,love,strain)values('");
			sbsql.append(name + "',");
			sbsql.append(health + ",");
			sbsql.append(love + ",'");
			sbsql.append(strain + "')");
			stat.execute(sbsql.toString());
			logger.info("������Ϣ�ɹ�~~~");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			// 3.�ر�����
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
