<#import "/spring.ftl" as spring/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<@spring.url 'css/main.css'/>">
</head>
<body>
	
	<div class="login-form" style="width: 30%; margin: 100px auto;">
	    <form id="js-login-frm" action="<@spring.url '/login'/>" method="post">
	        <h2 class="text-center">Log in</h2>
			<div class="form-group">
				<span id="js-usr-invalid" class="error"></span>
				<@spring.formInput "user.username" "id='js-username' class='form-control' placeholder='Username' required='required'" "text"/>
	        </div>
	        <div class="form-group">
	        	<span id="js-pwd-invalid" class="error"></span>
	        	<@spring.formPasswordInput "user.password" "id='js-password' class='form-control' placeholder='Password' required='required'"/>
	        </div>
	        
	        <div class="form-group">
	            <button type="submit" class="btn btn-primary btn-block">Log in</button>
	        </div> 
	    </form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<@spring.url '/js/validator.js'/>"></script>
	
</body>
</html>