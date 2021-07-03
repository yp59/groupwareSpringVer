package com.gw.groupware.approval.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectappVo {

	private int rowNo;
	private int approvalNo;
	private int appNo;
	private String approval;
	private String type;
	private String consesus;
	private String appDate;
	private int poNo;
	private String empName;
}
