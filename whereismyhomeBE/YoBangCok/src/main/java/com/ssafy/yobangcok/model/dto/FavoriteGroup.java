package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;

@Mapper
@ApiModel(value = "FavoriteGroup : 즐겨찾기 그룹 정보", description = "회원이 가지고 있는 즐겨찾기 그룹 목록을 조회한다.")
public class FavoriteGroup {
	
	private int groupNo;
	private String groupName;
	private String userId;
	private int count;
	
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
