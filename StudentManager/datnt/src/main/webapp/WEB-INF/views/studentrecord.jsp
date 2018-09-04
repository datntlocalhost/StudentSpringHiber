<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Student Records</title>
	<%@ include file="base-link.jsp"%>
	<link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<%@ include file="header.jsp"%>
	
	<div class="content-wrapper">
	
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<h1>
				Student Records <small>Control panel</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/datnt/home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Student Records</li>
			</ol>
		</div>
		
		<!-- Main content -->
		<div class="content">
			<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Records</h3>
						</div>
						<div class="box-body">
							<div class="" style="width: 50%; margin: auto;">
							
							<form action="" method="POST" id="js-student-update">
									
								<div class="form-group">
	        						<label for="studentcode">Student Code</label>
	        						<input type="hidden" name="studentCode" value="${student.studentCode}" >
	        						<input id="js-name-crate" class="form-control" type="text" disabled="disabled" value="${student.studentCode}" >
	        					</div>
	        						
								<div class="form-group">
	        						<label for="studentcode">Student Name</label>
	        						<input type="hidden" name="studentName" value="${student.studentName}">
	        						<input id="js-name-crate" class="form-control" name="studentName" type="text" disabled="disabled" value="${student.studentName}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Sex</label>
	        						<input type="hidden" name="sex" value="${student.sex}">
									<input type="text" class="form-control" disabled="disabled" value="${student.sex}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Birthday</label>
	        						<input type="hidden" name="birthday" value="${student.birthday}">
	        						<input id="js-birthday-create" class="form-control" type="date" disabled="disabled" value="${student.birthday}">
	        					</div>
	        				
	        					<div class="form-group">
	        						<label for="studentcode">School</label>
	        						<input type="hidden" name="schoolCode" value="${student.schoolCode}">
	        						<input type="text" class="form-control"  disabled="disabled" value="${student.schoolCode}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">School year</label>
	        						<input type="hidden" name="schoolYear" value="${student.schoolYear}">
	        						<input type="text" class="form-control" disabled="disabled" value="${student.schoolYear}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Phone</label>
	        						<div id="js-phone-error-cr" style="color: red;"></div>
	        						<input id="js-phone-create" class="form-control" name="phone" type="text" value="${student.phone}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Email</label>
	        						<div id="js-email-error-cr" style="color: red;"></div>
	        						<input id="js-email-create" class="form-control" name="email" type="text" value="${student.email}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Address</label>
	        						<input id="js-address-create" class="form-control" name="address" type="text" value="${student.address}">
	        					</div>
	        					
	        					<div class="form-group" style="text-align: right;">
	        						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
									<button type="submit" class="btn btn-primary">Save changes</button>
								</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
	<%@ include file="footer.jsp"%>
	<%@ include file="base-script.jsp"%>
	<script src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
	<script src="<c:url value="/js/validator.js" />"></script>
	<script src="<c:url value="/js/main.js" />"></script>
	<c:if test="${success}">
		<script>
			alert("Update info success.");
		</script>
	</c:if>
	
	<c:if test="${error}">
		<script>
			alert("Update info failed.");
		</script>
	</c:if>
	
	<c:if test="${invalid}">
		<script>
			alert("Invalid info.");
		</script>
	</c:if>
</body>
</html>