package com.conferenceroom.entity;


	public class Employee{
		
		int id;
		String firstName;
		String lastName;
		String department;
		String email;
		String username;
		String password;
		
		public Employee(int id, String firstName, String lastName, String department, String email, String username,
				String password) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.department = department;
			this.email = email;
			this.username = username;
			this.password = password;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
					+ department + ", email=" + email + ", username=" + username + ", password=" + password + "]";
		}
		
				
	}


