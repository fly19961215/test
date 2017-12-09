package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test1 {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		showFirstMenu();
	}

	/**
	 * һ���˵�
	 */
	public static void showFirstMenu() {
		System.out.println("��ӭ����**********");
		System.out.println("1ע�� 2.��½3.�˳�");
		System.out.println("��ѡ��----��");
		int choose = input.nextInt();
		switch (choose) {
		case 1:// ע��
			register();
			break;
		case 2:// ��½
			login();
			break;
		case 3:// �˳�
			System.out.println("~~~��ӭ�´ι���~~~");
			break;

		}
	}

	/**
	 * ��½�ķ���
	 */
	private static void login() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		// ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");// ������Ƽ���������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("�������½���ǳƣ�");
			String loginname = input.next();
			System.out.println("�������½�����룺");
			String password = input.next();
			stat = conn.createStatement();
			// ʹ�� "+����+"ƴ��
			String sql = "select * from easybuy_user where loginName='"
					+ loginname + "'and password='" + password + "'";
			rs = stat.executeQuery(sql);
			System.out.println("\t\t�����ϢΪ");
			System.out.println("���\t�ǳ�\t�û���\t����\t�Ա�");
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.print(rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 * �����˵�
		 */
		secondary();

	}

	/**
	 * �����˵�
	 */
	private static void secondary() {
		System.out.println("\n��������Ҫ���еĲ���");
		System.out.println("1.ɾ�� 2.�޸� 3.������һ��");
		int choose = input.nextInt();
		switch (choose) {
		case 1:// ɾ��
			delete();
			break;
		case 2:// �޸�
			Update();
			break;
		case 3:// ������һ��
			showFirstMenu();
			break;
		}
	}

	/**
	 * �޸�
	 */
	private static void Update() {
		Connection conn = null;
		Statement stat = null;
		// ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("������Ҫ�޸ĵĵ��ǳƣ�");
			String loginname = input.next();
			System.out.println("������Ҫ��ʵ������");
			String usename = input.next();
			System.out.println("������Ҫ�޸ĵ����룺");
			String password = input.next();
			System.out.println("������Ҫ�޸ĵ��Ա�(1�� --0Ů)");
			int sex = input.nextInt();
			String sql = "update easybuy_user set loginName='" + loginname
					+ "',userName='" + usename + "',password='" + password
					+ "',sex=" + sex + " where loginName='" + loginname + "'";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		secondary();// �޸��귵�ض����˵�
	}

	/**
	 * ɾ��
	 */
	private static void delete() {
		Connection conn = null;
		Statement stat = null;
		// ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("������Ҫ�޸ĵĵ��ǳƣ�");
			String loginname = input.next();

			String sql = "delete from  easybuy_user where loginName='"
					+ loginname + "'";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("ɾ���ɹ�");
			} else {
				System.out.println("û�и��û�������ɾ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		secondary();// ɾ���귵�ض����˵�
	}

	/**
	 * ע��ķ���
	 */
	private static void register() {
		Connection conn = null;
		Statement stat = null;
		// ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/easybuy", "root", "root");
			System.out.println("������ע����ǳƣ�");
			String loginname = input.next();
			System.out.println("������ע�����ʵ������");
			String usename = input.next();
			System.out.println("������ע������룺");
			String password = input.next();
			System.out.println("������ע����Ա�(1�� --0Ů)");
			int sex = input.nextInt();
			String sql = "insert into easybuy_user(loginName,userName,password,sex)values('"
					+ loginname
					+ "','"
					+ usename
					+ "','"
					+ password
					+ "',"
					+ sex + ")";
			stat = conn.createStatement();
			int rowNum = stat.executeUpdate(sql);
			if (rowNum > 0) {
				System.out.println("ע��ɹ�");
			} else {
				System.out.println("ע��ʧ��");
			}
			showFirstMenu();// ����ע���Ƿ�ɹ���Ҫ��ʾһ���˵�
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
