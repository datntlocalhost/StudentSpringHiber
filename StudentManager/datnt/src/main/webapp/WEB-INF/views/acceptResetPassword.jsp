<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
	<title>Login</title>
	<%@ include file="base-link.jsp"%>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">

	<%@ include file="header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<h1>
				Reset Student Password 
				<small>Control panel</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/datnt/home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Reset Student Password </li>
			</ol>
		</div>

		<!-- Main content -->
		<div class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Unprocess list</h3>
						</div>
						
						<div class="box-body">
							  <table id="accept-reset-table" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Student code</th>
										<th>Student name</th>
										<th>Email</th>
										<th>Time</th>
										<th style="width: 5%; text-align: center;">Accept</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listUnprocess}" var="item">
										<tr>
											<td>${item.studentCode}</td>
											<td>${item.studentName}</td>
											<td>${item.email}</td>
											<td>${item.time}</td>
											<td style="width: 5%; text-align: center;">
												<button class="accept"><i class="fa fa-check" aria-hidden="true"  style="font-size:20px;"></i></button>
											</td>
									</tr>	
									</c:forEach>
									

								</tbody>
								<tfoot>
									<tr>
										<th>Student code</th>
										<th>Student name</th>
										<th>Email</th>
										<th>Time</th>
										<th style="width: 5%; text-align: center;">Accept</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Processed list</h3>
						</div>
						
						<div class="box-body">
							  <table id="process-reset-table" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Student code</th>
										<th>Student name</th>
										<th>Email</th>
										<th>Time</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listProcessed}" var="item">
										<tr>
											<td>${item.studentCode}</td>
											<td>${item.studentName}</td>
											<td>${item.email}</td>
											<td>${item.time}</td>
										</tr>	
									</c:forEach>
									

								</tbody>
								<tfoot>
									<tr>
										<th>Student code</th>
										<th>Student name</th>
										<th>Email</th>
										<th>Time</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!-- /.content -->

	</div>
	<!-- /.content-wrapper -->

	<%@ include file="footer.jsp"%>
	<%@ include file="base-script.jsp"%>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
	<script src="<c:url value="/js/main.js" />"></script>

</body>
</html>