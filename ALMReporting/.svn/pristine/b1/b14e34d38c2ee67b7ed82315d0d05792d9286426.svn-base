package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ReadReleaseCyclesAndReleasesDetailsByInputListDTO;
import com.homedepot.is.as.dto.TargetListDTO;
import com.homedepot.is.as.util.AppUtil;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of release cycle names 
 * and release cycle Id's.
 * 
 * @author SXB8898
 *
 */
public class TargetNameDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(TargetNameDetailsDAO.class);

	/**
	 * This method returns list of target/stage or release cycleId's and cycle names 
	 * for given data base name and sub-project name.
	 * 
	 * @param dbName
	 * @param subProject
	 * @return
	 * @throws QueryException
	 */
	public static TargetListDTO readReleaseCyclesAndReleasesDetailsByInputList(
			final String dbName, final String subProject) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readReleaseCyclesAndReleasesDetailsByInputList");
		}

		final TargetListDTO relCyclesAndReleasesDetailsByInputList = new TargetListDTO();
		final List<ReadReleaseCyclesAndReleasesDetailsByInputListDTO> readReleaseCyclesAndReleasesDetailsByInputListList = new ArrayList<ReadReleaseCyclesAndReleasesDetailsByInputListDTO>();

		MapStream inputs = null;
		try {
			inputs = new MapStream(
					"readReleaseCyclesAndReleasesDetailsByInputList");
			inputs.put("qcrDatabaseName", dbName);
			inputs.put("releaseName", subProject);

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ReadReleaseCyclesAndReleasesDetailsByInputListDTO readReleaseCyclesAndReleasesDetailsByInputListDTO = null;
							while (results.next()) {
								readReleaseCyclesAndReleasesDetailsByInputListDTO = new ReadReleaseCyclesAndReleasesDetailsByInputListDTO();
								readReleaseCyclesAndReleasesDetailsByInputListDTO
										.setReleaseCyclesId(results
												.getInt("releaseCyclesId"));
								readReleaseCyclesAndReleasesDetailsByInputListDTO
										.setReleaseCyclesName(results
												.getString("releaseCyclesName"));
								readReleaseCyclesAndReleasesDetailsByInputListDTO
										.setReleaseName(results
												.getString("releaseName"));
								readReleaseCyclesAndReleasesDetailsByInputListList
										.add(readReleaseCyclesAndReleasesDetailsByInputListDTO);
							}
							relCyclesAndReleasesDetailsByInputList
									.setTargetNamesDTO(readReleaseCyclesAndReleasesDetailsByInputListList);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"Error while processing readReleaseCyclesAndReleasesDetailsByInputList",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger
					.debug("finish readReleaseCyclesAndReleasesDetailsByInputList");
			logger.debug("returning "
					+ readReleaseCyclesAndReleasesDetailsByInputListList.size()
					+ " item(s)");
		}
		return relCyclesAndReleasesDetailsByInputList;
	}

	/**
	 * This method gets stage name or release cycle name for the provided
	 * stageId
	 * 
	 * @param stageId
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static TargetListDTO readReleaseCyclesByReleaseCycleId(
			final String stageId, final String dbName) throws QueryException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("start readReleaseCyclesByReleaseCycleId");
		}

		final TargetListDTO relCyclesAndReleasesDetailsByInputList = new TargetListDTO();
		final List<ReadReleaseCyclesAndReleasesDetailsByInputListDTO> readReleaseCyclesAndReleasesDetailsByInputListList = new ArrayList<ReadReleaseCyclesAndReleasesDetailsByInputListDTO>();
		MapStream inputs = null;

		try {
			inputs = new MapStream("readReleaseCyclesByReleaseCycleId");
			inputs.put("qcrDatabaseName", dbName);
			if(AppUtil.isInteger(stageId))
				inputs.put("releaseCycleId", Integer.parseInt(stageId));
			
			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
				public void readResults(Results results, Query query,
						Inputs inputs) throws QueryException {
							ReadReleaseCyclesAndReleasesDetailsByInputListDTO readReleaseCyclesAndReleasesDetailsByInputListDTO = null;
							while (results.next()) {
								readReleaseCyclesAndReleasesDetailsByInputListDTO = new ReadReleaseCyclesAndReleasesDetailsByInputListDTO();
								readReleaseCyclesAndReleasesDetailsByInputListDTO
										.setReleaseCyclesId(Integer
												.parseInt(stageId));
								readReleaseCyclesAndReleasesDetailsByInputListDTO
										.setReleaseCyclesName(results
												.getString("releaseCycleName"));
								readReleaseCyclesAndReleasesDetailsByInputListList
										.add(readReleaseCyclesAndReleasesDetailsByInputListDTO);
							}
							relCyclesAndReleasesDetailsByInputList
									.setTargetNamesDTO(readReleaseCyclesAndReleasesDetailsByInputListList);
						}
					});
		} catch (Exception e) {
			logger.fatal(
					"Error while processing readReleaseCyclesByReleaseCycleId",
					e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readReleaseCyclesByReleaseCycleId");
			logger.debug("returning "
					+ readReleaseCyclesAndReleasesDetailsByInputListList.size()
					+ " item(s)");
		}
		return relCyclesAndReleasesDetailsByInputList;
	}
}