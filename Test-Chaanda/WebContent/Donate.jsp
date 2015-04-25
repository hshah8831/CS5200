<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.models.*,edu.neu.cs5200.chaanda.serviceclient.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Donate page</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background:
		url(http://static9.depositphotos.com/1037238/1160/v/950/depositphotos_11600834-Back-to-school-background.jpg)
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
		String action = request.getParameter("action");
		Integer studentId = 0;
		StudentDAO dao = new StudentDAO();

		Student s = new Student();

		String userName = "";

		if ("submit".equals(action)) {

			studentId = Integer.parseInt(request.getParameter("studentId"));
			String DonorAccountNumber = request
					.getParameter("DonorAccountNumber");
			String passKey = request.getParameter("passKey");
			String amount = request.getParameter("amount");

			s = dao.getStudentDetailsForStudentLandingPage(studentId);

			String RecieverAccountNumber = Integer.toString(s
					.getUniversity().getBankaccount().getAccountNumber());

			TransactionClient client = new TransactionClient();
			String msg = client.insertTransaction(DonorAccountNumber,
					passKey, RecieverAccountNumber, amount);

			if ("Success".equals(msg)) {

				BigDecimal sum = s.getFundCollected().add(
						new BigDecimal(amount));
				s.setFundCollected(sum);

				dao.updateStudent(studentId, s);

				out.println("Fund Transferred Successfully");
	%>

	<a href="ChaandaLandingPage.jsp" class="btn btn-success">Back</a>

	<%
		//response.sendRedirect("ChaandaLandingPage.jsp");

			} else {
				out.println("Sorry your donation could not be processed . Please try again later");
			}

		} else {
			studentId = Integer.parseInt(request.getParameter("studentId"));
			//out.println(studentId);
			s = dao.getStudentDetailsForStudentLandingPage(studentId);
		}

		if (session.getAttribute("userName") != null) {

			if (!session.getAttribute("userName").toString().isEmpty()) {
				userName = session.getAttribute("userName").toString();
				Integer personId = Integer.parseInt(session.getAttribute(
						"personId").toString());
				out.println("Hello  " + userName);

			}
		}

		else {
			session.setAttribute("studentId", studentId);
			response.sendRedirect("ChaandaIndex.html");
		}
	%>

	<form action="Donate.jsp">
		<div class="container">
			<h2>Chaanda Donate page</h2>
			<input type="hidden" name="studentId" value="<%=studentId%>" />
			<table class="table table-striped">
				<tr>
					<td>Donate to student</td>
					<td><input type="text" name="student" class="form-control"
						value=<%=s.getPerson().getPersonName()%> style="width: 250px;"
						readonly>
				</tr>
				<tr>
					<td>Donor Account Number</td>
					<td><input type="text" name="DonorAccountNumber"
						class="form-control" style="width: 250px;"></td>
				<tr />
				<tr>
					<td>Passkey</td>
					<td><input type="text" name="passKey" class="form-control"
						style="width: 250px;"></td>
				<tr />
				<tr>
					<td>Amount to donate</td>
					<td><input type="text" name="amount" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>
						<button type="submit" name="action" value="submit"
							class="btn btn-primary">Donate</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>