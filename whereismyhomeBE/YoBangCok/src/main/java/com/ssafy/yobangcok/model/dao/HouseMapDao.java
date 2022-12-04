package com.ssafy.yobangcok.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.HouseInfoDto;
import com.ssafy.yobangcok.model.dto.SidoGugunCodeDto;

@Mapper
public interface HouseMapDao {

	List<SidoGugunCodeDto> getSearchDong(String content);
	List<HouseInfoDto> getSearchApt(String content);
	List<HouseInfoDto> selectDong(String dongCode);
	List<HouseInfoDto> selectApt(String apartmentName,String dongCode);
	
	
}
