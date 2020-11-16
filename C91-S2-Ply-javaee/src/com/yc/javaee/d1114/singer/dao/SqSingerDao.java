package com.yc.javaee.d1114.singer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yc.javaee.d1114.singer.bean.SqSinger;
import com.yc.javaee.d1114.singer.util.DBHelper;
import com.yc.javaee.d1114.singer.util.DBHelper.ResultSetMapper;

public class SqSingerDao {

	private SqSingerMapper ssMapper = new SqSingerMapper();
	
	public List<SqSinger> selectPage(){
		String sql = "select * from sq_singer";
		try {
			return DBHelper.selectList(sql, ssMapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

class SqSingerMapper implements ResultSetMapper<SqSinger> {

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

	
}