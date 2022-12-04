package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.HouseInfoDto;
import com.ssafy.yobangcok.model.dto.SidoGugunCodeDto;


public interface HouseMapService {

	List<SidoGugunCodeDto> getSearchDong(String content);
	List<HouseInfoDto> getSearchApt(String content);
	List<HouseInfoDto> selectDong(String dongCode);
	List<HouseInfoDto> selectApt(String apartmentName,String dongCode);
	
	
}
