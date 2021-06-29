<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri = "http://java.sun.com/jsp/jstl/functions" %>
<c:set var="isSearch" value="${not empty param.keyword}"></c:set>
  
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/template/section.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

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
				//$(".page-form").submit();
			}	
			else if(pageNo == "다음"){//다음 링크 : 현재 링크 중 마지막 항목 값 + 1
				pageNo = parseInt($(".pagination > a:not(.move-link)").last().text()) + 1;
				$("input[name=pageNo]").val(pageNo);
				$(".search-form").submit();//강제 submit 발생
				//$(".page-form").submit();
			}
			else{//숫자 링크
				$("input[name=pageNo]").val(pageNo);
				$(".search-form").submit();//강제 submit 발생
				//$(".page-form").submit();
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


<form class = "search-form" action="approvalInsertMain">
<input type="hidden" name="pageNo">
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
<input type="button" class = "appinsert link-btn" value="기안서 작성">
</div>

<div class="row">
		<!-- 페이지 네비게이션 자리 -->
		<div class="pagination">
			<c:if test="${pagination.startBlock>1}">
			<a class="move-link">이전</a>
			</c:if>
			<c:forEach var="i" begin="${pagination.startBlock}" end="${pagination.endBlock}" step="1">
			<c:choose>
			<c:when test="${i==pagination.pageNo}">		
					<a class="on">${i}</a>
					</c:when>
			<c:otherwise>		
					<a>${i}</a>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			<c:if test="${pagination.endBlock<pagination.lastBlock}">
			<a class="move-link">다음</a>
			</c:if>
		</div>	
	</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>