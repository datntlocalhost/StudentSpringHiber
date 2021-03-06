$(document).ready(function() {
	
	$("#js-login-frm").on('submit', function(event) {
		var username = $("#js-username").val();
		var password = $("#js-password").val();
		var isValid  = true;
		
		if (!validUsername(username)) {
			$("#js-usr-invalid").html("Invalid username!");
			isValid = false;
		}
		
		if (!validPassword(password)) {
			$("#js-pwd-invalid").html("Invalid password!");
			isValid = false;
		}
		
		if (!isValid) {
			event.preventDefault();
		}
	});
	
	$("#reset-pwd-form").on('submit', function(event) {
		
		var username = $("#user-reset").val();
		var email    = $("#email").val();
		var password = $("#pwd-reset").val();
		var isValid  = true;
		
		if (!validUsername(username)) {
			$("#error-username").html("Invalid username!");
			isValid  = false;
		}
		if (!validEmail(email)) {
			$("#error-email").html("Invalid email!");
			isValid  = false;
		}
		if (!validPassword(password)) {
			$("#error-password").html("Invalid password!");
			isValid  = false;
		}
		if(isValid == false) {
			event.preventDefault();
		}
	});
});

/*
 * Check valid username
 * 
 * @param string username
 * 
 * @return boolean true if valid, else false
 */
function validUsername(string) {
	var regex = /^[a-zA-Z0-9]{4,30}$/;
	return checkRegex(string, regex);
}

/*
 * Check valid password
 * 
 * @param string password
 * 
 * @return boolean true if valid, else false
 */
function validPassword(string) {
	var regex = /^[a-zA-Z0-9!@#$%^&*()]{4,30}$/;
	return checkRegex(string, regex);
}

/*
 * Check valid email
 * 
 * @param string email
 * 
 * @return boolean true if valid, else false
 */
function validEmail(string) {
	if(!$.trim(string)) {
		return true;
	}
	var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	return checkRegex(string, regex);
}

function validDate(string) {
	var regex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])*$/;
	return checkRegex(string, regex);
}

function validPhone(string) {
	var regex = /^[0-9]*$/;
	if (!$.trim(string)) {
		return true;
	}
	return checkRegex(string, regex);
}

function validName(string) {
	if (!$.trim(string)) {
		return false;
	}
	var regex = /^[^~!@#$%^&*()_-]*$/;
	return checkRegex(string, regex);
}

function checkRegex(string, regex) {
	var result = string.match(regex);
	
	if (result == null) {
		return false;
	}
	return true;
}

