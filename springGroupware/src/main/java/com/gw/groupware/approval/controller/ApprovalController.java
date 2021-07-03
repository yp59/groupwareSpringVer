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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gw.groupware.approval.model.entity.ApprovalDto;
import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.entity.DirectappDto;
import com.gw.groupware.approval.model.entity.IndirectappDto;
import com.gw.groupware.approval.model.repository.ApprovalDao;
import com.gw.groupware.approval.model.service.ApprovalService;
import com.gw.groupware.common.Pagination;
import com.gw.groupware.employees.model.entity.EmployeesVo;
import com.gw.groupware.employees.service.EmployeesService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private EmployeesService employeesService;

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
	/*
	@PostMapping("/approvalInsert") //기안서 입력 후 DB 저장 controller
	public String approvalInsert(@RequestAttribute ApprovalDto approvalDto,
								 @RequestAttribute DirectappDto directappDto,
								 @RequestAttribute IndirectappDto indirectappDto) 
	{
		
		
		return "approval/approvalInsert";// javascript에 의해 자동으로 창 닫힌다?
	}
	*/
	@GetMapping("/InsertDepartmentPopUp")
	public String InsertDepartmentPopUp(Model model) {
	/*	List<EmployeesVo> insa = employeesService.departList("인사부");
		List<EmployeesVo> chong = employeesService.departList("총무부");
		List<EmployeesVo> hwa = employeesService.departList("회계부");
		List<EmployeesVo> gi = employeesService.departList("기획부");
		List<EmployeesVo> young = employeesService.departList("영업부");
		*/
		model.addAttribute("insa", employeesService.departList("인사부"));
		model.addAttribute("chong", employeesService.departList("총무부"));
		model.addAttribute("hwa", employeesService.departList("회계부"));
		model.addAttribute("gi", employeesService.departList("기획부"));
		model.addAttribute("young", employeesService.departList("영업부"));
		
		return "approval/InsertDepartmentPopUp";
	}
	
	
}
