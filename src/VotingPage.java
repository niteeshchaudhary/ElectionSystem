

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

/**
 * Servlet implementation class VotingPage
 */
@WebServlet("/VotingPage")
public class VotingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotingPage() {
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
        String qry="select ca.* from elections as el,candidates as ca where el.vdate=ca.vdate and el.ename=ca.ename and el.vdate=curdate() and status=\"3\";";
        PreparedStatement stcheck = con .prepareStatement(qry);
        ResultSet usercandidate=stcheck.executeQuery();
        
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
                 + "<th>Election</th><th>Voting Date</th><th>Candidate</th><th>Menifesto</th><th>Action</th></tr>"
              );

        while(usercandidate.next()) {
            System.out.println(usercandidate.getString("ename"));
            out.println(
                    "<tr>"
                     + "<td><label>"+usercandidate.getString("ename")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("vdate")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("Id")+"</label></td>"
                     + "<td><label>"+usercandidate.getString("menifesto")+"</label></td>"
                     + "<td><form action='VotingPage' method='post'>"
                     + "<input type='text' value='"+usercandidate.getString("ename")+","+usercandidate.getString("vdate")+","+usercandidate.getString("Id")+"' name='id' style='display:none'/>"
                             + "<input type='submit'  value='+1'/>"
                             + "</form></td>"
                             + "</tr>" 
                  );
               
            }
        usercandidate.close();
       
        out.println("</table>"
                + "<button onclick=\"window.location='LiveElections'\">Back</button>"
                + "</body></html>");
    
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
	    try {
            
            PrintWriter out = response.getWriter();
            HttpSession ses = request.getSession(); 
            String userid=(String)ses.getAttribute("userid");            
            
            String url = "jdbc:mysql://localhost:3306/elections"; //MySQL URL and followed by the database name
            String username = "universityDB0035"; //MySQL username
            String password = "Niteesh@123"; //MySQL password
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            response.setContentType("text/html");
            
                int err=0;
                
                String []inpStrings = request.getParameter("id").split(",");
                

                response.setContentType("text/html");

                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = sdformat.parse(inpStrings[1]);
                Date d2 = new Date();
                System.out.println("The date 1 is: " + sdformat.format(d1));
                System.out.println("The date 2 is: " + sdformat.format(d2));
                if(sdformat.format(d1).compareTo(sdformat.format(d2)) != 0) {
                    out.println(
                            "<h1 align = \"center\">This Election don't Accepts Votes</h1>\n");
                } 
                
            
                if(err==1) {
                    RequestDispatcher rd = request.getRequestDispatcher("LiveElections");
                    rd.include(request, response);
                    return;
                }else {
                    PreparedStatement stcheck = con.prepareStatement("select * from voting_history where ename=? and vdate=? and  id=? and vdate=curdate()");
                    stcheck.setString(1,inpStrings[0]);
                    stcheck.setString(2,inpStrings[1]);
                    stcheck.setString(3,userid);
                    ResultSet doexist=stcheck.executeQuery();
                    if(doexist.next()) {
                        
                        out.println(
                                "<h1 align = \"center\">Your Vote already counted</h1>\n");
                        RequestDispatcher rd = request.getRequestDispatcher("LiveElections");
                        rd.include(request, response);
                        return;
                    }
                    
                    stcheck = con.prepareStatement("insert into voting_history values(?,?,?,true)");
                    stcheck.setString(1,inpStrings[0]);
                    stcheck.setString(2,inpStrings[1]);
                    stcheck.setString(3,userid);
                    int insresult=stcheck.executeUpdate();
                    if(insresult<1) {
                        out.println(
                                "<h1 align = \"center\">Voting error</h1>\n");
                        RequestDispatcher rd = request.getRequestDispatcher("LiveElections");
                        rd.include(request, response);
                        return;
                    }
                    
                    stcheck = con.prepareStatement("update candidates set votes=votes+1 where  ename=? and vdate=? and  id=? ");
                    stcheck.setString(1,inpStrings[0]);
                    stcheck.setString(2,inpStrings[1]);
                    stcheck.setString(3,inpStrings[2]);
                    int updresult=stcheck.executeUpdate();
                    if(updresult<1) {
                        out.println(
                                "<h1 align = \"center\">Vote Casting error</h1>\n");
                        RequestDispatcher rd = request.getRequestDispatcher("LiveElections");
                        rd.include(request, response);
                        return;
                    }
                    
                  
                   
                        out.println(
                                 "<h1 align = \"center\">Congratulations!: You Voted Successfully </h1>\n");
                        System.out.println("DONEDOEN");
                      RequestDispatcher rd = request.getRequestDispatcher("LiveElections");
                      rd.include(request, response);
                    
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
