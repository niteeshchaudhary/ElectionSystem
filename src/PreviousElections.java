

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
import javax.servlet.http.HttpSession;

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
                HttpSession ses = request.getSession(); 


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
         + "<style>"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;width:15rem}td{margin:1rem;padding:1rem;width:15rem}"
         + "input{margin:1rem;padding:1rem;width:5rem;border-radius:1rem}"+
         "label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
 "body {"+
 "background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');"+
 "color: #FFFFFF;"+
   "background-repeat: no-repeat;"+
   "background-size: cover;"+
"}"+
         "button{margin:1rem;padding:1rem;border-radius:1rem}"
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
                     + "<td><form action='PreviousElections' method='post'>"
                     + "<input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'/>"
                             + "<input type='submit' value='Results'/>"
                     + "</form></td>"
                     + "</tr>" 
                  );
               
            }
        if(ses.getAttribute("adminid")!=null){
            out.println("</table>"
                    + "<button onclick=\"window.location='ecdashboard.jsp'\">Back</button>"
                    + "</body></html>");
            }else
        if(ses.getAttribute("userid")!=null){
        out.println("</table>"
                + "<button onclick=\"window.location='dashboard.jsp'\">Back</button>"
                + "</body></html>");
        }
        else {
            
        }
    
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
            
                Connection con = null;
                String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
                String username = "universityDB0035"; //MySQL username
                String password = "Niteesh@123"; //MySQL password
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
                response.setContentType("text/html");
                String []inpStrings = request.getParameter("id").split(",");

       
        System.out.println("Printing connection object "+con);
        
        PreparedStatement stcheck = con .prepareStatement("select * from candidates where ename=? and vdate=? order by votes desc;");
        stcheck.setString(1, inpStrings[0]);
        stcheck.setString(2, inpStrings[1]);
        ResultSet rs=stcheck.executeQuery();
         String docType =
                     "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
         +"<html><head><link rel='stylesheet' href=''>"
         + "<style>"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;}td{margin:1rem;padding:1rem;}input{margin:1rem;padding:1rem}"+
         "button{margin:1rem;padding:1rem}"
         + "</style></head<body>";
         
        out.println(docType +
                 "<table border='1'>");
        out.println(
                "<tr>"
                 + "<th>Election</th><th>Voting Date</th><th>Candidate</th><th>Menifesto</th><th>Votes</th></tr>"
              );
        while(rs.next()) {
                 
            out.println(
                    "<tr>"
                     + "<td><label>"+rs.getString("ename")+"</label></td>"
                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs.getString("Id")+"</label></td>"
                     + "<td><label>"+rs.getString("menifesto")+"</label></td>"
                     + "<td><label>"+rs.getString("votes")+"</label></td>"
                             + "</tr>" 
                  );
               
            }
        out.println("</table>"
                + "<button onclick=\"window.location='PreviousElections'\">Back</button>"
                + "</body></html>");
        con.close();
    }
         catch (Exception e) 
        {
            e.printStackTrace();
        }
        
//		doGet(request, response);
	}

}
