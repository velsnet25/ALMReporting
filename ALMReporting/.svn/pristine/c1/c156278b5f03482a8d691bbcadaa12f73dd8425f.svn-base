package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.NewAndExistingDefectsDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsListDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets new and existing defect details.
 * 
 * @author SXB8898
 *
 */
public class NewAndExistingDefectDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(NewAndExistingDefectDetailsDAO.class);

	/**
	 * This method gets new defects for the given inputs. one for (All Stages)
	 * stage name accepts nulls and other with selected release/stage name.
	 * 
	 * @param projectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static NewAndExistingDefectsListDTO getNewDefects(
			final String projectName, final String stageName,
			final String dbName) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readTestTypeInformationByInputList/getNewDefects");
		}

		final NewAndExistingDefectsListDTO newDefectListDTO = new NewAndExistingDefectsListDTO();
		final List<NewAndExistingDefectsDTO> newDefectDTOasList = new ArrayList<NewAndExistingDefectsDTO>();
		MapStream inputs = null;
		List<Object> inputBugStatusList1 = null;
		List<Object> inputBugStatusList2 = null;
		
		if (stageName != null
				&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
			// GET_NEW_DEFECTS
			if (logger.isDebugEnabled()) {
				logger.debug("Executing GET_NEW_DEFECTS Query");
			}
			
			inputBugStatusList1 = new ArrayList<Object>();
			inputBugStatusList2 = new ArrayList<Object>();
			inputBugStatusList1.add(GlobalConstants.CLOSED);
			inputs = new MapStream("readTestTypeInformationByInputList");		
			inputs.put("qcrDatabaseName", dbName);
			inputs.putAllowNull("bugUserTemplate271",
					GlobalConstants.BUG_USER_TEMPLATE_27_N); // can be null
			inputs.put("releaseName1", projectName);
			inputBugStatusList2.add(GlobalConstants.CLOSED);
			inputs.putAllowNull("bugUserTemplate272",
					GlobalConstants.BUG_USER_TEMPLATE_27_N); // can be null
			inputs.put("releaseName2", projectName);
			inputs.put("bugStatusList1", inputBugStatusList1);
			inputs.put("bugStatusList2", inputBugStatusList2);
		} else {
			// GET_NEW_DEFECTS_STAGE
			if (logger.isDebugEnabled()) {
				logger.debug("Executing GET_NEW_DEFECTS_STAGE Query");
			}
			inputBugStatusList1 = new ArrayList<Object>();
			inputBugStatusList2 = new ArrayList<Object>();
			inputBugStatusList1.add(GlobalConstants.CLOSED);
			inputs = new MapStream("readTestTypeInformationByInputList");		
			inputs.put("qcrDatabaseName", dbName);
			inputs.putAllowNull("bugUserTemplate271",
					GlobalConstants.BUG_USER_TEMPLATE_27_N); // can be null
			inputs.put("releaseName1", projectName);
			inputs.put("releaseCyclesName1", stageName); // optional
			inputBugStatusList2.add(GlobalConstants.CLOSED);
			inputs.put("releaseName2", projectName);
			inputs.put("releaseCyclesName2", stageName); // optional
			inputs.putAllowNull("bugUserTemplate272",
					GlobalConstants.BUG_USER_TEMPLATE_27_N); // can be null
			inputs.put("bugStatusList1", inputBugStatusList1);
			inputs.put("bugStatusList2", inputBugStatusList2);
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

						NewAndExistingDefectsDTO newDefectDetailDTO = null;
						String testType = null;
						while (results.next()) {
							testType = results.getString("testType");
							if (testType != null && testType.length() > 0) {
								newDefectDetailDTO = new NewAndExistingDefectsDTO();
								newDefectDetailDTO.setSumSev1(results
										.getInt("sumSev1"));
								newDefectDetailDTO.setSumSev2(results
										.getInt("sumSev2"));
								newDefectDetailDTO.setSumSev3(results
										.getInt("sumSev3"));
								newDefectDetailDTO.setSumSev4(results
										.getInt("sumSev4"));
								newDefectDetailDTO.setSumSev5(results
										.getInt("sumSev5"));
								if (testType != null && testType.contains("&"))
									testType = testType.replace("&", "and");
								newDefectDetailDTO.setTestType(testType);
								newDefectDetailDTO.setTotal(results
										.getInt("total"));
								newDefectDTOasList.add(newDefectDetailDTO);
							}
						}
						newDefectListDTO
								.setNewAndExistingDefectsDTO(newDefectDTOasList);
					}
				});
		}catch(Exception e){
			logger.fatal("error occured while getting new and existing defects",e);
		}
		if (logger.isDebugEnabled()) {
			logger
					.debug("finish readTestTypeInformationByInputList/getNewDefects");
			logger.debug("returning " + newDefectDTOasList.size() + " item(s)");
		}
		return newDefectListDTO;
	}

	/**
	 * This method gets existing defects for the given inputs. one with (All
	 * Stages) stage name which accepts null and other with selected release or
	 * stage name.
	 * 
	 * @param projectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static NewAndExistingDefectsListDTO getExistingDefects(
			String projectName, String stageName, String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readTestTypeInformationByInputList");
		}

		final NewAndExistingDefectsListDTO existingDeflistDTO = new NewAndExistingDefectsListDTO();
		final List<NewAndExistingDefectsDTO> existingDefectDTOasList = new ArrayList<NewAndExistingDefectsDTO>();

		MapStream inputs = new MapStream("readTestTypeInformationByInputList");
		List<Object> inputBugStatusList2 = null;
		List<Object> inputBugStatusList1 = null;
		inputs.put("qcrDatabaseName", dbName);
		if (stageName != null
				&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
			inputBugStatusList1 = new ArrayList<Object>();
			inputBugStatusList2 = new ArrayList<Object>();
			inputBugStatusList1.add(GlobalConstants.CLOSED);
			inputs.putAllowNull("bugUserTemplate271",
					GlobalConstants.BUG_USER_TEMPLATE_27_Y); // can be null
			inputs.put("releaseName1", projectName);
			inputBugStatusList2.add(GlobalConstants.CLOSED);
			inputs.putAllowNull("bugUserTemplate272",
					GlobalConstants.BUG_USER_TEMPLATE_27_Y); // can be null
			inputs.put("releaseName2", projectName);
			inputs.put("bugStatusList1", inputBugStatusList1);
			inputs.put("bugStatusList2", inputBugStatusList2);
		} else {
			inputBugStatusList1 = new ArrayList<Object>();
			inputBugStatusList2 = new ArrayList<Object>();
			inputs.put("releaseName1", projectName);
			inputs.put("releaseCyclesName1", stageName); // optional
			inputs.put("releaseName2", projectName);
			inputs.put("releaseCyclesName2", stageName); // optional
			inputBugStatusList1.add(GlobalConstants.CLOSED);
			inputBugStatusList2.add(GlobalConstants.CLOSED);
			inputs.put("bugStatusList1", inputBugStatusList1);
			inputs.put("bugStatusList2", inputBugStatusList2);
			inputs.putAllowNull("bugUserTemplate271",
					GlobalConstants.BUG_USER_TEMPLATE_27_Y); // can be null
			inputs.putAllowNull("bugUserTemplate272",
					GlobalConstants.BUG_USER_TEMPLATE_27_Y); // can be null
		}

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
				GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						String testType = null;
						NewAndExistingDefectsDTO existingDefectDetailDTO = null;
						while (results.next()) {
							testType = results.getString("testType");
							if (testType != null && testType.length() > 0) {
								existingDefectDetailDTO = new NewAndExistingDefectsDTO();
								existingDefectDetailDTO.setSumSev1(results
										.getInt("sumSev1"));
								existingDefectDetailDTO.setSumSev2(results
										.getInt("sumSev2"));
								existingDefectDetailDTO.setSumSev3(results
										.getInt("sumSev3"));
								existingDefectDetailDTO.setSumSev4(results
										.getInt("sumSev4"));
								existingDefectDetailDTO.setSumSev5(results
										.getInt("sumSev5"));
								//replace '&' with 'and' in test types, excluding 'Security Role Based and Access' test type.
								if (testType != null && testType.contains("&")
										&& !testType.equals(GlobalConstants.SECURITY_ROLE_BASED_AND_ACCESS_TEST_TYPE))
									testType = testType.replace("&", "and");
								existingDefectDetailDTO.setTestType(testType);
								existingDefectDetailDTO.setTotal(results
										.getInt("total"));
								existingDefectDTOasList
										.add(existingDefectDetailDTO);
							}
						}
						existingDeflistDTO
								.setNewAndExistingDefectsDTO(existingDefectDTOasList);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("finish newDefectDTOasList");
			logger.debug("returning " + existingDefectDTOasList.size()
					+ " item(s)");
		}
		return existingDeflistDTO;
	}
}