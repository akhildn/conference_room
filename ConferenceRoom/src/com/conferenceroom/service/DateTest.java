package com.conferenceroom.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;



public class DateTest {

	public static void main(String[] args) {
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "conferenceroom", "conferenceroom");
	
		PreparedStatement p = con.prepareStatement("update booking set bookingdate=? where id=1");
		LocalDate date = LocalDate.parse("2017-06-30");
		p.setDate(1, Date.valueOf(date));
		p.executeUpdate();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
