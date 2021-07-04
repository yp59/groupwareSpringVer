<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기안서</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script >
window.name ='appDetail';

$(function(){
	var isApproval = ${isApproval};
	var isConsesus = ${isConsesus};
	var appState = ${appState};
	var state = ${state};
	if(isApproval){//결재 후 되돌리기 불가 (버튼 지움)
		$('.isConsesus').css('display','none');
		$('.isApproval').css('display','none');
	}
	
	
	else{
	
		if(isConsesus){
		$('.isConsesus').css('display','block');
		$('.isApproval').css('display','none');
		}
	
		else{
		$('.isConsesus').css('display','none');
		$('.isApproval').css('display','block');
		}
	}
	
	if(appState){//상태가 중지면 결재 불가(버튼 지움)
		
		$('.isConsesus').css('display','none');
		$('.isApproval').css('display','none');
	}
/////////////////////	//버튼을 없앨까 경고창으로 막을까 고민중 --> 버튼을 없애자/////////////////////////////////////	
	if(state=="기결"){
		$('#already').css('display','none');
		$('#late').css('display','none');//기결일 때는 내 순서이므로 앞선 결재자는 다 결재한 상태이고 후결은 없는 상황
										//기결과 반려만 가능하다.
	}	
	if(state=="예결"){
		$('#fit').css('display','none');
		$('#late').css('display','none');//예결일 경우는 나보다 앞선 결재자가 결재하지 않았고 내 순서가 아직 오지 않았을 경우
}
	
	if(state=="후결"){
		$('#fit').css('display','none');
		$('#already').css('display','none');//후결일 경우 나보다 늦은 결재자가 결재를 1명이라도 한 경우 후결로 처리된다.
}
	$('#hapyee').click(function(){
		$('input[name=type]').val('합의');
		
	});
	
	$('#gobu').click(function(){
		$('input[name=type]').val('거부');
		
	});
	
	$('#back').click(function(){
		$('input[name=type]').val('반려');
	});

	$('.appSelec').click(function(){//submit을 누르면 부모창으로 값을 보내고 팝업창 종료
		window.opener.name = "appList";//부모 창 이름 설정
		document.appform.target = "appList";//어느 창으로 갈건지 target지정
		document.appForm.action = "directAppInsert.gw";//실제 데이터가 넘어가는 페이지
		document.appForm.submit();
		self.close();//현재 창을 닫는다.
	});
	
	$('.conSelec').click(function(){//submit을 누르면 부모창으로 값을 보내고 팝업창 종료
		window.opener.name = "appList";//부모 창 이름 설정
		document.conform.target = "appList";//어느 창으로 갈건지 target지정
		document.conform.action = "directAppInsert.gw";//실제 데이터가 넘어가는 페이지
		document.conform.submit();
		self.close();//현재 창을 닫는다.
	});
	
});

</script>
<style>

.apptag{
color:#3498DB;
font-weight : bold;
}
.ti{
margin-left:auto;
margin-right:auto;
 border : 2px solid #8080803b;
 width : 450px;
 height :25px;

}
.ti-po{
position: relative;
right: 85px;
} 

.co{
	margin-left:auto;
	margin-right:auto;
 border : 2px solid #8080803b;
 width : 450px;
 height :200px;　
}
.co-po{
position: relative;
right: 85px;
}
.ap{
margin-left:auto;
margin-right:auto;
width : 450px;
font-weight : bold;

}
.ap-po{
position: relative;
right: 85px;
}
.st{
float:left;
width:33%;
}
.md{
float:left;
width:33%;
}
.ed{
float:left;
width:33%;
} 
</style>
</head>
<body>
<div class ="container-700">

<h2 class = " text-center">기안서</h2>
<div class = "text-right">
<!-- 서블릿으로 결재분류,결재일 값 주고 팝업창 닫아야함 -->

<div class = "isConsesus">
	<form name = "conform" action="directAppInsert.gw" method = "get">
		<input class ="conSelec link-btn" id="hapyee" type = "submit" value = "합의" ><!-- submit에 name을 넣으면 form에서 넘어갈까?? -->
		<input class ="conSelec link-btn" id="gobu" type = "submit" value = "거부"><!-- 합의자 버튼 -->
		
		<input type = "hidden" name = "approval" value = "${sessionScope.empNo}"><!-- session empNo 실제 서블릿으로 보내지는 데이터 -->
		<input type = "hidden" name = "appNo"  value = "${appNo}">
		<input type = "hidden" name = "consesus" value = "1">
		<input type = "hidden" name = "type">
	</form>
</div>

<div class = "isApproval">
	<form name="appform" action="directAppInsert.gw" method = "get">
		<input class = "appSelec link-btn"  id ="already" type = "submit" value = "예결">
		<input class = "appSelec link-btn" id ="late" type = "submit" value = "후결">
		<input class = "appSelec link-btn" id ="fit" type = "submit" value = "기결">
		<input class = "appSelec link-btn" id ="back" type = "submit" value = "반려">
		
		<input type = "hidden" name = "type" value ="${state}"><!-- 실제 서블릿으로 보내지는 데이터 -->
		<input type = "hidden" name = "approval" value = "${sessionScope.empNo}">
		<input type = "hidden" name = "appNo"  value = "${appNo}">
		<input type = "hidden" name = "consesus" value = "0">
	</form>
</div>

</div>
<label class = "apptag">제 목</label><br>
	<div class = "ti ti-po">
	 ${draftapp.appTitle}
	</div>
	
<label class = "apptag">내 용</label><br>
	<div class = "co co-po">
	  ${draftapp.appContent} 
	</div>
						<div class="date">
							<div class = "st text-center">
								<label class = "apptag">상신일</label><br>  
									<div >
									 ${draftapp.appDateStart.substring(0, 10)}
									</div>
								</div>	
							<div class = "md text-center">
								<label class = "apptag">결제일</label><br>
									<div>
									${draftapp.appEndDate}
									</div>
							</div>
							<div class ="ed text-center">		
								<label class = "apptag">마감일</label><br>
									<div>
									   ${draftapp.appDateEnd.substring(0, 10)}
									</div>
							</div>	
						</div><br>	
<label class = "apptag">기안자</label><br>
	<div class = "ap ap-po">
	${draftapp.empName}
	</div>
	
<label class = "apptag">결재자</label><br>	
<c:forEach var ="directappdto" items="${draftdir}">
	<c:if test="${directappdto.consesus eq '0'}">
		<div class = "ap ap-po">
			${directappdto.empName}[${directappdto.approval}]
		</div>
	</c:if>
</c:forEach>		


<br>
<label class = "apptag">합의자</label><br>
<c:forEach var ="directappdto" items="${draftdir}">
	<c:if test="${directappdto.consesus eq '1'}">
		<div class = "ap ap-po">
			${directappdto.empName}[${directappdto.approval}]
		</div>
	</c:if>
</c:forEach>		
<br>
<label class = "apptag">참조자</label><br>

<c:forEach var ="indirectappdto" items="${draftindir}">
	<c:if test="${indirectappdto.consesus eq '참조'}">
		<div class = "ap ap-po">
			${indirectappdto.empName}[${indirectappdto.referrer}]
		</div>
	</c:if>
</c:forEach>		

<br>
<label class = "apptag">시행자</label><br> 
<c:forEach var ="indirectappdto" items="${draftindir}">
	<c:if test="${indirectappdto.consesus eq '시행'}">
		<div class = "ap ap-po">
			${indirectappdto.empName}[${indirectappdto.referrer}]
		</div>
	</c:if>
</c:forEach>	

</div>
</body>


</html>