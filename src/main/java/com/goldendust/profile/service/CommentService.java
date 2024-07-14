package com.goldendust.profile.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldendust.profile.dao.BoardDao;
import com.goldendust.profile.dao.CommentDao;

public class CommentService {

	public static void insertComment(SqlSession sqlSession, String pnum, String mid, String ctext) {
		CommentDao cmDao = sqlSession.getMapper(CommentDao.class);
		cmDao.insert(pnum, mid, ctext);
		int numOfComments = cmDao.getListByPnum(pnum).size();
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		bDao.setCommentCount(pnum, numOfComments);
	}
	
}
