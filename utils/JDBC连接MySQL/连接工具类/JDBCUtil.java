package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	// ���ݿ��û���
	private static String userName;
	// �û�����
	private static String passWord;
	// ���ݿ�����
	private static String driver;
	// ���ݿ�����·��
	private static String url;
	
	/**
	 * ��̬������������
	 */
	static{
		try {
			readyDataBase();
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ��ȡ���ݿ����������ļ�
	public static void readyDataBase() {
		// 1.�ļ�·��
		String filePath = "database.properties";
		// 2.���InputStream
		InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream(filePath);
		// 3.����properties��ȡ�ļ�����
		Properties properties = new Properties();
		// 4.���������ļ�
		try {
			properties.load(is);
			// 5.��ȡ�ļ����ݸ����Ը�ֵ
			userName = properties.getProperty("username");
			passWord = properties.getProperty("password");
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Ӷ���
	 * package:java.sql.Connection
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ر�results��Դ
	 * @param rs
	 * package:java.sql.ResultSet
	 */
	public static void closeResult(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ر�statement��Դ
	 * @param ps
	 * package:java.sql.Statement
	 */
	public static void closeStatement(Statement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ر�connection��Դ
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ͷ���Դ
	 * @param conn
	 * @param rs
	 * @param ps
	 */
	public static void closeJDBC(Connection conn, ResultSet rs, Statement ps) {
		closeResult(rs);
		closeStatement(ps);
		closeConnection(conn);
	}
}
