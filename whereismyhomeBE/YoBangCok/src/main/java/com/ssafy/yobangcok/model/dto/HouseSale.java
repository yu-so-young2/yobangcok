package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "HouseSale : 아파트 매물 정보", description = "매물의 상세 정보를 나타낸다.")
public class HouseSale {

	@ApiModelProperty(value = "매물 고유 번호")
	private int no;
	@ApiModelProperty(value = "아파트 번호")
	private long apt_code;
	@ApiModelProperty(value = "회원 아이디")
	private String user_id;
	@ApiModelProperty(value = "아파트 매물 내용")
	private String sale_content;
	@ApiModelProperty(value = "아파트 매물 가격")
	private String sale_amount;
	@ApiModelProperty(value = "판매 유무")
	private int deal_bool;
	@ApiModelProperty(value = "아파트 이름")
	private String apt_name;
	@ApiModelProperty(value = "아파트 동")
	private String apt_dong;
	@ApiModelProperty(value = "아파트 호")
	private String apt_ho;
	
	public String getApt_dong() {
		return apt_dong;
	}
	public void setApt_dong(String apt_dong) {
		this.apt_dong = apt_dong;
	}
	public String getApt_ho() {
		return apt_ho;
	}
	public void setApt_ho(String apt_ho) {
		this.apt_ho = apt_ho;
	}

	public String getApt_name() {
		return apt_name;
	}
	public void setApt_name(String apt_name) {
		this.apt_name = apt_name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public long getApt_code() {
		return apt_code;
	}
	public void setApt_code(long apt_code) {
		this.apt_code = apt_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSale_content() {
		return sale_content;
	}
	public void setSale_content(String sale_content) {
		this.sale_content = sale_content;
	}
	public String getSale_amount() {
		return sale_amount;
	}
	public void setSale_amount(String sale_amount) {
		this.sale_amount = sale_amount;
	}
	public int getDeal_bool() {
		return deal_bool;
	}
	public void setDeal_bool(int deal_bool) {
		this.deal_bool = deal_bool;
	}
	

}
