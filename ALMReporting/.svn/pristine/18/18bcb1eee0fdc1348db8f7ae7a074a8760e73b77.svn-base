package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.FMSIdListDTO;
import com.homedepot.is.as.dto.FMSIdsDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of FMS Id's.
 * 
 * @author SXB8898
 *
 */
public class FMSIdDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(FMSIdDetailsDAO.class);

	/**
	 * This method gets list of FMS Id's for given data base name.
	 *  
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static FMSIdListDTO readFMSIdsList(final String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readFMSIdsList");
		}
		final List<FMSIdsDTO> readReleaseUserTemplateListList = new ArrayList<FMSIdsDTO>();
		final FMSIdListDTO fmsListDTO = new FMSIdListDTO();

		MapStream inputs = new MapStream("readReleaseUserTemplateList");
		inputs.put("qcrDatabaseName", dbName);

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try {
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID, GlobalConstants.CONTRACT_VERSION,
					inputs, new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							FMSIdsDTO readReleaseUserTemplateListDTO = null;
							while (results.next()) {
								readReleaseUserTemplateListDTO = new FMSIdsDTO();
								readReleaseUserTemplateListDTO.setFmsId(results
										.getString("releaseUserTemplate01"));
								readReleaseUserTemplateListList
										.add(readReleaseUserTemplateListDTO);
							}
							fmsListDTO
									.setFMSIdsDTO(readReleaseUserTemplateListList);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readFMSIdsList", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readFMSIdsList");
			logger.debug("returning " + readReleaseUserTemplateListList.size()
					+ " item(s)");
		}
		return fmsListDTO;
	}
	
}
