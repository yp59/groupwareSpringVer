package com.gw.groupware.approval.model.service;
import java.util.List;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.IndirectappDto;
import com.gw.groupware.common.Pagination;


public interface ApprovalService {
	
	//기안서 작성 리스트
	List<ApprovalVo> insertListPagination(String empNo, Pagination pagination);//작성한 기안서 기본 리스트(페이지네이션적용 ㅇ)
	int insertListCount(String empNo);//작성한 기안서 수
	//기안서 작성 검색 리스트
	List<ApprovalVo> insertSearchPagination(String empNo, Pagination pagination, String keyword);//작성한 기안서 검색(페이지네이션적용 ㅇ)
	int insertSearchCount(String empNo,String keyword);//작성한 기안서 수
	//기안서 입력 DAO
	void approvalInsert(ApprovalDto approvalDto);
	int pkKeyValue(String drafter);
	void directInsert(DirectappDto directappdto);//dirapp table 삽입
	void indirectInsert(IndirectappDto indirectappdto);//indirapp table 삽입
	
	//결재해야되는 기안서 리스트
	List<ApprovalVo> listPagination(String empNo, Pagination pagination);//결재할 기안서 기본 리스트(페이지네이션적용 ㅇ)
	int listCount(String empNo);//결재 해야하는 기안서 수
	//결재해야되는 기안서 검색 리스트
	List<ApprovalVo> listSearchPagination(String empNo, Pagination pagination, String keyword);//결재할 기안서 검색(페이지네이션적용 ㅇ)
	int listSearchCount(String empNo,String keyword);//검색 중 결재 해야하는 기안서 수
	
}
