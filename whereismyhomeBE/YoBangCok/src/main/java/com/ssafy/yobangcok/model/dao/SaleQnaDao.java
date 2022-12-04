package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.HouseSaleQna;

@Mapper
public interface SaleQnaDao {
	
	List<HouseSaleQna> searchByQuestion(String userId);
	
	List<HouseSaleQna> searchByAnswer(String userId);
	
	HouseSaleQna selectQna(String qnaId);
	
	int insertQuestion(HouseSaleQna houseSaleQna);
	
	int insertAnswer(HouseSaleQna houseSaleQna);

}
