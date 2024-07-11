package com.goldendust.profile.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.goldendust.profile.dto.MemberDto;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberDaoTest {

	@Test
	@DisplayName("member insert test")
	void insertTest() {
		MemberDto mdto = new MemberDto();
		mdto.setMid("hong");
		mdto.setMpw("12345");
		mdto.setMname("홍길동");
		mdto.setMemail("hong@email.com");
		
		SqlSession sqlSession = null;
		MemberDao mdao = sqlSession.getMapper(MemberDao.class);
		mdao.insert(mdto.getMid(), mdto.getMpw(), mdto.getMname(), mdto.getMemail());
		
		int successFlag = mdao.isMidPresent("홍길동");
		System.out.println(successFlag);
	}

}
