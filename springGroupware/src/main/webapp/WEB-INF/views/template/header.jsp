<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var ="root" value="${pageContext.request.contextPath}"></c:set>   
<c:set var ="isLogin" value="${not empty employeesDto}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<title>Groupware</title>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${root }/css/common.css">
	<link rel="stylesheet" type="text/css" href="${root }/css/layout.css">
	<link rel="stylesheet" type="text/css" href="${root }/css/menu.css">
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<style>
.li-nav {
list-style: none;
float: right;
clear: left;
}
.right {
width: 900px;
margin-right:0px;
margin-bottom: 30px;
}
.float-container,nav {
margin-top: 10px;
margin-bottom: 20px;
height: 60px;
}
.loginSub{
margin-right: 37px;
text-align: right;
}
.logout {
margin-left: 10px;
}
.company-logo > a:link, a:visited {
  color : rgb(52, 152, 219);
text-decoration: none;
}
.company-logo {
font-size: 40px;
}

.loginSub > a:link, a:visited {
 color : rgb(52, 152, 219);
text-decoration: none;
}
	</style>
</head>  
<body>
	<!-- 로고영역 -->
			<div class="text-center ">
				<div class="company-logo">
				<a href="${root}/">GroupWare</a>
				</div>

			</div> 

				
			<c:if test="${isLogin}">
				<div class="loginSub">${employeesDto.empName}님 환영합니다.
				<a class="logout" href="${root}/logOut">로그아웃</a>
				
			</div>
			</c:if>
			<!-- 네비게이션 영역 -->
			<nav>
			<!-- 메뉴 -->
			<ul class="menu">
			  <li>
					<a href="#">공지 메일</a>
				</li>
				<li>
					<a href="#">일정 목록</a>
				</li>
				<li>
					<a href="#">주소록</a>
				</li>
				<li>
					<a href="#">메시지</a>
					<ul>
						<li><a href="#">수신함</a></li>
						<li><a href="#">발신함</a></li>
					</ul>
				</li>
				<li>
					<a href="#">전자 결재</a>
					<ul>



						<li><a href="#">기안서 작성</a></li>
						<li><a href="#">내 결재 관리</a></li>

					</ul>
				</li>
				<li>
					<a href="#">근태 관리</a>
					<ul>

						<li><a href="#">출퇴근 목록</a></li>
						<li><a href="#">휴가 목록</a></li>



					</ul>
				</li>
				<li>
					<a href="#">게시판</a>
					<ul>
						<li><a href="#">공지 게시판</a></li>
						<li><a href="#">질문 게시판</a></li>
						<li><a href="#">자유 게시판</a></li>
						<li><a href="#">내 게시글</a></li>
					</ul>
				</li>
				<li>
					<a href="#">마이 페이지</a> 
					<ul>
						<li><a href="#">나의 정보</a></li>
						<li>
						<a href="#">나의 급여</a>
						</li>
						
					</ul>
				</li>
			  </ul>
		</nav>
	
	
	
	
	<!-- 가장 바깥 영역 -->
	<main>
	


	

		
		<!-- 컨텐츠영역 -->
		<section>
			<header></header>
			<article>
			