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
 * Servlet implementation class BuyServlet
 */
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
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

     	int quantity=Integer.parseInt(request.getParameter("quantity"));

		try {

		   Class.forName("oracle.jdbc.driver.OracleDriver");

		   Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		   System.out.println("Connection established");
		   //Statement st=conn.createStatement();
		   PreparedStatement st=conn.prepareStatement("select * from product");
		   ResultSet rs=st.executeQuery();
		   int q1=0,p=0;
		   while(rs.next()) {
			   if(rs.getString(2).equals(name)) {
		         q1=rs.getInt(4);
		         p=rs.getInt(3);
		         break;
			   }
		   }
		   PreparedStatement pst=conn.prepareStatement("update product set quantity=? where pname=?");
		   pst.setInt(1,(q1-quantity));
		   pst.setString(2, name);
		   pst.executeUpdate();
		   output.println("Thank you<br>");
		   output.println("Total bill:"+(quantity*p));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
