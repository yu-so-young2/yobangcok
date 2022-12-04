package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.Comment;

@Mapper
public interface CommentDao {

	public List<Comment> select(int boardNo);

	public int insert(Comment comment);

	public int update(Comment comment);

	public int delete(int no);

}
