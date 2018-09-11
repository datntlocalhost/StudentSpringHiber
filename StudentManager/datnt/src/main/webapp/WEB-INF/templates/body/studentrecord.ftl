<#import "../spring.ftl" as spring />
	<div class="content-wrapper">
	
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<h1>
				Student Records <small>Control panel</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="<@spring.url '/home' />"><i class="fa fa-dashboard"></i> Home</a></li>
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
	        						<@spring.formHiddenInput "student.studentCode"/>
	        						<input id="js-name-crate" class="form-control" type="text" disabled="disabled" value="${student.studentCode}" >
	        					</div>
	        						
								<div class="form-group">
	        						<label for="studentcode">Student Name</label>
	        						<@spring.formHiddenInput "student.studentName"/>
	        						<input id="js-name-crate" class="form-control" name="studentName" type="text" disabled="disabled" value="${student.studentName}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Sex</label>
	        						<@spring.formHiddenInput "student.sex"/>
									<input type="text" class="form-control" disabled="disabled" value="${student.sex}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Birthday</label>
	        						<@spring.formHiddenInput "student.birthday"/>
	        						<input id="js-birthday-create" class="form-control" type="date" disabled="disabled" value="${student.dayString}">
	        					</div>
	        				
	        					<div class="form-group">
	        						<label for="studentcode">School</label>
	        						<@spring.formHiddenInput "student.schoolCode"/>
	        						<input type="text" class="form-control"  disabled="disabled" value="${student.schoolCode}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">School year</label>
	        						<@spring.formHiddenInput "student.schoolYear"/>
	        						<input type="text" class="form-control" disabled="disabled" value="${student.schoolYear}">
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Phone</label>
	        						<div id="js-phone-error-cr" style="color: red;"></div>
	        						<@spring.formInput "student.phone" "id='js-phone-create' class='form-control'" "text" />
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Email</label>
	        						<div id="js-email-error-cr" style="color: red;"></div>
	        						<@spring.formInput "student.email" "id='js-email-create' class='form-control'" "text" />
	        					</div>
	        					
	        					<div class="form-group">
	        						<label for="studentcode">Address</label>
	        						<@spring.formInput "student.address" "id='js-address-create' class='form-control'" "text" />
	        					</div>
	        					
	        					<div class="form-group" style="text-align: right;">
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