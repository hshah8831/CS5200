<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.university.model.*,edu.neu.cs5200.university.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Landing</title>
<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
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
<script>
	$(function() {
		var director = {
			firstName : "Pedro",
			lastName : "Jack"
		};
		//		createDirector(director);
		//		updateDirector(10, director);
		//removeDirector(7);
	});
	function createStudent(student) {
		$.ajax({
			url : "http://localhost:8080/Chaanda/rest/student/insert",
			data : student,
			type : "post",
			dataType : "json",
			contentType : "application/json",
			success : function(response) {
				alert("succesfully migrated");
			},
			error : function(response) {
				alert("migration failed");
			}
		});
	}
</script>
</head>
<body>
	<h2>
		<%
			String userName = session.getAttribute("userName").toString();
			Integer personId = Integer.parseInt(session
					.getAttribute("personId").toString());
			StudentDAO dao = new StudentDAO();
			StudentFundDeatailsDAO sfnddao = new StudentFundDeatailsDAO();
			Studentfunddetail sfnd = new Studentfunddetail();

			String action = request.getParameter("action");

			//if ("approve".equals(action)) {
			//	Integer stuId = Integer.parseInt(request.getParameter("stuId").toString());
			//	Student s = dao.getStudentDetailsForStudentLandingPage(stuId);

			//sfnd = s.getStudentfunddetail();
			//sfnd.setFundPetitionStatus(2);

			//sfnd = sfnddao.updateStudentFundDetails(stuId, sfnd);
			//s.setStudentfunddetail(sfnd);

			//dao.updateStudent(stuId, s);
			//}

			//else if ("reject".equals(action)) {
			//	Integer stuId = Integer.parseInt(request.getParameter("stuId").toString());
			//	Student s = dao.getStudentDetailsForStudentLandingPage(stuId);
			//	//s.getStudentfunddetail().setFundPetitionStatus(3);

			//sfnd = s.getStudentfunddetail();
			//sfnd.setFundPetitionStatus(3);

			//sfnd = sfnddao.updateStudentFundDetails(stuId, sfnd);
			//s.setStudentfunddetail(sfnd);

			//dao.updateStudent(stuId, s);
			//}

			if ("reject".equals(action)) {
				Integer stuId = Integer.parseInt(request.getParameter("stuId")
						.toString());
				Student s = dao.getStudentDetailsForStudentLandingPage(stuId);
				//s.getStudentfunddetail().setFundPetitionStatus(3);

				sfnd = s.getStudentfunddetail();
				sfnd.setFundPetitionStatus(3);

				sfnd = sfnddao.updateStudentFundDetails(stuId, sfnd);
				s.setStudentfunddetail(sfnd);

				//dao.updateStudent(stuId, s);
			}

			else if ("approve".equals(action)) {
				Integer stuId = Integer.parseInt(request.getParameter("stuId")
						.toString());
				Student s = dao.getStudentDetailsForStudentLandingPage(stuId);

				sfnd = s.getStudentfunddetail();
				sfnd.setFundPetitionStatus(2);

				sfnd = sfnddao.updateStudentFundDetails(stuId, sfnd);
				s.setStudentfunddetail(sfnd);

				//dao.updateStudent(stuId, s);
			}

					%>
	</h2>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<a href="UniversityLogin.jsp" class="navbar-brand">University</a>
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
	<form action="AdminLanding.jsp">
		<div class="container">
			<%
				List<Student> studentsPending = dao.getStudentsWaiting();

				List<Student> studentsApproved = dao.getStudentsApproved();
			%>

			<h3>Students requesting for funding</h3>
			<%
				if (studentsPending.size() == 0) {
			%>
			<h4>No pending approval</h4>
			<%
				} else {
			%>
			<table class="table table-striped">
				<tr>
					<td>Student name</td>
					<td>Tuition fee Required</td>
				</tr>
				<%
					for (Student s : studentsPending) {

							String sjsn = dao.convertStudenttoJSON(s);
				%>
				<tr>
					<td><a
						href="StudentDetails.jsp?studentId=<%=s.getPerson().getPersonId()%>"><%=s.getPerson().getPersonName()%></a>
						<input type="hidden" name="stuId"
						value=<%=s.getPerson().getPersonId()%>></td>
					<td><%=s.getStudentfunddetail().getFundRequired()%></td>
					<td><button class="btn btn-success" type="submit" name="action" value=<%=sjsn%> onclick="createStudent(this.value)">Upload</button></td>
					<td><button class="btn btn-success" type="submit" name="action" value="approve">Approve</button></td>
					<td><button class="btn btn-warning" type="submit" name="action" value="reject">Reject</button></td>
				</tr>

				<%
					}
				%>

			</table>

			<%
				}
			%>
			<br /> <br /> <br />

			<h3>List of students being funded</h3>

			<%
				if (studentsApproved.size() == 0) {
			%>
			<h4>No students have requested funding</h4>
			<%
				} else {
			%>
			<table class="table table-striped">
				<tr>
					<td>Student name</td>
					<td>Tuition fee Required</td>
				</tr>

				<%
					for (Student s : studentsApproved) {
				%>
				<tr>
					<td><a
						href="StudentDetails.jsp?studentId=<%=s.getPerson().getPersonId()%>"><%=s.getPerson().getPersonName()%></a></td>
					<td><%=s.getStudentfunddetail().getFundRequired()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				}
			%>
		</div>
	</form>
</body>
</html>