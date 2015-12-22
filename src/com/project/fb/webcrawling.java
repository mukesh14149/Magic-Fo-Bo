/*
 * @author Mukesh Gupta
 */
package com.project.fb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webcrawling {
	static int size_of_filtersport=0;
	static HashMap<String,String> finalpost=new HashMap<String,String>();
	/**
	 * @param args
	 * @return 
	 * @throws MalformedURLException 
	 */
	public static HashMap<String, String> post_display(){
		try{
			
			System.out.println(System.getProperty("user.dir"));
			File b=new File("/home/mukesh/Downloads/News_Fb/Data/filter_sports.csv");
			File stopword=new File("/home/mukesh/Downloads/News_Fb/Data/stopwords.csv");
			BufferedReader random=new BufferedReader(new FileReader(b));
			
			
			HashSet<Integer> set=new HashSet<Integer>();  //HashSet of Perfect post to Display
			
			//There could be a lot of post so we randomaly select 10 post of Selected Category.
			Random rand=new Random();
			while(true){
				//Make a list of randomly selected Index no. which is going to fill in hashset.
						set.add(rand.nextInt(286));
						if(set.size()==10)
							break;
			}
			  Object[] array=set.toArray();
			  String line;
			  int counter=0;
			  
			  //Arraylist of Keywords.
			  
			  ArrayList<String> Keywords=new ArrayList<String>();
			  while((line=random.readLine())!=null){
				  counter++;
				  Keywords.clear();
				  //Remove Stopword from source and make them HashTag of a post.
				  if(array[0].equals(counter)||array[1].equals(counter)||array[2].equals(counter)||array[3].equals(counter)||array[4].equals(counter)||array[5].equals(counter)||array[6].equals(counter)||array[7].equals(counter)||array[8].equals(counter)||array[9].equals(counter)){
					  String[] split=line.split("=>");
					  
					  String[] hashsplit=split[split.length-1].split("/|\\-");
					  String[] hashsplit1=split[split.length-2].split(" ");
					 
					  String str;
					  int ct=0;
					  
						  for(int j=0;j<hashsplit.length-1;j++){
							  ct=0;
							  BufferedReader sw=new BufferedReader(new FileReader(stopword));
							  while((str=sw.readLine())!=null){
								  if(str.equals(hashsplit[j])){
									  ct=1;
									  break;
								  } 
								  
							  }
							  for(int k=0;k<hashsplit1.length;k++){
								  if(hashsplit[j].equals(hashsplit1[k]))
								  {	  ct=1;
								  	break;
								  }
								  
							  }
							  if(ct==0)
								  Keywords.add(hashsplit[j]);
							  sw.close();
						  }
						  
					  
					  String av=new String();
					  //Add HashTag to post.
					  for(String s:Keywords){
						  
						  if(s.equals("")==false)
						  av+="#"+s+" ";
					  }
					  
					
					 finalpost.put(av,split[split.length-2] );
					  
				  }
			  }
			random.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalpost;
	}
	
	public static void extract_post(){
		try{
			File a=new File("/home/mukesh/Downloads/News_Fb/Data/sports.csv");			//Pure Source  
			File b=new File("/home/mukesh/Downloads/News_Fb/Data/filter_sports.csv");	//Filter Source
			File key=new File("/home/mukesh/Downloads/News_Fb/Data/keyword.csv");		//File to Save HashTag
			
			BufferedWriter writer=new BufferedWriter(new FileWriter(b));
			BufferedWriter writerk=new BufferedWriter(new FileWriter(key));
			BufferedReader reader=new BufferedReader(new FileReader(a));
			String line;
			
			
			while((line=reader.readLine())  != null){
				try{
				String[] split=line.split("=>");
				String[] string=split[0].split(" ");
				if(string.length>=5){
					writer.write(line);
					writer.newLine();
					size_of_filtersport++;
				}
				else{
					writerk.write(split[0]);
					writerk.newLine();
				}
				}catch(Exception e){
				}
			}
			writer.close();
			writerk.close();
			reader.close();
		}catch(Exception e){
		}
		
	}
	public HashMap<String, String> webcrawl(String url) throws MalformedURLException{
		finalpost.clear();
		
		try{
			//File that hold source from Selected Media
			File a=new File("/home/mukesh/Downloads/News_Fb/Data/sports.csv");
			BufferedWriter writer=new BufferedWriter(new FileWriter(a));
			
			//Fetch Source from Given URL.
			Document doc = Jsoup.connect(url).get();
			
			//Remove img tag from Source.
			doc.select("img").remove();
			
			//Select a tag element.
			Elements anewsHeadlines = doc.select("a");
			
			//Write a tag element in a file.
			for(int i=0;i<doc.select("a").size();i++){
				writer.write(anewsHeadlines.get(i).text().toString()+"=>"+anewsHeadlines.get(i).attr("href"));
				writer.newLine();
			}
			writer.close();
			extract_post(); //Method to convert Source of 'a' tag into Post with hashtag	
			
		}catch(Exception e){
		
		}
		return post_display();  //Method to return Post.
	}
	
	
	

}
