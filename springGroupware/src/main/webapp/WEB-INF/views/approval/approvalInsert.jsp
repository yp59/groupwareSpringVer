<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/referencePopUp.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="drafter" value="${sessionScope.empNo}"></c:set><!-- 현재 접속한 사원의 회원번호를 drafter로 저장 -->
<c:set var="approvalGroup" value="${param.approval}"></c:set>
<c:set var="consesusGroup" value="${param.consesus}"></c:set>
<c:set var="referrerGroup" value="${param.reffer}"></c:set>
<c:set var="implementerGroup" value="${param.implementer}"></c:set>

<%/*String id = (String) session.getAttribute("id");

String app = request.getParameter("approval");//부서리스트에서 얻은 데이터값을 파라미터로 저장한다.
String con = request.getParameter("consesus");
String ref = request.getParameter("reffer");
String imp = request.getParameter("implementer");

if(app==null){app="";}//아직 부서 리스트 별 결재자 값을 안받아 왔을 때 해당값을 공백으로 설정한다.
if(con==null){con="";}
if(ref==null){ref="";}
if(imp==null){imp="";}

*/
%>    

<body>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	
	function getFormatDate(date){//오늘 날짜 받는 함수
		
    var year = date.getFullYear();
    var month = (1 + date.getMonth());
    month = month >= 10 ? month : '0' + month;
    var day = date.getDate();
    day = day >= 10 ? day : '0' + day;
    return year + '-' + month + '-' + day;
	
	}
	var today = getFormatDate(new Date());   

$(function(){//window.load와 같은의미
	$('input[name=appDateStart]').val(today);//    date로 받은 값 cal을 appDateStart에 넣는다.
	$('input[name=appDateEnd]').val(today);//    date로 받은 값 cal을 appDateEnd에 넣는다.
	
	$(".appPeople").click(function(){
		var _width = '750';
	    var _height = '700';
	 
	    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
	    var _left = Math.ceil(( window.screen.width - _width )/2);
	    var _top = Math.ceil(( window.screen.height - _height )/2);
	    
	var option ='width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top ;
		//window.open("InsertDepartmentPopUp.jsp","DepartPopUp",option);//이름이 같으면 같은 창에서 열림 주의하자
		window.open("InsertDepartmentPopUp","departPopUp",option);
	});
	
	$('input[name=subb]').click(function(){//submit을 누르면 부모창으로 값을 보내고 팝업창 종료
		window.opener.name = "approvalMain";//부모창 이름 설정
		document.appForm.target = "approvalMain";//기안서 보낼때 어느 화면으로 갈건지 선택
		document.appForm.action = "approvalInsert";//실제 데이터가 보내지는 페이지
		document.appForm.submit();
		self.close();//지금 보고있는 창은 종료된다.
	});
	
	
	
});
</script>

<main>
	<section>
		<form name = "appForm" method ="post" class = "fon-color">
			<div>
				 <input type="hidden" name ="drafter" value="${drafter}">
					기안일  <input type = "date" name = "appDateStart" ><br><br>
			</div>
			
			<div>
					결재자 <input type = "text" class="appPeople design" name = "approvalGroup" value="${approvalGroup}"><br><!-- approvalNo로 이름 지었는데 Vo통일성을 위해서 No지움 -->
					합의자 <input type = "text" class="appPeople design" name = "consesusGroup" value="${consesusGroup}"><br> 
					참조자 <input type = "text" class="appPeople design" name = "refferGroup" value="${referrerGroup}"><br>
					시행자 <input type = "text" class="appPeople design" name = "implementerGroup" value="${implementerGroup}"><br><br>
			
			</div>
			
			<div>
					제목  <input type = "text" class="design" name = "appTitle"><br> 
			</div>
			
			<div>
				내용  <textarea rows="16" class="input design" name="appContent"></textarea><br><br>
			</div>
			
			<div>
				마감일 <input type = "date" name = "appDateEnd"> 
			</div>
			
			<!-- 첨부 아직 미구현 -->
			<div class = "text-right">
					<input class="link-btn" type ="submit" value = "기안" name ="subb">
			</div>		
		</form>
	</section>	
</main>
</body>
</html>
