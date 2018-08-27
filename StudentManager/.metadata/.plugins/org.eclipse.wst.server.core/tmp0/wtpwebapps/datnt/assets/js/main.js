$(document).ready(function() {
	
	var tableStudent = $("#student-table").DataTable({
		paging:   true,
		info : true,
		searching: true,
		"columnDefs": [
		               { "orderable": false, "targets": 6 },
		               { "orderable": false, "targets": 7 },
		               { "orderable": false, "targets": 8 }
		              ]
	});
	
	/*
	 * Process when user click on tr of table.
	 * Show info of student in that row
	 */
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
		
		//Check validation input 
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
			
			//Send post request contain update info to controller
			$.ajax({
				type: 'post',
				url: '/datnt/admin/update',
				data: $("#js-studentinfo-frm").serialize(),
				success : function(data) {
					if (!$.trim(data)) {
						alert("Update failed.");
					} else {
						//Re-render student's info after update success.
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
				if (data == false) {
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
					renderStudent(data, tableStudent);
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
	
	/*
	 * Process when user click on Create Button.
	 */
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
		               result[i].student.studentCode,
		               result[i].student.studentName,
		               result[i].records.sex,
		               result[i].records.birthday,
		               result[i].school.schoolCode,
		               result[i].student.startYear,
		               result[i].records.phone,
		               result[i].records.email,
		               result[i].records.address
		]).draw(false);
	}
}

function renderStudent(result, table) {

	table.row.add([
	               result.studentCode,
	               result.studentName,
	               result.sex,
	               result.birthday,
	               result.school,
	               result.schoolYear,
	               result.phone,
	               result.email,
	               result.address
	               ]).draw(false);
}
