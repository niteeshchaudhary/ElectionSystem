


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;;
/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/ShowTable")
public class ShowTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ShowTable() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
 	 			
	
	}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
 	 			PrintWriter out = response.getWriter();


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
 		String username = "universityDB0035"; //MySQL username
 		String password = "Niteesh@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);
 		PreparedStatement stcheck = con .prepareStatement("select * from voters;");
 		ResultSet rs=stcheck.executeQuery();
 		 String docType =
	 		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
 		 +"<html><head><link rel='stylesheet' href=''></head<body>";
 		 
 		out.println(docType +
 		         "<table border='1'>");
 		while(rs.next()) {
 	 		     
 			out.println(
 	 		        "<tr>"
 	 		         + "<td>"+rs.getString("Id")+"</td><td>"+rs.getString("password")+"</td></tr>"
 	 		      );
 	 		   
 			}
 		con.close();
 		out.println("</table></body></html>");
	
	}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}


}

