package com.ssafy.yobangcok.model.service;

import java.util.List;

import com.ssafy.yobangcok.model.dto.Favorite;
import com.ssafy.yobangcok.model.dto.FavoriteGroup;

public interface FavorService {

	int delete(int favoriteNo);

	int insert(Favorite favorite);

	int insertGroup(FavoriteGroup favoriteGroup);

	int modifyGroup(FavoriteGroup favoriteGroup);

	int deleteGroup(int groupNo);
	
	int favoriteGroupUpdate(int favoriteNo);
	
	List<Favorite> selectFavorite(String favoritegroup);

	List<FavoriteGroup> selectGroup(String id);

}
