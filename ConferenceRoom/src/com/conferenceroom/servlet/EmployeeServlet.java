package com.conferenceroom.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conferenceroom.entity.Employee;
import com.conferenceroom.service.EmployeeService;







/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String targetPage = null;
		EmployeeService es = new EmployeeService();
		
		if(action.equals("listEmployees")){
			List<Employee> employees= es.getEmployees();
			request.setAttribute("employees", employees);
			targetPage = "employees.jsp";

		}else if(action.equals("addEmployee")){
			String fname=request.getParameter("firstname");
			String lname=request.getParameter("lastname");
			String department=request.getParameter("department");
			String email=request.getParameter("email");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Employee e=new Employee(0, fname, lname, department, email, username, password);
			es.addemployee(e);
			List<Employee> employees= es.getEmployees();
			request.setAttribute("employees",employees);
			targetPage= "employees.jsp";
			
				
		}else if(action.equals("editEmployee")){
			int editEmployeeID = Integer.parseInt(request.getParameter("id"));
			List<Employee> employees=	es.displayEditEmployee(editEmployeeID);
			request.setAttribute("editemployee", employees);
			targetPage= "EditEmployee.jsp";
			
		}else if(action.equals("edited")){
			int editEmployeeID = Integer.parseInt(request.getParameter("editId"));
			String editFirstName = request.getParameter("editFirstName");
			String editLastName = request.getParameter("editLastName");
			String editDepartment = request.getParameter("editDepartment");
			String editEmail = request.getParameter("editEmail");
			String editUserName = request.getParameter("editUserName");
			String editPassword = request.getParameter("editPassword");
			
			Employee e = new Employee(editEmployeeID, editFirstName, editLastName, editDepartment, editEmail,editUserName, editPassword);
			es.editemployee(e);
			List<Employee> employees= es.getEmployees();
			request.setAttribute("employees",employees);
			targetPage= "employees.jsp";
			
		}else if(action.equals("deleteEmployee")){	
			int deleteEmployeeID = Integer.parseInt(request.getParameter("id"));
			es.deleteEmployee(deleteEmployeeID);
			System.out.println("Employee deleted !!");
			List<Employee> employees= es.getEmployees();
			request.setAttribute("employees",employees);
			targetPage= "employees.jsp";
			
		}
		else if(action.equals("searchemployees")){
			String searchName = request.getParameter("searchname");//searchname retriving from input tag name
			List<Employee> employees= es.searchEmployees(searchName);
			request.setAttribute("employees",employees);
			targetPage= "employees.jsp";
			
		}
		
		es.close();
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
