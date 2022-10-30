

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActiveElections
 */
@WebServlet("/ActiveElections")
public class ActiveElections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveElections() {
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
        PreparedStatement stcheck = con .prepareStatement("select * from elections where status='3';");
        ResultSet rs1=stcheck.executeQuery();
        
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
         "</style></head<body>";
         out.println(docType +
                 "<h2>On Going Elections</h2>"+
                 "<table border='1'>"+
                "<tr>"
                 + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
              );
        while(rs1.next()) {
           
            out.println(
                    "<tr>"
                     + "<td><label>"+rs1.getString("ename")+"</label></td>"
                     + "<td><label>"+rs1.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs1.getString("time")+"</label></td>"
                     + "<td><label>"+rs1.getString("contact_no")+"</label></td>"
                     + "<td><form action='ActiveElections' method='post'>"
                     + "<input type='text' value='"+rs1.getString("ename")+","+rs1.getString("vdate")+"' name='id' style='display:none'/>"
                             + "<input type='submit' formaction=\"ActiveElections?close=true\" value='close'/>"
                             + "</form></td>"
                             + "</tr>" 
                  );
               
            }

        out.println("</table><h2>Upcoming Elections</h2>"+
                 "<table border='1'>"+
                "<tr>"
                 + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
              );
        rs1.close();
        stcheck = con .prepareStatement("select * from elections where status='1';");
        ResultSet rs=stcheck.executeQuery();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date d2 = new Date();
        System.out.println("The date 2 is: " + sdformat.format(d2));
        while(rs.next()) {
            Date d1 = sdformat.parse(rs.getString("vdate"));
            System.out.print("The date 1 is: " + sdformat.format(d1));
            System.out.println(" * " +" "+d2.toString());
            
            String oth= "<input type='submit' formaction=\"ActiveElections\" value='Edit'/>";
            if(sdformat.format(d1).equals(sdformat.format(d2))) {
                oth="<input type='submit' formaction=\"ActiveElections?live=true\" value='Start'/>";
            }
            
            out.println(
                    "<tr>"
                     + "<td><label>"+rs.getString("ename")+"</label></td>"
                     + "<td><label>"+rs.getString("vdate")+"</label></td>"
                     + "<td><label>"+rs.getString("time")+"</label></td>"
                     + "<td><label>"+rs.getString("contact_no")+"</label></td>"
                     + "<td><form action='ActiveElections' method='post'>"
                     + "<input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'/>"
                             +oth
                             + "<input type='submit' formaction=\"ActiveElections?delete=true\" value='delete'/>"
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
                String queryclose = request.getParameter("close");
                String querydelete = request.getParameter("delete");
                String querylive = request.getParameter("live");
                String queryedit = request.getParameter("edit");
                Connection con = null;
                String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
                String username = "universityDB0035"; //MySQL username
                String password = "Niteesh@123"; //MySQL password
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
                response.setContentType("text/html");
                String []inpStrings = request.getParameter("id").split(",");
                
                
                if(queryedit!=null && queryedit.equals("true")) {
                    
                    int err=0;
                    String name = request.getParameter("ename");
                    String vdate = request.getParameter("vdate");
                    String contact_no = request.getParameter("contact_no");
                    String time= request.getParameter("time");
                    
                    response.setContentType("text/html");
                    System.out.println("yuiEP");
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
                    System.out.println("ertEEP");
                    if(err==1) {
                        doGet(request, response);
                    }else {
                    
                    PreparedStatement stcheck = con.prepareStatement("update elections set ename=?,vdate=?,time=?,contact_no=? where ename=? and vdate=?;");
                        stcheck.setString(1,name);
                        stcheck.setString(2,vdate);
                        stcheck.setString(3,time);
                        stcheck.setString(4,contact_no);
                        stcheck.setString(5,inpStrings[0]);
                        stcheck.setString(6,inpStrings[1]);
                        System.out.println("WEEP");
                        int rslt=stcheck.executeUpdate();
                        if(rslt>0) {
                            ses.setAttribute("name", name);
                            System.out.println("EEP");
                            response.sendRedirect("ActiveElections");
//                          RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
//                          rd.forward(request, response);  
                        }
                        else {
                            out.println(
                                     "<h1 align = \"center\">Error: Unable to Update your Details </h1>\n");
                            System.out.println("DONEDOEN");
                            doGet(request, response);
//                          RequestDispatcher rd = request.getRequestDispatcher("MoreInfo");
//                          rd.include(request, response);
                        }
                    }
                }
                else if(querylive!=null && querylive.equals("true")) {
                    PreparedStatement stcheck = con .prepareStatement("update elections set status ='3' where ename=? and vdate=?;");   
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
                else if(querydelete!=null && querydelete.equals("true")) {
                    PreparedStatement stcheck = con .prepareStatement("delete from elections  where ename=? and vdate=?;");   
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
                else if(queryclose!=null && queryclose.equals("true")) {
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
                    PreparedStatement stcheck = con .prepareStatement("select * from elections where ename=? and vdate=?;");
                    stcheck.setString(1, inpStrings[0]);
                    stcheck.setString(2, inpStrings[1]);
                    ResultSet rs=stcheck.executeQuery();
                    if(rs.next()) {
                     String docType =
                             "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
                          out.println(docType +"<style>" + 
                                "form{" + 
                                "padding:1rem;" + 
                                "margin:1rem;" + 
                                "}" + 
                                "label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
                                 "input{margin:1rem;padding:1rem;width:40rem;border-radius:1rem}"+
                                "body {"+
                                "background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');"+
                                "color: #FFFFFF;"+
                                  "background-repeat: no-repeat;"+
                                  "background-size: cover;"+
                            "}"+
                                "</style>"+
                             "<h2 align = \"center\">Election Details</h2>\n"
                             + "<form action=\"ActiveElections?edit=true&id="+rs.getString("ename")+","+rs.getString("vdate")+"\" method=\"post\">"
                             + "<label>Electiom Name:</label><br><input type=\"text\" name=\"ename\" value='"+rs.getString("ename")+"'/></br>"
                             + "<label>Voting Date:</label><br><input type=\"date\" name=\"vdate\" value='"+rs.getString("vdate")+"'/></br>"
                            + "<label>Time:</label><br><input type=\"time\" name=\"time\" value='"+rs.getString("time")+"'/></br>"
                             + "<label>Contact No.</label><br><input type=\"number\" name=\"contact_no\" value='"+rs.getString("contact_no")+"' /></br>"
                           +"<input type=\"submit\" /></br>"
                             + "</form>");
                    }
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
    
    }}
         catch (Exception e) 
        {
            e.printStackTrace();
        }
        
//		doGet(request, response);
	}

}
