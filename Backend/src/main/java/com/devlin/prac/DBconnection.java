package com.devlin.prac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBconnection {
	public Connection getConnection()
	{
		Connection conn=null;
//		String url = "jdbc:mysql://localhost:3308/grey_goose";
//		String user ="root";
//		String pass="";
		
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String  URL = "jdbc:mysql://localhost:3306/grey_goose";
		final String USER = "root";
		final String PASSWORD = "root";
				
				try{  
					Class.forName(JDBC_DRIVER);  
					conn = DriverManager.getConnection(URL,USER,PASSWORD);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				return conn;
	}
	
	public ArrayList<winterInternshipDataSet> getData()
	{
		ArrayList<winterInternshipDataSet> Hrdatas = new ArrayList<winterInternshipDataSet>();
		String sl_no,cust_number, invoice_id, posting_id;
		String total_open_amount;
		String doc_id;
		String buisness_year;
		String clear_date,posting_date,document_create_date,due_in_date, business_code, baseline_create_date;
		String invoice_currency,document_type, cust_payment_terms, aging_bucket ;
		try {
			Connection conn=getConnection();
			String sql_query = "Select * from winter_internship";
			PreparedStatement pst=conn.prepareStatement(sql_query);
			ResultSet rs=pst.executeQuery();
			
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
				aging_bucket=rs.getString("aging_bucket");
				
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
				hr.setAging_bucket(aging_bucket);
				
				Hrdatas.add(hr);	
			}
			for(winterInternshipDataSet hrr: Hrdatas)
			{
				System.out.println(hrr.toString());
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("exception occur");
			}
		return Hrdatas;
	}
}