package com.conferenceroom.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conferenceroom.entity.Employee;
import com.conferenceroom.entity.Room;

public class RoomService extends BaseService{

	public List<Room> getRooms() {
		List<Room> rooms  = new ArrayList<Room>();
		
		try {
			
			PreparedStatement p = con.prepareStatement("select * from conferenceroom");
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
	
	public Room getRoomById(int roomId) {
		Room room = null;
		
		try {
			
			PreparedStatement p = con.prepareStatement("select * from conferenceroom where id=?");
			p.setInt(1, roomId);
			ResultSet rs = p.executeQuery();
			
			if(rs.next()){
				room = new Room(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));				
								
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return room;
	}

}
