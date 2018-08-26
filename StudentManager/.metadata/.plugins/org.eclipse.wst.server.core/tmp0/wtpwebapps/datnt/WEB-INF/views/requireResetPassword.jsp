<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
	<title>Reset Password</title>
	<%@ include file="base-link.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<c:url value="/sendreset" var="reset" />
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>
								<i class="fa fa-lock fa-4x"></i>
							</h3>
							<h2 class="text-center">Forgot Password?</h2>
							<p>You can reset your password here.</p>
							<div class="panel-body">

								<form id="reset-pwd-form" action="${reset}" role="form" autocomplete="off"
									class="form" method="post">

									<div class="form-group">
										<div id="error-username" style="color: red;"></div>
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user color-blue"></i>
											</span>
											
											<input id="user-reset" name="username" placeholder="Username" class="form-control" type="text" required="required">
										</div>
									</div>
									
									<div class="form-group">
										<div id="error-email" style="color: red;"></div>
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-envelope color-blue"></i>
											</span>
											
											<input id="email" name="email" placeholder="Email address" class="form-control" type="email" required="required">
										</div>
									</div>
									
									<div class="form-group">
										<div id="error-password" style="color: red;"></div>
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-lock color-blue"></i>
											</span>
											
											<input id="pwd-reset" name="newPassword" placeholder="New Password" class="form-control" type="password" required="required">
										</div>
									</div>
									
									<div class="form-group">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Send Requirement" type="submit">
									</div>
									<input type="hidden" class="hide" name="token" id="token" value="">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="reset-modal" tabindex="-1"
		role="dialog" aria-labelledby="resetModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Success</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					You're requirement reset password was send to your administrator.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">>OK</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="error-modal" tabindex="-1"
		role="dialog" aria-labelledby="resetModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Error</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Username does not exist or Your request is pending.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">>OK</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="base-script.jsp"%>
	<script src="<c:url value="/js/validator.js" />"></script>
	<c:if test="${success}">
		<script>
			$(function() {
				$('#reset-modal').modal('toggle');
			})
		</script>
	</c:if>
	<c:if test="${errors}">
		<script>
			$(function() {
				$('#error-modal').modal('toggle');
			})
		</script>
	</c:if>
	
</body>
</html>