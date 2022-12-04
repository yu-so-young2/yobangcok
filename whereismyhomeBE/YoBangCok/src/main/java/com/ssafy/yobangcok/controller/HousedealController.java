package com.ssafy.yobangcok.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yobangcok.model.dto.HouseDeal;
import com.ssafy.yobangcok.model.dto.SidoGugunCodeDto;
import com.ssafy.yobangcok.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/housedeal")
@Api("아파트 매물 컨트롤러 API V1")
public class HousedealController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseDealService HouseDealService;

	@GetMapping("/apt")
	@ApiOperation(value = "아파트 코드를 통해 전체 거래 정보를 확인", response = SidoGugunCodeDto.class)
	public ResponseEntity<?> searchByAptCode(@RequestParam("aptCode") String aptCode) {
		try {
			List<HouseDeal> saleServices = HouseDealService.searchByAptCode(aptCode);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseDeal>>(saleServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}


	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
