package com.gw.groupware.approval.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.DirectappVo;
import com.gw.groupware.approval.model.entity.IndirectappDto;
import com.gw.groupware.approval.model.repository.ApprovalDao;
import com.gw.groupware.common.Pagination;

@Service
public class ApprovalServiceImpl implements ApprovalService{

	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public List<ApprovalVo> insertListPagination(String empNo, Pagination pagination) {
	
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

	@Override
	public List<ApprovalVo> listPagination(String empNo, Pagination pagination) {
		
		return approvalDao.listPagination(empNo, pagination);
	}

	@Override
	public int listCount(String empNo) {
		
		return approvalDao.listCount(empNo);
	}

	@Override
	public List<ApprovalVo> listSearchPagination(String empNo, Pagination pagination, String keyword) {
	
		return approvalDao.listSearchPagination(empNo, pagination, keyword);
	}

	@Override
	public int listSearchCount(String empNo, String keyword) {
	
		return approvalDao.listSearchCount(empNo, keyword);
	}

	@Override
	public ApprovalDto appdraftDoc(int appNo) {
		// TODO Auto-generated method stub
		return approvalDao.appdraftDoc(appNo);
	}

	@Override
	public List<DirectappDto> dirdraftDoc(int appNo) {
		// TODO Auto-generated method stub
		return approvalDao.dirdraftDoc(appNo);
	}

	@Override
	public List<IndirectappDto> indirdraftDoc(int appNo) {
		// TODO Auto-generated method stub
		return approvalDao.indirdraftDoc(appNo);
	}

	@Override
	public DirectappVo sequence(int appNo, String id) {
		// TODO Auto-generated method stub
		return approvalDao.sequence(appNo, id);
	}

	@Override
	public List<DirectappVo> sequence(int appNo) {
		// TODO Auto-generated method stub
		return approvalDao.sequence(appNo);
	}

}
