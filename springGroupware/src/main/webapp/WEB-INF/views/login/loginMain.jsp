<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>그룹웨어 로그인</title>
	<style>
		h1{
	 	color :rgb(52,152,219);
	 	}
		*{
		  margin: 0px;
		  padding: 0px;
		  font-family:sans-serif;
		}
		
		.loginForm {
		  position:absolute;
		  width:300px;
		  height:400px;
		  padding: 30px, 20px;
		  text-align:center;
		  top:50%;
		  left:50%;
		  transform: translate(-50%,-50%);
		  border:1px solid black !important;
		  border-radius: 15px;
		}
		
		.loginForm h1{
		  text-align: center;
		  margin: 30px;
		}
		
		.idForm{
		  border-bottom: 2px solid #adadad;
		  margin: 30px;
		  padding: 10px 10px;
		}
		
		.passForm{
		  border-bottom: 2px solid #adadad;
		  margin: 30px;
		  padding: 10px 10px;
		}
		
		.id {
		  width: 100%;
		  border:none;
		  outline:none;
		  font-size:16px;
		  height:25px;
		  background: none;
		}
		
		.pw {
		  width: 100%;
		  border:none;
		  outline:none;
		  font-size:16px;
		  height:25px;
		  background: none;
		}
		
		.form-btn {
		  width:70% !important;
		}
		
</style>
<link rel="stylesheet" type="text/css" href="${root}/css/common.css">
</head>
<body width="100%" height="100%">
<div class="loginForm container-500">

<form action="loginMain" method="post">
	<h1>GroupWare</h1>
	<div class="idForm">
	아이디  <input type="text" class="id" name="empNo" placeholder="ID" required>
	</div>
	<div class="passForm">
	비밀번호  <input type="password" class="pw" name="empPw" placeholder="PW" required><br>
	</div>
	
	<input type="submit" class="form-btn form-btn-positive" value="로그인">
</form>

<div class = "row error" style="height:20px; border:none;">
<c:if test="${param.error !=null}"><!-- 에러가 null이 아니면 -->
아이디 혹은 비밀번호가 일치하지 않습니다.
</c:if>
</div>
</div>

</body>
</html>