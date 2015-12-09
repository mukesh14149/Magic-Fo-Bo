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
		String name=request.getParameter("param1");
		PrintWriter out=response.getWriter();
		String url1="http://timesofindia.indiatimes.com/sports";
		String url2="http://timesofindia.indiatimes.com/entertainment";
		String url3="http://timesofindia.indiatimes.com/india";
		
			webcrawling web=new webcrawling();
			HashMap<String, String> post=null;
			
			if(name.equals("sports")){	
				post=web.webcrawl(url1);}
			
			if(name.equals("entertainment")){	
				post=web.webcrawl(url2);}
			
			if(name.equals("politics")){	
				post=web.webcrawl(url3);}
		
		HttpSession session=request.getSession();

		for (String key: post.keySet()){
            String hash =key.toString();
		  	String value=post.get(key);
		
		  out.println(hash+value);		}

		session.setAttribute("post", post);
		response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id01");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
