package com.adruijter.kingsvalley1.customFunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Functions {
	
	
	 public static String getUrlHighscore(String url) {
		 try{
         URL link = new URL(url);
         URLConnection lc = link.openConnection();
         lc.setRequestProperty("User-Agent", "KingsValley/Beagon 1.0");
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 lc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
         while ((inputLine = in.readLine()) != null)
             a.append(inputLine);
         in.close();

         return a.toString();
		 }catch(Exception e){
			 return "1337";
		 }
     }
	 
	 public static String getUrlSource(String url) {
		 try{
         URL link = new URL(url);
         URLConnection lc = link.openConnection();
         lc.setRequestProperty("User-Agent", "KingsValley/Beagon 1.0");
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 lc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
         while ((inputLine = in.readLine()) != null)
             a.append(inputLine);
         in.close();

         return a.toString();
		 }catch(Exception e){
			 return e.getMessage();
		 }
     }
}
