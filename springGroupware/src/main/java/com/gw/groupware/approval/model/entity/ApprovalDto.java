package com.gw.groupware.approval.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalDto {
	private int appNo;
	private String drafter;
	private String appTitle;
	private String appContent;
	private String appDateStart;
	private String appDateEnd;
	private String appState;
	
}
