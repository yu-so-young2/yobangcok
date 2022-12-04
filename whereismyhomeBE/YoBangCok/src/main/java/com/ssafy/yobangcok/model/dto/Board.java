package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "Board : 게시글", description = "게시글 정보를 나타낸다.")
public class Board {
	
	@ApiModelProperty(value = "게시글 번호")
	int no;
	@ApiModelProperty(value = "카테고리 번호")
	int category;
	@ApiModelProperty(value = "게시글 제목")
	String title;
	@ApiModelProperty(value = "게시글 내용")
	String content;
	@ApiModelProperty(value = "작성자 아이디")
	String userId;
	@ApiModelProperty(value = "작성자 닉네임")
	String nickname;
	@ApiModelProperty(value = "글 작성 시간")
	String createdTime;
	@ApiModelProperty(value = "게시글 조회수")
	int view;
	@ApiModelProperty(value = "게시글 추천 수")
	int good;
	@ApiModelProperty(value = "게시글 비추천 수")
	int bad;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	
	

}
