package com.ssafy.yobangcok.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.Subscription;

@Mapper
public interface SubsDao {

	int search(Subscription subscription);

	int insert(Subscription subscription);

	int delete(Subscription subscription);

}
