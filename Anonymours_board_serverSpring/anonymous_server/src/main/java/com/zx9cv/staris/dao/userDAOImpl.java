package com.zx9cv.staris.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zx9cv.staris.vo.memoVO;
import com.zx9cv.staris.vo.userVO;

@Repository
public class userDAOImpl implements userDAO{

	@Autowired
	private SqlSession sqlSession;// �ڵ� ������ ���� => mybatis������ ���ఴü
	@Override
	public boolean check(String userId, String userPw) {
		// TODO Auto-generated method stub
		userVO user=sqlSession.selectOne("selectOneUser",userId);
		//System.out.println(user.getPw()+" "+userPw);
		if(user!=null &&userPw.equals(user.getPw())) {
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public boolean check(String userId) {
		// TODO Auto-generated method stub
		userVO user=sqlSession.selectOne("selectOneUser",userId);
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void register(userVO user) {
		// TODO Auto-generated method stub
		
		sqlSession.insert("userRegister", user);
		
		
	}
	@Override
	public int getIndex(String writer) {
		// TODO Auto-generated method stub
		int index=sqlSession.selectOne("getIndex", writer);
		sqlSession.update("setIndex",writer);
		return index;
	}

}
