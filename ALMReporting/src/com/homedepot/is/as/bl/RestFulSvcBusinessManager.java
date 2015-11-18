package com.homedepot.is.as.bl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dao.ARBCommentsDAO;
import com.homedepot.is.as.dao.ActiveDefectDetailsDAO;
import com.homedepot.is.as.dao.DomainNameDetailsDAO;
import com.homedepot.is.as.dao.FMSIdDetailsDAO;
import com.homedepot.is.as.dao.NewAndExistingDefDrillDetailsDAO;
import com.homedepot.is.as.dao.NewAndExistingDefectDetailsDAO;
import com.homedepot.is.as.dao.ProjectNameDetailsDAO;
import com.homedepot.is.as.dao.StakeHoldersDAO;
import com.homedepot.is.as.dao.SubProjectNameDetailsDAO;
import com.homedepot.is.as.dao.TargetNameDetailsDAO;
import com.homedepot.is.as.dao.TestExecDetailsDAO;
import com.homedepot.is.as.dao.TestExecDrillDetailsDAO;
import com.homedepot.is.as.dao.TestTotActDrillDetailsDAO;
import com.homedepot.is.as.dao.TestTypeDetailsDAO;
import com.homedepot.is.as.dto.ARBCOmmentsListDTO;
import com.homedepot.is.as.dto.ActiveDefectsListDTO;
import com.homedepot.is.as.dto.DomainListDTO;
import com.homedepot.is.as.dto.FMSIdListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsDrillResultListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsListDTO;
import com.homedepot.is.as.dto.ProjectListDTO;
import com.homedepot.is.as.dto.ReadQCReportHeaderCycleAndDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadQCTestReportDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderCycleAndDetailByInputDTO;
import com.homedepot.is.as.dto.StakeHoldersDTO;
import com.homedepot.is.as.dto.SubProjectListDTO;
import com.homedepot.is.as.dto.TargetListDTO;
import com.homedepot.is.as.dto.TestExecDrillResultListDTO;
import com.homedepot.is.as.dto.TestExecResultListDTO;
import com.homedepot.is.as.dto.TestExecResultsDTO;
import com.homedepot.is.as.dto.TestTotActDrillResultListDTO;
import com.homedepot.is.as.dto.TestTypesDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.is.as.util.XMLGenerator;
import com.homedepot.ta.aa.dao.exceptions.QueryException;

/**
 * Business layer implementation class.
 * 
 * @author SXB8898
 * 
 */
public class RestFulSvcBusinessManager {

	private static final Logger logger = Logger
			.getLogger(RestFulSvcBusinessManager.class);

	/**
	 * This method gets list of domain names.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public DomainListDTO fetchDomainNames() throws ClassNotFoundException,
			IOException {
		logger.debug("fetchDomainNames() - Starts");
		DomainListDTO domainListDTO = null;

		try {
			domainListDTO = DomainNameDetailsDAO.readDomainNames();
		} catch (Exception e) {
			logger.fatal("Error processing while fetchDomainNames", e);
		}

		logger.debug("fetchDomainNames() - Ends");
		return domainListDTO;
	}

	/**
	 * This method gets domain name for given doaminId.
	 * 
	 * @param domainId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public DomainListDTO fetchDomainNameById(String domainId)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchDomainNameById() - Starts");
		DomainListDTO domainListDTO = null;

		try {
			domainListDTO = DomainNameDetailsDAO.readDomainNameById(domainId);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchDomainNamesById", e);
		}

		logger.debug("fetchDomainNameById() - Ends");
		return domainListDTO;
	}

	/**
	 * This method gets list of project or domainSubProject names.
	 * 
	 * @param domainName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ProjectListDTO fetchProjectNames(String domainName)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchProjectNames() - Starts");
		ProjectListDTO projectListDTO = null;
		try {
			projectListDTO = ProjectNameDetailsDAO
					.readProjectsByDomainName(domainName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchProjectNames", e);
		}

		logger.debug("fetchProjectNames() - Ends");
		return projectListDTO;
	}

	/**
	 * This method gets project name for given Id.
	 * 
	 * @param projectId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ProjectListDTO fetchProjectNameById(String projectId)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchProjectNameById() - Starts");
		ProjectListDTO projectListDTO = null;
		try {
			projectListDTO = ProjectNameDetailsDAO
					.readProjectsByProjectId(projectId);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchProjectNameById", e);
		}

		logger.debug("fetchProjectNameById() - Ends");
		return projectListDTO;
	}

	/**
	 * This method gets list of fmsIds.
	 * 
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public FMSIdListDTO fetchFMSIds(String dbName)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchFMSIds() - Starts");
		FMSIdListDTO fmsListDTO = null;
		try {
			fmsListDTO = FMSIdDetailsDAO.readFMSIdsList(dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchFMSIds", e);
		}

		logger.debug("fetchFMSIds() - Ends");
		return fmsListDTO;
	}

	/**
	 * This method gets the releaseIds or subProjectIds and subProjectName or
	 * releaseNames
	 * 
	 * @param dbName
	 * @param fmsId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public SubProjectListDTO fetchSubProjectNames(String dbName, String fmsId)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchSubProjectNames() - Starts");
		SubProjectListDTO subprojectListDTO = null;
		try {
			subprojectListDTO = SubProjectNameDetailsDAO
					.readSubProjectsByInputList(dbName, fmsId);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchSubProjectNames", e);
		}

		logger.debug("fetchSubProjectNames() - Ends");
		return subprojectListDTO;
	}

	/**
	 * This method gets project name.
	 * 
	 * @param subProjectId
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public SubProjectListDTO fetchSubProjectNameById(String subProjectId,
			String dbName) throws ClassNotFoundException, IOException {
		logger.debug("fetchSubProjectNameById() - Starts");
		SubProjectListDTO subprojectListDTO = null;
		try {
			subprojectListDTO = SubProjectNameDetailsDAO
					.readReleasesByReleaseId(subProjectId, dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchSubProjectNameById", e);
		}

		logger.debug("fetchSubProjectNameById() - Ends");
		return subprojectListDTO;
	}

	/**
	 * This method gets stage or release cycle Names.
	 * 
	 * @param subProjectName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TargetListDTO fetchTargetNames(String subProjectName, String dbName)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchTargetNames() - Starts");

		TargetListDTO targetCycleNamesListDTO = null;
		try {

			targetCycleNamesListDTO = TargetNameDetailsDAO
					.readReleaseCyclesAndReleasesDetailsByInputList(dbName,
							subProjectName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchTargetNames", e);
		}

		logger.debug("fetchTargetNames() - Ends");
		return targetCycleNamesListDTO;
	}

	/**
	 * This method gets stageName or release cycleName.
	 * 
	 * @param stageId
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TargetListDTO fetchTargetNameById(String stageId, String dbName)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchTargetNameById() - Starts");

		TargetListDTO targetCycleNamesListDTO = null;
		try {
			if (stageId != null
					&& stageId.equalsIgnoreCase(GlobalConstants.ALL_STAGES))
				stageId = null;
			if (stageId != null)
				targetCycleNamesListDTO = TargetNameDetailsDAO
						.readReleaseCyclesByReleaseCycleId(stageId, dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchTargetNameById", e);
		}

		logger.debug("fetchTargetNameById() - Ends");
		return targetCycleNamesListDTO;
	}

	/**
	 * This method gets test types for given inputs.
	 * 
	 * @param subProjectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public List<TestTypesDTO> fetchTestTypes(String projectName,
			String stageName, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchTestTypes() - Starts");
		List<TestTypesDTO> testTypeListDTO = null;
		try {

			/*
			 * if (stageName != null &&
			 * stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
			 * stageName = null; }
			 */
			// testTypeListDTO = TestTypeDetailsDAO.readTestTypeDetailsList(
			// projectName, stageName, dbName);
			// changed test type calls to load test types from WATER_FALL_DB
			testTypeListDTO = TestTypeDetailsDAO
					.readAllListsDetailsByInputList();

		} catch (Exception e) {
			logger.fatal("Error processing while fetchTestTypes", e);
		}

		logger.debug("fetchTestTypes() - Ends");
		return testTypeListDTO;
	}

	/**
	 * This method gets test execution results.
	 * 
	 * @param dbName
	 * @param projectName
	 * @param stageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TestExecResultListDTO fetchTestExecResults(String dbName,
			String projectName, String stageName)
			throws ClassNotFoundException, IOException {
		logger.debug("fetchTestExecResults() - Starts");
		TestExecResultListDTO textExecListDTO = null;
		List<TestTypesDTO> testTypeList = null;
		List<TestExecResultsDTO> testExecDTOasList = new ArrayList<TestExecResultsDTO>();
		List<TestExecResultsDTO> existingExecDTOasList = null;
		List<TestExecResultsDTO> totalTestCasesList = null;
		try {
			if (logger.isDebugEnabled()) {
				logger
						.debug("Loading Test types in  fetchTestExecResults() - Starts");
			}
			// load all existing test types
			testTypeList = TestTypeDetailsDAO.readAllListsDetailsByInputList();
			
			// set missing test type property to existing list VO by defaulting
			// other properties
			if (testTypeList != null && !testTypeList.isEmpty()) {
				try {
					textExecListDTO = TestExecDetailsDAO.getTestExecResults(
							dbName, projectName, stageName);
					// persist existing results
					if (textExecListDTO != null) {
						//copy the textExecListDTO to format the list
						existingExecDTOasList = textExecListDTO
								.getTestExecDTO();
						for (TestTypesDTO i : testTypeList) {
							String testType = null;
							testType = i.getTestType();
							if (testType != null) {
								if (validateExecTestType(testType,
										textExecListDTO)) {
									continue;
								} else {
									testExecDTOasList = loadExecDefaultValues(
											testExecDTOasList, testType);
								}
							}
						}
						totalTestCasesList = totalExecTestType(textExecListDTO,
								totalTestCasesList);

						// remove total defects test type from
						// existingNewDefectsList
						if (existingExecDTOasList != null
								&& !existingExecDTOasList.isEmpty()) {
							for (int i = 0; i < existingExecDTOasList.size(); i++) {
								String totalTestType = existingExecDTOasList
										.get(i).getTestType();
								if (totalTestType != null
										&& totalTestType
												.equalsIgnoreCase(GlobalConstants.TOTAL_TEST_CASES))
									existingExecDTOasList
											.remove(existingExecDTOasList
													.get(i));
							}
						}
						// add existing and non-existing VO properties to new
						// list VO
						if (existingExecDTOasList != null) {
							existingExecDTOasList.addAll(testExecDTOasList);
							Collections.sort(existingExecDTOasList,
									new TestExecResultsDTO());
							if (totalTestCasesList != null
									&& !totalTestCasesList.isEmpty())
								//add totalTestCasesList which should contain only total test cases value 
								//& make sure to add total test cases as the last list element
								existingExecDTOasList
										.addAll(totalTestCasesList);
							// add sorted and update list to VO
							textExecListDTO
									.setTestExecDTO(existingExecDTOasList);
						}
					}
				} catch (Exception ex) {
					logger
							.fatal(
									"Error occured while processing fetchTestExecResults ",
									ex);
				}
			}

		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing test types in fetchTestExecResults ",
							e);
		}finally{
			logger.fatal("Nullify all list values in fetchTestExecResults method.");
			testTypeList = null;
			testExecDTOasList = null;
			existingExecDTOasList = null;
			totalTestCasesList = null;

		}
		logger.debug("fetchTestExecResults() - Ends");
		return textExecListDTO;
	}

	/**
	 * This method validates existence of test types in the current DTO list
	 * object, reading from actual test types list.
	 * 
	 * @param type
	 * @param existDefectsListDTO
	 * @return
	 */
	private boolean validateExecTestType(String type,
			TestExecResultListDTO existDefectsListDTO) {
		List<TestExecResultsDTO> existingList = existDefectsListDTO
				.getTestExecDTO();
		for (int i = 0; i < existingList.size(); i++) {
			String temp = existingList.get(i).getTestType();
			if (temp != null && temp.equals(type))
				return true;
		}
		return false;
	}

	/**
	 * This method adds default values to missing test types.
	 * 
	 * @param newDefectDTOList
	 * @param testType
	 * @return
	 */
	private List<TestExecResultsDTO> loadExecDefaultValues(
			List<TestExecResultsDTO> execDTOList, String testType) {
		TestExecResultsDTO execResultsDTO;
		execResultsDTO = new TestExecResultsDTO();
		execResultsDTO.setTestType(testType);
		execResultsDTO.setBlocked(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO
				.setDefectDensity(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setDescoped(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setFailed(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setInProgress(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setNoRun(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setPassed(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setPlanned(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setTotActive(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execResultsDTO.setTotOverall(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		execDTOList.add(execResultsDTO);
		return execDTOList;
	}

	/**
	 * This method sets Total test cases Test Type as the last field after all
	 * test types are set in the DTO.
	 * 
	 * @param existDefectsListDTO
	 * @param totalDefectsList
	 * @return
	 */
	private List<TestExecResultsDTO> totalExecTestType(
			TestExecResultListDTO existListDTO,
			List<TestExecResultsDTO> totalDefectsList) {
		TestExecResultsDTO existingResultsDTO;
		for (int i = 0; i < existListDTO.getTestExecDTO().size(); i++) {
			String totalTestType = existListDTO.getTestExecDTO().get(i)
					.getTestType();
			if (totalTestType != null
					&& totalTestType
							.equalsIgnoreCase(GlobalConstants.TOTAL_TEST_CASES)) {
				totalDefectsList = new ArrayList<TestExecResultsDTO>();
				existingResultsDTO = new TestExecResultsDTO();
				existingResultsDTO.setTestType(totalTestType);
				existingResultsDTO.setBlocked(existListDTO.getTestExecDTO()
						.get(i).getBlocked());
				existingResultsDTO.setDefectDensity(existListDTO
						.getTestExecDTO().get(i).getDefectDensity());
				existingResultsDTO.setDescoped(existListDTO.getTestExecDTO()
						.get(i).getDescoped());
				existingResultsDTO.setFailed(existListDTO.getTestExecDTO().get(
						i).getFailed());
				existingResultsDTO.setInProgress(existListDTO.getTestExecDTO()
						.get(i).getInProgress());
				existingResultsDTO.setNoRun(existListDTO.getTestExecDTO()
						.get(i).getNoRun());
				existingResultsDTO.setPassed(existListDTO.getTestExecDTO().get(
						i).getPassed());
				existingResultsDTO.setPlanned(existListDTO.getTestExecDTO()
						.get(i).getPlanned());
				existingResultsDTO.setTotActive(existListDTO.getTestExecDTO()
						.get(i).getTotActive());
				existingResultsDTO.setTotOverall(existListDTO.getTestExecDTO()
						.get(i).getTotOverall());
				totalDefectsList.add(existingResultsDTO);
			}
		}
		return totalDefectsList;
	}

	/**
	 * This method gets test execution drill results.
	 * 
	 * @param drillStat
	 * @param drillType
	 * @param projectName
	 * @param stageName
	 * @param stat1
	 * @param stat2
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TestExecDrillResultListDTO fetchTestExecDrillResults(
			String drillStat, String drillType, String projectName,
			String stageName, String stat1, String stat2, String dbName)
			throws ClassNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("fetchTestExecDrillResults() - Starts");
		}
		TestExecDrillResultListDTO textExecDrillListDTO = null;
		try {
			//replace drill type if contains 'and' with '&' excluding 'Security Role Based and Access' test type
			drillType = replaceTestType(drillType);
			textExecDrillListDTO = TestExecDrillDetailsDAO
					.getTestExecDrillResults(drillStat, drillType, projectName,
							stageName, stat1, stat2, dbName);
		} catch (Exception e) {
			logger.fatal(
					"Error occured while processing fetchTestExecDrillResults",
					e);
		}
		logger.debug("fetchTestExecDrillResults() - Ends");
		return textExecDrillListDTO;
	}

	/**
	 * This method gets total active drill results.
	 * 
	 * @param drillStat
	 * @param drillType
	 * @param projectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TestTotActDrillResultListDTO fetchTestTotActDrillResults(
			String drillStat, String drillType, String projectName,
			String stageName, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchTestTotActDrillResults() - Starts");
		TestTotActDrillResultListDTO textTotActDrillListDTO = null;
		try {
			//replace drill type if contains 'and' with '&' excluding 'Security Role Based and Access' test type
			drillType = replaceTestType(drillType);
			textTotActDrillListDTO = TestTotActDrillDetailsDAO
					.getTestTotActDrillResults(drillStat, drillType,
							projectName, stageName, dbName);
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing fetchTestTotActDrillResults",
							e);
		}

		logger.debug("fetchTestTotActDrillResults() - Ends");
		return textTotActDrillListDTO;
	}

	/**
	 * This method gets open defects by severity.
	 * 
	 * @param projectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public NewAndExistingDefectsListDTO fetchNewDefects(String projectName,
			String stageName, String dbName) throws ClassNotFoundException,
			IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("fetchNewDefects() - Starts");
		}
		NewAndExistingDefectsListDTO newDefectsListDTO = null;
		List<TestTypesDTO> testTypeList = null;
		List<NewAndExistingDefectsDTO> newDefectDTOList = new ArrayList<NewAndExistingDefectsDTO>();
		List<NewAndExistingDefectsDTO> existingNewDefectsList = null;
		List<NewAndExistingDefectsDTO> totalDefectsList = null;
		try {
			if (logger.isDebugEnabled()) {
				logger
						.debug("Loading Test types in  fetchNewDefects() - Starts");
			}
			// get existing test types and then get new defects
			testTypeList = TestTypeDetailsDAO.readAllListsDetailsByInputList();

			// set missing test type property to existing list VO by defaulting
			// other properties
			if (testTypeList != null && !testTypeList.isEmpty()) {
				try {
					newDefectsListDTO = NewAndExistingDefectDetailsDAO
							.getNewDefects(projectName, stageName, dbName);
					// persist newDefectsList to existingNewDefectsList for
					// further processing/sorting
					if (newDefectsListDTO != null) {
						existingNewDefectsList = newDefectsListDTO
								.getNewAndExistingDefectsDTO();
						
						for (TestTypesDTO i : testTypeList) {
							String testType = null;
							testType = i.getTestType();
							if (testType != null) {
								if (validateTestType(testType,
										newDefectsListDTO)) {
									continue;
								} else {
									newDefectDTOList = loadDefaultValues(
											newDefectDTOList, testType);
								}
							}
						}
						totalDefectsList = totalDefectType(newDefectsListDTO,
								totalDefectsList);

						// remove total defects test type from
						// existingNewDefectsList
						if (existingNewDefectsList != null
								&& !existingNewDefectsList.isEmpty()) {
							for (int i = 0; i < existingNewDefectsList.size(); i++) {
								String totalDefects = existingNewDefectsList
										.get(i).getTestType();
								if (totalDefects != null
										&& totalDefects
												.equals(GlobalConstants.TOTAL_DEFECTS)) {
									existingNewDefectsList
											.remove(existingNewDefectsList
													.get(i));
								}
							}
						}

						// add existing and non-existing VO properties to new
						// list VO
						if (existingNewDefectsList != null) {
							existingNewDefectsList.addAll(newDefectDTOList);
							Collections.sort(existingNewDefectsList,
									new NewAndExistingDefectsDTO());
							if (totalDefectsList != null
									&& !totalDefectsList.isEmpty())
								existingNewDefectsList.addAll(totalDefectsList);
							// add updated list to VO
							newDefectsListDTO
									.setNewAndExistingDefectsDTO(existingNewDefectsList);
						}
					}
				} catch (Exception ex) {
					logger.fatal("Error processing while fetchNewDefects", ex);
				}
			}

		} catch (Exception e) {
			logger.fatal(
					"Error processing Test Types in while fetchNewDefects", e);
		} finally {
			logger.info("Nullify unused list objects in fetchNewDefects.");
			testTypeList = null;
			newDefectDTOList = null;
			totalDefectsList = null;
			existingNewDefectsList = null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("fetchNewDefects() - Ends");
		}
		return newDefectsListDTO;
	}

	/**
	 * This method adds default values to missing test types.
	 * 
	 * @param newDefectDTOList
	 * @param testType
	 * @return
	 */
	private List<NewAndExistingDefectsDTO> loadDefaultValues(
			List<NewAndExistingDefectsDTO> newDefectDTOList, String testType) {
		NewAndExistingDefectsDTO newDefectDTO;
		newDefectDTO = new NewAndExistingDefectsDTO();
		newDefectDTO.setTestType(testType);
		newDefectDTO.setSumSev1(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTO.setSumSev2(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTO.setSumSev3(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTO.setSumSev4(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTO.setSumSev5(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTO.setTotal(GlobalConstants.DEFAULT_INTEGER_PROPERTY);
		newDefectDTOList.add(newDefectDTO);
		return newDefectDTOList;
	}

	/**
	 * This method gets new defects drill results.
	 * 
	 * @param drillType
	 * @param projectName
	 * @param stageName
	 * @param severity
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public NewAndExistingDefectsDrillResultListDTO fetchNewDefDrillResults(
			String drillType, String projectName, String stageName,
			String severity, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchNewDefDrillResults() - Starts");
		NewAndExistingDefectsDrillResultListDTO newDefDrillListDTO = null;
		try {
			//replace drill type if contains 'and' with '&' excluding 'Security Role Based and Access' test type
			drillType = replaceTestType(drillType);
			newDefDrillListDTO = NewAndExistingDefDrillDetailsDAO
					.getNewDefDrillResults(drillType, projectName, stageName,
							severity, dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchNewDrillResults", e);
		}

		logger.debug("fetchNewDefDrillResults() - Ends");
		return newDefDrillListDTO;
	}

	/**
	 * This method replaces 'and' with '&' for Test Types,
	 * so that test type data matches HP and TDH DB's.
	 * 
	 * @param drillType
	 * @return
	 */
	private String replaceTestType(String drillType) {
		if(drillType != null
				&& drillType.contains("and")
				&& !drillType.equals(GlobalConstants.SECURITY_ROLE_BASED_AND_ACCESS_TEST_TYPE)){
			String replaceDrillType = drillType.replace("and", "&");
			drillType = replaceDrillType;
		}
		return drillType;
	}

	/**
	 * This method gets existing defects and sets missing test type, and other
	 * properties with default values.
	 * 
	 * @param projName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public NewAndExistingDefectsListDTO fetchExistingDefects(String projName,
			String stageName, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchExistingDefects() - Starts");
		NewAndExistingDefectsListDTO existDefectsListDTO = null;
		List<NewAndExistingDefectsDTO> existingDefectDTOasList = new ArrayList<NewAndExistingDefectsDTO>();
		List<TestTypesDTO> testTypeList = null;
		List<NewAndExistingDefectsDTO> existingDefectsList = null;
		List<NewAndExistingDefectsDTO> totalDefectsList = null;
		try {
			if (logger.isDebugEnabled()) {
				logger
						.debug("Loading Test types in  fetchExistingDefects() - Starts");
			}
			// get existing testTypes and then get existing defects
			testTypeList = TestTypeDetailsDAO.readAllListsDetailsByInputList();
			
			if (testTypeList != null && !testTypeList.isEmpty()) {
				try {
					existDefectsListDTO = NewAndExistingDefectDetailsDAO
							.getExistingDefects(projName, stageName, dbName);
					if (existDefectsListDTO != null) {
						existingDefectsList = existDefectsListDTO
								.getNewAndExistingDefectsDTO();
						
						for (TestTypesDTO i : testTypeList) {
							String testType = null;
							testType = i.getTestType();
							if (testType != null) {
								if (validateTestType(testType,
										existDefectsListDTO)) {
									continue;
								} else {
									// add default values and test types
									existingDefectDTOasList = loadDefaultValues(
											existingDefectDTOasList, testType);
								}
							}
						}
						// get TOTAL DEFECTS test type and add to the existing
						// list
						// to maintain require sequence
						totalDefectsList = totalDefectType(existDefectsListDTO,
								totalDefectsList);

						// remove total defects test type from
						// existingDefectsList
						if (existingDefectsList != null
								&& !existingDefectsList.isEmpty()) {
							for (int i = 0; i < existingDefectsList.size(); i++) {
								String totalDefects = existingDefectsList
										.get(i).getTestType();
								if (totalDefects != null
										&& totalDefects
												.equals(GlobalConstants.TOTAL_DEFECTS)) {
									existingDefectsList
											.remove(existingDefectsList.get(i));
								}
							}
						}
						// add existing VO with test types and non-existing test
						// types
						// to new list VO
						if (existingDefectsList != null) {
							existingDefectsList.addAll(existingDefectDTOasList);
							Collections.sort(existingDefectsList,
									new NewAndExistingDefectsDTO());
							// after sorting test types, add Total Defects type
							if (totalDefectsList != null
									&& !totalDefectsList.isEmpty())
								existingDefectsList.addAll(totalDefectsList);
							existDefectsListDTO
									.setNewAndExistingDefectsDTO(existingDefectsList);
						}
					}
				} catch (Exception ex) {
					logger.fatal("Error processing while fetchExistingDefects",
							ex);
				}
			}
		} catch (Exception e) {
			logger
					.fatal(
							"Error processing while processibg Test Types in fetchExistingDefects",
							e);
		} finally {
			logger.info("Nullify list objects in fetchExistingDefects");
			testTypeList = null;
			totalDefectsList = null;
			existingDefectDTOasList = null;
			existingDefectsList = null;
		}

		logger.debug("fetchExistingDefects() - Ends");
		return existDefectsListDTO;
	}

	/**
	 * This method sets Total Defects Test Type as the last field after all test
	 * types are set in the DTO.
	 * 
	 * @param existDefectsListDTO
	 * @param totalDefectsList
	 * @return
	 */
	private List<NewAndExistingDefectsDTO> totalDefectType(
			NewAndExistingDefectsListDTO existDefectsListDTO,
			List<NewAndExistingDefectsDTO> totalDefectsList) {
		NewAndExistingDefectsDTO existingDefectDetailDTO;
		for (int i = 0; i < existDefectsListDTO.getNewAndExistingDefectsDTO()
				.size(); i++) {
			String totalTestType = existDefectsListDTO
					.getNewAndExistingDefectsDTO().get(i).getTestType();
			if (totalTestType != null
					&& totalTestType.equals(GlobalConstants.TOTAL_DEFECTS)) {
				totalDefectsList = new ArrayList<NewAndExistingDefectsDTO>();
				existingDefectDetailDTO = new NewAndExistingDefectsDTO();
				existingDefectDetailDTO.setTestType(totalTestType);
				existingDefectDetailDTO.setSumSev1(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getSumSev1());
				existingDefectDetailDTO.setSumSev2(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getSumSev2());
				existingDefectDetailDTO.setSumSev3(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getSumSev3());
				existingDefectDetailDTO.setSumSev4(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getSumSev4());
				existingDefectDetailDTO.setSumSev5(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getSumSev5());
				existingDefectDetailDTO.setTotal(existDefectsListDTO
						.getNewAndExistingDefectsDTO().get(i).getTotal());
				totalDefectsList.add(existingDefectDetailDTO);
			}
		}
		return totalDefectsList;
	}

	/**
	 * This method validates existence of test types in the current DTO list
	 * object, reading from actual test types list.
	 * 
	 * @param type
	 * @param existDefectsListDTO
	 * @return
	 */
	private boolean validateTestType(String type,
			NewAndExistingDefectsListDTO existDefectsListDTO) {
		List<NewAndExistingDefectsDTO> existingList = existDefectsListDTO
				.getNewAndExistingDefectsDTO();
		for (int i = 0; i < existingList.size(); i++) {
			String temp = existingList.get(i).getTestType();
			if (temp != null && temp.equals(type))
				return true;
		}
		return false;
	}

	/**
	 * This method get the existing defects drill results.
	 * 
	 * @param drillType
	 * @param projectName
	 * @param stageName
	 * @param severity
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public NewAndExistingDefectsDrillResultListDTO fetchExistingDefDrillResults(
			String drillType, String projectName, String stageName,
			String severity, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchExistingDefDrillResults() - Starts");
		NewAndExistingDefectsDrillResultListDTO existingDefDrillListDTO = null;
		try {
			//replace drill type if contains 'and' with '&' excluding 'Security Role Based and Access' test type
			drillType = replaceTestType(drillType);
			existingDefDrillListDTO = NewAndExistingDefDrillDetailsDAO
					.getExistingDefDrillResults(drillType, projectName,
							stageName, severity, dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetcExistingDefDrillResults",
					e);
		}

		logger.debug("fetchExistingDefDrillResults() - Ends");
		return existingDefDrillListDTO;
	}

	/**
	 * This method gets active defects for given criteria.
	 * 
	 * @param projectName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ActiveDefectsListDTO fetchActiveDefects(String projectName,
			String stageName, String dbName) throws ClassNotFoundException,
			IOException {
		logger.debug("fetchActiveDefects() - Starts");
		ActiveDefectsListDTO activeDefectsListDTO = null;
		try {
			activeDefectsListDTO = ActiveDefectDetailsDAO.getActiveDefects(
					projectName, stageName, dbName);
		} catch (Exception e) {
			logger.fatal("Error processing while fetchActiveDefects", e);
		}

		logger.debug("fetchActiveDefects() - Ends");
		return activeDefectsListDTO;
	}

	/**
	 * This method inserts records into QC_TEST_RPT_HDR, QC_TEST_RPT_CYC,
	 * QC_TEST_RPT_DTL table by batch operation.
	 * 
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @param stageName
	 * @param stageId
	 * @param dbName
	 * @param arbRequestXML
	 * @return
	 */
	public ARBCOmmentsListDTO createQCTestARBReport(String domainId,
			String domainProjectId, String fmsId, String projectId,
			String stageName, String stageId, String dbName,
			String projectPath, String arbRequestXML, 
			List<String> roleList) {

		if (logger.isDebugEnabled())
			logger.debug("createQCTestARBReport() - Starts");

		ARBCOmmentsListDTO arbComments = null;
		List<ARBCOmmentsListDTO> arbCommentsList = null;
		int reportId = 0;
		try {
			arbCommentsList = new ArrayList<ARBCOmmentsListDTO>();
			arbCommentsList = (List<ARBCOmmentsListDTO>) XMLGenerator
					.transformXmlToARBCOmmentsListDTO(arbRequestXML);

			if (arbCommentsList != null && !arbCommentsList.isEmpty()
					&& null != roleList && !roleList.isEmpty()) {
				arbComments = ARBCommentsDAO.createQualityCenterBatchInsert(
						dbName, domainId, domainProjectId, fmsId, projectId,
						stageName, stageId, projectPath, arbCommentsList);
			}
			if(arbComments != null && arbComments.getReportId() != null
					&& arbComments.getReportId().trim().length() > 0){
				reportId = StakeHoldersDAO.createStakeHoldersRole(arbComments.getReportId(), dbName, roleList);
				arbComments.setReportId(Integer.toString(reportId));
			}
		} catch (Exception e) {
			logger.fatal("Error while creating ARB comments report: ", e);
		} finally {
			// nullify arbCommentsList
			arbCommentsList = null;
			roleList = null;
		}
		if (logger.isDebugEnabled())
			logger.debug("createQCTestARBReport() - Ends");

		return arbComments;
	}

	/**
	 * This method updates records to QC_TEST_RPT_DTL table,
	 *  and project path for given report Id in QC_TEST_RPT_HDR table.
	 * 
	 * @param reportId
	 * @param dbName
	 * @param arbUpdateXML
	 * @return
	 */
	public boolean updateARBQCTestReport(String reportId, String dbName,
			String arbUpdateXML, List<String> roleList, String projectPath) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateARBQCTestReport() - Start");
		}

		boolean updVal = false;
		//dtlFlag is set to false to retrieve Header table Ids to update project path.
		final boolean dtlFlag = false;
		List<ARBCOmmentsListDTO> arbCommentsList = null;
		ReadQCReportHeaderCycleAndDetailByInputListDTO hdrIdsDTO = null;
		
		try {
			//update project path in header table
			//if(projectPath != null){
				//project path is editable field, need to pass explicitly
				hdrIdsDTO = ARBCommentsDAO.readReportHeaderAndDetailByIds(reportId, dtlFlag);
				if(hdrIdsDTO != null){
					ReadReportHeaderCycleAndDetailByInputDTO hdrDTO = null;
					/*for(int i = 0; i < hdrIdsDTO.getQcReportDetailList().size();i++){
						hdrDTO = hdrIdsDTO.getQcReportDetailList().get(i);
						updVal = ARBCommentsDAO.updateQualityCenterTestReportHeaderByInputList(hdrDTO, projectPath);
					}*/
					hdrDTO = hdrIdsDTO.getQcReportDetailList().get(0);
					updVal = ARBCommentsDAO.updateQualityCenterTestReportHeaderByInputList(hdrDTO, projectPath);
				}
			//}// end of project path
			//if updVal is true, update detail table
			if(updVal){
				arbCommentsList = new ArrayList<ARBCOmmentsListDTO>();
				arbCommentsList = (List<ARBCOmmentsListDTO>) XMLGenerator
							.transformXmlToARBCOmmentsListDTO(arbUpdateXML);
			
				if (arbCommentsList != null) {
					updVal = ARBCommentsDAO
							.updateQualityCenterTestReportDetailBatch(dbName,
									reportId, arbCommentsList);
				}else{
					updVal = false;
				}
			}
			//if updVal is true, update roles table
			if(updVal){
				if(null != roleList && !roleList.isEmpty()){
					updVal = StakeHoldersDAO.updateStakeHoldersRole(reportId, roleList);
				}else{
					updVal = false;
				}
			}
		} catch (Exception e) {
			updVal = false;
			logger.fatal("Error while updating ARB comments report", e);
		} finally {
			// nullify arbCommentsList
			arbCommentsList = null;
			// nullify roleList
			roleList = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateARBQCTestReport() - Ends");
		}
		return updVal;
	}

	/**
	 * This method checks for existence of record in header table.
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @return
	 */
	public boolean readHeaderForExistence(String dbName, String domainId,
			String domainProjectId, String fmsId, String projectId) {
		if (logger.isDebugEnabled()) {
			logger.debug("readHeaderForExistence() - Starts");
		}
		Object obj = null;
		try {
			obj = ARBCommentsDAO.readHeaderForExistenceByInputList(dbName,
					domainId, domainProjectId, fmsId, projectId);
		} catch (QueryException e) {
			logger.fatal(
					"Error while fetching records from Header table existence",
					e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("readHeaderForExistence() - Ends");
		}
		return Boolean.valueOf(obj.toString());
	}

	/**
	 * This method checks for existence of record in cycle table.
	 * 
	 * @param reportId
	 * @param stageId
	 * @return
	 */
	public boolean readCycleForExistence(String reportId, String stageId) {
		if (logger.isDebugEnabled()) {
			logger.debug("readCycleForExistence() - starts");
		}
		Object obj = null;
		try {
			if (stageId != null
					&& (stageId.equalsIgnoreCase(GlobalConstants.ALL_STAGES) || stageId
							.equalsIgnoreCase(GlobalConstants.UNDEFINED)))
				stageId = null;
			obj = ARBCommentsDAO.readReportCycleForExistenceByInputList(
					reportId, stageId);
		} catch (QueryException e) {
			logger.fatal(
					"Error while fetching records from Cycle table existence",
					e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("readCycleForExistence() - Ends");
		}
		return Boolean.valueOf(obj.toString());
	}

	/**
	 * This method checks existence of record in cycle table and returns cycle
	 * Id.
	 * 
	 * @param reportId
	 * @return
	 */
	public Object readReleaseCycle(String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("readReleaseCycle() - starts");
		}
		Object obj = null;
		try {
			obj = ARBCommentsDAO
					.readReportCycleForExistenceByInputList(reportId);
		} catch (QueryException e) {

			logger
					.fatal(
							"Error while fetching release Cycle Id from Cycle table",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("readReleaseCycle() - Ends");
		}
		return obj.toString();
	}

	/**
	 * This method gets the reportId from header table.
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @return
	 */
	public List<ReadReportHeaderByInputListDTO> fetchReportHeaderByInputList(
			String dbName, String domainId, String domainProjectId,
			String fmsId, String projectId) {
		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportHeaderByInputList() - Starts");
		}
		List<ReadReportHeaderByInputListDTO> headerByInputList = null;
		try {
			headerByInputList = ARBCommentsDAO.readReportHeaderByInputList(
					dbName, domainId, domainProjectId, fmsId, projectId);
		} catch (QueryException e) {
			logger
					.fatal("Error occured while getting Ids from header table",
							e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportHeaderByInputList() - Ends");
		}
		return headerByInputList;
	}

	/**
	 * This method gets the reportId and stageId from cycle table.
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @param stageId
	 * @return
	 */
	public List<ReadReportHeaderByInputListDTO> fetchHeaderAndCycleByInputList(
			String dbName, String domainId, String domainProjectId,
			String fmsId, String projectId, String stageId) {
		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportHeaderAndCycleByInputList() - Starts");
		}
		List<ReadReportHeaderByInputListDTO> cycleByInputList = null;
		try {
			cycleByInputList = ARBCommentsDAO
					.readHeaderAndCycleDetailsByInputList(dbName, domainId,
							domainProjectId, fmsId, projectId, stageId);
		} catch (QueryException e) {
			logger.fatal("Error occured while getting Ids from cycle table", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportHeaderAndCycleByInputList() - Ends");
		}
		return cycleByInputList;
	}

	/**
	 * This method gets records from details table for given reportId.
	 * 
	 * @param dbName
	 * @param reportId
	 * @return
	 */
	public ReadQCTestReportDetailByInputListDTO fetchReportDetails(
			String dbName, String reportId) {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportDetails() - Starts");
		}
		ReadQCTestReportDetailByInputListDTO readReportDetails = null;

		try {
			readReportDetails = ARBCommentsDAO.readReportDetailByInputList(
					dbName, reportId);
		} catch (QueryException e) {
			logger.fatal("Error occured while fetchingReportDetails", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("fetchReportDetails() - Ends");
		}
		return readReportDetails;
	}

	/**
	 * This method gets domainId, domainSubProjId, fmsId, projectId, 
	 * test type, ARB Flag, Comments system user or dbName for the given reportId.
	 * 
	 * @param reportId
	 * @return
	 */
	public ReadQCReportHeaderCycleAndDetailByInputListDTO getARBHeaderAndDetailsById(
			String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getARBHeaderAndDetailsById() - Starts");
		}
		ReadQCReportHeaderCycleAndDetailByInputListDTO hdrDetailsList = null;
		boolean dtlFlag = true;
		try {
			//dtlFlag is true then reads header and detail table values for given reportId.
			//dtlFlag value gets updated while updating records in DAO layer to update projectPath
			//in header table.
			hdrDetailsList = ARBCommentsDAO
					.readReportHeaderAndDetailByIds(reportId, dtlFlag);
		} catch (QueryException e) {
			logger
					.fatal(
							"Error occured while processing getARBHeaderAndDetailsById",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getARBHeaderAndDetailsById() - Ends");
		}
		return hdrDetailsList;
	}

	/**
	 * This method gets domainId, domainSubProjId, fmsId, projectId, targetId or
	 * release cycle Id, test type, ARB Flag, Comments system user or dbName to
	 * the given inputs.
	 * 
	 * @param reportId
	 * @param stageId
	 * @return
	 */
	public ReadQCReportHeaderCycleAndDetailByInputListDTO getARBHeaderCycleAndDetailsById(
			String reportId, String stageId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getARBHeaderCycleAndDetailsById() - Starts");
		}
		ReadQCReportHeaderCycleAndDetailByInputListDTO hdrCycDetailsList = null;
		try {
			if (stageId != null
					&& !stageId.isEmpty()
					&& (stageId.equalsIgnoreCase(GlobalConstants.ALL_STAGES) || stageId
							.equalsIgnoreCase(GlobalConstants.UNDEFINED)))
				stageId = null;
			hdrCycDetailsList = ARBCommentsDAO
					.readReportHeaderCycleAndDetailByIds(reportId, stageId);
		} catch (QueryException e) {
			logger
					.fatal(
							"Error occured while processing getARBHeaderCycleAndDetailsById",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getARBHeaderCycleAndDetailsById() - Ends");
		}
		return hdrCycDetailsList;
	}
	
	/**
	 * This method loads stake holders details and project path
	 * and populates back to list.
	 * 
	 * @param reportId
	 * @return
	 */
	public List<StakeHoldersDTO> getStakeHolders(String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getStakeHolders() - Starts");
		}
		List<StakeHoldersDTO> stakeHoldersDTOList = null;
		List<StakeHoldersDTO> roleList = null;
		List<StakeHoldersDTO> solList = null;
		List<StakeHoldersDTO> projList = null;
		List<StakeHoldersDTO> qaManagerList = null;
		List<StakeHoldersDTO> qaLeadList = null;
		List<StakeHoldersDTO> projectPathList = null;
		try {
			stakeHoldersDTOList = StakeHoldersDAO
					.readStakeHoldersRole(reportId);
			if(stakeHoldersDTOList != null && !stakeHoldersDTOList.isEmpty()){
				for(int i = 0; i < stakeHoldersDTOList.size();i++){
					if(stakeHoldersDTOList.get(i).getRoleName().equalsIgnoreCase(GlobalConstants.SOLUTION_OWNER)){
						solList = new ArrayList<StakeHoldersDTO>();
						solList.add(stakeHoldersDTOList.get(i));
					}else if(stakeHoldersDTOList.get(i).getRoleName().equalsIgnoreCase(GlobalConstants.PROJECT_MANAGER)){
						projList = new ArrayList<StakeHoldersDTO>();
						projList.add(stakeHoldersDTOList.get(i));
					}else if(stakeHoldersDTOList.get(i).getRoleName().equalsIgnoreCase(GlobalConstants.QA_MANAGER)){
						qaManagerList = new ArrayList<StakeHoldersDTO>();
						qaManagerList.add(stakeHoldersDTOList.get(i));
					}else if(stakeHoldersDTOList.get(i).getRoleName().equalsIgnoreCase(GlobalConstants.QA_LEAD)){
						qaLeadList = new ArrayList<StakeHoldersDTO>();
						qaLeadList.add(stakeHoldersDTOList.get(i));
					}
					/* adding project path to stake Holders DTO */
					else if (stakeHoldersDTOList.get(i).getProjectPath() != null){
						projectPathList = new ArrayList<StakeHoldersDTO>();
						projectPathList.add(stakeHoldersDTOList.get(i));
					}
				}
								
				if(null != solList && !solList.isEmpty() 
						&& null != projList && !projList.isEmpty()
						&& null != qaManagerList && !qaManagerList.isEmpty()
						&& null != qaLeadList && !qaLeadList.isEmpty()
						){
					roleList = new ArrayList<StakeHoldersDTO>();
					if(null != solList && !solList.isEmpty())
						roleList.addAll(solList);
					if(null != projList && !projList.isEmpty())
						roleList.addAll(projList);
					if(null != qaManagerList && !qaManagerList.isEmpty())
						roleList.addAll(qaManagerList);
					if(null != qaLeadList && !qaLeadList.isEmpty())
						roleList.addAll(qaLeadList);
					// adding project path to roleList
					if(null != projectPathList && !projectPathList.isEmpty())
						roleList.addAll(projectPathList);
				}
			}
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing getStakeHolders",
							e);
		}finally{
			stakeHoldersDTOList = null;
			solList = null;
			projList = null;
			qaManagerList = null;
			qaLeadList = null;
			projectPathList = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getStakeHolders() - Ends");
		}
		return roleList;
	}
	
}
