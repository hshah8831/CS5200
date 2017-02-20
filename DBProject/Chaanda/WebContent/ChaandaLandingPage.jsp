<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.model.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chaanda Landing Page</title>
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
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<a href="DonorLogIn.jsp" class="navbar-brand">Chaanda</a>
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="DonorLogIn.jsp">Home</a></li>
					<li><a href="DonorLogOut.jsp">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<%
		String userName = "";
		if (session.getAttribute("userName") != null) {

			if (!session.getAttribute("userName").toString().isEmpty()) {
				userName = session.getAttribute("userName").toString();
				Integer personId = Integer.parseInt(session.getAttribute(
						"personId").toString());
			}
	%>
	<h4 id="element2">
		<%
			out.println("Hello  " + userName);
		%>
	</h4>

	<h4 id="element1"></h4>
	<br />


	<%
		}
		UniversityDAO uDao = new UniversityDAO();
		StudentDAO sDao = new StudentDAO();

		List<University> universities = uDao.getRegisteredUniversity();

		String action = request.getParameter("action");

		List<Student> students = new ArrayList<Student>();

		if ("searchByName".equals(action)) {
			String searchByName = request.getParameter("searchKeyName");
			students = sDao.getStudentFromUni(searchByName);
		}

		else if ("search".equals(action)) {
			String search = request.getParameter("select");
			students = sDao.getStudentFromUni(search);

			System.out.println(search);
		} else {
			students = sDao.getStudentsWantingFund();
		}
	%>
	<br>
	<form action="ChaandaLandingPage.jsp">
		<div class="container">
			<table class="table table-striped">
				<tr>
					<td colspan="2">Search University :</td>
					<td><input type="text" name="searchKeyName"
						class="form-control" style="width: 300px;"></td>
					<td>
						<button type="submit" name="action" value="searchByName"
							class="btn btn-primary">Search by name</button>
					</td>

				</tr>
				<tr>
					<td>Select a University :</td>
					<td><select name="select"> <Option>Select University</Option>
							<%
								for (int i = 0; i < universities.size(); i++) {
							%>
							
							<Option value="<%=universities.get(i).getUniversityName()%>"><%=universities.get(i).getUniversityName()%></Option>
							<% } %>
					</select></td>
					<td>
						<button type="submit" name="action" value="search"
							class="btn btn-primary">Search</button>
					</td>
				</tr>
				<tr>
					<td><a href="http://ope.ed.gov/accreditation/Search.aspx"
						class="btn btn-default">Check About Universities</a></td>
				</tr>
			</table>
			<br> <br>

		<% if(students.size() > 0 ) { %>
			<table class="table table-striped">
				<tr>
					<td>Student name</td>
					<td>Tuition fee Required</td>
					<td>Tuition fee Collected</td>
					<td>Tuition to be collected</td>
				</tr>
				<%
					for (Student s : students) {
				%>

				<tr>
					<td><a
						href="StudentDetails.jsp?studentId=<%=s.getPerson().getPersonId()%>"><%=s.getPerson().getPersonName()%></a>
						<input type="hidden" name="stuId"
						value=<%=s.getPerson().getPersonId()%>></td>
					<td><%=s.getStudentfunddetail().getFundRequired()%></td>
					<td><%=s.getStudentfunddetail().getFundCollected()%></td>
					<td><%=s.getStudentfunddetail().getFundRequired().subtract(s.getStudentfunddetail().getFundCollected())%></td>
					
					<td><a
						href="Donate.jsp?studentId=<%=s.getPerson().getPersonId()%>"
						class="btn btn-success">Donate</a></td>
					<!-- <td><button class="btn btn-success" type="submit" name="action" value="donate">Donate</button></td> -->

				</tr>

				<%	
		}
		}
		
		else{
			out.println("No students have requested funding");
		}
		
		%>

			</table>
		</div>
	</form>
</body>
</html>