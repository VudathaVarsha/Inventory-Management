package com.example.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html");

		PrintWriter output=response.getWriter();
		
		String name=request.getParameter("pname");

		int id=Integer.parseInt(request.getParameter("pid"));

		int price=Integer.parseInt(request.getParameter("price"));

		int quantity=Integer.parseInt(request.getParameter("quantity"));

		try {

		   Class.forName("oracle.jdbc.driver.OracleDriver");

		   Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		   System.out.println("Connection established");
		   PreparedStatement pStmt = conn.prepareStatement("insert into product values(?, ?, ?, ?)");
           pStmt.setInt(1, id);
		   pStmt.setString(2, name);
	       pStmt.setInt(3, price);
		   pStmt.setInt(4, quantity);

           pStmt.executeUpdate();
           response.sendRedirect("TableServlet");
		 }
	   catch(Exception e) {

	        System.out.println(e.getMessage());

	    }
	}

}
