<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Student List</title>
	<%@ include file="base-link.jsp"%>
	<link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
	
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<%@ include file="header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<h1>
				Student List <small>Control panel</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/datnt/home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Student List</li>
			</ol>
		</div>

		<!-- Main content -->
		<div class="content">
			
			<!-- Search row -->
			<div class="row">
				<div class="col-sm-6">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Student Search</h3>
						</div>
						
						<div class="box-body">
							<form id="js-search-frm-1" action="" method="post">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="studentcode">Student code</label>
											<input id="user-reset" name="studentCode" placeholder="Enter code" class="form-control" type="text" value="">
										</div>
									</div>
									
									<div class="col-sm-6">
										<div class="form-group">
											<label for="studentname">Student name</label>
											<input id="user-reset" name="studentName" placeholder="Enter name" class="form-control" type="text">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="studentcode">Sex</label>
											<div>
												<input type="radio" name="sex" value="" class="flat-red" checked><label style="margin-right: 7px;">All</label>
												<input type="radio" name="sex" value="Male" class="flat-red" ><label style="margin-right: 7px;">Male</label>
												<input type="radio" name="sex" value="Female" class="flat-red"><label>Female</label>
											</div>
										</div>
									</div>
									
									<div class="col-sm-6">
										<div class="form-group">
											<label for="studentcode">School</label>
											<select class="form-control" name="school">
												<option value=""></option>
												<c:forEach items="${schools}" var="sch">
													<option value="${sch.schoolId}"> ${sch.schoolName} </option>
												</c:forEach>
												
											</select>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>Date from</label><div class="js-date-message datefrom" style="color:red;"></div>
											<input id="js-date-from" class="form-control" type="date" name="dateFrom">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>Date to</label><div class="js-date-message dateto" style="color:red;"></div>
											<input id="js-date-to" class="form-control" type="date" name="dateTo">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
										<button id="js-search-btn-1" class="btn btn-primary" style="width: 100%;">Search</button>
									</div>
								</div>
							</form>
						</div>
						
					</div>
				</div>
			</div>
					
			<!-- Student list -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Student List</h3>
							<button id="js-btn-create" class="btn btn-success" style="margin-left: 10px;" data-toggle="modal" data-target="#js-student-create">Create new</button>
						</div>
						
						<div class="box-body">
							  <table id="student-table" class="table table-bordred table-striped">
								<thead>
									<tr>
										<th>Code</th>
										<th>Name</th>
										<th>Sex</th>
										<th>Birthday</th>
										<th>School</th>
										<th>School year</th>
										<th>Phone</th>
										<th>Email</th>
										<th>Address</th>
										<!-- 
										<th style="width: 5%; text-align: center;">Update</th>
										<th style="width: 5%; text-align: center;">Delete</th>
										 -->
									</tr>
								</thead>
								<tbody id="js-search-body">
					
									<c:forEach items="${studentList}" var="student">
										<tr id="js-row-st-${student.studentId}">
											<td>${student.studentCode}</td>
											<td>${student.studentName}</td>
											<td>${student.record.sex}</td>
											<td>${student.record.birthday}</td>
											<td>${student.school.schoolCode}</td>
											<td>${student.startYear}</td>
											<td>${student.record.phone}</td>
											<td>${student.record.email}</td>
											<td>${student.record.address}</td>
											<!-- 
											<td style="width: 5%; text-align: center;">
												<button onclick="updateBtn(${student.studentId})">
													<i class="fa fa-check" aria-hidden="true"
														style="font-size: 20px;"></i>
												</button>
											</td>
											<td style="width: 5%; text-align: center;">
												<button>
													<i class="fa fa-trash" aria-hidden="true"
														style="font-size: 20px;"></i>
												</button>
											</td>
											 -->
										</tr>
									</c:forEach>
									
								</tbody>
								<tfoot>
									<tr>
										<th>Code</th>
										<th>Name</th>
										<th>Sex</th>
										<th>Birthday</th>
										<th>School</th>
										<th>School year</th>
										<th>Phone</th>
										<th>Email</th>
										<th>Address</th>
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
	
<div class="modal fade" id="js-student-info" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Student Info</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-xs-12">
        		<div class="box">
        			<div class="box-body">
        				<form id="js-studentinfo-frm" action="" method="POST">
        					<div class="form-group">
        						<label for="studentcode">Student Code</label>
        						<input id="js-code-md" class="form-control" name="" type="text" disabled="disabled">
        						<input id="js-hide-md" type="hidden" name="studentCode">
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Student Name</label>
        						<div id="js-name-error-md" style="color: red;"></div>
        						<input id="js-name-md" class="form-control" name="studentName" type="text">
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Sex</label>
								<select id="js-sex-md" class="form-control"  name="sex">
        							<option value="Male">Male</option>
        							<option value="Female">Female</option>
        						</select>
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Birthday</label>
        						<div id="js-birthday-error-md" style="color: red;"></div>
        						<input id="js-birthday-md" class="form-control" name="birthday" type="date">
        					</div>
        				
        					<div class="form-group">
        						<label for="studentcode">School</label>
        						<select id="js-school-md" class="form-control" name="school">
        							<c:forEach items="${schools}" var="sch">
        								<option value="${sch.schoolCode}">${sch.schoolCode}</option>
        							</c:forEach>
        						</select>
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">School year</label>
        						<select id="js-schoolyear-md" class="form-control"  name="schoolYear">
        							<option value="2017-2018">2017-2018</option>
        						</select>
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Phone</label>
        						<div id="js-phone-error-md" style="color: red;"></div>
        						<input id="js-phone-md" class="form-control" name="phone" type="text">
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Email</label>
        						<div id="js-email-error-md" style="color: red;"></div>
        						<input id="js-email-md" class="form-control" name="email" type="text">
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Address</label>
        						<input id="js-address-md" class="form-control" name="address" type="text">
        					</div>
        					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        					<button id="js-delete-btn" type="button" class="btn btn-danger">Delete</button>
        					<button id="js-update-btn" type="submit" class="btn btn-primary" style="margin-left: 20px;">Update</button>
        				</form>
        			</div>
        		</div>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	


<div class="modal fade" id="js-student-create" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    	<form id="js-create-frm" action="" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Create New Student</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-xs-12">
        		<div class="box">
        			<div class="box-body">
        				
        					<div class="form-group">
        						<label for="studentcode">Student Name</label>
        						<div id="js-name-error-cr" style="color: red;"></div>
        						<input id="js-name-crate" class="form-control" name="studentName" type="text">
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Student Password</label>
        						<div id="js-pwd-error-cr" style="color: red;"></div>
        						<input id="js-password-crate" class="form-control" name="password" type="password">
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Sex</label>
								<select id="js-sex-create" class="form-control"  name="sex">
        							<option value="Male">Male</option>
        							<option value="Female">Female</option>
        						</select>
        					</div>
        					<div class="form-group">
        						<label for="studentcode">Birthday</label>
        						<div id="js-date-error-cr" style="color: red;"></div>
        						<input id="js-birthday-create" class="form-control" name="birthday" type="date">
        					</div>
        				
        					<div class="form-group">
        						<label for="studentcode">School</label>
        						<select id="js-school-create" class="form-control" name="school">
        							<c:forEach items="${schools}" var="sch">
        								<option value="${sch.schoolCode}">${sch.schoolCode}</option>
        							</c:forEach>
        						</select>
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">School year</label>
        						<select id="js-schoolyear-create" class="form-control"  name="schoolYear">
        							<option value="2017-2018">2017-2018</option>
        						</select>
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Phone</label>
        						<div id="js-phone-error-cr" style="color: red;"></div>
        						<input id="js-phone-create" class="form-control" name="phone" type="text">
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Email</label>
        						<div id="js-name-email-cr" style="color: red;"></div>
        						<input id="js-email-create" class="form-control" name="email" type="text">
        					</div>
        					
        					<div class="form-group">
        						<label for="studentcode">Address</label>
        						<input id="js-address-create" class="form-control" name="address" type="text">
        					</div>
        			</div>
        		</div>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
     	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button  type="submit" class="btn btn-primary">Create</button>
      </div>
      </form>
    </div>
  </div>
</div>



	<%@ include file="footer.jsp"%>
	<%@ include file="base-script.jsp"%>
	<script src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
	<!-- page script --><script src="<c:url value="/js/validator.js" />"></script>
	<script src="<c:url value="/js/main.js" />"></script>
	
	
</body>
</html>