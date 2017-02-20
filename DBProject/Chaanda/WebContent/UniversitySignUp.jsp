<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.model.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University Sign up</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
	<style>
body {
	background:
		url(http://www.pptback.com/backgrounds/funny-cartoon-characters-border-backgrounds-powerpoint.jpg)
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
</head>
<body>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<a href="DonorLogIn.jsp" class="navbar-brand">Chaanda</a>
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="DonorLogIn.jsp">Home</a></li>
				</ul>
			</div>
		</div>
	</div>

	<%
		String action = request.getParameter("action");
		String uniName = request.getParameter("universityName");

		UniversityDAO dao = new UniversityDAO();
		if ("register".equals(action)) {
			List<University> unis = dao.getUniversityFromName(uniName);

			University u = new University();
			for (University un : unis) {
				u = un;
			}
			
			// set registered field of universiy to 1 signifies the university is registered to chaanda
			u.setRegistered(1);

			dao.registerUniversity(u.getUniversityId(), u);

			out.println("Succesfully registerd to chaanda . Now your will be able to recieve fund from donors");
	%>

	<a href="DonorLogIn.jsp" class="btn btn-default">Go to
		chaanda website to see the list of students from your university</a>
	<%
		}
	%>




	<form action="UniversitySignUp.jsp">
		<div class="container">
			<h2>Welcome to University Sign Up</h2>
			<br />
			<h4>Please enter your university full name and register. A
				confirmation email will be sent to university admin to verify
				whether the university is willing to participate in Chaanda .</h4>
			<br /> <input type="text" name="universityName" class="form-control"
				style="width: 300px;"><br>
			<button type="submit" name="action" value="register"
				class="btn btn-primary">Register to Chaanda</button>
		</div>
	</form>
</body>
</html>