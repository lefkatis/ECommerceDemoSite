

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IWebTWQuery
 */
//@WebServlet("/LZServlet")
public class LZServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	*	The doGet method is called when the user queries the database
	*	this method gets the inputs from the form and uses them to connect
	*	to the database and fetch the results
	*	@param request 	The HttpServlet request
	*	@param response The HttpServlet response
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		List<String> credentials = new ArrayList<String>();
		credentials.add("stusql.dcs.shef.ac.uk");
		credentials.add("team106");
		credentials.add("4758a80b");
		credentials.add("team106");        
        */
        try {
			//DBFetcher assignment = new DBFetcher(credentials);
			//Connection currentCon = assignment.getCurrentConnection();
			
			String printer = null;
			request.setAttribute("results", printer);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
			
		} catch (Exception e) {
			String result = "Please check credentials and try again.";
			request.setAttribute("results", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
	}

	/**
	*	The doPost method is called when the user queries the database
	*	this method gets the inputs from the form and uses them to connect
	*	to the database and interact with Twitter.com and fetch the results
	*	@param request 	The HttpServlet request
	*	@param response The HttpServlet response
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		List<String> credentials = new ArrayList<String>();
		credentials.add("stusql.dcs.shef.ac.uk");
		credentials.add("team106");
		credentials.add("4758a80b");
		credentials.add("team106"); 
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String name= request.getParameter("name");
		String surname = request.getParameter("surname");
		
        try {
        	
        	DBFetcher sampleFetcher = new DBFetcher(credentials);
    		Connection sampleConnection = sampleFetcher.getCurrentConnection();
    		if (request.getParameter("login") != null){
    			List<String> userResult  = sampleFetcher.validateUser(sampleConnection, username, password);
        		request.setAttribute("results", userResult);
        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
    			dispatcher.forward(request,response);
    		} else if(request.getParameter("register") != null){
    			List<String> registerDetails  = sampleFetcher.register(sampleConnection, username, password, name, surname);
    			request.setAttribute("results", registerDetails);
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
    			dispatcher.forward(request,response);
    		}		
		} catch (Exception e) {
			String result = "Please check credentials and try again." + " username: "+ username + "password: "+ password;
			e.printStackTrace();
			request.setAttribute("results", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
        
	} 

}
