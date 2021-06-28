package com.gw.groupware.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
	
	
	private int pageNo;
	private int pageSize;
	private int count;
	private int startRow;
	private int endRow;
	private int blockSize;
	private int lastBlock;
	private int startBlock;
	private int endBlock;
	

	public void pageInfo(int pageNo,int pageSize,int count) {

	//받아와야 하는 값	
		this.pageNo = pageNo;
	/*
		try{
				
			if(pageNo<1){
				throw new Exception();
			}
			
		}catch(Exception e){
		
		
			pageNo = 1;//기본 페이지
		}
		
	*/	
		this.pageSize = pageSize;
		
		/*
		try{
			
			if(pageSize < 10){
				throw new Exception();
			}
		}
		catch(Exception e){
			pageSize = 5;//페이지당 게시글 수 설정
		}
	*/	
		//rownum의 시작번호와 종료번호를 계산
		this.startRow = pageNo * pageSize - (pageSize-1);
		this.endRow = pageNo * pageSize;
		
		
		
		this.count = count;
		
		this.blockSize = 10;
		this.lastBlock = (count + pageSize - 1) / pageSize;
		this.startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		this.endBlock = startBlock + blockSize - 1;
		
		if(endBlock > lastBlock){//범위를 벗어나면
		endBlock = lastBlock;//범위를 수정
		}
	}
	/*
	
	//////////////////////////페이지네이션/////////////////////////
	String id = (String) session.getAttribute("id");
	String keyword = request.getParameter("keyword");
	boolean isSearch = keyword!=null&&!keyword.trim().equals("");
	
	int pageNo;
	
	try{
		pageNo =Integer.parseInt(request.getParameter("pageNo"));
		if(pageNo<1){
			throw new Exception();
		}
		
		
	}catch(Exception e){
		pageNo = 1;//기본 페이지
	}
	
	int pageSize;
	try{
		pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if(pageSize < 10){
			throw new Exception();
		}
	}
	catch(Exception e){
		pageSize = 5;//페이지당 게시글 수 설정
	}
	//rownum의 시작번호와 종료번호를 계산
	int startRow = pageNo * pageSize - (pageSize-1);
	int endRow = pageNo * pageSize;
	///////////////////////////페이지네이션///////////////////////////
	
	***************여기는  service에서 Dao 호출해서 작업***************
	approvalDao approvaldao = new approvalDao();
	List<approvalDto> list ;

	if(isSearch){//검색 시 기안서 리스트 출력
		list = approvaldao.approvalSearch(id, keyword, startRow, endRow);
		
	}
	else{//일반 기안서 출력
		list = approvaldao.approvalList(id, startRow, endRow);
	}
	/////////////////////////////////////////////////////////////////////
	// 페이지 네비게이션 영역 계산
	/////////////////////////////////////////////////////////////////////
	// = 하단에 표시되는 숫자 링크의 범위를 페이지번호를 기준으로 계산하여 설정
	// = 하단 네비게이션 숫자는 startBlock 부터 endBlock 까지 출력
	// = (주의사항) 게시글 개수를 구해서 마지막 블록 번호를 넘어가지 않도록 처리
	int count;
	if(isSearch){
	
	count = approvaldao.getCount(id, keyword);
	}
	else{
	count = approvaldao.getCount(id);
	}
	int blockSize = 10;
	int lastBlock = (count + pageSize - 1) / pageSize;
	//int lastBlock = (count - 1) / pageSize + 1;
	int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
	int endBlock = startBlock + blockSize - 1;
	
	if(endBlock > lastBlock){//범위를 벗어나면
	endBlock = lastBlock;//범위를 수정
	
	}
	 * */




	
}
