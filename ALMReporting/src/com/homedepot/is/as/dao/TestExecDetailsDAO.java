package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.TestExecResultListDTO;
import com.homedepot.is.as.dto.TestExecResultsDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of total active defect density details.
 * 
 * @author SXB8898
 *
 */
public class TestExecDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(TestExecDetailsDAO.class);

	/**
	 * This method gets execution results for Test Execution Results tab on
	 * clicking show results.
	 * 
	 * @param dbName
	 * @param projectName
	 * @param stageName
	 * @return
	 * @throws QueryException
	 */
	public static TestExecResultListDTO getTestExecResults(String dbName,
			String projectName, String stageName) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readTotalActiveOverAllDefectDensityDetails/getTestExecResults");
		}
		final TestExecResultListDTO testExeclistDTO = new TestExecResultListDTO();
		final List<TestExecResultsDTO> TestExecDTOasList = new ArrayList<TestExecResultsDTO>();
		MapStream inputs = null;
		List<Object> inputBugStatusList = null;
		
		if (stageName != null
				&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
			if (logger.isDebugEnabled()) {
				logger.info("Test Execution Results Query for All Stages.");
			}
			inputs  = new MapStream(
				"readTotalActiveOverAllDefectDensityDetails");
			inputs.put("qcrDatabaseName", dbName);
			inputBugStatusList = new ArrayList<Object>();
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputs.put("bugStatusList", inputBugStatusList);
			inputs.put("releaseName1", projectName);
			inputs.put("releaseName2", projectName);
			inputs.put("releaseName3", projectName);
			inputs.put("releaseName4", projectName);
			inputs.put("releaseName5", projectName);
			inputs.put("releaseName6", projectName);
		} else {
			if (logger.isDebugEnabled()) {
				logger
						.info("Test Execution Results Query for Particular Stage.");
			}
			inputs  = new MapStream(
				"readTotalActiveOverAllDefectDensityDetails");
			inputs.put("qcrDatabaseName", dbName);
			inputBugStatusList = new ArrayList<Object>();
			inputs.put("releaseName1", projectName);
			inputs.put("releaseCyclesName1", stageName); // optional
			inputs.put("releaseName2", projectName);
			inputs.put("releaseCyclesName2", stageName); // optional
			inputs.put("releaseName3", projectName);
			inputs.put("releaseCyclesName3", stageName); // optional
			inputs.put("releaseName4", projectName);
			inputs.put("releaseCyclesName4", stageName); // optional
			inputs.put("releaseName5", projectName);
			inputs.put("releaseCyclesName5", stageName); // optional
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputs.put("bugStatusList", inputBugStatusList);
			inputs.put("releaseName6", projectName);
			inputs.put("releaseCyclesName6", stageName); // optional
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try{
		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
				GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						TestExecResultsDTO testExecDetailDTO = null;
						String testType = null;
						while (results.next()) {
							testType = results.getString("testType");
							if (testType != null && testType.length() > 0) {
								testExecDetailDTO = new TestExecResultsDTO();
								if (testType != null && testType.contains("&"))
									testType = testType.replace("&", "and");
								testExecDetailDTO.setTestType(testType);
								testExecDetailDTO.setPlanned(results
										.getInt("planned"));
								testExecDetailDTO.setPassed(results
										.getInt("passed"));
								testExecDetailDTO.setFailed(results
										.getInt("failed"));
								testExecDetailDTO.setInProgress(results
										.getInt("inProgress"));
								testExecDetailDTO.setNoRun(results
										.getInt("noRun"));
								testExecDetailDTO.setBlocked(results
										.getInt("blocked"));
								testExecDetailDTO.setDescoped(results
										.getInt("descoped"));
								testExecDetailDTO.setTotActive(results
										.getInt("totalActive"));
								testExecDetailDTO.setTotOverall(results
										.getInt("totalOverall"));
								testExecDetailDTO.setDefectDensity(results
										.getInt("defectDensity"));
								TestExecDTOasList.add(testExecDetailDTO);
							}
						}
						testExeclistDTO.setTestExecDTO(TestExecDTOasList);
					}
				});
		}catch(Exception e){
			logger.fatal("Error occured while getting exection details",e);
		}
		if (logger.isDebugEnabled()) {
			logger
					.debug("finish readTotalActiveOverAllDefectDensityDetails/getTestExecResults");
			logger.debug("returning " + TestExecDTOasList.size() + " item(s)");
		}
		return testExeclistDTO;
	}

}