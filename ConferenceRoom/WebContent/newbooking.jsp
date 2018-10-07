<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List, java.util.Map, com.conferenceroom.entity.Room" %>
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
	<%
	List<Room> rooms = (List<Room>)request.getAttribute("rooms");
	Map<Integer,List<Integer>> bookingsMap = (Map<Integer,List<Integer>>)request.getAttribute("bookingsMap");
	String bookingDate = request.getParameter("bookingDate");
	String capacity = request.getParameter("capacity");
	String projector = request.getParameter("projector");
	String phone = request.getParameter("phone");
	String system = request.getParameter("system");
	%>
	
	<form action="BookingServlet?action=findRooms" method="post">
	<table width="30%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		<tr>
			
			<td>Booking date</td>
			<td><input type="date" name="bookingDate" value="<%=bookingDate!=null?bookingDate:"" %>"><br></td>
			</tr>
			<tr>
			<td>Capacity</td>
			<td><input type="text" name="capacity" size="2" value="<%=capacity!=null?capacity:"" %>"><br></td>
			</tr>
			<tr>
			<td>Projector</td>
			<td><input type="checkbox" name="projector" <%=projector!=null?"checked":""%>><br></td>
			</tr>
			<tr>
			<td>Phone</td>
			<td><input type="checkbox" name="phone" <%=phone!=null?"checked":""%>><br></td>
			</tr>
			<tr>
			<td>System</td>
			<td><input type="checkbox" name="system" <%=system!=null?"checked":""%>><br></td>
			</tr>			
			<th colspan="2">&nbsp;</th>
		</tr>
		
		
		<tr>
		 <td align="center" colspan="2">
		<input type="submit" value="Search Rooms" >
		</td>
		</tr>
		</table>
<br><br><br>
<div>
<table width="90%" style="background-color:lightblue" align="center" border="0" cellpadding="0" cellspacing="1">
<% if(rooms != null){
	for(Room r: rooms){
		int roomId = r.getId();
		List<Integer> bookedSlots = (List<Integer>)bookingsMap.get(roomId);%>
		<tr><td>&nbsp;</td></tr>
		<tr><td width="5%"><b><%=r.getName() %></b></td>
		<% for(int i=0;i<48;i++){
			String altText = Math.abs(i/2)+":"+((i%2>0)?"30":"00");
		if(bookedSlots.contains(i+1)){		
		
		%>
	<td width="2%" bgcolor="red" title="<%=altText%>">&nbsp;</td>
	<%} else{%>
	<td width="2%" bgcolor="green" title="<%=altText%>"><a href="BookingServlet?action=book&id=<%=roomId%>&bookingDate=<%=bookingDate%>&startSlot=<%=i+1%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
		
	<%}}}} %>	
	
	<tr><td>&nbsp;</td></tr>	
	</table>	
</div>
</form>
</body>
</html>