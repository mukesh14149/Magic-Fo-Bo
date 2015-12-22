/*
 * @author Mukesh Gupta
 */
package com.project.fb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class news_post
 */
@WebServlet("/news_post")
public class news_post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public news_post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Category that User Selected.
		String name=request.getParameter("param1");
		
		PrintWriter out=response.getWriter();
		
		//Change News channel according to need.
		
		//News type Sports, Entertainment, Politics of India
		String url1="http://timesofindia.indiatimes.com/sports";
		String url2="http://timesofindia.indiatimes.com/entertainment";
		String url3="http://timesofindia.indiatimes.com/india";
		
		//Fetch Data from Webcrawling Class(crawl on Given URL to this class to Fetch their content)
		webcrawling web=new webcrawling();
		
		//Hash map of Post.
		HashMap<String, String> post=null;
			
			//if User_Selected_Category is sports
			if(name.equals("sports")){	
				//Crawl at particuler content
				post=web.webcrawl(url1);}
			
			//if User_Selected_Category is Entertainment
			if(name.equals("entertainment")){
				//Crawl at particuler content
				post=web.webcrawl(url2);}
			
			//if User_Selected_Category is Politics of India
			if(name.equals("politics")){	
				//Crawl at particuler content
				post=web.webcrawl(url3);}
		
		//Session to save a hashmap of post.
		HttpSession session=request.getSession();

		//Create Hashtag of particular type of post.
		for (String key: post.keySet()){
            String hash =key.toString();
		  	String value=post.get(key);
		  	out.println(hash+value);		}

		session.setAttribute("post", post);
		
		//Send Forward 
		response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id01");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
