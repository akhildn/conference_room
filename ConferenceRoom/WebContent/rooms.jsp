<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List,com.conferenceroom.entity.Room"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function addRoom() {
		document.location.href = "addRoom.jsp";
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
		List<Room> rooms = (List) request.getAttribute("rooms");
		String searchName = request.getParameter("searchname");
	%>

	<table width="40%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		<tr>

			<td colspan="4">
				<input type="button" value="Add" onclick="addRoom()">
				
			</td>

			<td align="right" colspan="5"><form method="Post"
					action="RoomServlet?action=searchRooms">
					
					Search Name:<input type="text" name="searchname"
						value="<%=searchName != null ? searchName : ""%>">&nbsp;<input
						type="submit" value="Search">
				</form></td>

		</tr>
		<tr><td colspan="8">&nbsp;</td></tr>
		<tr bgcolor="grey">
			<th>Id</th>
			<th>Name</th>
			<th>Capacity</th>
			<th>Projector</th>
			<th>Phone</th>
			<th>System</th>
			<th width="10%">&nbsp;</th>
		</tr>
		<%
			for (Room r : rooms) {
		%>
		<tr bgcolor="lightblue">
			<td align="center"><%=r.getId()%></td>
			<td><%=r.getName()%></td>
			<td align="center"><%=r.getCapacity()%></td>
			<td align="center"><%=r.getProjector()%></td>
			<td align="center"><%=r.getPhone()%></td>
			<td align="center"><%=r.getSystem()%></td>
			
			
			<td><nobr>&nbsp;<a href="RoomServlet?action=deleteRoom&id=<%=r.getId()%>">
					<input type="button" value="Delete">
			</a>&nbsp;<a
				href="RoomServlet?action=editRoom&id=<%=r.getId()%>">
					<input type="submit" value="Edit">
			</a>&nbsp;</nobr></td>
			

		</tr>

		<%
			}
		%>
	</table>

</body>
</html>