<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/css/main.css" />">
	
</head>
<body>
	<c:url value="/login" var="loginUrl" />
	
	<div class="login-form" style="width: 30%; margin: 100px auto;">
	    <form id="js-login-frm" action="${loginUrl}" method="post">
	        <h2 class="text-center">Log in</h2>
			<c:if test="${param.error != null}">
				<div class="error">
					<p>Invalid username and password.</p>
				</div>
			</c:if>
			<div class="form-group">
				<span id="js-usr-invalid" class="error"></span>
	            <input id="js-username" type="text" class="form-control" name="username" placeholder="Username" required="required">
	        </div>
	        <div class="form-group">
	        	<span id="js-pwd-invalid" class="error"></span>
	            <input id="js-password" type="password" class="form-control" name="password" placeholder="Password" required="required">
	        </div>
	        <div class="form-group">
	            <input type="checkbox" name="remember-me">
	            <label>Remember me</label>
	        </div>
	        <div class="form-group">
	            <button type="submit" class="btn btn-primary btn-block">Log in</button>
	        </div>
	        <div class="clearfix">
	            <a href="/datnt/sendreset" class="pull-right">Forgot Password?</a>
	        </div>
			<!-- input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->   
	    </form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<c:url value="/js/validator.js"/>"></script>
</body>
</html>