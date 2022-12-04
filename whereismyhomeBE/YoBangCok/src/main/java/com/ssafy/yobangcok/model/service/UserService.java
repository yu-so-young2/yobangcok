package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.User;

public interface UserService {

	List<User> selectAll();

	User searchById(String id);

	int insert(User user);

	User login(User user);

	int delete(String id);

	int update(User user);

}
