<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List,com.conferenceroom.entity.Employee"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<%
		List<Employee> employees = (List) request.getAttribute("editemployee");

		for (Employee e : employees) {
	%>
	<form action="EmployeeServlet?action=edited&id=<%=e.getId()%>"
		method="post">
		<br> <br>
		<table border="0" BORDERCOLOR="lightgray" align="center"
			cellspacing="0" cellpadding="2">


			<tr>
				<td>Enter ID</td>
				<td><input type="hidden" value="<%=e.getId()%>"
					name="editId"></td>
			</tr>
			<tr>
				<td>Enter FirstName</td>
				<td><input type="text" value="<%=e.getFirstName()%>"
					name="editFirstName"></td>
			</tr>
			<tr>
				<td>Enter LastName</td>
				<td><input type="text" value="<%=e.getLastName()%>"
					name="editLastName"></td>
			</tr>
			<tr>
				<td>Enter Department</td>
				<td><input type="text" value="<%=e.getDepartment()%>"
					name="editDepartment"></td>
			</tr>
			<tr>
				<td>Enter email</td>
				<td><input type="text" value="<%=e.getEmail()%>"
					name="editEmail"></td>
			</tr>
			<tr>
				<td>Enter UserName</td>
				<td><input type="text" value="<%=e.getUsername()%>"
					name="editUserName"></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="text" value="<%=e.getPassword()%>"
					name="editPassword"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update"></td>
			</tr>

			<%
				}
			%>

		</table>
	</form>


</body>
</html>