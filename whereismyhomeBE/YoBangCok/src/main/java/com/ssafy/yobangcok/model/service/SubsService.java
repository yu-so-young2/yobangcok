package com.ssafy.yobangcok.model.service;

import com.ssafy.yobangcok.model.dto.Subscription;

public interface SubsService {

	int search(Subscription subscription);

	int insert(Subscription subscription);

	int delete(Subscription subscription);

}
