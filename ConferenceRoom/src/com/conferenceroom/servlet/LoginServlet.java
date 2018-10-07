package com.conferenceroom.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.conferenceroom.service.EmployeeService;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String targetPage = null;
		EmployeeService ss = new EmployeeService();

		if(action.equals("login")){
			String userId = request.getParameter("loginid");
			String password = request.getParameter("loginpassword");
			String userRole = request.getParameter("userrole");
			boolean flag =false;
			if(userRole.equals("employee")){
				
				flag= ss.validateEmployee(userId,password);
			}else
			{
				if(userId.equals("admin")&&password.equals("admin")){
					flag= true;
					System.out.println("Admin " +flag);
				}
			}
			
			if(flag){
				request.getSession().setAttribute("userId",userId);
				request.getSession().setAttribute("userRole", userRole);
				targetPage= "welcome.jsp";
				System.out.println(userRole+"  flag !" +flag);
			}else{
				request.setAttribute("error","Login Failed!");
				targetPage= "login.jsp";
			}

		}
		else if(action.equals("logout")){
			request.getSession().invalidate();//killing session
			targetPage= "login.jsp";
			
		}
		ss.close();
		request.getRequestDispatcher(targetPage).forward(request, response);
		System.out.println("Login Sucessful!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
