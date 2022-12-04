package com.ssafy.yobangcok.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yobangcok.model.dao.FavorDao;
import com.ssafy.yobangcok.model.dto.Favorite;
import com.ssafy.yobangcok.model.dto.FavoriteGroup;

@Service
public class FavorServiceImpl implements FavorService {

	@Autowired
	FavorDao favorDao;
	

	@Override
	public int insert(Favorite favorite) {
		return favorDao.insert(favorite);
	}

	
	@Override
	public int delete(int favoriteNo) {
		return favorDao.delete(favoriteNo);
	}


	@Override
	public int insertGroup(FavoriteGroup favoriteGroup) {
		return favorDao.insertGroup(favoriteGroup);
	}


	@Override
	public int modifyGroup(FavoriteGroup favoriteGroup) {
		System.out.println(favoriteGroup.getGroupName()+" "+favoriteGroup.getGroupNo());
		return favorDao.updateGroup(favoriteGroup);
	}


	@Override
	public int deleteGroup(int groupNo) {
		return favorDao.deleteGroup(groupNo);
	}


	@Override
	public List<FavoriteGroup> selectGroup(String id) {
		return favorDao.selectGroup(id);
	}


	@Override
	public int favoriteGroupUpdate(int favoriteNo) {
		// TODO Auto-generated method stub
		return favorDao.favoriteGroupUpdate(favoriteNo);
	}


	@Override
	public List<Favorite> selectFavorite(String favoritegroup) {
		// TODO Auto-generated method stub
		return favorDao.selectFavorite(favoritegroup);
	}

}
