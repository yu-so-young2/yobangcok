package com.ssafy.yobangcok.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Subscription : 구독정보", description = "해당 회원이 구독한 회원의 아이디를 나타냅니다.")
public class Subscription {
	
	@ApiModelProperty(value = "구독 번호")
	private int no;
	@ApiModelProperty(value = "회원 아이디")
	private String id;
	@ApiModelProperty(value = "구독 회원 아이디")
	private String subscriptionId;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	
}
