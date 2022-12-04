package com.ssafy.yobangcok.model.dto;

import org.apache.ibatis.annotations.Mapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Mapper
@ApiModel(value = "User : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class User {

	@ApiModelProperty(value = "회원 아이디")
	private String id;
	@ApiModelProperty(value = "회원 비밀번호")
	private String password;
	@ApiModelProperty(value = "회원 이름")
	private String name;
	@ApiModelProperty(value = "회원 닉네임")
	private String nickname;
	@ApiModelProperty(value = "회원 생년월일")
	private String birth;
	@ApiModelProperty(value = "회원 이메일")
	private String mail;
	@ApiModelProperty(value = "회원 집주소")
	private String addr;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
