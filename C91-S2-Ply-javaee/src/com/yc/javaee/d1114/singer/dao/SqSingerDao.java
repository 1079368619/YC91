package com.yc.javaee.d1114.singer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yc.javaee.d1114.singer.bean.SqSinger;
import com.yc.javaee.d1114.singer.util.DBHelper;
import com.yc.javaee.d1114.singer.util.DBHelper.ResultSetMapper;

public class SqSingerDao {

	private SqSingerMapper ssMapper = new SqSingerMapper();
	
	public List<SqSinger> selectPage(int page, int pnumber){
		//
		int begin = (page - 1) * pnumber;
		//
		String sql = "select * from sq_singer limit ?, ?";
		try {
			return DBHelper.selectList(sql, ssMapper, begin, pnumber);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int selectCount() {
		String sql = "select count(*) cnt from sq_singer";
		try {
			List<Integer> list = DBHelper.selectList(sql, new ResultSetMapper<Integer>() {
				@Override
				public Integer map(ResultSet rs) throws SQLException {
					return rs.getInt("cnt");
				}
				
			});
			return list.get(0);
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
		if(rs.getString("members") == null) {
			ss.setMembers("");
		}else {
			ss.setMembers(rs.getString("members"));
		}
		ss.setNation(rs.getString("nation"));
		ss.setBirthday(rs.getString("birthday"));
		ss.setHead(rs.getString("head"));
		ss.setLikes(rs.getInt("likes"));
		return ss;
	}

	
}