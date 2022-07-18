package com.kmbtask;

import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent e) {
		Connection	con=null ;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "123456789");}
		
	catch(Exception ex){System.out.print(ex);} 
	
	 ServletContext context=e.getServletContext();  
	   context.setAttribute("mycon",con);  
		
	}
	public void contextDestroyed(ServletContextEvent event) {
	}
}
