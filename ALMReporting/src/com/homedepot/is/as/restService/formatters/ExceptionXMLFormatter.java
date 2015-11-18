package com.homedepot.is.as.restService.formatters;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;

/**  
 * Class used to format Exception objects to XML
 *
 * @author SXB8898
 *
 */
public class ExceptionXMLFormatter {
	
	private static final Logger logger = Logger.getLogger(ExceptionXMLFormatter.class);
	
    /** 
     * Convert an Exception object to an XML string
     *
     * @param e the Exception to format 
     */
	public static String formatMessage(Exception e) {
	
	    if(logger.isDebugEnabled()){
            logger.debug("Start formatMessage");
            logger.debug("Exception: '"+e.getMessage()+"'");
        }
				
		StringBuilder sb = new StringBuilder();
		
		sb.append("<Exception>");
		
		sb.append("  <![CDATA[\n");
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		sb.append(sw.toString());
		
		sb.append("  ]]>");

		sb.append("</Exception>");
		
		if(logger.isDebugEnabled()){
            logger.debug("End formatMessage");
        }
		
		return sb.toString();
	}

}

