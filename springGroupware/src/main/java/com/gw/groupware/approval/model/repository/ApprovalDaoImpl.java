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
	return sqlSession.selectList("approval.insertList",empNo);
	}

	@Override
	public List<ApprovalVo> approvaSearch(String empNo, String keyword) {//mybatis에 넣어주기 위해서 builder로 넣음
		
		ApprovalVo approvalVo = ApprovalVo.builder()
								.drafter(empNo)
								.appTitle(keyword)
								.build();
		
		return sqlSession.selectList("approval.insertSearch", approvalVo);
	}

}
