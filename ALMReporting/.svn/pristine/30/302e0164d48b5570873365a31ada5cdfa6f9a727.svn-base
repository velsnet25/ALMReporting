package com.homedepot.is.as.util;

import com.homedepot.is.as.dto.ARBCOmmentsListDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class initializes XStream object, 
 * serializing and deserializing of XML.
 * 
 * @author SXB8898
 *
 */
public class XStreamTransformer {

	private static XStreamTransformer transformerObj = null;
	private XStream xstreamObj = null;

	private XStreamTransformer() {
	}

	public synchronized static XStreamTransformer getInstance() {
		if (transformerObj == null) {
			transformerObj = new XStreamTransformer();
			transformerObj.initialiseXStream();
		}
		return transformerObj;
	}

	private void initialiseXStream() {
		xstreamObj = new XStream(new DomDriver("UTF-8"));
		xstreamObj.processAnnotations(getRequestClasses());
	}

	/**
	 * This method is used to check whether an object is null or not.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return null == obj ? true : false;
	}

	public String serializingToXML(final Object obj) throws Exception {
		if (isNull(obj))
			throw new Exception("Passed Object Cannot be NULL ");

		return new StringBuffer(xstreamObj.toXML(obj)).toString();
	}

	public Object deSerializingFromXML(final String strXml) throws Exception {
		Object obj = null;
		if ("".equals(strXml.trim()))
			throw new Exception("Passed String cannot be Empty String ");
		try {
			obj = xstreamObj.fromXML(strXml.trim());
		} catch (Exception e) {
			throw e;
		}
		// return xstreamObj.fromXML(strXml.trim()) ;
		return obj;
	}

	private Class[] getRequestClasses() {
		return new Class[] { ARBCOmmentsListDTO.class, };
	}

}