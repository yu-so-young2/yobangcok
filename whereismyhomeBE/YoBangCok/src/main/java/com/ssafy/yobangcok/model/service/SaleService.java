package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.HouseSale;

public interface SaleService {
	
	List<HouseSale> searchByAptCode(String aptCode);
	
	List<HouseSale> searchByUserId(String user_id);
	
	HouseSale selectSale(int saleNo);

	int insert(HouseSale houseSale);

	int delete(String no);

	int update(HouseSale houseSale);
	
	int dealUpdate(int dealBool);
	
}
