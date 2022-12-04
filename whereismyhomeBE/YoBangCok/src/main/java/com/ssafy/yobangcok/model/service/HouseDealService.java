package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.HouseDeal;


public interface HouseDealService {

	List<HouseDeal> searchByAptCode(String aptCode);
	
}
