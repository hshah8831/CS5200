<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.models.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Donor Sign Up</title>
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

	<%
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");

		if ("submit".equals(action)) {

			Integer zipcode = Integer.parseInt(request
					.getParameter("zipcode"));

			Address a = new Address();
			Random rand = new Random();
			int randomNumAddress = rand.nextInt((100 - 1) + 1) + 1;

			a.setAddressId(randomNumAddress);
			a.setCity(city);
			a.setCountry(country);
			a.setState(state);
			a.setStreet(street);
			a.setZipCode(zipcode);

			AddressDAO aDao = new AddressDAO();
			aDao.insertAddress(a);

			Role r = new Role();
			r.setRoleId(3);
			r.setRoleName("Donor");

			Person p = new Person();
			p.setAddress(a);
			p.setEmail(email);
			p.setPassword(password);

			Random randPerson = new Random();
			int randomNumPerson = randPerson.nextInt((100 - 1) + 1) + 1;

			p.setPersonId(randomNumPerson);
			p.setPersonName(name);
			p.setRole(r);
			p.setUserName(username);

			PersonDAO pDao = new PersonDAO();
			pDao.insertPerson(p);

			session.setAttribute("personId", p.getPersonId());
			session.setAttribute("userName", p.getUserName());

			response.sendRedirect("ChaandaLandingPage.jsp");
		}
	%>
	<div class="container">
		<br /> <br />
		<h4>Please fill the below fields to signup</h4>
		<br /> <br />


		<form action="DonorSignUp.jsp">


			<table class="table table-striped">
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" class="form-control"
						style="width: 250px;"></td>
				<tr />
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" class="form-control"
						style="width: 250px;"></td>
				<tr />
				<tr>
					<td>Password</td>
					<td><input type="text" name="password" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>Street Address</td>
					<td><input type="text" name="street" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="city" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><input type="text" name="country" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>ZipCode</td>
					<td><input type="text" name="zipcode" class="form-control"
						style="width: 250px;"></td>
				</tr>
				<tr>
					<td>
						<button type="submit" name="action" value="submit"
							class="btn btn-primary">Signup</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>