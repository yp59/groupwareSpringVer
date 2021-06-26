package com.gw.groupware.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.gw.groupware.employees.model.Dto.EmployeesDto;

public class EmployeesInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		EmployeesDto find =(EmployeesDto) request.getSession().getAttribute("employeesDto");
		
		if(find==null) {
			response.sendRedirect(request.getContextPath()+"/employees/loginMain");
		return false;
		}
		
		return true;
	}
	
}
