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
 *  ���ݿ����
 */
public class DBUtil {

	public static String url = "jdbc:mysql://localhost:3306/hospital?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
	public static String user = "root";
	public static String password = "";
	public static String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * ��������
	 */
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������
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
	 * ִ�и���
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
	 * ִ�в�ѯ
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
			//ִ�� ��ѯ
			result = statement.executeQuery();
			//result.getMetaData():�õ�������Ľṹ��Ϣ�����磺�ֶ������ֶ���
			ResultSetMetaData metaData = result.getMetaData();
			//��ȡ�����������������ԣ�����˽�е�
			//metaData.getColumnCount():�õ��ֶ���
			int columnCount = metaData.getColumnCount();
			while(result.next()) {
				T instance = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//����,���»���
					String columnLabel = metaData.getColumnLabel(i+1);
					//��DB������ת��Ϊjava��������շ�����
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
	 * ���ݿ����»�����������ת��Ϊjava�����շ�ʽ����
	 * @param dbname
	 * @return
	 */
	public static String nameInDb2nameInJava(String nameInDb) {
	    String coluname = nameInDb.toLowerCase();
	    //����
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
	 * �ͷ���Դ
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
