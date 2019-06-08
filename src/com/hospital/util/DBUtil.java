package com.hospital.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *  数据库管理
 */
public class DBUtil {

	public static String url = "jdbc:mysql://localhost:3306/hospital?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
	public static String user = "root";
	public static String password = "";
	public static String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * 加载驱动
	 */
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得连接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 执行更新
	 * @param sql
	 * @param args
	 * @return
	 */
	public static int exeUpdate(String sql , Object ... args) {
		PreparedStatement statement = null;
		Connection conn =  DBUtil.getConnection();
		try {
			statement = conn.prepareStatement(sql);
			if(args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					statement.setObject(i + 1, args[i]);
				}
			}
			System.out.println(sql);
			int index = statement.executeUpdate();
			return index;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, statement, conn);
		}
		return 0;
	}
	
	/**
	 * 执行查询
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public static <T> List<T> exeQuery(String sql , Class<T> clazz , Object ... args) {
		List<T> list = new ArrayList<>();
		PreparedStatement statement = null;
		Connection conn =  DBUtil.getConnection();
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(sql);
			if(args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					statement.setObject(i + 1, args[i]);
				}
			}
			//执行 查询
			result = statement.executeQuery();
			//result.getMetaData():得到结果集的结构信息，比如：字段名，字段数
			ResultSetMetaData metaData = result.getMetaData();
			//获取到传入的类的所有属性，包括私有的
			//metaData.getColumnCount():得到字段数
			int columnCount = metaData.getColumnCount();
			while(result.next()) {
				T instance = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//列名,带下划线
					String columnLabel = metaData.getColumnLabel(i+1);
					//将DB的列名转换为java里的属性驼峰命名
					Field field = clazz.getDeclaredField(nameInDb2nameInJava(columnLabel));
					field.setAccessible(true);
					Object object = result.getObject(columnLabel);
					if(object != null) {
						field.set(instance, object);
					}
				}
				list.add(instance);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(result, statement, conn);
		}
		return null;
	}
	
	/**
	 * 数据库里下划线命名规则转化为java里面驼峰式命名
	 * @param dbname
	 * @return
	 */
	public static String nameInDb2nameInJava(String nameInDb) {
	    String coluname = nameInDb.toLowerCase();
	    //正则
	    if (Pattern.compile("^\\S+_+\\S+$").matcher(coluname).find()) {
	        char[] ca = coluname.toCharArray();
	        for (int j = 1; j < ca.length - 1; j++) {
	            if (ca[j]=='_') {
	                ca[j]='\0';
	                ca[j + 1] = Character.toUpperCase(ca[j + 1]);
	            }
	        }
	        coluname = new String(ca);
	    }
	    return coluname.replaceAll("\0", "");
	}
	
	/**
	 * 释放资源
	 * @param result
	 * @param statement
	 * @param conn
	 */
	public static void closeAll(ResultSet result ,PreparedStatement statement ,Connection conn) {
		try {
			if(result != null) {
				result.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
