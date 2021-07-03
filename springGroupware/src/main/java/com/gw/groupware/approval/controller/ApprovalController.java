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
	
	@PostMapping("/approvalInsert") // appform post 기안 으로 일로 들어옴
	public String approvalInsert( ApprovalDto approvalDto
								, String approvalGroup
								, String consesusGroup
								, String referrerGroup
								, String implementerGroup) 
	{
		approvalService.approvalInsert(approvalDto);
		int appPK = approvalService.pkKeyValue(approvalDto.getDrafter());
		
		/////////////////////////direct table DB접속코드///////////////////////////////////

		DirectappDto directappdto = new DirectappDto();

		String[] splitApp = approvalGroup.split("&");//&구분자로 선택해서 결재자를 1명씩 배열에 넣는다.
		String[] splitCon = consesusGroup.split("&");
		
		directappdto.setAppNo(appPK);//결재번호 지정
		
		for(String person : splitApp) {//결재자
			directappdto.setApproval(person.substring(person.length()-5,person.length()-1));
			//뒤에서 5번째와 1번째 사이의 문자열을 잘라내고 결재자로 저장한다. ex) 배정호[1000] --> 1000
			
			directappdto.setConsesus("0");;//결재 합의 구분 코드 0이면 결재 1이면 합의
			approvalService.directInsert(directappdto);//direct table 저장
		}
		for(String person : splitCon) {//합의자 저장 방식. 설명은 위에 코드와 같다.
			directappdto.setApproval(person.substring(person.length()-5,person.length()-1));
			directappdto.setConsesus("1");
			approvalService.directInsert(directappdto);
		}
		
		/////////////////////////indirect table DB접속코드///////////////////////////////////
		//참조 시행자는 결재 권한이 없다.
		
		IndirectappDto indirectappdto = new IndirectappDto();
			
		String[] splitRef = referrerGroup.split("&");//&구분자로 선택해서 참조자를 1명씩 배열에 넣는다.
		String[] splitImp = implementerGroup.split("&");
		
		indirectappdto.setAppNo(appPK);//결재번호 지정
		
		for(String person : splitRef) {//참조자
			indirectappdto.setReferrer(person.substring(person.length()-5,person.length()-1));
			//뒤에서 5번째와 1번째 사이의 문자열을 잘라내고 참조자로 저장한다. ex) 배정호[1000] --> 1000
			
			indirectappdto.setType("참조");//참조자와 시행자는 결재권한이 없기 때문에 Type으로 구분한다.
			approvalService.indirectInsert(indirectappdto);//indirect table에 저장
			
		}
		for(String person : splitImp) {//시행자 위의 코드와 설명은 같다.
			indirectappdto.setReferrer(person.substring(person.length()-5,person.length()-1));
			indirectappdto.setType("시행");
			approvalService.indirectInsert(indirectappdto);
		}
		
		
		return "redirect:/approval/approvalInsertMain";// javascript에 의해 자동으로 창 닫힌다?
	}
	
	@GetMapping("/InsertDepartmentPopUp")
	public String InsertDepartmentPopUp(Model model) {

		model.addAttribute("insa", employeesService.departList("인사부"));
		model.addAttribute("chong", employeesService.departList("총무부"));
		model.addAttribute("hwa", employeesService.departList("회계부"));
		model.addAttribute("gi", employeesService.departList("기획부"));
		model.addAttribute("young", employeesService.departList("영업부"));
		
		return "approval/InsertDepartmentPopUp";
	}
	
	
}
