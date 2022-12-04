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

import com.ssafy.yobangcok.model.dto.HouseInfoDto;
import com.ssafy.yobangcok.model.dto.SidoGugunCodeDto;
import com.ssafy.yobangcok.model.service.HouseMapService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Swagger RestAPI 테스팅 url
//http://localhost:8080/swagger-ui.html#/board-controller

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/housesearch")
@Api("아파트 매물 컨트롤러 API V1")
public class HouseSearchController {

	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseMapService houseMapService;
	

	@GetMapping("autosugestion/dong")
	@ApiOperation(value = "동 이름으로 검색", response = SidoGugunCodeDto.class)
	public ResponseEntity<?> getSearchDong(@RequestParam("content") String content) {
		try {
			System.out.println(content);
			List<SidoGugunCodeDto> saleServices = houseMapService.getSearchDong(content);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<SidoGugunCodeDto>>(saleServices, HttpStatus.OK);
			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				return new ResponseEntity<Void>(HttpStatus.OK);

			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("autosugestion/apt")
	@ApiOperation(value = "아파트 이름으로 검색", response = HouseInfoDto.class)
	public ResponseEntity<?> getSearchApt(@RequestParam("content") String content) {
		try {
			System.out.println(content);
			List<HouseInfoDto> saleServices = houseMapService.getSearchApt(content);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseInfoDto>>(saleServices, HttpStatus.OK);
			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/dong")
	@ApiOperation(value = "동 이름으로 검색 결과", response = HouseInfoDto.class)
	public ResponseEntity<?> selectDong(@RequestParam("dongCode") String dongCode) {
		try {
			List<HouseInfoDto> saleServices = houseMapService.selectDong(dongCode);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseInfoDto>>(saleServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/apt")
	@ApiOperation(value = "아파트 이름으로 검색 결과", response = HouseInfoDto.class)
	public ResponseEntity<?> selectApt(@RequestParam("apartmentName") String apartmentName,@RequestParam("dongCode") String dongCode) {
		try {
			List<HouseInfoDto> saleServices = houseMapService.selectApt(apartmentName,dongCode);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseInfoDto>>(saleServices, HttpStatus.OK);
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