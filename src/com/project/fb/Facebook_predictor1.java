package com.project.fb;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.File;


import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Album;
import com.restfb.types.FacebookType;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Photo;
import com.restfb.types.Post;
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
		String accesscode=request.getParameter("accesscode");
		
		String posting1=request.getParameter("posting");
		String finalpost=(String) Session.getAttribute("posting1");
		//System.out.println(posting1);
		//System.out.println(finalpost);
		String finallaccesscode=null;
		accesscode=request.getParameter("first");
		//System.out.println(accesscode);
		if(accesscode==null){
			finallaccesscode=(String) Session.getAttribute("accesscode");
			System.out.println(finallaccesscode+"ye null ha kya");
if(finallaccesscode!=null){
				
				PrintWriter out=response.getWriter();	
				final FacebookClient facebookClient = new DefaultFacebookClient(accesscode);
				User loginUser = facebookClient.fetchObject("me", User.class,Parameter.with("fields","first_name,last_name,name,email,website"));
				FacebookType message=facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message",finalpost));
				response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id01");
			}

			Session.setAttribute("posting1", posting1);
		//	System.out.println(posting1);
			
			response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id06");
			
		}
		else{
			
			Session.setAttribute("accesscode", accesscode);
			PrintWriter out=response.getWriter();	
			final FacebookClient facebookClient = new DefaultFacebookClient(accesscode);
			User loginUser = facebookClient.fetchObject("me", User.class,Parameter.with("fields","first_name,last_name,name,email,website"));
			FacebookType message=facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message",finalpost));
			response.sendRedirect("http://localhost:8080/News_Fb/Index.jsp#id01");
		}
		//
		
		//fetch user  i.e a client  data who logged in with given fields. 
		//User loginUser = facebookClient.fetchObject("me", User.class,Parameter.with("fields","first_name,last_name,name,email,website"));
		
		//we need to connect to access another edge
		//Connection<Post> feed=facebookClient.fetchConnection("me/posts", Post.class, Parameter.with("fields","name,story,id,full_picture,picture,likes.limit(1000){name,pic_large},type,created_time,link"),Parameter.with("limit", 300));
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private String Extract_access_token(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
	
	private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
}