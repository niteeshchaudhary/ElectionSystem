


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
@WebServlet("/AuthenticateEle")
public class AuthenticateEle extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AuthenticateEle() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession ses = request.getSession();  
				response.setContentType("text/html");
 	 			PrintWriter out = response.getWriter();
	
		//getting input values from jsp page
		String userid = request.getParameter("userid");
		String userpassword = request.getParameter("password");
		
		

		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
 		String username = "universityDB0035"; //MySQL username
 		String password = "Niteesh@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);
 		PreparedStatement stcheck = con .prepareStatement("select * from ecusers where Id=?;");
 		stcheck.setString(1,userid);
 		ResultSet rs=stcheck.executeQuery();
 		if(rs.next()) {
 			if(rs.getString("password").equals(userpassword)) {
 				ses.setAttribute("adminid", userid);
 				response.sendRedirect("ecdashboard.jsp");
// 				RequestDispatcher rd = request.getRequestDispatcher("MoreInfo");
// 				rd.forward(request, response);
 			}
 			else {
 	 		      String docType =
 	 		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
 	 		      out.println(docType +
 	 		         "<html>\n" +
 	 		         "<head><title>Error </title></head>\n"+
 	 		         "<body bgcolor = \"#f0f0f0\">\n" +
 	 		         "<h1 align = \"center\">Incorrect Password</h1>\n" +"</body></html>"
 	 		      );
 	 		    RequestDispatcher rd = request.getRequestDispatcher("elehome.jsp");
 	 		    rd.include(request, response);

 			}
 			
 		}
 		else {
 		      String docType =
 		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
 		      out.println(docType +
 		         "<html>\n" +
 		         "<head><title>Error </title></head>\n"+
 		         "<body bgcolor = \"#f0f0f0\">\n" +
 		         "<h1 align = \"center\">Invalid User</h1>\n" +"</body></html>");
 		      
 		        RequestDispatcher rd = request.getRequestDispatcher("elehome.jsp");
	 		    rd.include(request, response);
 		}
	
	}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}


}

