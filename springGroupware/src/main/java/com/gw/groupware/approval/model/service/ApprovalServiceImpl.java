package com.gw.groupware.approval.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.IndirectappDto;
import com.gw.groupware.approval.model.repository.ApprovalDao;
import com.gw.groupware.common.Pagination;

@Service
public class ApprovalServiceImpl implements ApprovalService{

	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public List<ApprovalVo> insertListPagination(String empNo, Pagination pagination) {
		// TODO Auto-generated method stub
		return approvalDao.insertListPagination(empNo, pagination);
	}

	@Override
	public int insertListCount(String empNo) {
		
		return approvalDao.insertListCount(empNo);
	}

	@Override
	public List<ApprovalVo> insertSearchPagination(String empNo, Pagination pagination, String keyword) {
		
		return approvalDao.insertSearchPagination(empNo, pagination, keyword);
	}

	@Override
	public int insertSearchCount(String empNo, String keyword) {
		
		return approvalDao.insertSearchCount(empNo, keyword);
	}

	@Override
	public void approvalInsert(ApprovalDto approvalDto) {
		
		approvalDao.approvalInsert(approvalDto);
		
	}

	@Override
	public int pkKeyValue(String drafter) {
		
		return approvalDao.pkKeyValue(drafter);
	}

	@Override
	public void directInsert(DirectappDto directappdto) {
	approvalDao.directInsert(directappdto);
		
	}

	@Override
	public void indirectInsert(IndirectappDto indirectappdto) {
	approvalDao.indirectInsert(indirectappdto);
		
	}

}
