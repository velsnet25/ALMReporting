package com.homedepot.is.as.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * This is the utility class of the application.
 * 
 * @author SXB8898
 *
 */
public class AppUtil {
	
	/**
	 * This method validates given String and returns boolean
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isIntNumber(String num){    
		boolean flag = false;
	    try{
	        Integer.parseInt(num);
	        flag = true;
	    } catch(NumberFormatException nfe) {
	        return flag;
	    }
	    return flag;
	}
	
	/**
	 * This method validates given String as Big integer and returns boolean
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isInteger(String str){
		boolean flag = false;
		flag = new java.math.BigInteger(str) != null;
		return flag;
	}
	
	public static boolean isShort(String num){
		try{
			Short.valueOf(num);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * Parser Timestamp to String
	 * by returning the given date format
	 * @param dateTime
	 * @return
	 */
	public static String parseDate(Timestamp dateTime){
			  String date;
			  Format formatter;
			  //Date formatter
			  //formatter = new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
			  // Timestamp formatter
			  formatter = new SimpleDateFormat(GlobalConstants.DATE_TIME_FORMAT);
			  date = formatter.format(dateTime);
		return date;
	}
	
	/**
	 * Parses string by HTML tags and returns plain text or string.
	 *  
	 * @param strHTML
	 * @param intWorkFlow
	 * @return
	 */
	public static String clearHTMLTags(String strHTML, int intWorkFlow){
    	
    	Pattern pattern = null;
	    //Matcher matcher = null;
	   	String regex;
	   	String strTagLess = null; 
    	strTagLess = strHTML; 
    	
    	if(intWorkFlow == -1)
  		{
  			regex = "<[^>]*>";
  			//removes all html tags
			pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll(" "); 
		}
		
		if(intWorkFlow > 0 && intWorkFlow < 3)
		{
  	
		  	regex = "[<]";
			//matches a single <
			pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll("<"); 
				
			regex = "[>]"; 	
			//matches a single >	
		 	pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll(">"); 
		}
		return strTagLess; 
    }
}
