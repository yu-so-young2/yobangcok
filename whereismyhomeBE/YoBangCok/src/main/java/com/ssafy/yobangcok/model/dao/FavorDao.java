package com.ssafy.yobangcok.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.yobangcok.model.dto.Favorite;
import com.ssafy.yobangcok.model.dto.FavoriteGroup;

@Mapper
public interface FavorDao {

	int insert(Favorite favorite);

	int delete(int favoriteNo);

	int insertGroup(FavoriteGroup favoriteGroup);

	int updateGroup(FavoriteGroup favoriteGroup);

	int deleteGroup(int groupNo);
	
	int favoriteGroupUpdate(int favoriteNo);
	
	List<Favorite> selectFavorite(String favoritegroup);

	List<FavoriteGroup> selectGroup(String id);

	

}
