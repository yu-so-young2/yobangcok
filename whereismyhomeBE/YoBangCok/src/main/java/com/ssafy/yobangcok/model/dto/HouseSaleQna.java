package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "HouseSaleQan : 아파트 매물 질의응답", description = "특정 매물의 질의응답을 확인합니다.")
public class HouseSaleQna {

	@ApiModelProperty(value = "매물 고유 번호")
	private int no;
	@ApiModelProperty(value = "아파트 번호")
	private int house_sale_no;
	@ApiModelProperty(value = "매물 등록 회원 아이디")
	private String sale_user_id;
	@ApiModelProperty(value = "작성 회원 아이디")
	private String writer_user_id;
	@ApiModelProperty(value = "질의내용")
	private String contentQ;
	@ApiModelProperty(value = "응답내용")
	private String contentN;
	@ApiModelProperty(value = "작성일")
	private int created_time;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHouse_sale_no() {
		return house_sale_no;
	}
	public void setHouse_sale_no(int house_sale_no) {
		this.house_sale_no = house_sale_no;
	}
	public String getSale_user_id() {
		return sale_user_id;
	}
	public void setSale_user_id(String sale_user_id) {
		this.sale_user_id = sale_user_id;
	}
	public String getWriter_user_id() {
		return writer_user_id;
	}
	public void setWriter_user_id(String writer_user_id) {
		this.writer_user_id = writer_user_id;
	}
	public String getContentQ() {
		return contentQ;
	}
	public void setContentQ(String contentQ) {
		this.contentQ = contentQ;
	}
	public String getContentN() {
		return contentN;
	}
	public void setContentN(String contentN) {
		this.contentN = contentN;
	}
	public int getCreated_time() {
		return created_time;
	}
	public void setCreated_time(int created_time) {
		this.created_time = created_time;
	}
	
	
	
}
