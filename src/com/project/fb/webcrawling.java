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
import java.util.HashSet;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webcrawling {
	static int size_of_filtersport=0;
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void post_display(){
		try{
			File b=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/filter_sports.csv");
			File stopword=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/stopwords.csv");

			BufferedReader random=new BufferedReader(new FileReader(b));
			
			 HashSet<Integer> set=new HashSet<Integer>();  
			  Random rand=new Random();
		
			  while(true){
						set.add(rand.nextInt(286));
						if(set.size()==10)
							break;
			  }
			  Object[] array=set.toArray();
			  String line;
			  int counter=0;
			  ArrayList<String> Keywords=new ArrayList<String>();
			  while((line=random.readLine())!=null){
				  counter++;
				  Keywords.clear();
				  if(array[0].equals(counter)||array[1].equals(counter)||array[2].equals(counter)||array[3].equals(counter)||array[4].equals(counter)||array[5].equals(counter)||array[6].equals(counter)||array[7].equals(counter)||array[8].equals(counter)||array[9].equals(counter)){
					  String[] split=line.split("=>");
					  //System.out.println(line);
					  String[] hashsplit=split[split.length-1].split("/|\\-");
					  String[] hashsplit1=split[split.length-2].split(" ");
					  //System.out.println(hashsplit[1]);
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
						  
					  System.out.println("++++++++++++++++++++++++++++++++");
					  for(String s:Keywords){
						  
						  if(s.equals("")==false)
						  System.out.print("#"+s+" ");
						  
					  }
					  System.out.println();
					  System.out.println(split[split.length-2]);
				  }
			  }
			random.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("I am in post_diaplay");
		}
	}
	
	public static void extract_post(){
		try{
			File a=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/sports.csv");
			File b=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/filter_sports.csv");
			File key=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/keyword.csv");
			BufferedWriter writer=new BufferedWriter(new FileWriter(b));
			BufferedWriter writerk=new BufferedWriter(new FileWriter(key));
			BufferedReader reader=new BufferedReader(new FileReader(a));
			String line;
			while((line=reader.readLine())  != null){
				try{
				String[] split=line.split("=>");
				//System.out.println(split.length+split[0]);
				String[] string=split[0].split(" ");
				if(string.length>=5){
					//System.out.println("writing");
					writer.write(line);
					writer.newLine();
					size_of_filtersport++;
				}
				else{
					//System.out.println("writing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					writerk.write(split[0]);
					writerk.newLine();
					
				}
				}catch(Exception e){
					//e.printStackTrace();
					System.out.println("Stuck in while loop");
				}
			}
			writer.close();
			writerk.close();
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("I am stucking in extract_post");
		}
		
	}
	public void webcrawl(String urli) throws MalformedURLException{
		URL url=new URL(urli);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			File a=new File("/home/mukesh/Documents/Developers_stuff/Java_development/News_Fb/Data/sports.csv");
			BufferedWriter writer=new BufferedWriter(new FileWriter(a));
			
			Document doc = Jsoup.connect("http://timesofindia.indiatimes.com/sports").get();
			
			doc.select("img").remove();
			
			Elements anewsHeadlines = doc.select("a");
			//Elements pnewsHeadlines = doc.select("p");
			//Elements elements = doc.body().select("*");

			
			for(int i=0;i<doc.select("a").size();i++){
				
			//	System.out.println("!!Afte parsing, Heading : " +i+ anewsHeadlines.get(i).text()+"=>"+anewsHeadlines.get(i).attr("href"));
				writer.write(anewsHeadlines.get(i).text().toString()+"=>"+anewsHeadlines.get(i).attr("href"));
				writer.newLine();
				
			}
			
			writer.close();
			
			extract_post();	
			post_display();
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("I am Stuck!");
		}
	}

}
