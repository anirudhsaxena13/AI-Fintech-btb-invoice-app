package com.devlin.prac;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();
		
		String customer_number = request.getParameter("cust_number");
		
		String sql_query = "SELECT * from winter_internship WHERE cust_number LIKE ? ";
		
		PreparedStatement pst = conn.prepareStatement(sql_query);
		pst.setString(1, customer_number+'%');
		
		ArrayList<winterInternshipDataSet> Hrdatas = new ArrayList<winterInternshipDataSet>();
		String sl_no,cust_number, invoice_id, posting_id;
		String total_open_amount;
		String doc_id;
		String buisness_year;
		String clear_date,posting_date,document_create_date,due_in_date, business_code, baseline_create_date;
		String invoice_currency,document_type, cust_payment_terms ;
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
		{
			winterInternshipDataSet hr = new winterInternshipDataSet();
			sl_no=rs.getString("sl_no");
			business_code=rs.getString("business_code");
			cust_number=rs.getString("cust_number");
			clear_date=rs.getString("clear_date");
			doc_id=rs.getString("doc_id");
			buisness_year=rs.getString("buisness_year");
			posting_date=rs.getString("posting_date");
			document_create_date=rs.getString("document_create_date");
			due_in_date=rs.getString("due_in_date");
			invoice_currency=rs.getString("invoice_currency");
			document_type=rs.getString("document_type");
			posting_id=rs.getString("posting_id");
			total_open_amount=rs.getString("total_open_amount");
			baseline_create_date=rs.getString("baseline_create_date");
			cust_payment_terms=rs.getString("cust_payment_terms");
			invoice_id=rs.getString("invoice_id");
			
			hr.setSl_no(sl_no);
			hr.setBusiness_code(business_code);
			hr.setCust_number(cust_number);
			hr.setClear_date(clear_date);
			hr.setDoc_id(doc_id);
			hr.setBuisness_year(buisness_year);
			hr.setPosting_date(posting_date);
			hr.setDocument_create_date(document_create_date);
			hr.setDue_in_date(due_in_date);
			hr.setInvoice_currency(invoice_currency);
			hr.setDocument_type(document_type);
			hr.setPosting_id(posting_id);
			hr.setTotal_open_amount(total_open_amount);
			hr.setBaseline_create_date(baseline_create_date);
			hr.setCust_payment_terms(cust_payment_terms);
			hr.setInvoice_id(invoice_id);
			
			Hrdatas.add(hr);	
		}
		for(winterInternshipDataSet hrr: Hrdatas)
		{
			System.out.println(hrr.toString());
		}
		
		Gson gson=new Gson();
		String respData=gson.toJson(Hrdatas);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().print(respData);

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
