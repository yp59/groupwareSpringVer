package com.gw.groupware.approval.model.service;
import java.util.List;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.common.Pagination;


public interface ApprovalService {
	
	List<ApprovalVo> insertList(String empNo);//작성한 기안서 기본 리스트(페이지네이션적용 x)
	List<ApprovalVo> insertListPagination(String empNo, Pagination pagination);//작성한 기안서 기본 리스트(페이지네이션적용 ㅇ)
	int insertListCount(String empNo);//작성한 기안서 수
	List<ApprovalVo> insertSearch(String empNo, String keyword);//작성한 기안서 검색(페이지네이션적용 x)
}
