package com.zx9cv.staris.dao;

import com.zx9cv.staris.vo.userVO;

public interface userDAO {

	boolean check(String userId, String userPw);

	boolean check(String userId);

	void register(userVO user);

	int getIndex(String writer);
	

}
