package com.zx9cv.staris.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zx9cv.staris.vo.memoVO;

@Repository
public class memodaoImpl implements memoDAO{
	@Autowired
	private SqlSession sqlSession;// �ڵ� ������ ���� => mybatis������ ���ఴü
	public void insertMemo(memoVO m) {
		sqlSession.insert("insertMemo", m);
		
	}
}
