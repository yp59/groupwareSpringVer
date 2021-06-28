package com.gw.groupware.approval.model.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gw.groupware.approval.model.entity.ApprovalVo;

public interface ApprovalDao {

	List<ApprovalVo> insertList(String empNo);//페이지네이션 미적용
	//List<ApprovalVo> approvalListPagenation(String empNo, ni)
	List<ApprovalVo> insertSearch(String empNo, String keyword);
}
