/*
 * @author Mukesh Gupta
 */

package com.project.fb;

//Post on User Wall.


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.File;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;


/**
 * Servlet implementation class Fetch_data
 */
@WebServlet("/Facebook_predictor1")
public class Facebook_predictor1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String APP_ID = "758937600918147";
	public static String APP_SECRET = "127770845824b36aad9f23c3f1139670";
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facebook_predictor1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession Session=request.getSession();		
		String accesscode=request.getParameter("accesscode");  //Get accesscode from Session if user uses app 2nd time.
		String posting1=request.getParameter("posting");	   //Get Category of post.
		String finalpost=(String) Session.getAttribute("posting1");
		String finallaccesscode=null;
		accesscode=request.getParameter("first");			
		
		
		if(accesscode==null){		//if access code is null then send user to a form which take access code from user.
			finallaccesscode=(String) Session.getAttribute("accesscode");
			Session.setAttribute("posting1", posting1);
			response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id06");
		}
		else{	//if access code is not null then post on their wall.
			
			Session.setAttribute("accesscode", accesscode);
			
			@SuppressWarnings("deprecation")
			final FacebookClient facebookClient = new DefaultFacebookClient(accesscode);
			
			@SuppressWarnings("unused")
			User loginUser = facebookClient.fetchObject("me", User.class,Parameter.with("fields","first_name,last_name,name,email,website"));
			
			//Publish a post on User timeline.
			@SuppressWarnings("unused")
			FacebookType message=facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message",finalpost));
			
			//Send to Same Page.
			response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id01");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}