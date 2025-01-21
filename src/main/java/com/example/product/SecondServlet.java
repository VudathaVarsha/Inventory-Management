package com.example.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation class SecondServlet
 */
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter output=response.getWriter();
        
		String btnVal=request.getParameter("button");
		        if(btnVal.equals("add"))
		        {
		         RequestDispatcher rd1=request.getRequestDispatcher("home.html");
				  rd1.forward(request,response);
				}
				else if(btnVal.equals("update"))
				{
				  RequestDispatcher rd2=request.getRequestDispatcher("update.html");
				  rd2.forward(request,response);
				}
				else if(btnVal.equals("delete"))
				{
				  RequestDispatcher rd3=request.getRequestDispatcher("delete.html");
				  rd3.forward(request,response);
					
				}
				else if(btnVal.equals("view"))
				{
				  RequestDispatcher rd4=request.getRequestDispatcher("TableServlet");
				  rd4.forward(request,response);
				}
	        }
	       

}
