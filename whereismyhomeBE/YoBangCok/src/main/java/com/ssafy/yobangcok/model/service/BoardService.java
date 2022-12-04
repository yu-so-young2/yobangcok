package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.Board;

public interface BoardService {

	List<Board> selectAll(int category);

	int insert(Board board);

	Board select(int boardNo);

	int update(Board board);

	int delete(int boardNo);

	int voteGood(int boardNo);

	int voteBad(int boardNo);

	int createVote(int boardNo);

	int updateView(int boardNo);

}
