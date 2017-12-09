package zuoye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Stu1 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			// ��������
			pstmt = conn
					.prepareStatement("insert into student(id,name)values(?,?)");
			pstmt.setInt(1, 1);
			pstmt.setString(2, "С��");
			pstmt.executeUpdate();
			pstmt.setInt(1, 2);
			pstmt.setString(2, "С��");
			pstmt.execute();

			System.out.println("��ӳɹ�~~~");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.�ر�����
			try {
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
