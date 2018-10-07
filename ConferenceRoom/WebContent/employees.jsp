<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List,com.conferenceroom.entity.Employee"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function addEmployees() {
		document.location.href = "addemployee.jsp";
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<%
		List<Employee> employees = (List) request.getAttribute("employees");
		String searchName = request.getParameter("searchname");
	%>

	<table width="50%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		<tr>

			<td colspan="4">
				<input type="button" value="Add" onclick="addEmployees()">
				
			</td>

			<td align="right" colspan="5"><form method="Post"
					action="EmployeeServlet?action=searchemployees">
					
					Search Name:<input type="text" name="searchname"
						value="<%=searchName != null ? searchName : ""%>">&nbsp;<input
						type="submit" value="Search">
				</form></td>

		</tr>
		<tr><td colspan="8">&nbsp;</td></tr>
		<tr bgcolor="grey">
			<th>Employee Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Department</th>
			<th>Email</th>
			<th>Username</th>
			<th>Password</th>
			<th colspan="2">&nbsp;</th>
		</tr>
		<%
			for (Employee e : employees) {
		%>
		<tr bgcolor="lightblue">
			<td align="center"><%=e.getId()%></td>
			<td><%=e.getFirstName()%></td>
			<td><%=e.getLastName()%></td>
			<td><%=e.getDepartment()%></td>
			<td><%=e.getEmail()%></td>
			<td><%=e.getUsername()%></td>
			<td><%=e.getPassword()%></td>
			
			<td><nobr>&nbsp;<a href="EmployeeServlet?action=deleteEmployee&id=<%=e.getId()%>">
					<input type="button" value="Delete">
			</a>&nbsp;<a
				href="EmployeeServlet?action=editEmployee&id=<%=e.getId()%>">
					<input type="submit" value="Edit">
			</a>&nbsp;</nobr></td>
			

		</tr>

		<%
			}
		%>
	</table>

</body>
</html>