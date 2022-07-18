package com.kmbtask;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ListService extends HttpServlet {
	int empCode,empSalary;
	String empName="";
	PreparedStatement ps; 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		//int empCode = Integer.parseInt(request.getParameter("empCode"));
		//System.out.println(empCode);
		StringBuffer sb =new StringBuffer();
		String line =null;
		String st;
		PrintWriter out = response.getWriter();
		ServletContext ctx=getServletContext();  
		Connection con=(Connection)ctx.getAttribute("mycon");  
	       // System.out.println(con);
	        try {
	        	BufferedReader br =request.getReader();
	        	while((line=br.readLine())!=null) {
	        		sb.append(line);
	        	}
	        	st=sb.toString();
	        	JSONParser jsp=new JSONParser();
	        	JSONObject jo = new JSONObject();
				jo = (JSONObject) jsp.parse(st);
				String s=jo.get("empCode").toString();
				empCode= Integer.parseInt(s);

	        	String sql="SELECT * from permanentemployee WHERE empCode=?";
	        	ps = con.prepareStatement(sql);
	        	ps.setInt(1, empCode);
				
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
					empCode=rs.getInt(1);
					empName=rs.getString(2);
					empSalary=rs.getInt(3);
					
				}
				JSONObject jobj =new JSONObject();
				jobj.put("Code",empCode);
				jobj.put("NAME", empName);
				jobj.put("Salary", empSalary);
				System.out.println(jobj);
				out.println(jobj);
				
	        }
catch (SQLException | ParseException e) {
				
				e.printStackTrace();
			}
	}

}
