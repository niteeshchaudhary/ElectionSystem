

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
@WebServlet("/MoreInfo")
public class MoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoreInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(); 
		if(ses.getAttribute("userid")==null || ses.getAttribute("name")!=null) {
			return;
		}
		System.out.println("--"+ses.getAttribute("userid")+"--"+ses.getAttribute("name")+"--");
		
		try {
		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
 		String username = "universityDB0035"; //MySQL username
 		String password = "Niteesh@123"; //MySQL password
		
 		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
		PreparedStatement stcheck = con.prepareStatement("select * from votersdetails where Id=?;");
		stcheck.setString(1,(String)ses.getAttribute("userid"));
 		ResultSet rs=stcheck.executeQuery();
 		if(rs.next()) {
 			String name=rs.getString("name");
 			ses.setAttribute("name", name);
 			response.sendRedirect("dashboard.jsp");
 		}
		String docType =
 		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
 		      out.println(docType +"<style>" + 
 		      		"form{" + 
 		      		"padding:1rem;" + 
 		      		"margin:1rem;" + 
 		      		"}" + 
 		      		"input{" + 
 		      		"padding:1rem;" + 
 		      		"margin:1rem;" + 
 		      		"}" + 
 		      		"</style>"+
 		         "<h2 align = \"center\">Add Your Details here</h2>\n"
 		         + "<form action=\"MoreInfo\" method=\"post\">"
 		         + "Name:<input type=\"text\" name=\"name\"/></br>"
 		         + "DOB:<input type=\"date\" name=\"dob\"/></br>"
 		         + "Phone No.<input type=\"number\" name=\"phone_no\" /></br>"
 		         + "Address Line 1:<input type=\"text\" name=\"add1\" /></br>"
 		         + "Address Line 2:<input type=\"text\" name=\"add2\" /></br>"
 		        + "Pincode:<input type=\"number\" name=\"pincode\"/></br></br>"
 		       +"<input type=\"submit\" /></br>"
 		         + "</form>");
		
		//RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	    //rd.include(request, response);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out = response.getWriter();
			HttpSession ses = request.getSession(); 
			Object usr=ses.getAttribute("userid");
			if(usr==null) {
				return;
			}
			String userid=(String)usr;
	
		//getting input values from jsp page
			
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String phone_no = request.getParameter("phone_no");
		String add1= request.getParameter("add1");
		String add2= request.getParameter("add2");
		String pnc=request.getParameter("pincode");
		int err=0;
		int pincode=0;
		if(pnc!="") {
			pincode=Integer.parseInt(pnc);
		}
		System.out.print("{"+dob+"}");
		
		response.setContentType("text/html");
		
		if(name.strip().length()==0||add1.strip().length()==0||dob=="") {
			out.println(
	 		         "<h1 align = \"center\">Provide all details</h1>\n");
			err=1;
		}
		else if(phone_no.length()!=10) {
			out.println(
	 		         "<h1 align = \"center\">Invalid Phone Number</h1>\n");
			err=1;
		}
		else if(pincode/1000==0) {
			out.println("<h1 align = \"center\">Pincode not correct</h1>\n");
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
 		PreparedStatement stcheck = con.prepareStatement("select * from voters where Id=?;");
 		stcheck.setString(1,userid);
 		ResultSet rs=stcheck.executeQuery();
 		if(rs.next()) {
 			stcheck = con.prepareStatement("insert into votersdetails values(?,?,?,?,?);");
 	 		stcheck.setString(1,userid);
 	 		stcheck.setString(2,name);
 	 		stcheck.setString(3,dob);
 	 		stcheck.setString(4,phone_no);
 	 		stcheck.setString(5,add1+" "+add2);
 	 		int rslt=stcheck.executeUpdate();
 	 		if(rslt>0) {
 	 			ses.setAttribute("name", name);
 	 			response.sendRedirect("dashboard.jsp");
// 	 			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
// 	 		    rd.forward(request, response);  
 	 		}
 	 		else {
 	 			out.println(
 		 		         "<h1 align = \"center\">Error: Unable to Update your Details </h1>\n");
 	 			RequestDispatcher rd = request.getRequestDispatcher("MoreInfo");
 	 		    rd.include(request, response);
 	 		}
 			
 			
			//rd.forward(request, response);
 		}
 		else {
 			
 			out.println(
	 		         "<h1 align = \"center\">Invalid user or user Session </h1>\n");
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		    rd.include(request, response);  
 		}
	
	}}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
		
	}

}
