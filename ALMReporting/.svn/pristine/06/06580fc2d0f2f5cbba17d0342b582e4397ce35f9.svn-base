package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.TestTypesDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of test types.
 * 
 * @author SXB8898
 *
 */
public class TestTypeDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(TestTypeDetailsDAO.class);

	/**
	 * This method gets all test types for specified inputs.
	 * 
	 * This method is not used currently, instead we are using
	 * readAllListsDetailsByInputList to get all existing test types.
	 *  
	 * @param subProjectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static List<TestTypesDTO> readTestTypeDetailsList(String subProjectName,
			String stageName, String dbName) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readTestTypeDetailsList");
		}
		final List<TestTypesDTO> readTestCycleList = new ArrayList<TestTypesDTO>();

		MapStream inputs = new MapStream(
				"readTestCycleAndTestDetailsByInputList");
		inputs.put("qcrDatabaseName", dbName);
		inputs.put("releaseName", subProjectName);

		if (stageName != null)
			inputs.put("releaseCyclesName", stageName); // optional

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
							TestTypesDTO readTestCycleAndTestDetailsByInputListDTO = null;
							String testType = null;
							while (results.next()) {
								readTestCycleAndTestDetailsByInputListDTO = new TestTypesDTO();
								testType = results
										.getString("timestampUserTemplate11");
								if (testType != null && testType.contains("&"))
									testType = testType.replace("&", "and");
								readTestCycleAndTestDetailsByInputListDTO
										.setTestType(testType);
								readTestCycleList
										.add(readTestCycleAndTestDetailsByInputListDTO);
							}
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readTestTypeDetailsList", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readTestTypeDetailsList");
			logger.debug("returning " + readTestCycleList.size() + " item(s)");
		}
		return readTestCycleList;
	}

	/**
	 * This method lists all test types for the given father_id. 
	 * Loads data from ALL_LISTS table from TEMPLATE_T_WATERFALL_DB database
	 * 
	 * @return List of AL_Description
	 * @throws QueryException
	 */
	public static List<TestTypesDTO> readAllListsDetailsByInputList()
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readAllListsDetailsByInputList");
		}
		final List<TestTypesDTO> readAllListsDetailsByInputListList = new ArrayList<TestTypesDTO>();

		MapStream inputs = new MapStream("readAllListsDetailsByInputList");
		// father_id is provided by QC application, which is same as the one in
		// WATER_FALL_DB.
		inputs.putAllowNull("alFatherId", GlobalConstants.FATHER_ID); // can be
																		// null

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query to get all test types using readAllListsDetailsByInputList selector.");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
				GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						TestTypesDTO readAllListsDetailsByInputListDTO = null;
						String replaceString = null;
						while (results.next()) {
							readAllListsDetailsByInputListDTO = new TestTypesDTO();
							replaceString = results.getString("alDescription");
							if (replaceString != null
									&& replaceString.length() > 0
									&& replaceString.contains("&"))
								replaceString = replaceString.replace("&",
										"and");

							// readAllListsDetailsByInputListDTO.setTestType(results.getString("alDescription"));
							readAllListsDetailsByInputListDTO
									.setTestType(replaceString);
							readAllListsDetailsByInputListList
									.add(readAllListsDetailsByInputListDTO);
						}
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("finish readAllListsDetailsByInputList");
			logger.debug("returning "
					+ readAllListsDetailsByInputListList.size() + " item(s)");
		}
		return readAllListsDetailsByInputListList;
	}
}