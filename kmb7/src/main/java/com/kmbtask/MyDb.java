package com.kmbtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDb {
	private Connection conn;
	public Connection getconn() {
		return conn;
	}
	 public MyDb(String url,String username,String password) {  
         try {  
               Class.forName("Driver");  
               this.conn = DriverManager.getConnection(url,username,password);  
         } catch (ClassNotFoundException e) {  
              
               e.printStackTrace();  
         } catch (SQLException e) {  
              
               e.printStackTrace();  
         }  

}
}
