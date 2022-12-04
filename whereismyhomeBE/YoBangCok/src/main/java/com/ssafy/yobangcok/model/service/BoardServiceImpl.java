package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.BoardDao;
import com.ssafy.yobangcok.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<Board> selectAll(int category) {
		return boardDao.selectAll(category);
	}

	@Override
	public int insert(Board board) {
		return boardDao.insert(board);
	}

	@Override
	public int createVote(int boardNo) {
		return boardDao.createVote(boardNo);
	}

	@Override
	public Board select(int boardNo) {
		return boardDao.select(boardNo);
	}

	@Override
	public int updateView(int boardNo) {
		return boardDao.updateView(boardNo);
	}
	
	@Override
	public int update(Board board) {
		return boardDao.update(board);
	}

	@Override
	public int delete(int boardNo) {
		return boardDao.delete(boardNo);
	}

	@Override
	public int voteGood(int boardNo) {
		return boardDao.voteGood(boardNo);
	}

	@Override
	public int voteBad(int boardNo) {
		return boardDao.voteBad(boardNo);
	}


}
