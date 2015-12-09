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


%>
<%
HttpSession session1=request.getSession();
HashMap<String,String> post=null; 

%>



		
		
		<div class="w3-topnav w3-teal w3-padding-8  w3-center w3-card-16 ">
				<p class="w3-xlarge">Magic Fo-Bo</p>
  			
		</div>
		
		
		<div class="w3-center">
			<div class="w3-row">
					<div class="w3-col m2 w3-light-grey w3-padding-64" >
					      <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxlarge" ><button onclick="document.getElementById('id02').style.display='block',
					      document.getElementById('id03').style.display='none'">Rounder</button></div>
					 
					      <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxlarge" ><button onclick="document.getElementById('id03').style.display='block',
					      document.getElementById('id02').style.display='none'">Rounder</button></div>
					      
    				</div>
    				<div id="id02" class="w3-col m8 ">
							<h1>Choose Post Type</h1>
							 <div class="w3-dropdown-hover w3-padding-64" >
								  <button class="w3-btn w3-red " style="width:10.5em">Hover Me!</button>
								  
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
							<h1>Choose Post Type1</h1>
						<div class="w3-padding-64">	
							
								  <h2 class="w3-text-blue">Access Code</h2>
								  <br>
								  <div class="w3-input-group">      
								    <label class="w3-label w3-text-blue w3-validate"><b>Enter your acess Code</b></label>
								    <input class="w3-input w3-border" name="first" type="text">
								  </div>
									
								  <button class="w3-btn w3-blue" onclick="document.getElementById('id04').style.display='block'">Register</button>
							
							
						</div>

					</div>

					



					<div class="w3-col m2 w3-light-grey">
					      <p><a href="#">Link</a></p>
					      <p><a href="#">Link</a></p>
					      <p><a href="#">Link</a></p>
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
						<tr class="w3-blue">
						  <th >First Name</th>
						  <th >Points</th>
						</tr>
						</thead>
						<%try{for (String key: post.keySet()){
				            String hash =key.toString();
						  	String value=post.get(key); %>
						<tr>
						  <td ><%=hash%><br><%=value %></td>
						  <td ><button class="w3-btn">Submit</button></td>
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
						  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button >Rounder</button></div>

						  </td>
						  <td ><p>Name:Hello world</p>
						  		<p>Total likes:Why world</p>
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
						<tr>
						  <td >
						  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button >Rounder</button></div>

						  </td>
						  <td ><p>Name:Hello world</p>
						  		<p>Total likes:Why world</p>
						  		<button onclick="document.getElementById('id05').style.display='block'">click</button>
						  </td>
						</tr>
						<tr>
						   <td >
						  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button >Rounder</button></div>

						  </td>
						  <td ><p>Name:Hello world</p>
						  		<p>Total likes:Why world</p>
						  		<button onclick="document.getElementById('id05').style.display='block'">click</button>
						  </td>
						</tr>
						<tr>
						   <td >
						  		<div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-xxxlarge" ><button >Rounder</button></div>

						  </td>
						  <td ><p>Name:Hello world</p>
						  		<p>Total likes:Why world</p>
						  		<button onclick="document.getElementById('id05').style.display='block'">click</button>
						  </td>
						</tr>
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
			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" ><p title="Mukesh gupta">hilo</p>
			       	
			       </div>

			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" ><p title="Mukesh gupta">hilo</p></div>
			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" ><p title="Mukesh gupta">hilo</p></div>
			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" ><p title="Mukesh gupta">hilo</p></div>
			       <div class="w3-container w3-light-grey w3-padding-32 w3-btn  w3-small" ><p title="Mukesh gupta">hilo</p></div>

			      	
			      
			    </div>
			  </div>
		</div>

		
		<footer class="w3-container w3-dark-grey   w3-card-8 ">
				 <p class="copyright text-muted">Copyright &copy; Mukesh Gupta 2014</p>
  			
		
		</footer>
		
</body>
</html>