package com.yc.javaee.d1114.singer.util;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.yc.javaee.d1114.singer.bean.SqSinger;
import com.yc.javaee.d1114.singer.dao.SqSingerDao;

public class DBHelper {

	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;

	/**
	 * 静态块，初始化
	 */
	static {
		try {
			// 读取配置文件 ==> 通过类加载器读取类路径里面的文件
			String path = "com/yc/javaee/d1114/singer/conn.properties";
			InputStream in = DBHelper.class.getClassLoader().getResourceAsStream(path);
			// 创建集合对象
			Properties prop = new Properties();
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			name = prop.getProperty("name");
			pwd = prop.getProperty("pwd");
			Class.forName(driver);
		} catch (Exception e) {
			// 异常转型 + 抛出运行期异常
			// 块不能直接抛出编译器异常
			throw new RuntimeException(e);
		}

	}

	/**
	 * 获取连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, name, pwd);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		System.out.println("conn:"+conn);
		
		List<SqSinger> list1 = selectList("select * from sq_singer where id < 30",
				new ResultSetMapper<SqSinger>() {
					@Override
					public SqSinger map(ResultSet rs) throws SQLException {
						SqSinger ss = new SqSinger();
						ss.setId(rs.getInt("id"));
						ss.setName(rs.getString("name"));
						ss.setCategory(rs.getString("category"));
						ss.setMembers(rs.getString("members"));
						ss.setNation(rs.getString("nation"));
						ss.setBirthday(rs.getString("birthday"));
						ss.setIntro(rs.getString("intro"));
						ss.setHead(rs.getString("head"));
						ss.setLikes(rs.getInt("likes"));
						return ss;
					}
			
		});
		
		System.out.println("list1的size:"+list1.size());
		System.out.println("list1:"+list1);
		
		
		List<SqSinger> list2 = selectList("select * from sq_singer where id < 10",
				(rs)->{
					SqSinger ss = new SqSinger();
					ss.setId(rs.getInt("id"));
					ss.setName(rs.getString("name"));
					return null;
		});
		System.out.println("list2:"+list2);	
		
		
		SqSingerDao dao = new SqSingerDao();
		List<SqSinger> list = dao.selectPage(1, 10);
		for(SqSinger s : list) {
			System.out.println(s.getId() + s.getName());
		}
		List<String> list3 = dao.selectCategory();
		for(String s : list3) {
			System.out.println(s);
		}
		int result = dao.deleteByid(789);
		if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");

		}
	}

	/**
	 * @param <T>
	 * @param sql
	 * @param callback
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> selectList(
			String sql, 
			ResultSetMapper<T> callback,
			Object...params) throws SQLException {
		System.out.println("SQL:" + sql);
		System.out.println("参数:" + Arrays.toString(params));
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			//
			List<T> ret = new ArrayList<>();
			for(; rs.next();) {
				T t = callback.map(rs);
				ret.add(t);
			}
			return ret;
		} finally {
			conn.close();
		}
	}
	
	public int excuteDML(String sql, Object...params) throws SQLException {
		// 获取Connection连接对象
		Connection conn = null;
		// 获取编译执行对象
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return result;
	}


	/**
	 * 
	 * @param <T>
	 */
	public static interface ResultSetMapper<T>{
		T map(ResultSet rs) throws SQLException;
	}
}
