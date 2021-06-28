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
/*	
	@GetMapping("/approvalInsertMain")
	public String approvalInsertMain(HttpSession session, Model model) {
	String empNo =(String)session.getAttribute("empNo");
	
	model.addAttribute("applist", approvalService.insertList(empNo));
	return "approval/approvalInsertMain";
	}
	
	*/
	@GetMapping("/approvalInsertMain")
	public String approvalInsertMain(@RequestParam(required = false, defaultValue = "1") int pageNo,
									 @RequestParam(required = false, defaultValue = "5") int pageSize,
									 HttpSession session, Model model) {
		
	String empNo =(String)session.getAttribute("empNo");
	
	int insertListCount = approvalService.insertListCount(empNo);

		Pagination pagination = new Pagination();

		pagination.pageInfo(pageNo, pageSize, insertListCount);		

	model.addAttribute("pagination", pagination);
	model.addAttribute("applist", approvalService.insertListPagination(empNo, pagination));
	return "approval/approvalInsertMain";
	}
	@PostMapping("/approvalInsertMain")
	public String approvalInsertSearch(@RequestParam(value="keyword") String keyword, 
									HttpSession session,Model model) {
		
	String empNo =(String)session.getAttribute("empNo");	
	model.addAttribute("applist", approvalService.insertSearch(empNo,keyword));

	return "approval/approvalInsertMain";
	}
}
