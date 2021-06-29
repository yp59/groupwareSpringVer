package com.gw.groupware.approval.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.repository.ApprovalDao;
import com.gw.groupware.approval.model.service.ApprovalService;
import com.gw.groupware.common.Pagination;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private ApprovalService approvalService;

	@GetMapping("/approvalInsertMain")//페이지네이션 적용한 기안서 작성 메인 페이지 
	public String approvalInsertMain(@RequestParam(required = false, defaultValue = "1") int pageNo,
									 @RequestParam(required = false, defaultValue = "5") int pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword,
									 HttpSession session, Model model) {
		
	String empNo =(String)session.getAttribute("empNo");
	Pagination pagination = new Pagination();
	
	if(keyword.equals("")) {//검색이 없을 때
		
	int insertListCount = approvalService.insertListCount(empNo);

		pagination.pageInfo(pageNo, pageSize, insertListCount);		

	model.addAttribute("pagination", pagination);
	model.addAttribute("applist", approvalService.insertListPagination(empNo, pagination));
	}
	else {//검색이 있을 때
		
		int insertSearchCount = approvalService.insertSearchCount(empNo,keyword);
	
		pagination.pageInfo(pageNo, pageSize, insertSearchCount);	
		
	model.addAttribute("pagination", pagination);	
	model.addAttribute("applist", approvalService.insertSearchPagination(empNo,pagination,keyword));
	}
	
	return "approval/approvalInsertMain";
	}
	
	
	@GetMapping("/approvalInsert")
	public String approvalInsert() {
		
		return "approval/approvalInsert";
	}
	
	
	@GetMapping("/InsertDepartmentPopUp")
	public String InsertDepartmentPopUp() {
		
		return "approval/InsertDepartmentPopUp";
	}
}
