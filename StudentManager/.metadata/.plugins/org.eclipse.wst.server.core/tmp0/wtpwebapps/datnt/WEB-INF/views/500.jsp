<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>500 Server Error</title>
	 <link rel="stylesheet" href="<c:url value="/css/error.css" />">
	 
</head>
<body>
	<h1>500 Server Error</h1>
	<p class="zoom-area">
		<b>Oops!</b>. Something went wrong.
	</p>
	<div class="error-container"> <span>5</span> <span><span
		class="screen-reader-text">0</span></span> <span>0</span> </div>
	<div class="link-container">
		<a target="_blank"
			href="/datnt/login"
			class="more-link">Go Login</a>
	</div>

	<script src="<c:url value="/js/error.js" />"></script>
</body>
</html>