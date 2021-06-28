<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri = "http://java.sun.com/jsp/jstl/functions" %>
<c:set var="isSearch" value="${not empty param.keyword}"></c:set>
  
<%/*
	String id = (String) session.getAttribute("id");//EL 에 있음

	String keyword = request.getParameter("keyword");//EL 로 처리가능
	
	boolean isSearch = keyword!=null&&!keyword.trim().equals("");
	
	//////////////////////////페이지네이션/////////////////////////
	
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
	
	}*/
%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/template/section.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- 

if Search 자리

 -->

<script>
	//페이지 네비게이션에 있는 a태그를 누르면 전송하는 것이 아니라 form 내부에 값을 설정한 뒤 form을 전송
	//= 검색어, 검색분류, 페이지번호, 페이지크기까지 한 번에 전송해야 화면이 유지되기 때문
	$(function(){
		$(".pagination > a").click(function(){
			//주인공 == a태그
			var pageNo = $(this).text();
			if(pageNo == "이전"){//이전 링크 : 현재 링크 중 첫 번째 항목 값 - 1
				pageNo = parseInt($(".pagination > a:not(.move-link)").first().text()) - 1;
				$("input[name=pageNo]").val(pageNo);
				$(".search-form").submit();//강제 submit 발생
			}	
			else if(pageNo == "다음"){//다음 링크 : 현재 링크 중 마지막 항목 값 + 1
				pageNo = parseInt($(".pagination > a:not(.move-link)").last().text()) + 1;
				$("input[name=pageNo]").val(pageNo);
				$(".search-form").submit();//강제 submit 발생
			}
			else{//숫자 링크
				$("input[name=pageNo]").val(pageNo);
				$(".search-form").submit();//강제 submit 발생
			}
		});
	});
</script>

<script>

$(function(){

	////////////////////////////////////////////////////////////////////////
	var _width = '750';
    var _height = '700';
 
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var _left = Math.ceil(( window.screen.width - _width )/2);
    var _top = Math.ceil(( window.screen.height - _height )/2);
    
var option ='width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top +
			', scrollbars = no';
	$(".appinsert").click(function(){
		 window.open("approvalInsert.jsp","draftPopUp",option);
		 
	});
///////////////////////////////////////////////////////////////////////////////
	
	
});
</script>

<div class = "container-1200">
<div style="text-align: center; border-bottom: 2px solid rgb(102, 177, 227); margin-bottom: 10px;">
<h2>기안서 작성</h2>
</div>

<form class = "search-form" action="approvalInsertMain" method="post">
<!-- <input type="hidden" name="pageNo"> -->

<div class = "text-right">
<input type="text" name="keyword" placeholder="기안서 검색"><!-- 검색 keyword 입력창-->
<input class = "link-btn"type ="submit" value = "검색">
</div>

</form>

<table class="table table-border table-hover" >
		<thead>
			<tr>
				<th>결재 서류 번호</th>
				<th>제목</th>
				<th>기안자</th>
				<th>기안일</th>
				<th>마감일</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="approvalVo" items="${applist}">
				<tr>
					<td>${approvalVo.appNo}</td>
					<td>${approvalVo.appTitle}</td>
					<td>${approvalVo.empName}</td>
					<td>${approvalVo.appDateStart.substring(0,10)}</td><!-- el에 그냥 써도 되네... -->
					<td>${approvalVo.appDateEnd.substring(0,10)}</td>
					<td>${approvalVo.appState}</td>
				</tr>
		</c:forEach>
		</tbody>
</table>

</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>