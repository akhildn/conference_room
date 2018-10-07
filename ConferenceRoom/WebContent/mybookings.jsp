<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List,com.conferenceroom.entity.Booking,com.conferenceroom.entity.RecursiveBooking"%>
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
		List<Booking> bookings = (List) request.getAttribute("bookings");
		List<RecursiveBooking> recursiveBookings = (List) request.getAttribute("recursiveBookings");
		
	%>

	<table width="50%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		
		<tr><td colspan="8"><h2>Non-recursive</h2><br></td></tr>
		<tr bgcolor="grey">
			<th>Booking Id</th>
			<th>Room Id</th>
			<th>Booking Date</th>
			<th>Start Time</th>
			<th>End Time</th>
			<th>&nbsp;</th>			
		</tr>
		<%
			for (Booking b : bookings) {
				int startSlot = b.getStartSlot()-1;
				int endSlot = b.getEndSlot();
				
		%>
		<tr bgcolor="lightblue">
			<td align="center"><%=b.getId()%></td>
			<td align="center"><%=b.getRoomId()%></td>
			<td align="center"><%=b.getBookingDate()%></td>
			<td align="right"><%=(Math.abs(startSlot/2)+":"+((startSlot%2>0)?"30":"00"))%></td>
			<td align="right"><%=(Math.abs(endSlot/2)+":"+((endSlot%2>0)?"30":"00"))%></td>
			<td align="center"><a href="BookingServlet?action=cancelBooking&id=<%=b.getId()%>"><input type="button" value="Cancel"/></a></td>			
		</tr>

		<%
			}
		%>
	</table>
	<br><br>
	<table width="50%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		
		<tr><td colspan="8"><h2>Recursive</h2><br></td></tr>
		<tr bgcolor="grey">
			<th>Booking Id</th>
			<th>Room Id</th>			
			<th>Start Time</th>
			<th>End Time</th>
			<th>From Date</th>
			<th>To Date</th>
			<th>Recurrence</th>	
			<th>&nbsp;</th>		
		</tr>
		<%
			for (RecursiveBooking b : recursiveBookings) {
				int startSlot = b.getStartSlot()-1;
				int endSlot = b.getEndSlot();
				
		%>
		<tr bgcolor="lightblue">
			<td align="center"><%=b.getId()%></td>
			<td align="center"><%=b.getRoomId()%></td>			
			<td align="right"><%=(Math.abs(startSlot/2)+":"+((startSlot%2>0)?"30":"00"))%></td>
			<td align="right"><%=(Math.abs(endSlot/2)+":"+((endSlot%2>0)?"30":"00"))%></td>
			<td><%=b.getFromDate()%></td>
			<td><%=b.getToDate()%></td>
			<td><%=b.getRecurrence().equals("W")?"Weekly":"Daily"%></td>
			<td align="center"><a href="BookingServlet?action=cancelBooking&id=<%=b.getId()%>"><input type="button" value="Cancel"/></a></td>			
		</tr>

		<%
			}
		%>
	</table>

</body>
</html>