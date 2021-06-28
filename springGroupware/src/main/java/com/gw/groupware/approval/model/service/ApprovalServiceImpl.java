package com.gw.groupware.approval.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.repository.ApprovalDao;
import com.gw.groupware.common.Pagination;

@Service
public class ApprovalServiceImpl implements ApprovalService{

	@Autowired
	private ApprovalDao approvalDao;
	
	
	@Override
	public List<ApprovalVo> insertList(String empNo) {
		
		return approvalDao.insertList(empNo);
	}

	@Override
	public List<ApprovalVo> insertSearch(String empNo, String keyword) {
		
		return approvalDao.insertSearch(empNo, keyword);
	}

	@Override
	public List<ApprovalVo> insertListPagination(String empNo, Pagination pagination) {
		// TODO Auto-generated method stub
		return approvalDao.insertListPagination(empNo, pagination);
	}

	@Override
	public int insertListCount(String empNo) {
		
		return approvalDao.insertListCount(empNo);
	}

}