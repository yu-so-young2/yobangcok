package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.HouseDeal;

@Mapper
public interface DealDao {
	
	List<HouseDeal> searchByAptCode(String aptCode);

}
