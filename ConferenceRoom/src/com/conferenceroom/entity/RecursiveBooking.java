package com.conferenceroom.entity;

import java.sql.Date;

public class RecursiveBooking extends Booking{
	
	Date fromDate;
	Date toDate;
	String recurrence;
	
	public RecursiveBooking() {
		
	}
	
	public RecursiveBooking(int id, int roomId, Date bookingDate, int startSlot, int endSlot, String recursive,
			int employeeId, String status, Date fromDate, Date toDate, String recurrence) {
		super(id, roomId, bookingDate, startSlot, endSlot, recursive, employeeId, status);
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.recurrence = recurrence;
	}	

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	@Override
	public String toString() {
		return "RecursiveBooking [fromDate=" + fromDate + ", toDate=" + toDate + ", recurrence=" + recurrence + "]";
	}	

}
