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
		System.out.println("\t�������˵�½");
		input = new Scanner(System.in);
		System.out.print("�������û�����");
		String name = input.next();
		System.out.print("���������룺");
		String pwd = input.next();
		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 2.��������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epet", "root", "root");
			// �ж��û��Ƿ��½�ɹ�
			pstmt = conn.prepareStatement("select * from master where name='"
					+ name + "'and password='" + pwd + "'");
			System.out.println("select * from master where name='" + name
					+ "'and password='" + pwd + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("��½�ɹ�~~~��ӭ��~~~");
			} else {
				System.out.println("��½ʧ�ܣ������´�ע��!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.�ر�����
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
