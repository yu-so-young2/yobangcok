package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.HouseSale;



@Mapper
public interface SaleDao {
	
	List<HouseSale> searchByAptCode(String aptCode);
	
	List<HouseSale> selectUserId(String user_id);
	
	HouseSale selectSale(int saleNo);

	int insert(HouseSale houseSale);

	int delete(String no);

	int update(HouseSale houseSale);
	
	int dealUpdate(int dealBool);
	
	
}
