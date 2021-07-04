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

<div class = "container-1200">
<div style="text-align: center; border-bottom: 2px solid rgb(102, 177, 227); margin-bottom: 10px;">
<h2>나의 결재 목록</h2>
</div>

<form class = "search-form" action="approvalList">
<input type="hidden" name="pageNo">

<div class = "text-right">
<input type="text" name="keyword" placeholder="기안서 검색"><!-- 검색 keyword 입력창-->
<input class = "link-btn" type ="submit" value = "검색">
</div>

</form>
<!-- appNo가 한번에 들어감 why? -->
<table class="table table-border table-hover" >
		<thead>
			<tr>
				<th>결재 서류 번호</th>
				<th>결재 분류</th><!-- (미결예결후결합의반려거부참조시행) 들어오는 자리 결재일은 detail에서만 표시하자.
				insert에서 결재순서에따라 type을 넣어주는 값도 수정해야된다.-->
				<th>제목</th>
				<th>기안자</th>
				<th>기안일</th>
				<th>마감일</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "approvalVo" items="${list}">
				<tr>
					<td>${approvalVo.appNo}</td>
					<td>${approvalVo.type}</td>
					<td>
					<a href="${pageContext.request.contextPath}/approval/approvalDetail?appNo=
					${approvalVo.appNo}&dirType=${approvalVo.type}&appStateG=${approvalVo.appState}"   
					onclick="window.name = 'appList'; window.open(this.href, '_blank', 'width=750, height=750'); return false;">
					<!-- 기안서에 현재 선택한 appNo을 보내는 구문 -->	
					${approvalVo.appTitle}</a>	
					</td>
					<td>${approvalVo.empName}</td>
					<td>${approvalVo.appDateStart.substring(0,10)}</td>
					<td>${approvalVo.appDateEnd.substring(0,10)}</td>
					<td>${approvalVo.appState}</td>
			
				</tr>
				
				
		</c:forEach>
		</tbody>
</table>
<!-- 일단 appNo만 보내고 필요시 다른 것도 보내주도록 설정 -->
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