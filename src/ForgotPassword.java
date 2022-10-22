

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
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
		         + "<form action=\"ForgotPassword\" method=\"post\">"
		         + "Id:<input type=\"text\" name=\"name\"/></br>"
		       +"<input type=\"submit\" value=\"Send Verification Mail\" /></br>"
		         + "</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String docType =
		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">";
		out.println(docType +"<h1>Mail Sent</h1></br>"
				+ "<button onclick=\"window.location=\'home.jsp\'\">Home</button>"
				+ "");
//		doGet(request, response);
	}

}
