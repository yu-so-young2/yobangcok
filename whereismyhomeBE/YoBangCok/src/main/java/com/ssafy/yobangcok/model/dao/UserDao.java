package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.User;

@Mapper
public interface UserDao {

	List<User> selectAll();

	User searchById(String id);

	User login(User user);

	int insert(User user);

	int delete(String id);

	int update(User user);

}
