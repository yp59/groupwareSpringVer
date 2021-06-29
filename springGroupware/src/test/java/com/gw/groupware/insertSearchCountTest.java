package com.gw.groupware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.common.Pagination;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class insertSearchCountTest {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	List<ApprovalVo> list ;
	Pagination pagination = new Pagination();
	String empNo;
	String keyword;
	Map<String, Object> map = new HashMap<>();
	@Before
	public void before() {
	
	empNo = "1005";
	keyword= "테";
	map.put("drafter", empNo);
	map.put("keyword", keyword);
	pagination.pageInfo(1, 5, sqlSession.selectOne("approval.insertSearchCount",map));	
		
	}
	
	@Test
	public void test()throws Exception{
	int t =sqlSession.selectOne("approval.insertSearchCount",map);	
	System.out.println(t);
		
		
	}
	
	@After
	public void after() {
		
	}
}
