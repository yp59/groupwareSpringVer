package com.gw.groupware.approval.model.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gw.groupware.approval.model.entity.ApprovalVo;

public interface ApprovalDao {

	List<ApprovalVo> approvalList(String empNo);
	
}
