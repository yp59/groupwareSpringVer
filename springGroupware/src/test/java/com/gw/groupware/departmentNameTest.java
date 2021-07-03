package com.gw.groupware;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gw.groupware.employees.model.entity.EmployeesVo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class departmentNameTest {

	//ok
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		String departmentName= "인사부";
	List<EmployeesVo> list =sqlSession.selectList("employees.departList",departmentName);
		
	for(EmployeesVo d : list) {
		System.out.println(d);
	}
	}
}
