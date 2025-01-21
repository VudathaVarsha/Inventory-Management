package com.example.product;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter output=response.getWriter();
		String btnVal=request.getParameter("button");
		if(btnVal.equals("admin"))
		{
		  //RequestDispatcher rd=request.getRequestDispatcher("home.html");
		  RequestDispatcher rd=request.getRequestDispatcher("admin.html");
		  rd.forward(request, response);
		}
		else if(btnVal.equals("customer"))
		{
		  RequestDispatcher rs=request.getRequestDispatcher("login.html");
		  rs.forward(request, response);
		}

	}

}
