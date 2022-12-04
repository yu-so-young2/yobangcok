package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.UserDao;
import com.ssafy.yobangcok.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public User searchById(String id) {
		return userDao.searchById(id);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public int delete(String id) {
		return userDao.delete(id);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

}
