package com.kmbtask;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

@WebServlet
public class MyServlet extends HttpServlet {
	
	PreparedStatement ps; 
	int empCode,empSalary;
	String empName;
       
   
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		//int empCode = Integer.parseInt(request.getParameter("empCode"));
		//System.out.println(empCode);
		//String empName = request.getParameter("empName");
		//int empSalary = Integer.parseInt(request.getParameter("empSalary"));
		StringBuffer sb= new StringBuffer();
		String line =null;
		String st;
		PrintWriter out = response.getWriter();
		ServletContext ctx=getServletContext();  
		Connection con=(Connection)ctx.getAttribute("mycon");  
	        System.out.println(con);
	        try {
	        	BufferedReader br=request.getReader();
	        	while((line=br.readLine())!=null) {
	        		sb.append(line);
	        	}
	        	st=sb.toString();
	        	JSONParser jsp=new JSONParser();
	        	JSONObject jo = new JSONObject();
				jo = (JSONObject) jsp.parse(st);
				String input1=jo.get("empCode").toString();
				String input2=jo.get("empName").toString();
				String input3=jo.get("empSalary").toString();
				empCode= Integer.parseInt(input1);
				empName=input2;
				empSalary=Integer.parseInt(input3);
				System.out.println(empCode +" "+empSalary+" "+empName);
				ps = con.prepareStatement("INSERT INTO permanentemployee (empCode,empName,empSalary) values(?,?,?)");
				ps.setInt(1, empCode);
				ps.setString(2, empName);
				ps.setInt(3,empSalary);
				ps.executeUpdate();
				out.println("created...");
				con.close();
			} 
	        catch (SQLException | ParseException e) {
				
				e.printStackTrace();
			}
			
	}

	
	
}
