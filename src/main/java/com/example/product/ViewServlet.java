package com.example.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewServlet
 */
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
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
		
		try {

			   Class.forName("oracle.jdbc.driver.OracleDriver");

			   Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			   Statement smt=conn.createStatement();
			   ResultSet rs = smt.executeQuery("select * from product");
	           output.println("<center><table border=2px solid black style=border-collapse:collapse><tr><th>Id</th><th>Name</th><th>Price</th><th>Quantity</th>");
	           while(rs.next()) {
	              output.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
	           }
	           RequestDispatcher rd=request.getRequestDispatcher("buy.html");
	           rd.include(request, response);
			   
	}
	catch(Exception e) {

	   System.out.println(e.getMessage());

	 }
  }
}
