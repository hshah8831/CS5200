<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.models.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"
	errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student details</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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

	<div class="container">
		<h2>Student Details</h2>
		<%
			String studentId = request.getParameter("studentId");
			Integer personId = Integer.parseInt(studentId);

			StudentDAO dao = new StudentDAO();

			Student s = dao.getStudentDetailsForStudentLandingPage(personId);
		%>

		<table class="table table-striped">
			<tr>
				<td>Student Name :</td>
				<td><%=s.getPerson().getPersonName()%></td>
			</tr>
			<tr>
				<td>University :</td>
				<td><%=s.getUniversity().getUniversityName()%></td>
			</tr>
			<tr>
				<td>College enrolled :</td>
				<td><%=s.getDegree().getCollege().getCollegename()%></td>
			</tr>
			<tr>
				<td>Degree :</td>
				<td><%=s.getDegree().getDegreeName()%></td>
			</tr>
			<tr>
				<td>Enrolled Year :</td>
				<td><%=s.getEnrolledYear()%></td>
			</tr>
			<tr>
				<td>Degree Duration :</td>
				<td><%=s.getDegree().getDuration()%></td>
			</tr>
			<tr>
				<td>Current GPA :</td>
				<td><%=s.getGpa()%></td>
			</tr>
			<tr>
				<td>Tuition fee :</td>
				<td><%=s.getDegree().getTuition()%></td>
			</tr>
			<tr>
				<td>Fund required :</td>
				<td><%=s.getFundRequired()%></td>
			</tr>
			<tr>
				<td>Family Income :</td>
				<td><%=s.getFamilyIncome()%></td>
			</tr>
			<tr>
				<td>Message for Donors :</td>
				<td><%=s.getPetitionDescription()%></td>
			</tr>
		</table>
	</div>
</body>
</html>