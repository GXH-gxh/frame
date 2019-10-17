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
	// 数据库用户名
	private static String userName;
	// 用户密码
	private static String passWord;
	// 数据库驱动
	private static String driver;
	// 数据库连接路径
	private static String url;
	
	/**
	 * 静态代码块加载驱动
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
	
	// 读取数据库连接配置文件
	public static void readyDataBase() {
		// 1.文件路径
		String filePath = "database.properties";
		// 2.获得InputStream
		InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream(filePath);
		// 3.创建properties读取文件内容
		Properties properties = new Properties();
		// 4.加载配置文件
		try {
			properties.load(is);
			// 5.读取文件内容给属性赋值
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
	 * 获得连接对象
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
	 * 关闭results资源
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
	 * 关闭statement资源
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
	 * 关闭connection资源
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
	 * 释放资源
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
