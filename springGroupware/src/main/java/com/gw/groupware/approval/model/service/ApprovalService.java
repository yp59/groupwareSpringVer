package com.gw.groupware.approval.model.service;
import java.util.List;
import com.gw.groupware.approval.model.entity.ApprovalVo;


public interface ApprovalService {
	
	List<ApprovalVo> approvalList(String empNo);//작성한 기안서 기본 리스트(페이지네이션적용 x)
	List<ApprovalVo> approvalSearch(String empNo, String keyword);//작성한 기안서 검색(페이지네이션적용 x)
}
