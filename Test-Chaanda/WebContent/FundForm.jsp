<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.chaanda.models.*,edu.neu.cs5200.chaanda.dao.*,java.util.*,java.math.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fund form</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
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
		<form action="FundForm.jsp" method="post">

			<%
		
		Integer personId = Integer.parseInt(session.getAttribute("personId").toString());
		String action = request.getParameter("action");
		
		StudentDAO dao = new StudentDAO();
		
		Student s = dao.getStudentDetailsForStudentLandingPage(personId);
		
		//Degree d = dao.getTuitionFee(s.getDegree().getDegreeId());
		
		Degree d = s.getDegree();

		if(s.getFundPetitionStatus() == 0) {		
		
		if("submit".equals(action)){
			
			
			String fund = request.getParameter("nRequiredFund");
			String message = request.getParameter("nFundDescription");
			
			if(fund!=null && !fund.isEmpty()){
			
			BigDecimal fundRequired = new BigDecimal(fund);
			
			Integer validation = s.getFamilyIncome().compareTo(d.getTuition());
			
			Integer res = d.getTuition().compareTo(fundRequired);
			
			if(( res == 0 || res == 1 )){
				
				if( validation == -1 ){
			s.setFundPetitionStatus(1);
			s.setFundRequired(fundRequired);
			s.setPetitionDescription(message);
			
		    s = dao.filForm(s);
		    
		    
		    response.sendRedirect("StudentLanding.jsp");
				}
				
				else{
					 %>
			<h3 class="text-danger">
				<% out.println("You are not eligible to request funding. Your family income is enough to fund for your education"); %>
			</h3>
			<%
				}
			}
				
				else {
					%>
			<h3 class="text-danger">
				<% out.println("Please enter fund required amount less than tuition fee"); %>
			</h3>
			<%
				}
		    
		}else{
			%>
			<h3 class="text-danger">
				<% out.println("Please enter required fund "); %>
			</h3>
			<%
		}
		}}
		/* else{
			System.out.println("Your request is submitted. No changes in Fund required field allowed. Please talk to respective administrator to make changes");
		} */
		
		if("cancel".equals(action)){
			response.sendRedirect("StudentLanding.jsp");
		}
			
		%>
			<h2>Fund request form</h2>
			<br /> <br />
			<table class="table table-striped">
				<tr>
					<td>Required Fund: (in dollars)</td>
					<td><input id="txtRequiredFund" name="nRequiredFund"
						type="text" class="form-control" style="width: 250px;" /></td>
				</tr>
				<tr>
					<td>Message for donors</td>
					<td><textarea name="nFundDescription"
							style="width: 450px; height: 150px;" class="form-control"
							rows="5"></textarea></td>
				</tr>
			</table>
			<button type="submit" name="action" value="submit"
				class="btn btn-primary">Submit Form</button>
			<button type="submit" name="action" value="cancel"
				class="btn btn-default">Cancel</button>
		</form>
	</div>
</body>
</html>