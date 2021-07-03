package com.gw.groupware.employees.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesVo {

	private String empNo;
	private String empPw;
	private int poNo;
	private String empName;
	private String joinDate;
	private String empPhone;
	private String email;
	private String address;
	private String department;
	private int holidayCount; // 사원별 남은 휴가일수 추가했습니다 ! 
	private String posi; //직급 
}
