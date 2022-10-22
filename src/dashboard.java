


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;;
/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession ses = request.getSession();  
		//getting input values from jsp page
//		int student_id = Integer.parseInt(request.getParameter("student_id"));
//		int book_id = Integer.parseInt(request.getParameter("book_id"));
//		String issue_date = request.getParameter("issue_date");
//		String return_date = request.getParameter("return_date");		
//
//		Connection con = null;
// 		String url = "jdbc:mysql://localhost:3306/Library"; //MySQL URL and followed by the database name
// 		String username = "universityDB0035"; //MySQL username
// 		String password = "Niteesh@123"; //MySQL password
//		
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
// 		System.out.println("Printing connection object "+con);
// 		PreparedStatement stcheck = con .prepareStatement("select * from book where book_id=?;");
// 		
 		System.out.println("{["+ses.getAttribute("userid")+"]}");
		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}


}

