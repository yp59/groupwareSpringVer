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
import com.gw.groupware.approval.model.entity.DirectappVo;
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
	
	@GetMapping("/approvalList")//페이지네이션 적용한 기안서 작성 메인 페이지 
	public String approvalList(		@RequestParam(required = false, defaultValue = "1") int pageNo,
									 @RequestParam(required = false, defaultValue = "5") int pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword,
									 HttpSession session, Model model) {
		
	String empNo =(String)session.getAttribute("empNo");
	Pagination pagination = new Pagination();
	
	if(keyword.equals("")) {//검색이 없을 때
		
	int ListCount = approvalService.listCount(empNo);

		pagination.pageInfo(pageNo, pageSize, ListCount);		

	model.addAttribute("pagination", pagination);
	model.addAttribute("list", approvalService.listPagination(empNo, pagination));
	}
	else {//검색이 있을 때
		
		int listSearchCount = approvalService.listSearchCount(empNo,keyword);
	
		pagination.pageInfo(pageNo, pageSize, listSearchCount);	
		
	model.addAttribute("pagination", pagination);	
	model.addAttribute("list", approvalService.listSearchPagination(empNo,pagination,keyword));
	}
	
	return "approval/approvalList";
	}
	
	
	
	
	
	@GetMapping("/approvalDetail")
	public String approvalDetail(HttpSession session,
								@RequestParam int appNo,
								@RequestParam String dirType,
								@RequestParam String appStateG,
								Model model
								) {
		
		String id = (String) session.getAttribute("empNo");
			
		//이미 위에서 선언함int appNo = Integer.parseInt(request.getParameter("appNo"));//결재 리스트에서 어떤 페이지를 눌렀는지 appNo를 가져온다.	
		 //이미 위에서 선언함String dirType = request.getParameter("DirType");//type을 가져와서 이미 결재를 한 상태이면 버튼을 없앤다.
			
		
		 boolean appState = appStateG.equals("중지");
		 boolean isApproval = !dirType.equals("미결");//미결이 아니면 ture값을 설정해 버튼을 없앤다.

		 ///////////////////////////////////////////////////////////기안서 내용을 출력할 저장 객체 선언
		
		 ApprovalDto draftapp = approvalService.appdraftDoc(appNo);//approval table에서 기안서에 출력시킬 데이터 저장

		
		 List<IndirectappDto> draftindir = approvalService.indirdraftDoc(appNo);//indirectapp table에서 기안서에 출력시킬 데이터 저장

		 
		 List<DirectappDto> draftdir = approvalService.dirdraftDoc(appNo);//directapp table에서 기안서에 출력시킬 데이터 저장

		 ///////////////////////////////////////////////////////////////////////////////////////////


		 DirectappVo mysequence = approvalService.sequence(appNo, id);//내 결재 순서

		 List<DirectappVo> allSequence =approvalService.sequence(appNo);//전체 결재 순서
		 //list 사이즈로 전체 결재 크기를 알아낸 후 위치를 알아보자

		 int appSize = allSequence.size();//전체 결재 수
		 int appsizetest = appSize;

		 String appEndDate ="";
		 boolean isConsesus =true;
		  String state = "기결";
		  
		 if(mysequence.getConsesus()==null){
		 	
		 }
		 else{
		 if(mysequence.getConsesus().equals("0")){//내 결재 유형이 합의자인지 결재자인지에 따라 결재 순서를 잰다.
		 	
		 	isConsesus = false;
		 	
		 ////////////////////////////////예결은 제일 먼저//////////////////////////////////////////
		 	int conSize1 =0;//합의자 수 세는 변수
		 	for(DirectappVo x : allSequence){
		 		 
		 		if(x.getConsesus().equals("1")){//합의자는 결재순서와 상관없이 모두 합의하면 결재 넘어간다.
		 			conSize1+=1;//합의자 수당 conSize 증가
		 			continue;
		 			}
		 		
		 			
		 			if(mysequence.getRowNo()-1==conSize1){break;}//만약 내 결재 순서에서 -1을 한값이 합의자의 수와 같다면
		 			//지금의 나는 첫 결재자이므로 예결없이 무조건 기결 후결이다.
		 			
		 			if(x.getAppDate()==null){//내 순서 앞의 결재자가 결재를 하지 않았으면
		 				state = "예결";
		 				//예결버튼만 생긴다.
		 				break;

		 			}
		 	}
		 /////////////////////////////////기결은 내순서가 돼었을 때 앞의 결재자가 결재를 다 했으면///////////////////// 
		 int fitCount =0;//밖에서 선언해줘야 값이 증가한다.
		 int conSize2 =0;//합의자 수 세는 변수
		 for(DirectappVo x : allSequence){
		 	
		 	if(x.getConsesus().equals("1")){//합의자는 결재순서와 상관없이 모두 합의하면 결재 넘어간다.
		 		conSize2+=1;//합의자 수당 conSize 증가
		 		continue;
		 		}
		 	
		 	if(mysequence.getRowNo()-1==conSize2){break;}//만약 내 결재 순서에서 -1을 한값이 합의자의 수와 같다면
		 	//지금의 나는 첫 결재자이므로 예결없이 무조건 기결 후결이다.
		 		
		 	
		 		if(x.getAppDate()!=null){//결재를 했으면
		 		
		 			fitCount+=1;
		 		
		 			if(fitCount==mysequence.getRowNo()-1-conSize2){//내 순서 앞의 결재자까지 조회한 후 모두 결재 했으면
		 				state = "기결";//위의 구문을 만족하지 않았을 경우에는 내차례가 맞으므로 기결이다.
		 				break;
		 			}
		 			
		 		}	
		 }
		 ////////////////////////후결 라인은 젤 마지막에 넣어서 앞의 조건 무시하고 후결이 우선순위가 되어야 한다/////////////////
		 	int conSize3 =0;//합의자 수 세는 변수
		 	for(DirectappVo x : allSequence){
		 		
		 		if(x.getConsesus().equals("1")){//합의자는 결재순서와 상관없이 모두 합의하면 결재 넘어간다.
		 			conSize3+=1;//합의자 수당 conSize 증가
		 			continue;
		 			}
		 		
		 		if(mysequence.getRowNo()-1==conSize3){break;}//만약 내 결재 순서에서 -1을 한값이 합의자의 수와 같다면
		 		//지금의 나는 첫 결재자이므로 예결없이 무조건 기결 후결이다.
		 			
		 			if(mysequence.getRowNo()<x.getRowNo()){//내 순서가 현재 검사하는 전체 결재 순서보다 값이 작을때
		 				if(x.getAppDate()!=null){//뒤의 결재자가 결재를 했으면
		 					 state ="후결"; 
		 					//후결해야 한다.
		 					break;
		 				}
		 			}
		 		}
		 /////////////////////////////////////////////////////////////////////////////////////////////////

		 for(DirectappVo x : allSequence){	//결재자가 모두 결재했을경우 결재일 표시
		 	 int appCount =0;
		 			if(x.getAppDate()!=null){//결재한 사람 수를 센다
		 				appCount++;
		 			}
		 	
		 	if(appCount==appSize){//전체 결재자 수와 결재한 사람 수가 같으면 appEndDate에 최종 결재자 결재일을 저장
		 		appEndDate = x.getAppDate();
		 	}
		 }
		 	
		 }

		 else{
		 	
		 	isConsesus = true;//getConsesus의 값이 "1"(합의자)이므로 해당 기안서를 열어보는 '나'는 합의자이다.
		 }
		 }
		model.addAttribute("isApproval", isApproval);
		model.addAttribute("isConsesus", isConsesus);
		model.addAttribute("appState", appState);
		model.addAttribute("state", state);
		model.addAttribute("appNo", appNo);
		model.addAttribute("draftapp", draftapp);
		model.addAttribute("draftdir", draftdir);
		model.addAttribute("draftindir", draftindir);
		
		return "approval/approvalDetail";
	}
	
}
