

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
		      		"}" + "input{margin:1rem;padding:1rem;width:20rem;border-radius:1rem}"+
	                 "label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
	                 "body {"+
	                 "background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');"+
	                 "color: #FFFFFF;"+
	                   "background-repeat: no-repeat;"+
	                   "background-size: cover;"+
	             "}"+
		      		"</style>"+
		         "<h2 align = \"center\">Add Your Details here</h2>\n"
		         + "<form action=\"ForgotPassword\" method=\"post\">"
		         + "<label>Id:</label><input type=\"email\" name=\"name\"/></br>"
		       +"<input type=\"submit\" value=\"Send Verification Mail\" /></br>"
		         + "</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		System.out.println("job"+System.nanoTime()+((int)Math.random()*100));
		String docType =
		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">"+"<style>" + 
		                    "form{" + 
		                    "padding:1rem;" + 
		                    "margin:1rem;" + 
		                    "}" + "input{margin:1rem;padding:1rem;width:20rem;border-radius:1rem}"+
		                     "label{padding:1rem;margin:1rem;width:60rem;font-size:1.3rem}"+
		                     "body {"+
		                     "background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');"+
		                     "color: #FFFFFF;"+
		                       "background-repeat: no-repeat;"+
		                       "background-size: cover;"+
		                 "}"+"button{"+
		                 "padding:1rem;" + 
	                            "margin:1rem;" +
	                            "border-radius:1rem;" +
		                          "}"+
		                    "</style>";
		out.println(docType +"<h1>Mail Sent</h1></br>"
				+ "<button onclick=\"window.location=\'WebHome.jsp\'\">Home</button>"
				+ "");
//		doGet(request, response);
	}

}
