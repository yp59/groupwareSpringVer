package com.gw.groupware.employees.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gw.groupware.employees.model.entity.EmployeesDto;
import com.gw.groupware.employees.model.repository.EmployeesDao;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	
	@Autowired
	EmployeesDao employeesDao;
	
	@GetMapping("/loginMain")
	public String loginMain() {
		
		return "login/loginMain";
	}
	
	@PostMapping("/loginMain")
	public String loginMain(EmployeesDto employeesDto, HttpSession session) {
		
	 EmployeesDto find = employeesDao.login(employeesDto);	
	
	 if(find!=null) {
		 session.setAttribute("empNo", find.getEmpNo());//session에 로그인 정보를 저장시킨다.
		 session.setAttribute("employeesDto", find);//session에 로그인 정보를 저장시킨다.
		 return "redirect:/";
	 }
	 else {
		 return "redirect:loginMain?error";
	 }
	 
	}
	
	@GetMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("empNo");
		session.removeAttribute("employeesDto");
		
		return "redirect:/";
	}
	
}
