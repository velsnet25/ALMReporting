package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.DomainListDTO;
import com.homedepot.is.as.dto.DomainNamesDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of domain names and domain Id's.
 *
 * @author SXB8898
 *
 */
public class DomainNameDetailsDAO {

	private static final Logger LOGGER = Logger.getLogger(DomainNameDetailsDAO.class);
	
	/**
	 * This method loads all domain names.
	 * 
	 * @return
	 * @throws QueryException
	 */
	public static DomainListDTO readDomainNames() throws QueryException {
		final List<DomainNamesDTO> readDomainNamesList = new ArrayList<DomainNamesDTO>();
		final DomainListDTO domainListDTO = new DomainListDTO();
		MapStream inputs = null;

		try
		{
			LOGGER.debug("Entering readDomainNames() in DomainNameDetailsDAO");
			inputs = new MapStream("readDomainNames");
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() 
			{
				public void readResults(Results results, Query query,Inputs inputs) throws QueryException 
				{
					DomainNamesDTO readDomainNamesDTO = null;
					while (results.next()) 
					{
						readDomainNamesDTO = new DomainNamesDTO();
						readDomainNamesDTO.setDomainId(results.getString("domainId"));
						readDomainNamesDTO.setDomainName(results.getString("domainName"));
						readDomainNamesList.add(readDomainNamesDTO);
					}
					domainListDTO.setDomainNamesDTO(readDomainNamesList);
				}
			});
		}
		catch(Exception e)
		{
			LOGGER.fatal ("Exception in readDomainNames", e);
		}
		LOGGER.debug("Exiting readDomainNames() in DomainNameDetailsDAO");
		return domainListDTO;
	}
	
	/**
	 * This method gets the domain name for the given domainId
	 * 
	 * @return
	 * @throws QueryException
	 */
	public static DomainListDTO readDomainNameById(final String domainId) throws QueryException {
		final List<DomainNamesDTO> readDomainNamesList = new ArrayList<DomainNamesDTO>();
		final DomainListDTO domainListDTO = new DomainListDTO();
		MapStream inputs = null;

		try
		{
			LOGGER.debug("Entering readDomainNameById() in DomainNameDetailsDAO");
			inputs = new MapStream("readDomainNames");
			if(domainId != null && domainId.trim().length() > 0)
			inputs.put("domainId", Integer.parseInt(domainId)); // optional
			
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() 
			{
				public void readResults(Results results, Query query,Inputs inputs) throws QueryException 
				{
					DomainNamesDTO readDomainNamesDTO = null;
					while (results.next()) 
					{
						readDomainNamesDTO = new DomainNamesDTO();
						if(domainId != null && 
								domainId.equalsIgnoreCase(results.getString("domainId"))){
							readDomainNamesDTO.setDomainId(results.getString("domainId"));
							readDomainNamesDTO.setDomainName(results.getString("domainName"));
							readDomainNamesList.add(readDomainNamesDTO);
						}
					}
					domainListDTO.setDomainNamesDTO(readDomainNamesList);
				}
			});
		}
		catch(Exception e)
		{
			LOGGER.fatal ("Exception in readDomainNamebyId", e);
		}
		LOGGER.debug("Exiting readDomainNameById() in DomainNameDetailsDAO");
		return domainListDTO;
	}
}