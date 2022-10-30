

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
@WebServlet("/UpcomingElections")
public class UpcomingElections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpcomingElections() {
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
                HttpSession ses=request.getSession();
                String userid=(String)ses.getAttribute("userid");


        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
        String username = "universityDB0035"; //MySQL username
        String password = "Niteesh@123"; //MySQL password
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
        System.out.println("Printing connection object "+con);
        String qry="select el.* from elections as el,candidates as ca where  el.vdate=ca.vdate and el.ename=ca.ename and id=? and el.vdate>curdate() and status=\"1\";";
        String qry2="select * from elections as el where status='1' and vdate>CURDATE() and (el.ename,el.vdate) not in (select ename,vdate from candidates where id=?);";
        PreparedStatement stcheck = con .prepareStatement(qry);
        stcheck.setString(1, userid);
        ResultSet usercandidate=stcheck.executeQuery();
        
         String docType =
                     "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"
         +"<html><head><link rel='stylesheet' href=''>"
         + "<style>"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;width:15rem}td{margin:1rem;padding:1rem;width:15rem}"
         + "input{margin:1rem;padding:1rem;width:10rem;border-radius:1rem}"+
         "label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
         "table{background:rgba(20,20,20,0.4);}"+
 "body {"+
 "background-image: url('https://media.istockphoto.com/photos/indian-voter-hand-with-voting-sign-after-casting-vote-in-election-picture-id1148772178?k=20&m=1148772178&s=612x612&w=0&h=0xT4MDxjCKM8MzTDjgWaVbpTITW3Oci6B3NvwKncAD4=');"+
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
        while(usercandidate.next()) {
            out.println(
                    "<tr>"
                     + "<td><label>"+usercandidate.getString("ename")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("vdate")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("time")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("contact_no")+"</label></td>"
                     + "<td><form action='ApplyElections?withdraw=true' method='post'>"
                     + "<input type='text' value='"+usercandidate.getString("ename")+","+usercandidate.getString("vdate")+"' name='id' style='display:none'/>"
                             + "<input type='submit'  value='.  .  .  Withdraw  .  .  .'/>"
                             + "</form></td>"
                             + "</tr>" 
                  );
               
            }
        usercandidate.close();
        stcheck = con .prepareStatement(qry2);
        stcheck.setString(1, userid);
        ResultSet rs=stcheck.executeQuery();
        while(rs.next()) {
                 
            out.println(
                    "<tr>"
                     + "<td><label>"+rs.getString("ename")+"</label></td>"
                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs.getString("time")+"</label></td>"
                     + "<td><label>"+rs.getString("contact_no")+"</label></td>"
                     + "<td><form action='UpcomingElections' method='post'>"
                     + "<input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'/>"
                             + "<input type='submit'  value='Apply for Nomination'/>"
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
                HttpSession ses = request.getSession(); 
                String idStrings =request.getParameter("id");
                if(idStrings==null) {
                   // doGet(request, response);
                    return;
                }
                String []inpStrings = idStrings.split(",");
                
                String docType =
                      "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
                   out.println(docType +"<style>" + 
                      "h1{ -webkit-text-stroke: 2px black;font-size:4rem;}"+
                         "form{" + 
                         "padding:1rem;" + 
                         "margin:1rem;" + 
                         "}" + 
                         "textarea{"+
                         "padding:1rem;" + 
                         "margin:1rem;" + 
                         "width:100rem;" +
                         "color:#fff;"+
                         "font-size:1rem;"+
                         "height:30rem;"+
                         "background:rgba(20,20,20,0.6);"+
                   "}"+"tr{padding:1rem;margin:1rem;}th{padding:1rem;margin:1rem;width:15rem}td{margin:1rem;padding:1rem;width:15rem}"
                   + "input{margin:1rem;padding:1rem;width:20rem;border-radius:1rem}"+
                   "label{padding:1rem;margin:0.2rem;width:60rem;font-size:1.3rem}"+
           "body {"+
           "background-image: url('https://media.istockphoto.com/photos/indian-voter-hand-with-voting-sign-after-casting-vote-in-election-picture-id1148772178?k=20&m=1148772178&s=612x612&w=0&h=0xT4MDxjCKM8MzTDjgWaVbpTITW3Oci6B3NvwKncAD4=');"+
           "color: #FFFFFF;"+
             "background-repeat: no-repeat;"+
             "background-size: cover;"+
          "}"+
                   "button{margin:1rem;padding:1rem;border-radius:1rem}"
                   +
                         "</style>"+
                      "<h1 align = \"center\">Apply Nomination</h1>\n"
                      + "<form action=\"ApplyElections\" method=\"post\">"
                      + "<label>Electiom Name:</label><br><input type=\"text\" name=\"ename\" value='"+inpStrings[0]+"' /></br>"
                      + "<label>Voting Date:</label><br><input type=\"date\" name=\"vdate\" value='"+inpStrings[1]+"'/></br>"
                      + "<label>Write Your Manifesto here:</label></br><textarea  name=\"manifesto\"> </textarea></br>"
                    +"<input type=\"submit\" /></br>"
                      + "</form>");
//                Connection con = null;
//                String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
//                String username = "universityDB0035"; //MySQL username
//                String password = "Niteesh@123"; //MySQL password
//                
//                Class.forName("com.mysql.jdbc.Driver");
//                con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
//                response.setContentType("text/html");
//                
                
//                if(queryedit!=null && queryedit.equals("true")) {
//                    
//                    int err=0;
//                    String name = request.getParameter("ename");
//                    String vdate = request.getParameter("vdate");
//                    String contact_no = request.getParameter("contact_no");
//                    String time= request.getParameter("time");
//                    
//                    response.setContentType("text/html");
//                    System.out.println("yuiEP");
//                    if(name.strip().length()==0 || vdate=="" ||time=="") {
//                        out.println(
//                                 "<h1 align = \"center\">Provide all details</h1>\n");
//                        err=1;
//                    }
//                    else if(contact_no.length()!=10) {
//                        out.println(
//                                 "<h1 align = \"center\">Invalid Phone Number</h1>\n");
//                        err=1;
//                    }
//                    System.out.println("ertEEP");
//                    if(err==1) {
//                        doGet(request, response);
//                    }else {
//                    
//                    PreparedStatement stcheck = con.prepareStatement("update elections set ename=?,vdate=?,time=?,contact_no=? where ename=? and vdate=?;");
//                        stcheck.setString(1,name);
//                        stcheck.setString(2,vdate);
//                        stcheck.setString(3,time);
//                        stcheck.setString(4,contact_no);
//                        stcheck.setString(5,inpStrings[0]);
//                        stcheck.setString(6,inpStrings[1]);
//                        System.out.println("WEEP");
//                        int rslt=stcheck.executeUpdate();
//                        if(rslt>0) {
//                            ses.setAttribute("name", name);
//                            System.out.println("EEP");
//                            response.sendRedirect("ActiveElections");
////                          RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
////                          rd.forward(request, response);  
//                        }
//                        else {
//                            out.println(
//                                     "<h1 align = \"center\">Error: Unable to Update your Details </h1>\n");
//                            System.out.println("DONEDOEN");
//                            doGet(request, response);
////                          RequestDispatcher rd = request.getRequestDispatcher("MoreInfo");
////                          rd.include(request, response);
//                        }
//                    }
//                }
//                
//                else if(queryclose!=null && queryclose.equals("true")) {
//                    PreparedStatement stcheck = con .prepareStatement("update elections set status ='2' where ename=? and vdate=?;");   
//                    stcheck.setString(1, inpStrings[0]);
//                    stcheck.setString(2, inpStrings[1]);
//                    int  rline=stcheck.executeUpdate();
//                    if(rline==1) {
//                        
//                    }
//                    else {
//                        out.println("Error in Closing Election");
//                    }
//                    doGet(request, response);
//                }
//                else {
//                    PreparedStatement stcheck = con .prepareStatement("select * from elections where ename=? and vdate=?;");
//                    stcheck.setString(1, inpStrings[0]);
//                    stcheck.setString(2, inpStrings[1]);
//                    ResultSet rs=stcheck.executeQuery();
//                    if(rs.next()) {
//                     String docType =
//                             "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
//                          out.println(docType +"<style>" + 
//                                "form{" + 
//                                "padding:1rem;" + 
//                                "margin:1rem;" + 
//                                "}" + 
//                                "input{" + 
//                                "padding:1rem;" + 
//                                "margin:1rem;" + 
//                                "}" + 
//                                "</style>"+
//                             "<h2 align = \"center\">Election Details</h2>\n"
//                             + "<form action=\"ActiveElections?edit=true&id="+rs.getString("ename")+","+rs.getString("vdate")+"\" method=\"post\">"
//                             + "Electiom Name:<input type=\"text\" name=\"ename\" value='"+rs.getString("ename")+"'/></br>"
//                             + "Voting Date:<input type=\"date\" name=\"vdate\" value='"+rs.getString("vdate")+"'/></br>"
//                            + "Time:<input type=\"time\" name=\"time\" value='"+rs.getString("time")+"'/></br>"
//                             + "Contact No.<input type=\"number\" name=\"contact_no\" value='"+rs.getString("contact_no")+"' /></br>"
//                           +"<input type=\"submit\" /></br>"
//                             + "</form>");
//                    }
//        System.out.println("Printing connection object "+con);
        
      
//         
//        out.println(docType +
//                 "<table border='1'>");
//        out.println(
//                "<tr>"
//                 + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
//              );
//        while(rs.next()) {
//                 
//            out.println(
//                    "<tr>"
//                     + "<td><label>"+rs.getString("ename")+"</label></td>"
//                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
//                     + "<td><label>"+rs.getString("time")+"</label></td>"
//                     + "<td><label>"+rs.getString("contact_no")+"</label></td>"
//                     + "<td><form action='ActiveElections' method='post'><input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'><input type='submit' value='Edit'/><input type='submit' value='close'/></form></td>"
//                             + "</tr>" 
//                  );
//               
//            }
//        out.println("</table></body></html>");
    
    //}
	    }
         catch (Exception e) 
        {
            e.printStackTrace();
        }
        
//		doGet(request, response);
	}

}
