package com.gw.groupware.approval.model.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.IndirectappDto;
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

	@Override
	public void approvalInsert(ApprovalDto approvalDto) {
	
		sqlSession.insert("approval.appInsert", approvalDto);
	}

	@Override
	public int pkKeyValue(String drafter) {
		
		return sqlSession.selectOne("approval.pkKey",drafter);
	}

	@Override
	public void directInsert(DirectappDto directappdto) {
		sqlSession.insert("directapp.dirInsert", directappdto);
		
	}

	@Override
	public void indirectInsert(IndirectappDto indirectappdto) {
		sqlSession.insert("indirectapp.indirInsert", indirectappdto);
		
	}

	@Override
	public List<ApprovalVo> listPagination(String empNo, Pagination pagination) {
	
		Map<String, Object> map = new HashMap<>();
		map.put("drafter",empNo);
		map.put("startRow",pagination.getStartRow());
		map.put("endRow", pagination.getEndRow());
		
		return sqlSession.selectList("approval.listPagination",map);
	}

	@Override
	public int listCount(String empNo) {
		
		return sqlSession.selectOne("approval.listCount",empNo);
	}

	@Override
	public List<ApprovalVo> listSearchPagination(String empNo, Pagination pagination, String keyword) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("drafter",empNo);
		map.put("startRow",pagination.getStartRow());
		map.put("endRow", pagination.getEndRow());
		map.put("keyword", keyword);
		return sqlSession.selectList("approval.listSearchPagination",map);
	}

	@Override
	public int listSearchCount(String empNo, String keyword) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("empNo", empNo);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("approval.listSearchCount",map);
	}

}
