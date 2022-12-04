package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.Board;

@Mapper
public interface BoardDao {

	List<Board> selectAll(int category);

	int insert(Board board);
	int createVote(int boardNo);

	Board select(int boardNo);

	int update(Board board);

	int delete(int boardNo);

	int voteGood(int boardNo);

	int voteBad(int boardNo);

	int updateView(int boardNo);



}
