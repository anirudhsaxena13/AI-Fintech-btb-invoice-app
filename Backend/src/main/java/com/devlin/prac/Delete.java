package com.devlin.prac;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public Connection getConnection()
	{
		 Connection conn =null;
		 String url ="jdbc:mysql://localhost:3306/grey_goose";
		 String user = "root";
		 String pass ="root";
			
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn =DriverManager.getConnection(url,user,pass);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return conn;
		}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 Connection conn = getConnection();
			 String sl_no = request.getParameter("sl_no");
			 System.out.print("sl_no = [(");
//			 int c=0;
//			 for(String i:sl_no) {
//				 System.out.print(i+", ");
//				 c++;
//			 }
//			 System.out.println(")]");
			 //System.out.println(c);
			 
			 
			 //String sql_query="DELETE FROM grey_goose.winter_internship where sl_no IN (?)";
			 
			 String sql_query="DELETE FROM winter_internship where sl_no IN ("+sl_no+")";
			 
			 
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			 pst.executeUpdate();
			 
			 //pst.setArray(1, conn.createArrayOf("text", sl_no));
			 
			 if(pst.executeUpdate()>0) {
				 System.out.println("Data deleted sucessfully");
			 }else {
				 System.out.println("Error in deleting data");
			 }

				response.setHeader("Access-Control-Allow-Origin","*");

		     }catch(Exception e) {
			    e.printStackTrace();
		     }
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}