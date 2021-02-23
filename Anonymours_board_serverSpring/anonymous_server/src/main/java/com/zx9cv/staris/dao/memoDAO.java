package com.zx9cv.staris.dao;

import java.util.List;

import com.zx9cv.staris.vo.memoVO;

public interface memoDAO {
	 void insertMemo(memoVO m);

	List<memoVO> allList();
	
}
