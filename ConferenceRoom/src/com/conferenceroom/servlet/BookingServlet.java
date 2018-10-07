package com.conferenceroom.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conferenceroom.entity.Booking;
import com.conferenceroom.entity.Employee;
import com.conferenceroom.entity.RecursiveBooking;
import com.conferenceroom.entity.Room;
import com.conferenceroom.service.BookingService;
import com.conferenceroom.service.EmployeeService;
import com.conferenceroom.service.RoomService;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String targetPage = null;
		BookingService bs = new BookingService();
		RoomService rs = new RoomService();
		EmployeeService es = new EmployeeService();
		if(action.equals("findRooms")){
			LocalDate bookingDate = LocalDate.parse(request.getParameter("bookingDate"));
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String projector = request.getParameter("projector");
			String phone = request.getParameter("phone");
			String system = request.getParameter("system");
			System.out.println("Phone: "+phone);
			List<Room> rooms= bs.findRooms(capacity, projector, phone, system);
			Map<Integer,List<Integer>> bookingsMap = bs.getBookings(Date.valueOf(bookingDate), rooms);
			
			request.setAttribute("rooms", rooms);
			request.setAttribute("bookingsMap", bookingsMap);
			
			System.out.println(rooms);
			System.out.println(bookingsMap);
			targetPage = "newbooking.jsp";

		} else if(action.equals("book")){
			int roomId = Integer.parseInt(request.getParameter("id"));
			Room room = rs.getRoomById(roomId);
			request.setAttribute("room", room);
			targetPage = "booking.jsp";
		} else if(action.equals("saveBooking")){
			int roomId = Integer.parseInt(request.getParameter("id"));
			LocalDate bookingDate = LocalDate.parse(request.getParameter("bookingDate"));
			int startSlot = Integer.parseInt(request.getParameter("startSlot"));
			int endSlot = Integer.parseInt(request.getParameter("endSlot"));
			
			boolean clash = bs.isBookingClash(roomId, Date.valueOf(bookingDate), startSlot, endSlot);
			if(clash){
				request.setAttribute("error", "Booking is clashing with an existing booking.");
				Room room = rs.getRoomById(roomId);
				request.setAttribute("room", room);
				targetPage = "booking.jsp";
			} else {
				String username = (String)request.getSession().getAttribute("userId");
				int employeeId = es.getEmployeeIdByUsername(username);
				String recursive = request.getParameter("recursive");
				String frequency = null;
				LocalDate endDate = null;
				if(recursive != null){
					frequency = request.getParameter("frequency");
					String occurrence = request.getParameter("occurrence");
					if(occurrence.equals("byDate")){
						endDate = LocalDate.parse(request.getParameter("endDate"));
					} else {
						int numOccurrences = Integer.parseInt(request.getParameter("numOccurrences"));
						int dayIncrement = numOccurrences-1;
						if(frequency.equals ("W")){
							dayIncrement = dayIncrement*7;
						}
						endDate = bookingDate.plusDays(dayIncrement);
					}
					
				}
				Booking b = null;
				if(recursive == null){
					b = new Booking(0, roomId, Date.valueOf(bookingDate), startSlot, endSlot, "N", employeeId, "B");
				} else {
					b = new RecursiveBooking(0, roomId, Date.valueOf(bookingDate), startSlot, endSlot, "Y", employeeId, "B", Date.valueOf(bookingDate), Date.valueOf(endDate), frequency);
				}				
				bs.saveBooking(b);
				targetPage = "bookingConfirmation.jsp";
			}
			
		} else if(action.equals("viewBookings")){
			String username = (String)request.getSession().getAttribute("userId");
			int employeeId = es.getEmployeeIdByUsername(username);
			List<Booking> bookings = bs.getMyBookings(employeeId);
			List<RecursiveBooking> recursiveBookings = bs.getMyRecursiveBookings(employeeId);
			request.setAttribute("bookings", bookings);
			request.setAttribute("recursiveBookings", recursiveBookings);
			targetPage = "mybookings.jsp";
		} else if(action.equals("cancelBooking")){
			int id = Integer.parseInt(request.getParameter("id"));
			bs.cancelBooking(id);
			targetPage = "cancelConfirmation.jsp";
		}
		rs.close();
		bs.close();
		request.getRequestDispatcher(targetPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
