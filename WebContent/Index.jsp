<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Magic FO-BO</title>
<title></title>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<style type="text/css">
		.w3-row {height: 470px}
    
		/* Set a 100% height to left and right col */
		.w3-col.m2, w3-col.m2 {height: 100%;}
		    
		/* On small screens, set grid height to 'auto' */
		@media screen and (max-width: 601px) {
		  .w3-row {height:auto;}
		  .w3-padding{padding: auto;}
		 }
	</style>
</head>
<body>
<%@ page 
import="java.util.HashMap"
import="javax.servlet.ServletException"
import="javax.servlet.annotation.WebServlet"
import="javax.servlet.http.HttpServlet"
import="javax.servlet.http.HttpServletRequest"
import="javax.servlet.http.HttpServletResponse"
import="javax.servlet.http.HttpSession"
import="java.io.BufferedWriter"
import="java.io.ByteArrayOutputStream"
import="java.io.FileWriter"
import="java.io.FileReader"
import="java.io.BufferedReader"
import="java.io.IOException"
import="java.io.InputStream"
import="java.net.URL"
import="java.net.URLEncoder"
import="java.util.Date"
import="java.util.HashSet"
import="java.util.List"
import="java.util.Set"
import="java.util.Random"
import="org.apache.tomcat.jni.File"

import="com.restfb.Connection"
import="com.restfb.DefaultFacebookClient"
import="com.restfb.FacebookClient"
import="com.restfb.Parameter"
import="com.restfb.types.Album"
import="com.restfb.types.NamedFacebookType"
import="com.restfb.types.Photo"
import="com.restfb.types.Post"
import="com.restfb.types.User"

%>
<%
HttpSession session1=request.getSession();
HashMap<String,String> post=null; 

%>
<% 	
 		
		HashMap<String,Integer> timeline_friends = (HashMap<String,Integer>)session1.getAttribute("timeline_friends");	
 		HashMap<String,Integer> shared_friends = (HashMap<String,Integer>)session1.getAttribute("shared_friends");	
 		HashMap<String,Integer> status_friends = (HashMap<String,Integer>)session1.getAttribute("status_friends");	
 		HashMap<String,Integer> special_friends = (HashMap<String,Integer>)session1.getAttribute("special_friends");	
 		 HashMap<String,String> name_id=(HashMap<String,String>)session1.getAttribute("name_id");	
 		
 		List<Post> posts=(List<Post>)session1.getAttribute("posts");
 		User loginUser=(User)session1.getAttribute("loginUser");
 		int counter=0,counter1=0,counter2=0;
 		int status_avg=0,timeline_avg=0,shared_avg=0;
 		
 		try{
 	 		counter=(Integer)session1.getAttribute("counter");
 	 		counter1=(Integer)session1.getAttribute("counter1");
 	 		 counter2=(Integer)session1.getAttribute("counter2");
 	 		
		 		status_avg=(Integer)session.getAttribute("status_avg");
		 		timeline_avg=(Integer)session.getAttribute("timeline_avg");
		 		shared_avg=(Integer)session.getAttribute("shared_avg");
 		}catch(Exception e){}
 		Random rand = new Random();
 		 		int i=-1;
 		
 		try{
	
%>


		
		
		<div class="w3-topnav w3-teal w3-padding-8  w3-center w3-card-16 ">
				<p class="w3-xlarge">Magic Fo-Bo</p>
  			
		</div>
		
		
		<div class="w3-center">
			<div class="w3-row">
					<div class="w3-col m2 w3-light-grey w3-padding-64" >
					      <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxlarge" ><button onclick="document.getElementById('id02').style.display='block',
					      document.getElementById('id03').style.display='none'">Post</button></div>
					 
					      <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxlarge" ><button onclick="document.getElementById('id03').style.display='block',
					      document.getElementById('id02').style.display='none'">Predicter</button></div>
					      
    				</div>
    				<div id="id02" class="w3-col m8 ">
							<h1>Choose Post Type</h1>
							 <div class="w3-dropdown-hover w3-padding-64" >
								  <button class="w3-btn w3-red " style="width:10.5em">Category</button>
								  
								  <div class="w3-dropdown-content w3-border "  >
								    <a  href="news_post?param1=sports" 
				class="w3-btn" >Sports</a>
								    <a href="news_post?param1=politics" 
				class="w3-btn" >Politics</a>
								    <a href="news_post?param1=entertainment" 
				class="w3-btn" >Entertainment</a>
				  				  </div>
							</div>
					</div>

					<div id="id03" class="w3-col m8 " style="display:none;">
							<h1>Predict your post's like</h1>
						<div class="w3-padding-64">	
							
								  <h2 class="w3-text-blue">Access Code</h2>
								  <br>
								  <form action="Facebook_predictor">
								  <div class="w3-input-group">      
								    <label class="w3-label w3-text-blue w3-validate"><b>Enter your access Code</b></label>
								    <input class="w3-input w3-border" name="first" type="text">
								  </div>
									
								  <button class="w3-btn w3-blue" >Register</button>
								  </form>
							
						</div>

					</div>

					



					<div class="w3-col m2 w3-light-grey">
					      <p><a href="http://mukeshgupta.me" class="w3-btn">Developer</a></p>
					     
    				</div>

			</div>
		</div>
		
		
							 
		</div>	
		<div id="id01" class="w3-modal" style="height:50em;">
			<%
			
			post=(HashMap<String,String>)session1.getAttribute("post");%>
			  <div  class="w3-modal-content w3-animate-top" style="overflow:scroll; height:30em;">
			    <div class="w3-container w3-center w3-btn-group" >
			      <span onclick="document.getElementById('id01').style.display='none'"
			      class="w3-closebtn">&times;</span>
			      
			      <h1>Trending Post</h1>



			      	<table class="w3-table w3-striped w3-bordered w3-card-4">
						<thead>
						<tr class="w3-blue">
						  <th >First Name</th>
						  <th >Points</th>
						</tr>
						</thead>
						<%try{for (String key: post.keySet()){
				            String hash =key.toString();
						  	String value=post.get(key); 
						  	String hv=hash+"\n"+value;
						  	%>
						<tr>
						  <td ><%=hash%><br><%=value %></td>
						  
						  	<td > <a href="<c:url value="Facebook_predictor1"><c:param name="posting" value="<%=hv%>"/></c:url>"
				class="w3-btn" ><button class="w3-btn">Submit</button></a></td>
						 
						</tr>
						
						<%}}catch(Exception e){} %>
						
						  
						</table>     
			            
			      
			    </div>
			  </div>
		</div>
		
		<div id="id04" class="w3-modal" style="height:50em;">
			  <div  class="w3-modal-content w3-animate-top" style="overflow:scroll; height:30em;">
			    <div class="w3-container w3-center w3-btn-group" >
			      <span onclick="document.getElementById('id04').style.display='none'"
			      class="w3-closebtn">&times;</span>
			      
			      <h1>Trending Post</h1>
			      	<table class="w3-table w3-striped w3-bordered w3-card-4">
						<tr class="w3-blue">
						  <th >First Name</th>
						  <th >Points</th>
						</tr>
						</thead>
						<tr>
						  <td >
						  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button ><img src='https://graph.facebook.com/<c:out value="${loginUser.id}"/>/picture' /></button></div>

						  </td>
						  <td ><p>Name:<c:out value="${loginUser.id}" /></p>
						  		<p>ID:<c:out value="${loginUser.name}" /></p>
						  </td>
						</tr>
					</table>  
					<br>

			      	<table class="w3-table w3-striped w3-bordered w3-card-4">
						<tr class="w3-blue">
						  <th >First Name</th>
						  <th >Points</th>
						</tr>
						</thead>
						
						<c:forEach begin="0" end="5" var="loop">
							<tr>
							  <td >
							  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button ><img src="<%=posts.get(i+1).getPicture()%>"/></button></div>
	
							  </td>
							  <td ><p>Name:<c:out value="${posts.get(loop).getName()}"/></p>
							  <% 		
									i++;
									if(i==5)
										break;
									if(posts.get(i).getType().equals("status")){
										System.out.println("yo1");
								%>		
							  		
							  		<p>Total likes:<%=status_avg+rand.nextInt(2)%></p>
							  		
							  		
							  	<%}else if(posts.get(i).getType().equals("photo")||posts.get(i).getType().equals("link")||posts.get(i).getType().equals("video")){
							  		System.out.println("yo2");
							  		try{
											 %>	
							  		<p>Total likes:<%=timeline_avg+rand.nextInt(2) %></p>
							  		
							  		<%}catch(Exception e){System.out.println("yo2adsf");}}else{ 
							  			System.out.println("yo2");
							  		%>
							  		<p>Total likes:<%=shared_avg+rand.nextInt(2)%></p>
							  		
							  		<%} %>
							  		<button onclick="document.getElementById('id05').style.display='block'">click</button>
							  </td>
							</tr>
						</c:forEach>
						</table>     
			            
			      
			    </div>
			  </div>
		</div>

		<div id="id05" class="w3-modal" style="height:50em;">
			  <div  class="w3-modal-content w3-animate-top" style="overflow:scroll; height:30em;">
			    <div class="w3-container w3-center w3-btn-group" >
			      <span onclick="document.getElementById('id05').style.display='none'"
			      class="w3-closebtn">&times;</span>
			      
			      <h1>Trending Post</h1>
			       

			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" >
			     
			       <%
			       if(posts.get(1).getType().equals("status")){ 
			    	  try{
			    	   for (String name: status_friends.keySet()){
			    		   String key =name.toString();
			    		   float value=0,a=0;
			    		   int k;
			               try{
			            	   value = status_friends.get(name);  
				               a=(value/counter)*100;
			               	k=special_friends.get(name);
			               }catch(Exception e){
			               	k=0;
			               }
			               
			               String image=name_id.get(name);
			               if(a>=10.0 ||k==5){
			            	   if(k==5) {
			    	%> 
			       	<button class="w3-red" title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>
			     	<%}else{ %>
			       			<button title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>	
			       
			     <%}}}}catch(Exception e){}}%>
			      <%
			       if(posts.get(i).getType().equals("photo")||posts.get(i).getType().equals("link")||posts.get(i).getType().equals("video")){ 
			    	   try{
			    		   for (String name: timeline_friends.keySet()){
			    			   String key =name.toString();
			    			   float value=0,a=0;
					            int k;
					            try{
					            	timeline_friends.get(name);  
						            a=(value/counter1)*100;
					            	k=special_friends.get(name);
					            
					            }catch(Exception e){
					            	k=0;
					            }
					             
					            String image=name_id.get(name);
			               if(a>=10.0 ||k==5){
			            	   if(k==5) {
			    	%> 
			       	<button class="w3-red" title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>
			     	<%}else{ %>
			       			<button title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>	
			       
			     <%}}}}catch(Exception e){}} else{
			    	 try{
			    	 for (String name: shared_friends.keySet()){
				            String key =name.toString();
				            int k;
				            try{
				            	k=special_friends.get(name);
				            }catch(Exception e){
				            	k=0;
				            }
				            float value=0,a=0;
				            try{
				            	value = shared_friends.get(name);  
				            	a=(value/counter2)*100;
				            }catch(Exception e){
				            	
				            }
				            String image=name_id.get(name);
				            if(a>=10.0 ||k==5){
			     				if(k==5) {
			    	%> 
			       	<button class="w3-red" title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>
			     	<%}else{ %>
			       			<button title="<%=key%>"><img src='https://graph.facebook.com/<%=image %>/picture' /></button>	
			      
			      <%}}}}catch(Exception e){}} %>
			     
			     
			      
			       </div>
			      
			      	
			      
			    </div>
			  </div>
		</div>
		<div id="id06" class="w3-modal" style="height:50em;">
			  <div  class="w3-modal-content w3-animate-top" style="overflow:scroll; height:30em;">
			    <div class="w3-container w3-center w3-btn-group" >
			      <span onclick="document.getElementById('id06').style.display='none'"
			      class="w3-closebtn">&times;</span>
			      
			      <h1>Trending Post</h1>
			       

			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" >
			       				 <form action="Facebook_predictor1">
								  <div class="w3-input-group">      
								    <label class="w3-label w3-text-blue w3-validate"><b>Enter your acess Code</b></label>
								    <input class="w3-input w3-border" name="first" type="text">
								  </div>
									
								  <button class="w3-btn w3-blue" >Register</button>
								  </form>
			       </div>
			   </div>
			 </div>
		</div>
		       
		
		<footer class="w3-container w3-dark-grey   w3-card-8 ">
				 <p class="copyright text-muted">Copyright &copy; Mukesh Gupta 2014</p>
  			
		
		</footer>
		<%}catch(Exception e){e.printStackTrace();} %>
</body>
</html>