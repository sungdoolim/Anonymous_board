package com.zx9cv.staris.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zx9cv.staris.vo.memoVO;

@Repository
public class memodaoImpl implements memoDAO{
	@Autowired
	private SqlSession sqlSession;// 자동 의존성 주입 => mybatis쿼리문 수행객체
	public void insertMemo(memoVO m) {
		sqlSession.insert("insertMemo", m);
		
	}
	@Override
	public List<memoVO> allList() {
		List<memoVO>mlist=sqlSession.selectList("allmemoList");
		return mlist;
	}
	@Override
	public void delOne(memoVO m) {
		// TODO Auto-generated method stub
		sqlSession.delete("delOne", m);
	}
}
