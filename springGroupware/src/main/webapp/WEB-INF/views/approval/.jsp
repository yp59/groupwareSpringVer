<%@page import="java.util.List"%>
<%@page import="groupware.beans.employeesDto"%>
<%@page import="groupware.beans.employeesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/departmentPopUp.jsp"></jsp:include>
<%
employeesDao employeesdao = new employeesDao();//jquery와 jsp 는 연동이 안되기 때문에(jsp-->jquery는 가능)
List<employeesDto> insa = employeesdao.list("인사부");//화면을 미리 만들어두고 event에 따라 보여줄지 안보여줄지 선택
List<employeesDto> chong = employeesdao.list("총무부");
List<employeesDto> hwa = employeesdao.list("회계부");
List<employeesDto> gi = employeesdao.list("기획부");
List<employeesDto> young = employeesdao.list("영업부");
%>
<body>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>

$(function(){

//////////제이쿼리를 이용하여 부서별 사원 리스트를 display로 on/off 할 수 있게끔 함////////////////////////////////
	
	$('#insa').click(function(){
		$('.appSelect').prop('checked',false);//부서 리스트를 닫았을 때 체크를 해제
		
		if($('#view1').css('display')=='block'){
			$('.view').css('display','none');
			$('#view1').css('display','none');
		
		}
		else{
			$('.view').css('display','none');
			$('#view1').css('display','block');
		}
		
	});
	
	$('#chong').click(function(){
		$('.appSelect').prop('checked',false);//부서 리스트를 닫았을 때 체크를 해제
		
		if($('#view2').css('display')=='block'){
			$('.view').css('display','none');
			$('#view2').css('display','none');
			
			
		}
		else{
			$('.view').css('display','none');
			$('#view2').css('display','block');
		}
		
	});
	
	$('#hwa').click(function(){
		$('.appSelect').prop('checked',false);//부서 리스트를 닫았을 때 체크를 해제
		
		if($('#view3').css('display')=='block'){
			$('.view').css('display','none');
			$('#view3').css('display','none');
		
			
		}
		else{
			$('.view').css('display','none');
			$('#view3').css('display','block');
		}
		
	});
	
	$('#gi').click(function(){
		$('.appSelect').prop('checked',false);//부서 리스트를 닫았을 때 체크를 해제
		
		if($('#view4').css('display')=='block'){
			$('.view').css('display','none');
			$('#view4').css('display','none');
			
			
		}
		else{
			$('.view').css('display','none');
			$('#view4').css('display','block');
		}
		
	});
	
	$('#young').click(function(){
		$('.appSelect').prop('checked',false);//부서 리스트를 닫았을 때 체크를 해제
		
		if($('#view5').css('display')=='block'){
			$('.view').css('display','none');
			$('#view5').css('display','none');
				
		}
		else{
			$('.view').css('display','none');
			$('#view5').css('display','block');
		}
		
	});
	
	////////////////////////////////////////////////////////////////////////
	
	$('.appSelect').click(function(){//선택한 사원을 아래에 표시해 주는 jquery 구문 체크박스를 체크하고 버튼을 누르면 해당값이 나타남
		//console.log($('.appSelect:checked').val()); //여러개 선택하면 처음 값만 나온다.
		/*
		$('#mid').click(function(){
			
			$('#mids').html($('.appSelect:checked').val());
			
			var empNo = $('.appSelect:checked').val();
			empNo = empNo.slice(-5,-1);//뒤에서부터 empNO을 잘라서 empNo을 구한다
			$('input[name=midApproval]').val(empNo);//empNo값을 저장한다.
			console.log($('input[name=midApproval]').val());// console창 값 확인용
		
		});
		*/
		$('#mid').click(function(){
			
			$('#mids').html($('.appSelect:checked').val());
			
			var emp = $('.appSelect:checked').val();
			console.log(emp);// console창 값 확인용
			$('input[name=midApproval]').val(emp);//emp값을 저장한다.
			console.log($('input[name=midApproval]').val());// console창 값 확인용
		
		});
		
		$('#final').click(function(){
			
			$('#finals').html($('.appSelect:checked').val());
		
			var emp = $('.appSelect:checked').val();
			
			$('input[name=finalApproval]').val(emp);//empNo값을 저장한다.
			console.log($('input[name=finalApproval]').val());// console창 값 확인용
			
		});
		
		$('#con').click(function(){
			
			$('#cons').html($('.appSelect:checked').val());
		
			var emp = $('.appSelect:checked').val();
			
			$('input[name=consesus]').val(emp);//empNo값을 저장한다.
			console.log($('input[name=consesus]').val());// console창 값 확인용
		});
	
		$('#ref').click(function(){
		
		$('#refs').html($('.appSelect:checked').val());
	
		var emp = $('.appSelect:checked').val();
	
		$('input[name=reffer]').val(emp);//empNo값을 저장한다.
		console.log($('input[name=reffer]').val());// console창 값 확인용
	});
	
		$('#imple').click(function(){
		
		$('#imples').html($('.appSelect:checked').val());
	
		var emp = $('.appSelect:checked').val();
	
		$('input[name=implementer]').val(emp);//empNo값을 저장한다.
		console.log($('input[name=implementer]').val());// console창 값 확인용
	});
	});
	
	$('input[name=subb]').click(function(){//submit을 누르면 부모창으로 값을 보내고 팝업창 종료
		window.opener.name = "draftPopUp";
		document.appForm.target = "draftPopUp";
		document.appForm.action = "approvalInsert.jsp";
		document.appForm.submit();
		self.close();
	});
});


</script>
	<main>
		<aside>
		<!-- 조직도 리스트 -->
		<div>
			<ul>
				<li >
				조직도
				</li>
			<ul>
				<li class="depart" id ="insa">
				인사부
				</li>
				<li class="depart" id ="chong">
				총무부
				</li>
				<li class="depart" id ="hwa">
				회계부
				</li>
				<li class="depart" id ="gi">
				기획부
				</li>
				<li class="depart" id ="young">
				영업부
				</li>
			</ul>
			</ul>
		</div>	
		</aside>
		
	
		<section>
		<h2>부서 리스트</h2>
			<header >
			<div class = "view" id="view1">
			<table class="table table-border table-hover">
		<thead>
		
			<tr><th>√</th>
				<th>부서</th>
				<th>직급</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody><%for(employeesDto employeesdto : insa){%>	
				<tr>
					<th><input type = "checkbox" class ="appSelect" value = "<%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]"></th>
					<td><%=employeesdto.getDepartment()%></td>
					<td><%=employeesdto.getPoSi()%></td>
					<td><%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]</td>
				</tr>
		<%}%>
		</tbody>
</table>
</div>	

		<div class = "view" id="view2">
			<table class="table table-border table-hover">
		<thead>
		
			<tr><th>√</th>
				<th>부서</th>
				<th>직급</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody><%for(employeesDto employeesdto : chong){%>	
				<tr>
					<th><input type = "checkbox" class ="appSelect" value = "<%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]"></th>
					<td><%=employeesdto.getDepartment()%></td>
					<td><%=employeesdto.getPoSi()%></td>
					<td><%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]</td>
				</tr>
		<%}%>
		</tbody>
</table>
</div>	
		<div class = "view" id="view3">
			<table class="table table-border table-hover">
		<thead>
		
			<tr><th>√</th>
				<th>부서</th>
				<th>직급</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody><%for(employeesDto employeesdto : hwa){%>	
				<tr>
					<th><input type = "checkbox" class ="appSelect" value = "<%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]"></th>
					<td><%=employeesdto.getDepartment()%></td>
					<td><%=employeesdto.getPoSi()%></td>
					<td><%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]</td>
				</tr>
		<%}%>
		</tbody>
</table>
</div>	
		<div class = "view" id="view4">
			<table class="table table-border table-hover">
		<thead>
		
			<tr><th>√</th>
				<th>부서</th>
				<th>직급</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody><%for(employeesDto employeesdto : gi){%>	
				<tr>
					<th><input type = "checkbox" class ="appSelect" value = "<%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]"></th>
					<td><%=employeesdto.getDepartment()%></td>
					<td><%=employeesdto.getPoSi()%></td>
					<td><%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]</td>
				</tr>
		<%}%>
		</tbody>
</table>
</div>	
		<div class = "view" id="view5">
			<table class="table table-border table-hover">
		<thead>
		
			<tr><th>√</th>
				<th>부서</th>
				<th>직급</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody><%for(employeesDto employeesdto : young){%>	
				<tr>
					<th><input type = "checkbox" class ="appSelect" value = "<%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]"></th>
					<!-- check박스의 value를  넣어서 값을 넣어준다. empNo뽑아낼때는 subString 사용???-->
					<td><%=employeesdto.getDepartment()%></td>
					<td><%=employeesdto.getPoSi()%></td>
					<td><%=employeesdto.getEmpName()%>[<%=employeesdto.getEmpNo()%>]</td>
				</tr>
		<%}%>
		</tbody>
</table>
</div>

</header>
		<article>
	<div class="row"><!-- 결재 파트 사원 클릭하고 버튼 누르면 결재자 지정됨 -->
				<input type = "button" id = "mid" value = "중간 결재"><span id = "mids"></span><br><br>
				<input type = "button" id = "final" value = "최종 결재"><span id = "finals"></span><br><br>
				<input type = "button" id = "con" value = "합의"><span id = "cons"></span><br><br>
				<input type = "button" id = "ref" value = "참조"><span id = "refs"></span><br><br>
				<input type = "button" id = "imple" value = "시행"><span id = "imples"></span><br><br>
	</div>
	
	<div>
	<form name ="appForm" method = "get"><!-- 부서 리스트에서 결재자를 선택하고 실제로 데이터를 보내는 파트
														post로 보내면 한글 깨진다 -->
	<input type ="hidden" name = "midApproval">
	<input type ="hidden" name = "finalApproval">
	<input type ="hidden" name = "consesus">
	<input type ="hidden" name = "reffer">
	<input type ="hidden" name = "implementer">
	<input type = "submit" value ="전송√" name ="subb">
	</form>
	</div>
	
		</article>
	</section>
	</main>
</body>
</html>