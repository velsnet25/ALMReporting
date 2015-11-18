package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.SubProjNamesDTO;
import com.homedepot.is.as.dto.SubProjectListDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of release names and release Id's.
 * 
 * @author SXB8898
 *
 */
public class SubProjectNameDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(SubProjectNameDetailsDAO.class);

	/**
	 * This method gets the releaseIds and releaseNames for given inputs.
	 * 
	 * @param dbName
	 * @param fmsID
	 * @return
	 * @throws QueryException
	 */
	public static SubProjectListDTO readSubProjectsByInputList(String dbName,
			String fmsId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readSubProjectsByInputList");
		}

		final List<SubProjNamesDTO> readReleasesDetailsByInputListList = new ArrayList<SubProjNamesDTO>();
		final SubProjectListDTO subprojectListDTO = new SubProjectListDTO();
		MapStream inputs = new MapStream("readReleasesDetailsByInputList");
		inputs.put("qcrDatabaseName", dbName);
		// fmsId can be null
		inputs.putAllowNull("releaseUserTemplate01", fmsId);

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		try {
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							SubProjNamesDTO readReleasesDetailsByInputListDTO = null;
							while (results.next()) {
								readReleasesDetailsByInputListDTO = new SubProjNamesDTO();
								readReleasesDetailsByInputListDTO
										.setSubProjId(results
												.getInt("relatedId"));
								readReleasesDetailsByInputListDTO
										.setSubProjName(results
												.getString("releaseName"));
								readReleasesDetailsByInputListList
										.add(readReleasesDetailsByInputListDTO);
							}
							subprojectListDTO
									.setSubProjNamesDTO(readReleasesDetailsByInputListList);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readSubProjectsByInputList", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readSubProjectsByInputList");
			logger.debug("returning "
					+ readReleasesDetailsByInputListList.size() + " item(s)");
		}
		return subprojectListDTO;
	}

	/**
	 * This method returns release name for the give database and releaseId.
	 * 
	 * @param subProjectId
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static SubProjectListDTO readReleasesByReleaseId(
			final String subProjectId, final String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readSubProjectsByInputListById");
		}

		final List<SubProjNamesDTO> readReleasesDetailsByInputListList = new ArrayList<SubProjNamesDTO>();
		final SubProjectListDTO subprojectListDTO = new SubProjectListDTO();
		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		try {
			inputs = new MapStream("readReleasesByReleaseId");
			if (dbName != null && dbName.length() > 0)
				inputs.put("qcrDatabaseName", dbName);
			if (subProjectId != null && subProjectId.trim().length() > 0)
				inputs.put("releaseId", Integer.parseInt(subProjectId));

			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							SubProjNamesDTO readReleasesDetailsByInputListDTO = null;
							while (results.next()) {
								readReleasesDetailsByInputListDTO = new SubProjNamesDTO();
								readReleasesDetailsByInputListDTO
										.setSubProjId(Integer
												.parseInt(subProjectId));
								readReleasesDetailsByInputListDTO
										.setSubProjName(results
												.getString("releaseName"));
								readReleasesDetailsByInputListList
										.add(readReleasesDetailsByInputListDTO);
							}
							subprojectListDTO
									.setSubProjNamesDTO(readReleasesDetailsByInputListList);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readSubProjectsByInputListById", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readSubProjectsByInputListById");
			logger.debug("returning "
					+ readReleasesDetailsByInputListList.size() + " item(s)");
		}
		return subprojectListDTO;
	}

}
