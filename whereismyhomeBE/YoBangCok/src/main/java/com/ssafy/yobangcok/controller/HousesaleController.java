package com.ssafy.yobangcok.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yobangcok.model.dto.HouseSale;
import com.ssafy.yobangcok.model.dto.HouseSaleQna;
import com.ssafy.yobangcok.model.service.SaleQnaService;
import com.ssafy.yobangcok.model.service.SaleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Swagger RestAPI 테스팅 url
//http://localhost:9999/swagger-ui.html#/board-controller

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/housesale")
@Api("아파트 매물 컨트롤러 API V1")
public class HousesaleController {

	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleQnaService saleQnaService;
	

	@GetMapping("")
	@ApiOperation(value = "해당 게시판에 등록된 모든 게시글을 반환한다.", response = HouseSale.class)
	public ResponseEntity<?> searchByAptCode(@RequestParam("aptCode") String aptCode) {
		try {
			System.out.println(aptCode);
			List<HouseSale> saleServices = saleService.searchByAptCode(aptCode);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseSale>>(saleServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/user")
	@ApiOperation(value = "등록한 유저의 모든 게시글을 반환한다.", response = HouseSale.class)
	public ResponseEntity<?> searchByuserId(@RequestParam("user_id") String user_id) {
		try {
			System.out.println(user_id);
			List<HouseSale> saleServices = saleService.searchByUserId(user_id);
			if (saleServices != null && saleServices.size() > 0) {
				return new ResponseEntity<List<HouseSale>>(saleServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/regist")
	@ApiOperation(value = "새로운 매물을 삽입한다.", response = Integer.class)
	public ResponseEntity<?> createNewsale(@RequestBody HouseSale houseSale) {
		try {
			int result = saleService.insert(houseSale);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{saleNo}")
	@ApiOperation(value = "특정 매물 게시글을 반환한다.", response = HouseSale.class)
	public ResponseEntity<?> selectSale(@PathVariable int saleNo) {
		try {
			
			HouseSale result = saleService.selectSale(saleNo);
			if (saleService != null) {
				return new ResponseEntity<HouseSale>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{saleNo}")
	@ApiOperation(value = "id의 테이블을 삭제한다.", response = Integer.class)
	public ResponseEntity<?> delete(@PathVariable int saleNo) {
		try {
			int no = saleService.selectSale(saleNo).getNo();
			int result = saleService.delete(Integer.toString(no));
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/{saleNo}")
	@ApiOperation(value = "id의 테이블을 수정한다.", response = Integer.class)
	public ResponseEntity<?> update(@RequestBody HouseSale houseSale) {
		try {
			int result = saleService.update(houseSale);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	////////////////////////////////////////////////////////////////////////////////
	// QnA
	////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/qna")
	@ApiOperation(value = "새로운 qna를 삽입한다.", response = Integer.class)
	public ResponseEntity<?> createNewQna(@RequestBody HouseSaleQna houseSaleQna) {
		try {
			int result = saleQnaService.insertQuestion(houseSaleQna);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/qna")
	@ApiOperation(value = "qna에 답변을 한다.", response = Integer.class)
	public ResponseEntity<?> updateQna(@RequestBody HouseSaleQna houseSaleQna) {
		try {
			int result = saleQnaService.insertAnswer(houseSaleQna);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/qna/{saleQnaNo}")
	@ApiOperation(value = "특정 질의응답 게시글을 반환한다.", response = HouseSale.class)
	public ResponseEntity<?> selectQna(@PathVariable String saleQnaNo) {
		try {
			
			HouseSaleQna result = saleQnaService.selectQna(saleQnaNo);
			if (saleService != null) {
				return new ResponseEntity<HouseSaleQna>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@GetMapping("/qna/sales")
	@ApiOperation(value = "판매자 기준의 질문 리스트가 나옵니다.", response = HouseSale.class)
	public ResponseEntity<?> searchByQuestion(@RequestParam("userId") String userId) {
		try {
			List<HouseSaleQna> saleQnaServices = saleQnaService.searchByQuestion(userId);
			if (saleQnaServices != null && saleQnaServices.size() > 0) {
				return new ResponseEntity<List<HouseSaleQna>>(saleQnaServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/qna/writer")
	@ApiOperation(value = "질문자 기준의 질문 리스트가 나옵니다.", response = HouseSale.class)
	public ResponseEntity<?> searchByAnswer(@RequestParam("userId") String userId) {
		try {
			List<HouseSaleQna> saleQnaServices = saleQnaService.searchByAnswer(userId);
			if (saleQnaServices != null && saleQnaServices.size() > 0) {
				return new ResponseEntity<List<HouseSaleQna>>(saleQnaServices, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

}