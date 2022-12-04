package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.DealDao;
import com.ssafy.yobangcok.model.dto.HouseDeal;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private DealDao dealDao;
	
	@Override
	public List<HouseDeal> searchByAptCode(String aptCode) {
		// TODO Auto-generated method stub
		return dealDao.searchByAptCode(aptCode);
	}

}
