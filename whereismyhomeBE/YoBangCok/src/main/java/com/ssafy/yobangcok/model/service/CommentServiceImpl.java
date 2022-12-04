package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.CommentDao;
import com.ssafy.yobangcok.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> select(int boardNo) {
		return commentDao.select(boardNo);
	}

	@Override
	public int insert(Comment comment) {
		return commentDao.insert(comment);
	}

	@Override
	public int update(Comment comment) {
		return commentDao.update(comment);
	}

	@Override
	public int delete(int no) {
		return commentDao.delete(no);
	}

}
