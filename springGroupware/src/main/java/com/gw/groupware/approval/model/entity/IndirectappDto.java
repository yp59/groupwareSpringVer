package com.gw.groupware.approval.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndirectappDto {

	private int referrerNo;//프라이머리키
	private int appNo; //approval table 외래키
	private String referrer; //참조 시행자?
	private String type; //결재 분류
	
}
