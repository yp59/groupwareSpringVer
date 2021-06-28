package com.gw.groupware.approval.model.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//view에 보여줄 값들이 필요하면 Vo로 보여준다. approval해당 테이블에 필요한 값에 관한 건 Dto로
//Vo>Dto

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalVo {

	private int rowNum;
	private int appNo;
	private String drafter;
	private String appTitle;
	private String appContent;
	private String appDateStart;
	private String appDateEnd;
	private String appState;
	private String empName;
	private String dirType;
	private String consesus;
	private String app_date;
	private int poNo;
	private String indirType;
	private String approval;
	private List<ApprovalVo> list;//list를 뽑으려면 Vo로 넣어줘야한다?? 생성자필요?????
}
