package com.example.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
        	PreparedStatement st=conn.prepareStatement("delete from product where pid=?");
        	st.setInt(1, id);
        	st.executeUpdate();	
        	response.sendRedirect("TableServlet");
        }
        catch(Exception e) {
        	 System.out.println(e.getMessage());
        }
	}

}
