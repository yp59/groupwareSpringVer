package com.gw.groupware.approval.model.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.employees.model.entity.EmployeesDto;

@Repository
public class ApprovalDaoImpl implements ApprovalDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ApprovalVo> approvalList(String empNo) {
	return sqlSession.selectList("approval.list",empNo);
	}

}
