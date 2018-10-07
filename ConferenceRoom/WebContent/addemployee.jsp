<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<form action="EmployeeServlet?action=addEmployee" method="post">
	<table width="30%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		<tr>
			
			<td>First Name</td>
			<td><input type="text" name="firstname"><br></td>
			</tr>
			<tr>
			<td>Last Name</td>
			<td><input type="text" name="lastname"><br></td>
			</tr>
			<tr>
			<td>Department</td>
			<td><input type="text" name="department"><br></td>
			</tr>
			<tr>
			<td>Email</td>
			<td><input type="text" name="email"><br></td>
			</tr>
			<tr>
			<td>Username</td>
			<td><input type="text" name="username"><br></td>
			</tr>
			<tr>
			<td>Password</td>
			<td><input type="password" name="password"><br></td>
			</tr>
			<th colspan="2">&nbsp;</th>
		</tr>
		
		
		<tr>
		 <td align="center" colspan="2">
		<input type="submit" name="submit" value="submit" >
		</td>
		</tr>
		</table>
		 

</form>
</body>
</html>