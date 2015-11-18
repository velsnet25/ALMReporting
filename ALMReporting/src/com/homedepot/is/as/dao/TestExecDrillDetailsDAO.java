package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ReadTestDetailsByInputListDTO;
import com.homedepot.is.as.dto.TestExecDrillResultListDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of drill details.
 * 
 * @author SXB8898
 *
 */
public class TestExecDrillDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(TestExecDrillDetailsDAO.class);

	/**
	 * This method returns list of test execution drill results 
	 * for given inputs accordingly.
	 * 
	 * @param drillStat
	 * @param drillType
	 * @param projectName
	 * @param stageName
	 * @param stat1
	 * @param stat2
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static TestExecDrillResultListDTO getTestExecDrillResults(
			String drillStat, String drillType, String projectName,
			String stageName, String stat1, String stat2, String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readTestDetailsByInputList/getTestExecDrillResults");
		}
		boolean totTestResults = true;
		boolean allStages = true;
		final TestExecDrillResultListDTO testExecDrillListDTO = new TestExecDrillResultListDTO();
		final List<ReadTestDetailsByInputListDTO> readTestDetailsByInputListList = new ArrayList<ReadTestDetailsByInputListDTO>();

		MapStream inputs = null;
		List<Object> inputTcStatusList = null;
		inputs = new MapStream("readTestDetailsByInputList");
		if (drillType != null
				&& drillType.equalsIgnoreCase(GlobalConstants.TOTAL_TEST_CASES)) {
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				inputTcStatusList = new ArrayList<Object>();
				inputs.put("qcrDatabaseName", dbName);
				inputTcStatusList.add(stat1);
				inputTcStatusList.add(stat2);
				inputs.put("tcStatusList", inputTcStatusList);
				// drill type column is required for drill results 
				// can be null and optional field
				//inputs.putAllowNull("timestampUserTemplate11", null);
				inputs.put("releaseName", projectName);
			} else {
				allStages = false;
				inputTcStatusList = new ArrayList<Object>();
				inputs.put("qcrDatabaseName", dbName);
				inputTcStatusList.add(stat1);
				inputTcStatusList.add(stat2);
				inputs.put("tcStatusList", inputTcStatusList);
				// drill type column is required for drill results
				// can be null and optional field
				//inputs.putAllowNull("timestampUserTemplate11", null);
				inputs.put("releaseName", projectName);
				// optional
				inputs.put("releaseCyclesName", stageName);
			}
		} else {
			totTestResults = false;
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				inputTcStatusList = new ArrayList<Object>();
				inputs.put("qcrDatabaseName", dbName);
				inputTcStatusList.add(stat1);
				inputTcStatusList.add(stat2);
				if (!totTestResults) {
					// can be null and optional field
					inputs.putAllowNull("timestampUserTemplate11", drillType);
					inputs.put("releaseName", projectName);
					if (!allStages)
						// optional
						inputs.put("releaseCyclesName", stageName);
				}
				inputs.put("tcStatusList", inputTcStatusList);
				inputs.put("releaseName", projectName);
				// can be null and optional field
				inputs.putAllowNull("timestampUserTemplate11", drillType);
			} else {
				allStages = false;
				inputTcStatusList = new ArrayList<Object>();
				inputs.put("qcrDatabaseName", dbName);
				inputTcStatusList.add(stat1);
				inputTcStatusList.add(stat2);
				if (!totTestResults) {
					// drill type column is required for drill results
					// can be null and optional field
					//inputs.putAllowNull("timestampUserTemplate11", null);
					inputs.put("releaseName", projectName);
					if (!allStages)
						// optional
						inputs.put("releaseCyclesName", stageName);
				}
				inputs.put("tcStatusList", inputTcStatusList);
				inputs.put("releaseName", projectName);
			}
		}

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
							ReadTestDetailsByInputListDTO readTestDetailsByInputListDTO = null;
							while (results.next()) {
								readTestDetailsByInputListDTO = new ReadTestDetailsByInputListDTO();
								readTestDetailsByInputListDTO
										.setPlanApplicationGroup(results
												.getString("planApplicationGroup"));
								readTestDetailsByInputListDTO
										.setPlanApplication(results
												.getString("planApplication"));
								readTestDetailsByInputListDTO
										.setExecutionDate(results
												.getString("executionDate"));
								readTestDetailsByInputListDTO.setStatus(results
										.getString("status"));
								readTestDetailsByInputListDTO
										.setPlanTestName(results
												.getString("planTestName"));
								readTestDetailsByInputListDTO
										.setTcStatus(results
												.getString("tcStatus"));
								readTestDetailsByInputListList
										.add(readTestDetailsByInputListDTO);
							}
							testExecDrillListDTO
									.setTestExecDrillDTO(readTestDetailsByInputListList);
						}
					});
		} catch (Exception e) {
			logger.fatal("Error Occured in readTestDetailsByInputList", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readTestDetailsByInputList");
			logger.debug("returning " + readTestDetailsByInputListList.size()
					+ " item(s)");
		}
		return testExecDrillListDTO;
	}

}