package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.SaleDao;
import com.ssafy.yobangcok.model.dto.HouseSale;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleDao saleDao;
	
	@Override
	public List<HouseSale> searchByAptCode(String aptCode) {
		// TODO Auto-generated method stub
		return saleDao.searchByAptCode(aptCode);
	}

	@Override
	public int insert(HouseSale houseSale) {
		// TODO Auto-generated method stub
		return saleDao.insert(houseSale);
	}

	@Override
	public int delete(String no) {
		// TODO Auto-generated method stub
		return saleDao.delete(no);
	}

	@Override
	public int update(HouseSale houseSale) {
		// TODO Auto-generated method stub
		return saleDao.update(houseSale);
	}

	@Override
	public int dealUpdate(int dealBool) {
		// TODO Auto-generated method stub
		return saleDao.dealUpdate(dealBool);
	}

	@Override
	public HouseSale selectSale(int saleNo) {
		// TODO Auto-generated method stub
		return saleDao.selectSale(saleNo);
	}

	@Override
	public List<HouseSale> searchByUserId(String user_id) {
		// TODO Auto-generated method stub
		return saleDao.selectUserId(user_id);
	}

}
