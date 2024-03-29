package com.gw.groupware.approval.model.entity;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

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

	
	/////common/////////////////
	private int rn;
	private String empName;
	private int poNo;
	private String type;
	///////approval table////////
	private int appNo;
	private String drafter;
	private String appTitle;
	private String appContent;
	private String appDateStart;
	private String appDateEnd;
	private String appState;
	/////////dirtable//////////////
	private int approvalNo;
	//private int appNo;
	private String approval;//들어올땐 &로 들어온다.
	private String consesus;
	//private String dirType;
	private String app_date;
	
	/////////indirtable////////////////
	private int referrerNo;
	//private int appNo;
	private String refferrer;
	private String implemneter;// 일부러 오타냄
	//private String indirType;
	
	////////???????/////////일단 남겨둠
	//private String approval;
	//private String consesus;
	
	private List<ApprovalVo> list;//list를 뽑으려면 Vo로 넣어줘야한다
}
