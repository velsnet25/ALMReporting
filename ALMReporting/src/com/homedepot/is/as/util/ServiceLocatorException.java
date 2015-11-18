package com.homedepot.is.as.util;

/*
 * This program is proprietary to The Home Depot and
 * not to be reproduced, used, or disclosed without
 * permission of:
 *     The Home Depot
 *     2727 Paces Ferry Road, N.W.
 *     Atlanta, GA 30339-4053
 * 
 * File Name: $Workfile:   ServiceLocatorException.java  $
 * 
 */

//Import Statements
import com.homedepot.ta.aa.exceptions.WrapperException;

/**
 * Exception class for ServiceLocator
 */
@SuppressWarnings("serial")
public class ServiceLocatorException extends WrapperException
{
	/**
	 * Empty Constuctor
	 */
	public ServiceLocatorException()
	{
	}

	/**
	 * Constuctor using a String message
	 * 
	 * @param s the exception message
	 */
	public ServiceLocatorException(String s)
	{
		super(s);
	}

	/**
	 * Constuctor for wrapping another Exception
	 * 
	 * @param e the Exception to wrap
	 */
	public ServiceLocatorException(Exception e)
	{
		super(e);
	}

	/**
	 * Constuctor for wrapping another Exception
	 * 
	 * @param s the exception message
	 * @param e the Exception to wrap
	 */
	public ServiceLocatorException(String s, Exception e)
	{
		super(s, e);
	}
}