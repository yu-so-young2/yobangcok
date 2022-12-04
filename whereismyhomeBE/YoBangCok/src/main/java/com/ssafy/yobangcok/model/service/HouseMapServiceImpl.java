package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.HouseMapDao;
import com.ssafy.yobangcok.model.dto.HouseInfoDto;
import com.ssafy.yobangcok.model.dto.SidoGugunCodeDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapDao houseMapDao;

	
	@Override
	public List<SidoGugunCodeDto> getSearchDong(String content) {
		// TODO Auto-generated method stub
		return houseMapDao.getSearchDong(content);
	}

	@Override
	public List<HouseInfoDto> getSearchApt(String content) {
		// TODO Auto-generated method stub
		return houseMapDao.getSearchApt(content);
	}
	
	@Override
	public List<HouseInfoDto> selectDong(String dongCode) {
		// TODO Auto-generated method stub
		return houseMapDao.selectDong(dongCode);
	}
	
	@Override
	public List<HouseInfoDto> selectApt(String apartmentName,String dongCode) {
		// TODO Auto-generated method stub
		return houseMapDao.selectApt(apartmentName,dongCode);
	}
	

}
