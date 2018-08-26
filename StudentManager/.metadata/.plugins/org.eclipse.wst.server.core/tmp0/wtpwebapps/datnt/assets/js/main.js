$(document).ready(function() {
	
	//Init datatables student and scores
	var tableStudent = $("#student-table").DataTable({
		"columnDefs": [
		               { "orderable": false, "targets": 6 },
		               { "orderable": false, "targets": 7 },
		               { "orderable": false, "targets": 8 }
		              ]
	});
	
	var tableUnprocess = $("#accept-reset-table").DataTable({
		"columnDefs": [
		               { "orderable": false, "targets": 4 }
		             ]
	});
	
	var tableProcess = $("#process-reset-table").DataTable({
		"columnDefs": [
		               { "orderable": false, "targets": 3 }
		             ]
	});
	
	$('#student-table tbody').on('click', 'tr', function() {
		var data = tableStudent.row(this).data();

	    tableStudent.$('tr.selected').removeClass('selected');
	    $(this).addClass('selected');
	    
		$("#js-code-md").val(data[0]);
		$("#js-hide-md").val(data[0]);
		$("#js-name-md").val(data[1]);
		$("#js-sex-md").val(data[2]);
		$("#js-birthday-md").val(data[3]);
		$("#js-school-md").val(data[4]);
		$("#js-schoolyear-md").val(data[5]);
		$("#js-phone-md").val(data[6]);
		$("#js-email-md").val(data[7]);
		$("#js-address-md").val(data[8]);
		$("#js-student-info").modal('toggle');
	});

	$("#js-reset-frm").on('submit', function(event) {

		$.ajax({
			type: 'post',
			url: '/datnt/admin/reset',
			data: $(this).serialize(),
			success: function(result) {
				console.log(result);
			}
		});
		event.preventDefault();
	});

	$("#js-change-frm").on('submit', function(event) {

		$.ajax({
			type: 'post',
			url: '/datnt/student/changepwd',
			data: $(this).serialize(),
			success: function(result) {
				console.log(result);
			}
		});
		event.preventDefault();
	});
	
	/*
	 * Process submit Student Search form.
	 */
	$("#js-search-frm-1").on('submit', function(event) {
		
		var valid = true;
		var dateFrom = new Date($("#js-date-from").val());
		var dateTo = new Date($("#js-date-to").val());
	
		if (dateFrom > dateTo) {
			$(".js-date-message").html("Invalid date range.");
			valid = false;
		}
			
		if (valid) {
			$.ajax({
				type: 'post',
				url: '/datnt/admin/search',
				data: $(this).serialize(),
				success : function(data) {
				
					//remove current data in student list datatables
					tableStudent.rows().remove().draw();
					
					//render new student list	
					renderSearch(data, tableStudent);
				
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			$(".js-date-message").html("");
		}
		event.preventDefault();
	});
	
	/*
	 * Process submit update student info form.
	 */
	$("#js-studentinfo-frm").on('submit',function(event) {
		
		var valid = true;
		
		if (!validName($("#js-name-md").val())) {
			$("#js-name-error-md").html("Invalid name");
			valid = false;
		} else {
			$("#js-name-error-md").html("");
		}
		
		if (!validEmail($("#js-email-md").val())) {
			$("#js-email-error-md").html("Invalid email");
			valid = false;
		} else {
			$("#js-email-error-md").html("");
		}
		
		if (!validDate($("#js-birthday-md").val())) {
			$("#js-birthday-error-md").html("Invalid date");
			valid = false;
		} else {
			$("#js-birthday-error-md").html("");
		}
		
		if (!validPhone($("#js-phone-md").val())) {
			$("#js-phone-error-md").html("Invalid phone");
			valid = false;
		} else {
			$("#js-phone-error-md").html("");
		}
		
		if (valid == false) {
			event.preventDefault();
		} else {
			$("#js-name-error-md").html("");
			$("#js-date-error-md").html("");
			$("#js-phone-error-md").html("");
			$("#js-email-error-md").html("");
			$.ajax({
				type: 'post',
				url: '/datnt/admin/update',
				data: $("#js-studentinfo-frm").serialize(),
				success : function(data) {
					if (!$.trim(data)) {
						alert("Update failed.");
					} else {
						var data = tableStudent.row('.selected').data();
						data[0] = $("#js-hide-md").val();
						data[1] = $("#js-name-md").val();
						data[2] = $("#js-sex-md").val();
						data[3] = $("#js-birthday-md").val();
						data[4] = $("#js-school-md").val();
						data[5] = $("#js-schoolyear-md").val();
						data[6] = $("#js-phone-md").val();
						data[7] = $("#js-email-md").val();
						data[8] = $("#js-address-md").val();
						tableStudent.row('.selected').data(data).draw();
						alert("Update success");
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert("Update failed.");
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			$("#js-student-info").modal('toggle');
			event.preventDefault();
		}
	});
	
	
	/*
	 * Process when user click delete button in update student info form.
	 */
	$("#js-delete-btn").click(function(event) {
		$.ajax({
			type: 'post',
			url: '/datnt/admin/delete',
			data: $("#js-studentinfo-frm").serialize(),
			success : function(data) {
				if (data == null) {
					alert("Delete failed.");
				} else {
					tableStudent.row('.selected').remove().draw();
					alert("Delete success");
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				alert("Delete failed.");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		$("#js-student-info").modal('toggle');
	});
	
	/*
	 * Create new student.
	 */
	$("#js-create-frm").on('submit', function(event) {
		

		var valid = true;
		
		if (!validName($("#js-name-crate").val())) {
			$("#js-name-error-cr").html("Invalid name");
			valid = false;
		} else {
			$("#js-name-error-cr").html("");
		}
		
		if (!validPassword($("#js-password-crate").val())) {
			$("#js-pwd-error-cr").html("Invalid password");
			valid = false;
		} else {
			$("#js-pwd-error-cr").html("");
		}
		
		if (!validDate($("#js-birthday-create").val())) {
			$("#js-date-error-cr").html("Invalid date");
			valid = false;
		} else {
			$("#js-date-error-cr").html("");
		}
		
		if (!validPhone($("#js-phone-create").val())) {
			$("#js-phone-error-cr").html("Invalid phone");
			valid = false;
		} else {
			$("#js-phone-error-cr").html("");
		}
		
		if (!validEmail($("#js-email-create").val())) {
			$("#js-email-error-cr").html("Invalid email");
			valid = false;
		} else {
			$("#js-email-error-cr").html("");
		}

		if (valid == false) {
			event.preventDefault();
		} else {
			$("#js-pwd-error-cr").html("");
			$("#js-date-error-cr").html("");
			$("#js-phone-error-cr").html("");
			$("#js-email-error-cr").html("");
		$.ajax({
			type: 'post',
			url: '/datnt/admin/create',
			data: $(this).serialize(),
			success : function(data) {
				if (!$.trim(data)) {
					alert("Create failed.");
				} else {
					renderSearch(data, tableStudent);
					$("#js-student-create").modal('toggle');
					alert("Create success");
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				alert("Create failed.");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		event.preventDefault();
		}
	});
	
	$("#accept-reset-table tbody").on('click', '.accept', function() {
		var data = tableUnprocess.row($(this).parents('tr')).data();
		var row = ($(this).parents('tr'));
		row.addClass('selected');
		
		$.ajax({
			type: 'get',
			url: '/datnt/admin/accreset/' + data[0] + '?param=accept',
			data: $(this).serialize(),
			success : function(result) {
				if (result) {
					tableProcess.row.add([
					       data[0],
					       data[1],
					       data[2],
					       data[3]
					]).draw(false);
					tableUnprocess.row('.selected').remove().draw();
					alert("Process success");
				} else {
					alert("Process failed.");
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				alert("Process failed.");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	});
	
	$("#js-rg-list-frm").on('submit', function(event) {
		
		var course = $('textarea#txt-courses').val().replace(/^\s+|\s+$/g,'').split(/\s+/);
		
		$.ajax({
			type: 'POST',
			url: '/datnt/student/regis',
			data: course,
			success : function(result) {
				if (result) {
					
				} else {
					alert("Process failed.");
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				alert("Process failed.");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		event.preventDefault();
	});
	
	$("#js-btn-create").click(function(event) {
		$("#js-name-crate").val("");
		$("#js-password-crate").val("");
		$("#js-birthday-create").val("");
		$("#js-phone-crate").val("");
		$("#js-email-crate").val("");
		$("#js-address-crate").val("");
	});

});


/*
 * Render list of student on datatable student list.
 * 
 * @param result list of student.
 */
function renderSearch(result, table) {
	
	for (var i = 0; i < result.length; i++) {
		
		table.row.add([
		               result[i].studentCode,
		               result[i].studentName,
		               result[i].record.sex,
		               result[i].record.birthday,
		               result[i].school.schoolCode,
		               result[i].startYear,
		               result[i].record.phone,
		               result[i].record.email,
		               result[i].record.address
		]).draw(false);
	}
}