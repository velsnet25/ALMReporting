package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.TestTotActDrillResultListDTO;
import com.homedepot.is.as.dto.TestTotActDrillResultsDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of active bug drill details.
 * 
 * @author SXB8898
 *
 */
public class TestTotActDrillDetailsDAO {
	
	private static final Logger logger = Logger.getLogger(TestTotActDrillDetailsDAO.class);
		
	/**
	 * This method reads readBugDetailsByInputList and readBugByInputList selectors
	 * 
	 * This method reads total active IRB defects for the given input values.
	 * 
	 * @param drillStat
	 * @param drillType
	 * @param projName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static TestTotActDrillResultListDTO getTestTotActDrillResults(
			String drillStat, String drillType,	String projName, 
			String stageName, String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start getTestTotActDrillResults");
		}
		final TestTotActDrillResultListDTO testTotActDrillListDTO = new TestTotActDrillResultListDTO();
		final List<TestTotActDrillResultsDTO> readBugDetailsByInputListList = new ArrayList<TestTotActDrillResultsDTO>();
		
		MapStream inputs = null;
		List<Object> inputBugStatusList = null;
		
		
		if(drillType != null && drillType.equalsIgnoreCase(GlobalConstants.TOTAL_TEST_CASES))
		{
			inputs = new MapStream("readBugByInputList");
			if(stageName != null && stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES))
			{
				//GET_TEST_TOT_ACT_DRILL_ALL_RESULTS_ALL_STAGES or readBugByInputList selector
				if (logger.isDebugEnabled()) {
					logger.debug("In getTestTotActDrillResults to get total test active drill results all stages with total test cases.");
				}
				inputBugStatusList = new ArrayList<Object>();
				inputBugStatusList.add(GlobalConstants.CLOSED);
				inputs.put("bugStatusList", inputBugStatusList);
				inputs.put("qcrDatabaseName", dbName);
				inputs.put("releaseName", projName);
			}else{
				//GET_TEST_TOT_ACT_DRILL_ALL_RESULTS or readBugByInputList selector
				if (logger.isDebugEnabled()) {
					logger.debug("In getTestTotActDrillResults to get total test active drill results all stages with out total test cases.");
				}
				inputBugStatusList = new ArrayList<Object>();
				inputBugStatusList.add(GlobalConstants.CLOSED);
				inputs.put("bugStatusList", inputBugStatusList);
				inputs.put("qcrDatabaseName", dbName);
				inputs.put("releaseName", projName);
				if(stageName != null)
				inputs.put("releaseCyclesName", stageName); // optional
			}
		}else{
			inputs = new MapStream("readBugDetailsByInputList");
			if (logger.isDebugEnabled()) {
				logger.debug("start readBugDetailsByInputList");
			}
			if(stageName != null && stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES))
			{	
				//GET_TEST_TOT_ACT_DRILL_RESULTS_ALL_STAGES or readBugDetailsByInputList selector
				if(logger.isDebugEnabled()){
					logger.debug("In getTestTotActDrillResults to get total test active drill results for all stages.");
				}
				inputBugStatusList = new ArrayList<Object>();
				inputBugStatusList.add(GlobalConstants.CLOSED);
				inputs.put("bugStatusList", inputBugStatusList);
				inputs.put("qcrDatabaseName", dbName);
				inputs.put("releaseName", projName);
				// can be null,optional
				inputs.putAllowNull("bugUserTemplate06", drillType); 				
			}else{
				// GET_TEST_TOT_ACT_DRILL_RESULTS or readBugDetailsByInputList selector
				if(logger.isDebugEnabled()){
					logger.debug("In getTestTotActDrillResults to get total test active drill results.");
				}
				inputBugStatusList = new ArrayList<Object>();
				inputBugStatusList.add(GlobalConstants.CLOSED);
				inputs.put("bugStatusList", inputBugStatusList);
				inputs.put("qcrDatabaseName", dbName);
				inputs.put("releaseName", projName);
				inputs.put("releaseCyclesName", stageName); // optional
				// can be null,optional
				inputs.putAllowNull("bugUserTemplate06", drillType);
			}
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query for readBugByInputList selector.");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						TestTotActDrillResultsDTO testTotActDrillDetailDTO = null;
						while (results.next()) {
							testTotActDrillDetailDTO = new TestTotActDrillResultsDTO();
							//Commented out by gxg8154 due to issue raised by glenn and sent back the defect ID as integer
							//Hoping converting to binary string could be an accident
							//Checked with david greco as well
							//testTotActDrillDetailDTO.setDefectId(Integer.toBinaryString(results
								//	.getInt("defectId")));
							testTotActDrillDetailDTO.setDefectId(Integer.toString(results
									.getInt("defectId")));
							testTotActDrillDetailDTO
									.setDetectDt(results
											.getTimestamp("detectedOnDate"));
							testTotActDrillDetailDTO
									.setAssignedTeam(results
											.getString("assignedTeam"));
							testTotActDrillDetailDTO.setAssignedTo(results
									.getString("assignedTo"));
							testTotActDrillDetailDTO
									.setAssignedToName(results
											.getString("assignedToName"));// missing in DTO
							testTotActDrillDetailDTO.setSummary(results
									.getString("summary"));
							testTotActDrillDetailDTO.setBugStatus(results
									.getString("bugStatus")); // missing in DTO
//							testTotActDrillDetailDTO
//									.setBugUserTemplate06(results
//											.getString("bugUserTemplate06")); // missing in DTO
							readBugDetailsByInputListList
									.add(testTotActDrillDetailDTO);
						}
						testTotActDrillListDTO.setTestTotActDrillDTO(readBugDetailsByInputListList);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("finish getTestTotActDrillResults");
			logger.debug("returning " + readBugDetailsByInputListList.size()
					+ " item(s)");
		}
		return testTotActDrillListDTO;
	}
		
}