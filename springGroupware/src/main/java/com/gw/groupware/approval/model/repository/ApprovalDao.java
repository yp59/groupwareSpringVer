package com.gw.groupware.approval.model.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.common.Pagination;

public interface ApprovalDao {

	List<ApprovalVo> insertListPagination(String empNo, Pagination pagination);//작성한 기안서 기본 리스트(페이지네이션적용 ㅇ)
	int insertListCount(String empNo);//작성한 기안서 수
	List<ApprovalVo> insertSearchPagination(String empNo, Pagination pagination, String keyword);//작성한 기안서 검색(페이지네이션적용 ㅇ)
	int insertSearchCount(String empNo,String keyword);//작성한 기안서 수
	
	
}
