package com.gw.groupware.approval.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.repository.ApprovalDao;

@Service
public class ApprovalServiceImpl implements ApprovalService{

	@Autowired
	private ApprovalDao approvalDao;
	
	
	@Override
	public List<ApprovalVo> approvalList(String empNo) {
		
		return approvalDao.approvalList(empNo);
	}

	@Override
	public List<ApprovalVo> approvalSearch(String empNo, String keyword) {
		
		return approvalDao.approvaSearch(empNo, keyword);
	}

}
