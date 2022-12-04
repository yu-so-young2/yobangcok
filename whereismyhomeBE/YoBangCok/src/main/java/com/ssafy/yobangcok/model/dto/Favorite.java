package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "Favorite : 즐겨찾기 매물", description = "회원의 즐겨찾기 정보를 저장합니다.")
public class Favorite {
	@ApiModelProperty(value = "즐겨찾기 번호")
	private int no;
	@ApiModelProperty(value = "매물 번호")
	private int aptSaleNo;
	@ApiModelProperty(value = "그룹 번호")
	private int favoritegroup;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAptSaleNo() {
		return aptSaleNo;
	}
	public int getFavoritegroup() {
		return favoritegroup;
	}
	public void setFavoritegroup(int favoritegroup) {
		this.favoritegroup = favoritegroup;
	}
	public void setAptSaleNo(int aptSaleNo) {
		this.aptSaleNo = aptSaleNo;
	}
	
	
}
