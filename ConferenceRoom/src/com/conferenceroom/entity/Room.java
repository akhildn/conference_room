package com.conferenceroom.entity;

public class Room {
	int id;
	String name;
	int capacity;
	String projector;
	String phone;
	String system;

	public Room(int id, String name, int capacity, String projector, String phone, String system) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.projector = projector;
		this.phone = phone;
		this.system = system;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getProjector() {
		return projector;
	}
	public void setProjector(String projector) {
		this.projector = projector;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	@Override
	public String toString() {
		return "Rooms [id=" + id + ", name=" + name + ", capacity=" + capacity + ", projector=" + projector + ", phone="
				+ phone + ", system=" + system + "]";
	}
	

}
