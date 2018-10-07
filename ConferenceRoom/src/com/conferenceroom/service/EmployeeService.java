package com.conferenceroom.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conferenceroom.entity.Employee;





public class EmployeeService extends BaseService {
	public EmployeeService() {
		super();

}
	public boolean validateEmployee(String userId, String password){
		boolean flag = false;
		try {
			con.setAutoCommit(false);
			PreparedStatement p = con.prepareStatement("select password from employee where username=?");
			p.setString(1,userId);
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				String dbPassword = rs.getString("password");
				if(password.equals(dbPassword)){
					flag = true;
				}else{
					flag = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public List<Employee> getEmployees() {
		List<Employee> employees  = new ArrayList<Employee>();
		
		try {
			
			PreparedStatement p = con.prepareStatement("select * from employee");
			ResultSet rs = p.executeQuery();
			Employee e  = null;
			while(rs.next()){
				e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));				
				employees.add(e);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}
	public void addemployee(Employee emp) {
		try {
			PreparedStatement ps = con.prepareStatement("select max(id) from employee");
			ResultSet rs = ps.executeQuery();
			int id = 0;
			while(rs.next()){
				id = rs.getInt(1);
			}
			 ps = con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
			 ps.setInt(1,id+1);
			 ps.setString(2, emp.getFirstName());
			 ps.setString(3, emp.getLastName());
			 ps.setString(4, emp.getDepartment());
			 ps.setString(5,emp.getEmail());
			 ps.setString(6,emp.getUsername());
			 ps.setString(7,emp.getPassword());
			 
			 ps.executeUpdate();
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	public void deleteEmployee(int deleteEmployeeID) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from employee where id = ? ");
			ps.setInt(1, deleteEmployeeID);
			ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		// TODO Auto-generated method stub
	public List<Employee> displayEditEmployee(int editEmployeeID) {
		List<Employee> employees = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from employee where ID =?");
			ps.setInt(1, editEmployeeID);
			ResultSet rs = ps.executeQuery();
			Employee e = null;
			while(rs.next()){
				e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				employees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	public void editemployee(Employee emp) {
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("update employee set firstname = ?, lastname = ?, department = ?, email = ?, username = ?, password = ? where id = ?");
			 
			 ps.setString(1,emp.getFirstName());
			 ps.setString(2,emp.getLastName());
			 ps.setString(3,emp.getDepartment());
			 ps.setString(4,emp.getEmail());
			 ps.setString(5,emp.getUsername());
			 ps.setString(6,emp.getPassword());
			 ps.setInt(7, emp.getId());
			 ps.executeUpdate();
			 con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Employee> searchEmployees(String searchName) {
		List<Employee> employees= new ArrayList<Employee>();
		try {
			
			PreparedStatement p = con.prepareStatement("select * from employee where Upper(FIRSTNAME) || Upper(LASTNAME) like ?");//for searching case insensitive we use upper or lower
			p.setString(1,"%"+searchName.toUpperCase()+"%");//for searching case insensitive we use upper or lower
			ResultSet rs = p.executeQuery();
			Employee e  = null;
			while(rs.next()){
				e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7));
				employees.add(e);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}
	public int getEmployeeIdByUsername(String username) {
		int empId = 0;
		try{
			PreparedStatement p = con.prepareStatement("select id from employee where username=?");
			p.setString(1, username);
			
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				empId = rs.getInt(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empId;
	}
		
	}


