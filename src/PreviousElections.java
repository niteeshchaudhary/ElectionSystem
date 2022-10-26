

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActiveElections
 */
@WebServlet("/PreviousElections")
public class PreviousElections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviousElections() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PreparedStatement stcheck = con .prepareStatement("select * from elections where status='2';");
        ResultSet rs=stcheck.executeQuery();
         String docType =
                     "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
         +"<html><head><link rel='stylesheet' href=''>"
         + "<style>"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;}td{margin:1rem;padding:1rem;}input{margin:1rem;padding:1rem}"
         + "</style></head<body>";
         
        out.println(docType +
                 "<table border='1'>");
        out.println(
                "<tr>"
                 + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
              );
        while(rs.next()) {
                 
            out.println(
                    "<tr>"
                     + "<td><label>"+rs.getString("ename")+"</label></td>"
                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs.getString("time")+"</label></td>"
                     + "<td><label>"+rs.getString("contact_no")+"</label></td>"
                     + "<td><form action='#' method='post'>"
                     + "<input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'/>"
                             + "<input type='submit' value='View Details'/>"
                     + "</form></td>"
                     + "</tr>" 
                  );
               
            }
        out.println("</table></body></html>");
    
    }
         catch (Exception e) 
        {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
                PrintWriter out = response.getWriter();
                String query = request.getParameter("close");
                Connection con = null;
                String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
                String username = "universityDB0035"; //MySQL username
                String password = "Niteesh@123"; //MySQL password
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
                response.setContentType("text/html");
                String []inpStrings = request.getParameter("id").split(",");
                
                if(query!=null && query.equals("true")) {
                    PreparedStatement stcheck = con .prepareStatement("update elections set status ='2' where ename=? and vdate=?;");   
                    stcheck.setString(1, inpStrings[0]);
                    stcheck.setString(2, inpStrings[1]);
                    int  rline=stcheck.executeUpdate();
                    if(rline==1) {
                        
                    }
                    else {
                        out.println("Error in Closing Election");
                    }
                    doGet(request, response);
                }
                else {

       
        System.out.println("Printing connection object "+con);
        
        PreparedStatement stcheck = con .prepareStatement("select * from elections where ename=? and vdate=?;");
        stcheck.setString(1, inpStrings[0]);
        stcheck.setString(2, inpStrings[1]);
        ResultSet rs=stcheck.executeQuery();
         String docType =
                     "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
         +"<html><head><link rel='stylesheet' href=''>"
         + "<style>"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;}td{margin:1rem;padding:1rem;}input{margin:1rem;padding:1rem}"
         + "</style></head<body>";
         
        out.println(docType +
                 "<table border='1'>");
        out.println(
                "<tr>"
                 + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
              );
        while(rs.next()) {
                 
            out.println(
                    "<tr>"
                     + "<td><label>"+rs.getString("ename")+"</label></td>"
                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs.getString("time")+"</label></td>"
                     + "<td><label>"+rs.getString("contact_no")+"</label></td>"
                     + "<td><form action='#' method='post'><input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'><input type='submit' value='Edit'/><input type='submit' value='close'/></form></td>"
                             + "</tr>" 
                  );
               
            }
        out.println("</table></body></html>");
    
    }}
         catch (Exception e) 
        {
            e.printStackTrace();
        }
        
//		doGet(request, response);
	}

}
