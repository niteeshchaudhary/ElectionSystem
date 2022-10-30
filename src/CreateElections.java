

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MoreInfo
 */
@WebServlet("/CreateElections")

public class CreateElections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateElections() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(); 
		if(ses.getAttribute("adminid")==null) {
			return;
		}
//		System.out.println("--"+ses.getAttribute("userid")+"--"+ses.getAttribute("name")+"--");
//		
//	try {
//		Connection con = null;
// 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
// 		String username = "universityDB0035"; //MySQL username
// 		String password = "Niteesh@123"; //MySQL password
		
// 		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
//		PreparedStatement stcheck = con.prepareStatement("select * from votersdetails where Id=?;");
//		stcheck.setString(1,(String)ses.getAttribute("userid"));
// 		ResultSet rs=stcheck.executeQuery();
// 		if(rs.next()) {
// 			String name=rs.getString("name");
// 			ses.setAttribute("name", name);
// 			response.sendRedirect("dashboard.jsp");
// 		}
		String docType =
 		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
 		      out.println(docType +"<style>" + 
 		      		"form{" + 
 		      		"padding:1rem;" + 
 		      		"margin:1rem;" + 
 		      		"}" + 
 		      		"label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
 		      		"input{padding:1rem;margin:1rem;border-radius:1rem;width:40rem;}"+
 		      		     "body {"+
 		      		     "background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');"+
 		      		     "color: #FFFFFF;"+
 		      		       "background-repeat: no-repeat;"+
 		      		       "background-size: cover;"+
 		      		 "}"+

 		      		"</style>"+
 		         "<h1 align = \"center\">Election Details</h1>\n"
 		         + "<form action=\"CreateElections\" method=\"post\">"
 		         + "<label>Electiom Name:</label><br><input type=\"text\" name=\"ename\"/></br>"
 		         + "<label>Voting Date:</label><br><input type=\"date\" name=\"vdate\"/></br>"
 		        + "<label>Time:</label><br><input type=\"time\" name=\"time\" /></br>"
 		         + "<label>Contact No.</label><br><input type=\"number\" name=\"contact_no\" /></br>"
 		       +"<input type=\"submit\" /></br>"
 		         + "</form>");
		
		//RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	    //rd.include(request, response);
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out = response.getWriter();
			HttpSession ses = request.getSession(); 
			Object usr=ses.getAttribute("adminid");
			if(usr==null) {
		        out.println(
		                "<h1 align = \"center\">Session: Expired </h1>\n");
				return;
			}
			String adminid=(String)usr;
	
		//getting input values from jsp page
			
		String name = request.getParameter("ename");
		String vdate = request.getParameter("vdate");
		String contact_no = request.getParameter("contact_no");
		String time= request.getParameter("time");

		int err=0;
		
		response.setContentType("text/html");
		
		if(name.strip().length()==0 || vdate=="" ||time=="") {
			out.println(
	 		         "<h1 align = \"center\">Provide all details</h1>\n");
			err=1;
		}
		else if(contact_no.length()!=10) {
			out.println(
	 		         "<h1 align = \"center\">Invalid Phone Number</h1>\n");
			err=1;
		}
		
		if(err==1) {
	 		doGet(request, response);
		}else {
		

		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
 		String username = "universityDB0035"; //MySQL username
 		String password = "Niteesh@123"; //MySQL password
		
 		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);
 		PreparedStatement stcheck = con.prepareStatement("select * from ecusers where Id=?;");
 		stcheck.setString(1,adminid);
 		ResultSet rs=stcheck.executeQuery();
 		if(rs.next()) {
 			stcheck = con.prepareStatement("insert into elections(ename,vdate,time,contact_no,createdby,status) values(?,?,?,?,?,?);");
 	 		stcheck.setString(1,name);
 	 		stcheck.setString(2,vdate);
 	 		stcheck.setString(3,time);
 	 		stcheck.setString(4,contact_no);
 	 		stcheck.setString(5,adminid);
 	 		stcheck.setString(6,"1");
 	 		int rslt=stcheck.executeUpdate();
 	 		if(rslt>0) {
 	 			ses.setAttribute("name", name);
 	 			response.sendRedirect("ecdashboard.jsp");
// 	 			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
// 	 		    rd.forward(request, response);  
 	 		}
 	 		else {
 	 			out.println(
 		 		         "<h1 align = \"center\">Error: Unable to Update your Details </h1>\n");
 	 			doGet(request, response);
// 	 			RequestDispatcher rd = request.getRequestDispatcher("MoreInfo");
// 	 		    rd.include(request, response);
 	 		}
 			
 			
			//rd.forward(request, response);
 		}
 		else {
 			
 			out.println(
	 		         "<h1 align = \"center\">Invalid user or user Session </h1>\n");
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		    rd.include(request, response);  
 		}
	
	}
			}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 	        PrintWriter out = response.getWriter();
 	        out.println(
 	                "<h1 align = \"center\">Session: Expired </h1>\n");
 		}
		
	}

}
