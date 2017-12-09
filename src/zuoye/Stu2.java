package zuoye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stu2 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 2.��������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/back", "root", "root");
			// ��ѯ���� ��ʾidֵ��nameֵ
			pstmt = conn.prepareStatement("select * from student");
			rs = pstmt.executeQuery();
			System.out.println("\tѧ����Ϣ��");
			System.out.println("���\t����");
			while (rs.next()) {
				System.out.print(+rs.getInt(1) + "\t");
				System.out.println(rs.getString(2));
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
