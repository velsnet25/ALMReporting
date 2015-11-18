package com.homedepot.is.as.util;


/*
 * This program is proprietary to The Home Depot and
 * not to be reproduced, used, or disclosed without
 * permission of:
 *     The Home Depot
 *     2727 Paces Ferry Road, N.W.
 *     Atlanta, GA 30339-4053
 * 
 * File Name: $Workfile:   ServiceLocator.java  $
 * 
 * $Header:   V:/vcs/j2ee/mm/vn/BEARRTVHostWeb/src/com/homedepot/mm/vn/bearrtv/data/ServiceLocator.java_v   1.0   08 Sep 2004 09:48:22   mtw04  $
 */

//Import Statements
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * This class is an implementation of the Service Locator pattern. It is
 * used to lookup resources such as EJBHomes, DataSources, JMS Queues, etc.
 * This implementation uses the "singleton" strategy and also the "caching"
 * strategy.
 * 
 * This class is for use in the web tier only
 */
public class ServiceLocator
{
    public static final String RCS_HEADER = "$Header:   V:/vcs/j2ee/mm/vn/BEARRTVHostWeb/src/com/homedepot/mm/vn/bearrtv/data/ServiceLocator.java_v   1.0   08 Sep 2004 09:48:22   mtw04  $";
    public static final String RCS_REVISION = "$Revision:   1.0  $";
    
	/** The Singleton instance */
	private static ServiceLocator locator;

	/** The Initial Context */
	private InitialContext context;

	/** Used to hold references to EJBHomes/JMS Resources for re-use */
	private Map cache;

	/** synch object used to ensure singleton creation */
	private static Object synch = new Object();

	/**
	 * Constructor
	 */
	private ServiceLocator() throws ServiceLocatorException
	{
		try
		{
			context = new InitialContext();
			cache = Collections.synchronizedMap(new HashMap());
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}
	}

	/**
	 * Singleton accesor method
	 * 
	 * @throws ServiceLocatorException
	 */
	public static ServiceLocator getInstance() throws ServiceLocatorException
	{
		if (locator == null)
		{
			synchronized (synch)
			{
					locator = new ServiceLocator();
			}
		}

		return locator;
	}
	
	/**
	 * Method used to lookup QueueConnectionFactory objects
	 * 
	 * @param qConnFactoryName the QueueConnectionFactory JNDI name
	 * @return the QueueConnectionFactory object
	 * @throws ServiceLocatorException
	 */
	public QueueConnectionFactory getQueueConnectionFactory(String qConnFactoryName) throws ServiceLocatorException
	{
		QueueConnectionFactory factory = null;
		try
		{
			factory = (QueueConnectionFactory)cache.get(qConnFactoryName);
			if (factory == null)
			{
				factory = (QueueConnectionFactory)context.lookup(qConnFactoryName);
				cache.put(qConnFactoryName, factory);
			}
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}

		return factory;
	}

	/**
	 * Method used to lookup Queue objects
	 * 
	 * @param queueName the Queue JNDI name
	 * @return the Queue object
	 * @throws ServiceLocatorException
	 */
	public Queue getQueue(String queueName) throws ServiceLocatorException
	{
		Queue queue = null;
		try
		{
			queue = (Queue)cache.get(queueName);
			if (queue == null)
			{
				queue = (Queue)context.lookup(queueName);
				cache.put(queueName, queue);
			}
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}

		return queue;
	}

	/**
	 * Method used to lookup DataSource objects
	 * 
	 * @param dataSourceName the DataSource JNDI name
	 * @return the DataSource object
	 * @throws ServiceLocatorException
	 */
	public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException
	{
		DataSource dataSource = null;
		try
		{
			dataSource = (DataSource)cache.get(dataSourceName);
			if (dataSource == null)
			{
				dataSource = (DataSource)context.lookup(dataSourceName);
				cache.put(dataSourceName, dataSource);
			}
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}

		return dataSource;
	}

	/**
	 * Method used to lookup URL objects
	 * 
	 * @param urlName the URL JNDI name
	 * @return the URL object
	 * @throws ServiceLocatorException
	 */
	public URL getUrl(String urlName) throws ServiceLocatorException
	{
		try
		{
			return (URL)context.lookup(urlName);
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}
	}

	/**
	 * Method used to lookup boolean values
	 * 
	 * @param booleanName the boolean JNDI name
	 * @return the boolean value
	 * @throws ServiceLocatorException
	 */
	public boolean getBoolean(String booleanName) throws ServiceLocatorException
	{
		try
		{
			return ((Boolean)context.lookup("java:comp/env/" + booleanName)).booleanValue();
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}
	}

	/**
	 * Method used to lookup String objects
	 * 
	 * @param stringName the String JNDI name
	 * @return the String object
	 * @throws ServiceLocatorException
	 */
	public String getString(String stringName) throws ServiceLocatorException
	{
		try
		{
			return (String)context.lookup("java:comp/env/" + stringName);
		}
		catch (Exception e)
		{
			throw new ServiceLocatorException(e);
		}
	}

}
