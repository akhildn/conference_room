<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List, java.util.Map, com.conferenceroom.entity.Room" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function recursiveDisplay(){
		if(document.booking.recursive.checked){
			document.getElementById('recursiveDiv').style.display = 'block';
		} else {
			document.getElementById('recursiveDiv').style.display = 'none';
		}
		
	}
</script>
</head>
<body>
<%@ include file="header.jsp"%>
	<br>
	<br>
	
	<%
		Room room = (Room)request.getAttribute("room");	
		String bookingDate = request.getParameter("bookingDate");
		String error = (String)request.getAttribute("error");
		String startSlot = request.getParameter("startSlot");
		String endSlot = request.getParameter("endSlot");
	%>
	
	<form name="booking" action="BookingServlet?action=saveBooking&id=<%=room.getId() %>&bookingDate=<%=bookingDate %>" method="post">
	<table width="30%" align="center" border="0" cellspacing="1"
		cellpadding="0" BORDERCOLOR="lightgray">
		<tr>
			
			<td colspan="2"><font color="red"><%=error!=null?error:"" %></font></td>
		</tr>
		<tr>
			
			<td colspan="2">&nbsp;</td>
		</tr>	
		<tr>
			
			<td><b>Booking date</b></td>
			<td><%=bookingDate%><br></td>			</tr>
			<tr>
			<tr>
			
			<td><b>Conference Room</b></td>
			<td><%=room.getName()%><br></td>			</tr>
			<tr>
			<td><b>Capacity</b></td>
			<td><%=room.getCapacity() %></td>
			</tr>
			<tr>
			<td><b>Projector</b></td>
			<td><%=room.getProjector() %></td>
			</tr>
			<tr>
			<td><b>Phone</b></td>
			<td><%=room.getPhone() %></td>
			</tr>
			<tr>
			<td><b>System</b></td>
			<td><%=room.getSystem() %></td>
			</tr>			
			<th colspan="2">&nbsp;</th>
		</tr>		
		<tr><td>&nbsp;</td></tr>
		<tr><td>
		<b>From:</b>&nbsp;
		<select name="startSlot">
			<option>Select</option>
			<%for(int i=0;i<24;i++){ %>
			<option value="<%=2*i+1 %>"><%=i+":00" %></option>
			<option value="<%=2*i+2 %>"><%=i+":30" %></option>
			<%} %>		
		</select>
		<%if(startSlot!=null){ %>
		<script>
		document.booking.startSlot.value = <%=startSlot%>;
		</script>
		<%} %>
		</td>
		<td>
		<b>To:</b>&nbsp;
		<select name="endSlot">
			<option>Select</option>
			<%for(int i=0;i<24;i++){ %>
			<option value="<%=2*i+1 %>"><%=i+":00" %></option>
			<option value="<%=2*i+2 %>"><%=i+":30" %></option>
			<%} %>
		
		</select>
		<%if(endSlot!=null){ %>
		<script>
		document.booking.endSlot.value = <%=endSlot%>;
		</script>
		<%} %>
		
		</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td colspan="2"><input type="checkbox" name="recursive" onChange="recursiveDisplay()"/><b>Recursive</b></td></tr>
		
		<tr><td colspan="2">
		<div id="recursiveDiv" style="display:none;border:1px solid powderblue">
			<input type="radio" name="frequency" value="D" checked="true"/>&nbsp;Daily&nbsp;&nbsp;
			<input type="radio" name="frequency" value="W"/>&nbsp;Weekly<br><br>
			
			<input type="radio" name="occurrence" value="byDate" checked="true"/>&nbsp;End by date <input type="date" name="endDate"/><br><br>
			<input type="radio" name="occurrence" value="byNumber"/>&nbsp;End after <input type="text" name="numOccurrences" size="2"/>&nbsp; occurrences
		</div>
		</td></tr>
		<tr><td>&nbsp;<br><br></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="Book"/></td></tr>
		</table>
		
<br><br><br>
<div>
	
</div>
</form>
</body>
</html>