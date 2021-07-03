package com.gw.groupware.employees.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.groupware.employees.model.entity.EmployeesDto;
import com.gw.groupware.employees.model.entity.EmployeesVo;

@Repository
public class EmployeesDaoImpl implements EmployeesDao{
//dao에서 mybatis를 적용해 db와의 데이터 처리를 한다. 컨트롤은 아님
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public EmployeesDto login(EmployeesDto employeesDto) {
		return sqlSession.selectOne("employees.login",employeesDto);
	}

	@Override
	public List<EmployeesVo> departList(String departmentName) {
		
		return sqlSession.selectList("employees.departList",departmentName);
	}

}
