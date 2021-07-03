package com.gw.groupware.employees.service;

import java.util.List;

import com.gw.groupware.employees.model.entity.EmployeesDto;
import com.gw.groupware.employees.model.entity.EmployeesVo;

public interface EmployeesService {

	EmployeesDto login(EmployeesDto employeesDto);
	List<EmployeesVo> departList(String departmentName);
}
