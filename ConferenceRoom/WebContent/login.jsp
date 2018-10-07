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
<% String error = (String)request.getAttribute("error");
%>
	<form action="LoginServlet?action=login" method="post">
		
		<table border="0">
			<tr>
				<td width="400px" height="200"><img src="images/login.jpg"
					width="200px" height="250px" align="left"></td>
				<td align="left" width="800px" height="200">
					<table>
						<tr>
							<td colspan="2" ><font color = "red"> <%=error!=null?error:"" %></font></td>
							
						</tr>
						<tr>
							<td>Enter your ID</td>
							<td><input type="text" name="loginid"><br></td>
						</tr>
						<tr>
							<td>Enter your Password</td>
							<td><input type="password" name="loginpassword"><br></td>
						</tr>
						<tr>
							<td>Select Role</td>
							<td><input type="radio" name="userrole" value="employee" checked= "true"/>&nbsp;Employee&nbsp;&nbsp;<input
								type="radio" name="userrole" value="admin" />&nbsp;Admin</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="submit" value="Submit" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>