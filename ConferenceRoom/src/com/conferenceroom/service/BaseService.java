package com.conferenceroom.service;

import java.sql.Connection;
import java.sql.DriverManager;


public class BaseService {

	protected Connection con;
	public BaseService(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "conferenceroom", "conferenceroom");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	public void close(){
		try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
