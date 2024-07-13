package com.goldendust.profile.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.goldendust.profile.dto.CommentDto;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CommentDaoTest {

	@Autowired
	SqlSession sqlSession;
	
	@Test
	@DisplayName("get comment list test")
	public void getCommentList() {
		
		CommentDao cmDao = sqlSession.getMapper(CommentDao.class);
		List<CommentDto> comments = cmDao.getListByPnum("68");
		for (CommentDto comment : comments) {
			System.out.println(comment.getCtext());
			System.out.println(comment.getCdate());
		}
	}

}
