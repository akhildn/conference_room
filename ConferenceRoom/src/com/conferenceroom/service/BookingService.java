package com.conferenceroom.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.conferenceroom.entity.Booking;
import com.conferenceroom.entity.RecursiveBooking;
import com.conferenceroom.entity.Room;

public class BookingService extends BaseService{

	public List<Room> findRooms(int capacity, String projector, String phone, String system) {
		
		List<Room> rooms = new ArrayList<Room>();
		
		String qry = "select * from conferenceroom where capacity>=?";
		if(projector != null){
			qry += " and projector=?";
		}
		if(phone != null){
			qry += " and phone=?";
		}
		if(system != null){
			qry += " and system=?";
		}
		System.out.println(qry);
		try{
			PreparedStatement p = con.prepareStatement(qry);
			p.setInt(1, capacity);
			int index = 2;
			if(projector != null){
				p.setString(index, "Y");
				index++;
			}
			if(phone != null){
				p.setString(index, "Y");
				index++;
			}
			if(system != null){
				p.setString(index, "Y");
				index++;
			}
			
			ResultSet rs = p.executeQuery();
			Room r = null;
			while(rs.next()){
				r = new Room(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				rooms.add(r);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return rooms;
	}

	public Map<Integer, List<Integer>> getBookings(Date bookingDate, List<Room> rooms) {
		Map<Integer, List<Integer>> bookingsMap = new HashMap<Integer, List<Integer>>();
		
		try{
			PreparedStatement p = null;
			
			for(Room r: rooms){
				int roomId = r.getId();
				p = con.prepareStatement("select startslot, endslot from booking "
						+ "where roomid=? and bookingdate=? and recursive='N' and status='B'");
				p.setInt(1, roomId);
				p.setDate(2, bookingDate);
				ResultSet rs = p.executeQuery();
				List<Integer> slotsList = new ArrayList<Integer>();
				while(rs.next()){
					int start = rs.getInt(1);
					int end = rs.getInt(2);
					for(int i=start;i<=end;i++){
						slotsList.add(i);
					}
				}
				
				p = con.prepareStatement("select b.startslot, b.endslot, r.fromdate, r.todate,"
						+ " r.recurrence from booking b, recursive r where b.roomid=? and "
						+ "r.fromdate<=? and r.todate>=? and b.recursive='Y' and b.status='B'");
				p.setInt(1, roomId);
				p.setDate(2, bookingDate);
				p.setDate(3, bookingDate);
				rs = p.executeQuery();
				while(rs.next()){
					boolean match = false;
					String recurrence = rs.getString(5);
					if(recurrence.equals("W")){
						LocalDate fromDate = rs.getDate(3).toLocalDate();
						LocalDate toDate = rs.getDate(4).toLocalDate();
						LocalDate date = fromDate;
						while(date.isEqual(toDate)||date.isBefore(toDate)){
							if(date.isEqual(bookingDate.toLocalDate())){
								match = true;
							}
							date = date.plusDays(7);
							System.out.println("Date: "+date);
						}
						
					} else {
						match = true;
					}
					if(match){
						int start = rs.getInt(1);
						int end = rs.getInt(2);
						for(int i=start;i<=end;i++){
							slotsList.add(i);
						}
					}
				}
				bookingsMap.put(roomId, slotsList);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return bookingsMap;
	}	

	public void saveBooking(Booking b) {
		try{
			con.setAutoCommit(false);
			PreparedStatement p = con.prepareStatement("select max(id) from booking");
			ResultSet rs = p.executeQuery();
			int id = 0;
			if(rs.next()){
				id = rs.getInt(1);
			}
			
			p = con.prepareStatement("insert into booking values(?,?,?,?,?,?,?,?)");
			p.setInt(1, id+1);
			p.setInt(2,b.getRoomId());
			p.setDate(3,b.getBookingDate());
			p.setInt(4,b.getStartSlot());
			p.setInt(5, b.getEndSlot()-1);
			p.setString(6, b.getRecursive());
			p.setInt(7,b.getEmployeeId());
			p.setString(8, b.getStatus());			
			p.execute();
			
			if(b.getRecursive().equals("Y")){
				RecursiveBooking rb = (RecursiveBooking)b;
				p = con.prepareStatement("insert into recursive values(?,?,?,?)");
				p.setInt(1, id+1);
				p.setDate(2, rb.getFromDate());
				p.setDate(3, rb.getToDate());
				p.setString(4, rb.getRecurrence());				
				p.execute();
			}
			con.commit();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	public boolean isBookingClash(int roomId, Date bookingDate, int startSlot, int endSlot) {
		
		boolean clash = false;
		
		try{
			PreparedStatement p = con.prepareStatement("select count(*) from booking "
					+ "where roomid=? and bookingdate=? and (startslot between ? and ?"
					+ " or endslot between ? and ? or (startSlot<? and endSlot>?))");
			
			p.setInt(1, roomId);
			p.setDate(2, bookingDate);
			p.setInt(3, startSlot);
			p.setInt(4, endSlot);
			p.setInt(5, startSlot);
			p.setInt(6, endSlot);
			p.setInt(7, startSlot);
			p.setInt(8, endSlot);
			
			ResultSet rs = p.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			 if(count>0){
				 return true;
			 } else {
				 return false;
			 }
		 
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return clash;
	}

	public List<Booking> getMyBookings(int employeeId) {
		List<Booking> bookings = new ArrayList<Booking>();
		
		try{
			PreparedStatement p = con.prepareStatement("select * from booking where employeeid=? and bookingdate>=sysdate and recursive='N' and status='B'");
			p.setInt(1, employeeId);
			
			ResultSet rs = p.executeQuery();
			Booking b = null;
			while(rs.next()){
				b = new Booking(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8));
				bookings.add(b);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return bookings;
	}

	public List<RecursiveBooking> getMyRecursiveBookings(int employeeId) {
		List<RecursiveBooking> bookings = new ArrayList<RecursiveBooking>();
		
		try{
			PreparedStatement p = con.prepareStatement("select b.id, b.roomid, b.bookingdate, "
					+ "b.startslot, b.endslot, r.fromdate, r.todate, r.recurrence from booking b,"
					+ " recursive r where b.id=r.bookingid and b.employeeid=? and "
					+ "b.bookingdate>=sysdate and b.recursive='Y' and b.status='B'");
			p.setInt(1, employeeId);
			
			ResultSet rs = p.executeQuery();
			RecursiveBooking b = null;
			while(rs.next()){
				b = new RecursiveBooking();
				b.setId(rs.getInt(1));
				b.setRoomId(rs.getInt(2));
				b.setBookingDate(rs.getDate(3));
				b.setStartSlot(rs.getInt(4));
				b.setEndSlot(rs.getInt(5));
				b.setFromDate(rs.getDate(6));
				b.setToDate(rs.getDate(7));
				b.setRecurrence(rs.getString(8));
				
				bookings.add(b);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return bookings;
	}

	public void cancelBooking(int id) {
		try{
			PreparedStatement p = con.prepareStatement("update booking set status='C' where id=?");
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
}
