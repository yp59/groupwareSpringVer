package com.gw.groupware;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gw.groupware.approval.model.entity.ApprovalVo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class insertListCountTest {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	@Transactional
	public void test() {
	String empNo = "1005";
		
		int c = sqlSession.selectOne("approval.insertListCount",empNo);
		
		System.out.println(c);
		
	}
}
