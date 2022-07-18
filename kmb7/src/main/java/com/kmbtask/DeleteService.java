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
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DeleteService extends HttpServlet {
	PreparedStatement ps; 
	int empCode;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		//int empCode = Integer.parseInt(request.getParameter("empCode"));
		//System.out.println(empCode);
		StringBuffer sb= new StringBuffer();
		String line =null;
		String st;
		PrintWriter out = response.getWriter();
		ServletContext ctx=getServletContext();  
		Connection con=(Connection)ctx.getAttribute("mycon");  
	       //System.out.println(con);
	        try {
	        	BufferedReader br=request.getReader();
	        	while((line=br.readLine())!=null) {
	        		sb.append(line);
	        	}
	        	st=sb.toString();
	        	JSONParser jsp=new JSONParser();
	        	JSONObject jo = new JSONObject();
				jo = (JSONObject) jsp.parse(st);
				String s=jo.get("empCode").toString();
				empCode= Integer.parseInt(s);

	        	ps = con.prepareStatement("DELETE  FROM employee.permanentemployee where empCode="+empCode+";");
				ps.executeUpdate();
				out.println(empCode+" : Deleted");	
				con.close();
	        }
 catch (SQLException | ParseException e) {
				
				e.printStackTrace();
			}
			
	}
}
