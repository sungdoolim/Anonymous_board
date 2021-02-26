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
	private SqlSession sqlSession;// 자동 의존성 주입 => mybatis쿼리문 수행객체
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

}
