package com.conferenceroom.entity;

import java.sql.Date;

public class Booking {
	
	int id;
	int roomId;
	Date bookingDate;
	int startSlot;
	int endSlot;
	String recursive;
	int employeeId;
	String status;
	
	public Booking(){
		
	}
	public Booking(int id, int roomId, Date bookingDate, int startSlot, int endSlot, String recursive, int employeeId,
			String status) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.bookingDate = bookingDate;
		this.startSlot = startSlot;
		this.endSlot = endSlot;
		this.recursive = recursive;
		this.employeeId = employeeId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getStartSlot() {
		return startSlot;
	}

	public void setStartSlot(int startSlot) {
		this.startSlot = startSlot;
	}

	public int getEndSlot() {
		return endSlot;
	}

	public void setEndSlot(int endSlot) {
		this.endSlot = endSlot;
	}

	public String getRecursive() {
		return recursive;
	}

	public void setRecursive(String recursive) {
		this.recursive = recursive;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", roomId=" + roomId + ", bookingDate=" + bookingDate + ", startSlot=" + startSlot
				+ ", endSlot=" + endSlot + ", recursive=" + recursive + ", employeeId=" + employeeId + ", status="
				+ status + "]";
	}

}
