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
			pstmt = conn
					.prepareStatement("insert into student(id,name)values(?,?)");
			pstmt.setInt(1, 1);
			pstmt.setString(2, "小黑");
			pstmt.executeUpdate();
			pstmt.setInt(1, 2);
			pstmt.setString(2, "小白");
			pstmt.execute();

			System.out.println("添加成功~~~");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.关闭连接
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
