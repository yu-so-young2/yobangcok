package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "HouseDeal : 거래된 아파트 정보", description = "거래된 아파트 상세 정보를 나타낸다.")
public class HouseDeal {

	@ApiModelProperty(value = "아파트정보번호")
	private long no;
	@ApiModelProperty(value = "판매가격")
	private String dealAmount;
	@ApiModelProperty(value = "년")
	private String dealYear;
	@ApiModelProperty(value = "월")
	private String dealMonth;
	@ApiModelProperty(value = "일")
	private String dealDay;
	@ApiModelProperty(value = "면적")
	private String area;
	@ApiModelProperty(value = "층수")
	private int floor;
	@ApiModelProperty(value = "아파트일련번호")
	private long aptCode;

	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
}
