package com.ssafy.yobangcok.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.ssafy.yobangcok.model.dao"
)
public class DatabaseConfiguration {}