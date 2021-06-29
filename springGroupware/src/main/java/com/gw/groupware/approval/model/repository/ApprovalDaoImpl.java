package com.gw.groupware.approval.model.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.common.Pagination;
import com.gw.groupware.employees.model.entity.EmployeesDto;

@Repository
public class ApprovalDaoImpl implements ApprovalDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ApprovalVo> insertListPagination(String empNo, Pagination pagination) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("drafter",empNo);
		map.put("startRow",pagination.getStartRow());
		map.put("endRow", pagination.getEndRow());
		
		return sqlSession.selectList("approval.insertListPagination",map);
	}

	@Override
	public int insertListCount(String empNo) {
		
		return sqlSession.selectOne("approval.insertListCount",empNo);
	}

	@Override
	public List<ApprovalVo> insertSearchPagination(String empNo, Pagination pagination, String keyword) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("drafter", empNo);
		map.put("startRow", pagination.getStartRow());
		map.put("endRow", pagination.getEndRow());
		map.put("keyword", keyword);
		
		return sqlSession.selectList("approval.insertSearchPagination", map);
	}

	@Override
	public int insertSearchCount(String empNo, String keyword) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("drafter", empNo);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("approval.insertSearchCount", keyword);
	}

}
