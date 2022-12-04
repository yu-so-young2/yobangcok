package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.Comment;

public interface CommentService {

	List<Comment> select(int boardNo);

	int insert(Comment comment);

	int update(Comment comment);

	int delete(int no);

}
