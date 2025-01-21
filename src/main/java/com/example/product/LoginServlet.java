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
 * Servlet implementation class LoginServlet
 */
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
		response.setContentType("text/html");
        PrintWriter output=response.getWriter();
        String name=request.getParameter("uname");
		int pass=Integer.parseInt(request.getParameter("upass"));
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from stu");
		int flag=0;
		while(rs.next()) {
		if(name.equals(rs.getString(2)) && pass==rs.getInt(1)) {
			flag=1;
			break;
		}
		}
		if(flag==1) {
			RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
			rd.forward(request,response);
		}
		else {
			output.println("Enter valid credentials for access");
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
