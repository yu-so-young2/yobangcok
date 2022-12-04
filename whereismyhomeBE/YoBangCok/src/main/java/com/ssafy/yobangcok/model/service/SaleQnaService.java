package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dto.HouseSaleQna;

public interface SaleQnaService{
	
	List<HouseSaleQna> searchByQuestion(String userId);
	
	List<HouseSaleQna> searchByAnswer(String userId);
	
	HouseSaleQna selectQna(String qnaId);
	
	int insertQuestion(HouseSaleQna houseSaleQna);
	
	int insertAnswer(HouseSaleQna houseSaleQna);

}
