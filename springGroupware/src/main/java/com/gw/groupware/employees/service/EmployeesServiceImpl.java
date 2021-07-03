package com.gw.groupware.employees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gw.groupware.employees.model.entity.EmployeesDto;
import com.gw.groupware.employees.model.entity.EmployeesVo;
import com.gw.groupware.employees.model.repository.EmployeesDao;

@Service
public class EmployeesServiceImpl implements EmployeesService{

	@Autowired
	private EmployeesDao employeesDao;
	
	@Override
	public EmployeesDto login(EmployeesDto employeesDto) {
		
		return employeesDao.login(employeesDto);
	}

	@Override
	public List<EmployeesVo> departList(String departmentName) {
		
		return employeesDao.departList(departmentName);
	}

}
