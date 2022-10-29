

import javax.crypto.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print(request.getQueryString());
		try
		{
			PrintWriter out = response.getWriter();
			HttpSession ses = request.getSession();  
	
		//getting input values from jsp page
		String userid = request.getParameter("userid");
		String userpassword = request.getParameter("password");
		String confirm_password = request.getParameter("cpassword");
		int err=0;
		response.setContentType("text/html");
		
		if(userid.trim().length()==0) {
			out.println(
	 		         "<h1 align = \"center\">Please Provide a valid UserId</h1>\n");
			err=1;
		}
		else if(!userpassword.equals(confirm_password)) {
			out.println(
	 		         "<h1 align = \"center\">Password Miss Match</h1>\n");
			err=1;
		}
		else if(userpassword.length()<6) {
			out.println(
	 		         "<h1 align = \"center\">Password length Must be >= 6</h1>\n");
			err=1;
		}
		if(err==1) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
 		    rd.include(request, response);  
 		    return;
		}
		

		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
 		String username = "universityDB0035"; //MySQL username
 		String password = "Niteesh@123"; //MySQL password
		
 		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);
 		PreparedStatement stcheck = con.prepareStatement("select * from voters where Id=?;");
 		stcheck.setString(1,userid);
 		ResultSet rs=stcheck.executeQuery();
 		if(rs.next()) {
 	 		 out.println(
 	 		         "<h1 align = \"center\">User Already Exists</h1>\n");
 			
 			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
 		    rd.include(request, response);  
			//rd.forward(request, response);
 		}
 		else {
 			stcheck = con.prepareStatement("insert into voters values(?,?)");
 	 		stcheck.setString(1,userid);
 	 		stcheck.setString(2,userpassword);
 	 		
 	 		int r_count=stcheck.executeUpdate();
 	 		con.close();
 	 		if(r_count>0) {
 	 			ses.setAttribute("userid", userid);
 	 			con.close();
 	 			response.sendRedirect(request.getContextPath() + "/MoreInfo");
// 			out.println(
//	 		         "<h1 align = \"center\">Welcone "+userid+"</h1>\n");
// 			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
// 		    //rd.include(request, response);  
//			rd.forward(request, response);
 	 		}else {
 	 			out.print("<h2>some error occured</h2>");
 	 			con.close();
 	 			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
 	 		    rd.include(request, response);  
 	 		}
 		}
	
	}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}


}

