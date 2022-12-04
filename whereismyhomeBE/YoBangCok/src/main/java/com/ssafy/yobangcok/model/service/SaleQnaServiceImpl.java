package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.SaleQnaDao;
import com.ssafy.yobangcok.model.dto.HouseSaleQna;

@Service
public class SaleQnaServiceImpl implements SaleQnaService {

	@Autowired
	private SaleQnaDao saleQnaDao;
	

	@Override
	public int insertQuestion(HouseSaleQna houseSaleQna) {
		// TODO Auto-generated method stub
		return saleQnaDao.insertQuestion(houseSaleQna);
	}

	@Override
	public int insertAnswer(HouseSaleQna houseSaleQna) {
		// TODO Auto-generated method stub
		return saleQnaDao.insertAnswer(houseSaleQna);
	}

	@Override
	public List<HouseSaleQna> searchByQuestion(String userId) {
		// TODO Auto-generated method stub
		return saleQnaDao.searchByQuestion(userId);
	}

	@Override
	public List<HouseSaleQna> searchByAnswer(String userId) {
		// TODO Auto-generated method stub
		return saleQnaDao.searchByAnswer(userId);
	}

	@Override
	public HouseSaleQna selectQna(String qnaId) {
		// TODO Auto-generated method stub
		return saleQnaDao.selectQna(qnaId);
	}

}
