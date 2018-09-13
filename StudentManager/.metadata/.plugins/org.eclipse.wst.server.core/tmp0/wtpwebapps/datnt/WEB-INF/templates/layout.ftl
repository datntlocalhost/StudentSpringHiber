<#import "spring.ftl" as spring/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- <title><@tiles.insertAttribute name="title" /></title> -->
	<title><@tiles.insertAttribute name="title"/></title>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<@spring.url '/css/bootstrap.min.css'/>" >
    <link rel="stylesheet" href="<@spring.url '/css/ionicons.min.css'/>" >
    <link rel="stylesheet" href="<@spring.url '/css/adminlte.min.css'/>" >
    <link rel="stylesheet" href="<@spring.url '/css/skinblue.min.css'/>" >
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<@tiles.insertAttribute name="header" /> 
	<@tiles.insertAttribute name="body" /> 
	<@tiles.insertAttribute name="footer" />
	

</body>
</html>