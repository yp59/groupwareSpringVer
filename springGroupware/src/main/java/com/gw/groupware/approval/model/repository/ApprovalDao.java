package com.gw.groupware.approval.model.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.IndirectappDto;
import com.gw.groupware.common.Pagination;

public interface ApprovalDao {

	List<ApprovalVo> insertListPagination(String empNo, Pagination pagination);//작성한 기안서 기본 리스트(페이지네이션적용 ㅇ)
	int insertListCount(String empNo);//작성한 기안서 수
	List<ApprovalVo> insertSearchPagination(String empNo, Pagination pagination, String keyword);//작성한 기안서 검색(페이지네이션적용 ㅇ)
	int insertSearchCount(String empNo,String keyword);//작성한 기안서 수
	void approvalInsert(ApprovalDto approvalDto);
	int pkKeyValue(String drafter);
	void directInsert(DirectappDto directappdto);//dirapp table 삽입
	void indirectInsert(IndirectappDto indirectappdto);//indirapp table 삽입
	
}
