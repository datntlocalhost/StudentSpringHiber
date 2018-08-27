<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
    Dear <strong>${user}</strong>, Welcome to Home Page.
    <a href="<c:url value="/logout" />">Logout</a>
     -->
<div class="wrapper">

	<div class="main-header">
		<!-- Logo -->
		<a href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>G</b>MO</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Admin</b>Runsystem</span>
		</a>
		<!-- Header Navbar: style can be found in header.less -->
		<div class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="push-menu"
				role="button"> <span class="sr-only">Toggle navigation</span>
			</a>

			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<!-- Logut button -->
					<li><a href="/datnt/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- Left side column. contains the logo and sidebar -->
	<div class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<div class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="<c:url value="/imgs/avatar.png" />" class="img-circle"
						alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${user}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">MAIN NAVIGATION</li>

			<c:choose>
				<c:when test="${role.equals('ROLE_ADMIN')}">
				<li class="treeview">
					<a href="#"> <i class="fa fa-edit"></i>
						<span>Managerment</span> <span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<li><a href="/datnt/admin/list"><i class="fa fa-circle-o"></i> Student
								List</a></li>
					</ul>
				</li>
				</c:when>
				
				<c:when test="${role.equals('ROLE_STUDENT')}">
				<li class="treeview">
					<a href="#"> <i class="fa fa-table"></i>
						<span>Student</span> <span class="pull-right-container"> 
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<li><a href="/datnt/student/info"><i class="fa fa-circle-o"></i> Student
								Records</a></li>
					</ul>
				</li>
				</c:when>
			</c:choose>
			</ul>

		</div>
		<!-- /.sidebar -->
	</div>