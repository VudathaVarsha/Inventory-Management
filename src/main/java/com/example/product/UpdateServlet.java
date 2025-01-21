package com.example.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter output=response.getWriter();
        
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
        	int id=Integer.parseInt(request.getParameter("id"));
        	int price=Integer.parseInt(request.getParameter("price"));
        	int quantity=Integer.parseInt(request.getParameter("quantity"));
        	PreparedStatement st=conn.prepareStatement("update product set price=?,quantity=? where pid=?");
        	st.setInt(1,price);
        	st.setInt(2,quantity);
        	st.setInt(3,id);
        	st.executeUpdate();
        	response.sendRedirect("TableServlet");
      	}
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}
}
