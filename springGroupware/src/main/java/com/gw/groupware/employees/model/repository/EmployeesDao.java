package com.gw.groupware.employees.model.repository;

import java.util.List;

import com.gw.groupware.employees.model.entity.EmployeesDto;
import com.gw.groupware.employees.model.entity.EmployeesVo;

public interface EmployeesDao {

	EmployeesDto login(EmployeesDto employeesDto);
	List<EmployeesVo> departList(String departmentName);
}
