<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="api/director">Find All Directors</a>
	<br />
	<a href="api/director/1">Find Director with ID = 1</a>
	<br />
	<a href="api/director/2">Find Director with ID = 2</a>

	<script src="js/jquery.js"></script>

	<script>
		$(function() {
			var director = {
				firstName : "Pedro",
				lastName : "Jack"
			};
			//		createDirector(director);
			//		updateDirector(10, director);
			removeDirector(7);
		});
		function createStudent(student) {
			$.ajax({
				url : "api/student",
				data : JSON.stringify(student),
				type : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(response) {
					console.log(response);
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
	</script>
</body>
</html>