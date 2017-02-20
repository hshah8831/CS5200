<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.models.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"
	errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Landing</title>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
#element1 {
	float: right;
	margin-right: 15px;
}

#element2 {
	float: right;
}

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
		<%
			String userName = session.getAttribute("userName").toString();
			Integer personId = Integer.parseInt(session
					.getAttribute("personId").toString());

			StudentDAO dao = new StudentDAO();

			Student s = dao.getStudentDetailsForStudentLandingPage(personId);

			String requestStatus = "";

			if (s.getFundPetitionStatus() == 2) {
				requestStatus = "Request Approved";
			}

			else if (s.getFundPetitionStatus() == 1) {
				requestStatus = "Pending Approval";
			}

			else if (s.getFundPetitionStatus() == 3) {
				requestStatus = "Request denied";

			}
		%>
		<div class="navbar navbar-default navbar-static-top">
			<div class="container">
				<a href="UniversityLogin.jsp" class="navbar-brand"><%=s.getUniversity().getUniversityName()%></a>
				<div class="collapse navbar-collapse navHeaderCollapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="UniversityLogin.jsp">Home</a></li>
						<li><a href="Logout.jsp">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
		<h4 id="element2">
			<%
				out.println("Hello  " + userName);
			%>
		</h4>

		<br />

		<%
			if (s.getFundPetitionStatus() == 0) {
		%>
		<h3>
			<a href="FundForm.jsp" class="btn btn-primary">Request Funding</a>
		</h3>
		<br />

		<%
			}

			if ((s.getFundPetitionStatus() == 1)
					|| (s.getFundPetitionStatus() == 2)
					|| (s.getFundPetitionStatus() == 3)) {
		%>


		<h3>Fund Request Details</h3>
		<br />
		<table class="table table-striped">
			<tr>
				<td>Student name</td>
				<td>Tuition fee Required</td>
				<td>Tuition fee Collected</td>
				<td>Tuition fee to be collected</td>
				<td>Fund approval status</td>
				<td>Message to donor</td>
			</tr>
			<tr>
				<td><a
					href="StudentDetails.jsp?studentId=<%=s.getPerson().getPersonId()%>"><%=s.getPerson().getPersonName()%></a></td>
				<td><%=s.getFundRequired()%></td>
				<td><%=s.getFundCollected()%></td>
				<td><%=s.getFundRequired().subtract(s.getFundCollected())%></td>
				<td><%=requestStatus%></td>
				<td><%=s.getPetitionDescription()%></td>
			</tr>

		</table>
		<%
			}
		%>
	</div>
</body>
</html>