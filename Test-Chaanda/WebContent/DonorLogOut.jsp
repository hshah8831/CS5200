<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
<style>
body {
	background:
		url(http://download.free-download-web.com/files/2014/09/Cartoon-study-article-vector-material-2.jpg)
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
</head>
<body>
<%

        session.removeAttribute("userName");
        session.removeAttribute("personId");
        session.invalidate();
        %>
 <h3> You are currently logged out of Chaanda website. click </h3><a href="DonorLogIn.jsp">here</a><h3>to login. </h3>
</body>
</html>