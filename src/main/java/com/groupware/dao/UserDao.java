package com.groupware.dao;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.vo.USER_TABLE_VO;

@Mapper
public interface UserDao {
	public int checkId(String USER_ID);

	public void insertUser(USER_TABLE_VO user_table_vo);

	public void updateUser(USER_TABLE_VO user_table_vo);

	public USER_TABLE_VO selectUser(USER_TABLE_VO user_table_vo);
}
