package com.gw.groupware.approval.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gw.groupware.approval.model.entity.ApprovalVo;
import com.gw.groupware.approval.model.repository.ApprovalDao;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private ApprovalDao approvalDao;
	
	@RequestMapping("/aprrovalInsertMain")
	public String approvalInsertMain(HttpSession session,
									 Model model) {
	
	String empNo =(String)session.getAttribute("empNo");	
	List<ApprovalVo> list = new ArrayList<>();
	
	list=approvalDao.approvalList(empNo);//session을 넣는게 아니라 여기서 session값을 뽑고 넘겨주는것?
	model.addAttribute("applist", list);
	return "approval/approvalInsertMain";
	}
	
}
