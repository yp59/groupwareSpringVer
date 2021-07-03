package com.gw.groupware.approval.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectappDto {
	private int approvalNo;//프라이머리키
	private int appNo; //approval table에서 외래키 키  
	private String approval;//결재자 
	private String type;//결재분류
	private String consesus;//합의자 구분
	private String appDate;//결재일
}
