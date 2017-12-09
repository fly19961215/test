package zuoye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Two {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 2.建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/back", "root", "root");
			// 插入数据
			pstmt = conn.prepareStatement("select count(*)from student");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("总记录：" + rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.关闭连接
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
