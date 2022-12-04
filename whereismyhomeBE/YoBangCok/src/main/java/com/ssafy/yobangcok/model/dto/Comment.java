package com.ssafy.yobangcok.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comment : 댓글", description = "댓글 정보를 반환한다.")
public class Comment {
	
	@ApiModelProperty(value = "댓글 번호")
	int no;
	@ApiModelProperty(value = "게시글 번호")
	int boardNo;
	@ApiModelProperty(value = "댓글 내용")
	String content;
	@ApiModelProperty(value = "댓글 작성자 아이디")
	String userId;
	@ApiModelProperty(value = "댓글 작성자 닉네임")
	String nickname;
	@ApiModelProperty(value = "댓글 작성 시간")
	String createdTime;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	
	

}
