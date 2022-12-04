package com.ssafy.yobangcok.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.SubsDao;
import com.ssafy.yobangcok.model.dto.Subscription;

@Service
public class SubsServiceImpl implements SubsService {

	@Autowired
	private SubsDao subsDao;
	
	@Override
	public int search(Subscription subscription) {
		return subsDao.search(subscription);
	}

	@Override
	public int insert(Subscription subscription) {
		return subsDao.insert(subscription);
	}

	@Override
	public int delete(Subscription subscription) {
		return subsDao.delete(subscription);
	}

}
