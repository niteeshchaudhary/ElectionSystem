

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Time;

/**
 * Servlet implementation class ApplyElections
 */
@WebServlet("/ApplyElections")
public class ApplyElections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyElections() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    
		    PrintWriter out = response.getWriter();
            HttpSession ses = request.getSession(); 
            
            
            String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
            String username = "universityDB0035"; //MySQL username
            String password = "Niteesh@123"; //MySQL password
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            response.setContentType("text/html");
            String query = request.getParameter("withdraw");
            if(query!=null && query.equals("true")) {
                String []inpStrings = request.getParameter("id").split(",");
                PreparedStatement stcheck = con.prepareStatement("delete from candidates where ename=? and vdate=? and vdate>curdate() and id=?;");
                stcheck.setString(1,inpStrings[0]);
                stcheck.setString(2,inpStrings[1]);
                stcheck.setString(3,(String)ses.getAttribute("userid"));
                int rs =stcheck.executeUpdate();
                if(rs>0) {
                    response.sendRedirect("UpcomingElections");
                }
                else {
                    out.println(
                            "<h1 align = \"center\">Error: Unable to Withdraw</h1>\n");
                   System.out.println("DONEDOEN");
                 RequestDispatcher rd = request.getRequestDispatcher("UpcomingElections");
                 rd.include(request, response);
                }
            }
            else {
                int err=0;
                String name = request.getParameter("ename");
                String vdate = request.getParameter("vdate");
                String manifesto = request.getParameter("manifesto");
                
                response.setContentType("text/html");
                System.out.println(name);
                System.out.println(vdate);
                System.out.println(manifesto);
                
                if(name==null ||name.strip().length()==0 || vdate=="") {
                    out.println(
                             "<h1 align = \"center\">Provide all details</h1>\n");
                    err=1;
                    return;
                }
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = sdformat.parse(vdate);
                Date d2 = new Date();
                System.out.println("The date 1 is: " + sdformat.format(d1));
                System.out.println("The date 2 is: " + sdformat.format(d2));
                if(d1.compareTo(d2) <= 0) {
                    out.println(
                            "<h1 align = \"center\">This Election don't Accepts Candidates</h1>\n");
                } 
                
                else if(manifesto.length()<50) {
                    out.println(
                             "<h1 align = \"center\">Write atlest 50 characters</h1>\n");
                    err=1;
                }
                if(err==1) {
                    RequestDispatcher rd = request.getRequestDispatcher("UpcomingElections");
                    rd.include(request, response);
                    return;
                }else {
                    PreparedStatement stcheck = con.prepareStatement("select * from elections where ename=? and vdate=?");
                    stcheck.setString(1,name);
                    stcheck.setString(2,vdate);
                    ResultSet doexist=stcheck.executeQuery();
                    if(!doexist.next()) {
                        out.println(
                                "<h1 align = \"center\">Not valid Election or voting date</h1>\n");
                        RequestDispatcher rd = request.getRequestDispatcher("UpcomingElections");
                        rd.include(request, response);
                        return;
                    }
                    stcheck = con.prepareStatement("insert into candidates values(?,?,?,?,?)");
                    stcheck.setString(1,name);
                    stcheck.setString(2,vdate);
                    stcheck.setString(3,(String)ses.getAttribute("userid"));
                    stcheck.setString(4,manifesto);
                    stcheck.setInt(5, 0);

                    int rslt=stcheck.executeUpdate();
                    
                    if(rslt>0) {
                        response.sendRedirect("UpcomingElections");
                    }
                    else {
                        out.println(
                                 "<h1 align = \"center\">Error: Unable to Update your Details </h1>\n");
                        System.out.println("DONEDOEN");
                      RequestDispatcher rd = request.getRequestDispatcher("UpcomingElections");
                      rd.include(request, response);
                    }
                }
            }
//            
//            else if(queryclose!=null && queryclose.equals("true")) {
//                PreparedStatement stcheck = con .prepareStatement("update elections set status ='2' where ename=? and vdate=?;");   
//                stcheck.setString(1, inpStrings[0]);
//                stcheck.setString(2, inpStrings[1]);
//                int  rline=stcheck.executeUpdate();
//                if(rline==1) {
//                    
//                }
//                else {
//                    out.println("Error in Closing Election");
//                }
//                doGet(request, response);
//            }
//            else {
//                PreparedStatement stcheck = con .prepareStatement("select * from elections where ename=? and vdate=?;");
//                stcheck.setString(1, inpStrings[0]);
//                stcheck.setString(2, inpStrings[1]);
//                ResultSet rs=stcheck.executeQuery();
//                if(rs.next()) {
//                 String docType =
//                         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
//                      out.println(docType +"<style>" + 
//                            "form{" + 
//                            "padding:1rem;" + 
//                            "margin:1rem;" + 
//                            "}" + 
//                            "input{" + 
//                            "padding:1rem;" + 
//                            "margin:1rem;" + 
//                            "}" + 
//                            "</style>"+
//                         "<h2 align = \"center\">Election Details</h2>\n"
//                         + "<form action=\"ActiveElections?edit=true&id="+rs.getString("ename")+","+rs.getString("vdate")+"\" method=\"post\">"
//                         + "Electiom Name:<input type=\"text\" name=\"ename\" value='"+rs.getString("ename")+"'/></br>"
//                         + "Voting Date:<input type=\"date\" name=\"vdate\" value='"+rs.getString("vdate")+"'/></br>"
//                        + "Time:<input type=\"time\" name=\"time\" value='"+rs.getString("time")+"'/></br>"
//                         + "Contact No.<input type=\"number\" name=\"contact_no\" value='"+rs.getString("contact_no")+"' /></br>"
//                       +"<input type=\"submit\" /></br>"
//                         + "</form>");
//                }
//    System.out.println("Printing connection object "+con);
    
  
//     
//    out.println(docType +
//             "<table border='1'>");
//    out.println(
//            "<tr>"
//             + "<th>Election</th><th>Voting Date</th><th>Time</th><th>Contact Number</th><th>Action</th></tr>"
//          );
//    while(rs.next()) {
//             
//        out.println(
//                "<tr>"
//                 + "<td><label>"+rs.getString("ename")+"</label></td>"
//                 + "<td><label>"+rs.getString("vdate")+"</label></td>"
//                 + "<td><label>"+rs.getString("time")+"</label></td>"
//                 + "<td><label>"+rs.getString("contact_no")+"</label></td>"
//                 + "<td><form action='ActiveElections' method='post'><input type='text' value='"+rs.getString("ename")+","+rs.getString("vdate")+"' name='id' style='display:none'><input type='submit' value='Edit'/><input type='submit' value='close'/></form></td>"
//                         + "</tr>" 
//              );
//           
//        }
//    out.println("</table></body></html>");

//}
    }
     catch (Exception e) 
    {
        e.printStackTrace();
        PrintWriter out = response.getWriter();
        out.println(
                "<h1 align = \"center\">Session: Expired </h1>\n");
    }
		//doGet(request, response);
	}

}
