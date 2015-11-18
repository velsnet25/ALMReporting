package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.NewAndExistingDefectsDrillResultListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsDrillResultsDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets new and existing defect
 * drill details.
 * 
 * @author SXB8898
 *
 */
public class NewAndExistingDefDrillDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(NewAndExistingDefDrillDetailsDAO.class);
		
	/**
	 * This method gets new defects drill result for the selected result.
	 * 
	 * @param drillType
	 * @param projName
	 * @param stageName
	 * @param severity
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static NewAndExistingDefectsDrillResultListDTO getNewDefDrillResults(
			final String drillType, final String projName,
			final String stageName, final String severity, final String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readBugByReleaseAndReleaseCyclesInputList/getNewDefDrillResults");
		}

		final NewAndExistingDefectsDrillResultListDTO newDefDrillListDTO = new NewAndExistingDefectsDrillResultListDTO();
		final List<NewAndExistingDefectsDrillResultsDTO> newDefDrillDTOasList = new ArrayList<NewAndExistingDefectsDrillResultsDTO>();

		List<Object> inputBugStatusList = null;
		MapStream inputs = new MapStream(
				"readBugByReleaseAndReleaseCyclesInputList");

		inputs.put("qcrDatabaseName", dbName);
		if (drillType != null
				&& drillType.equalsIgnoreCase(GlobalConstants.TOTAL_DEFECTS)) {
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_NEW_DEF_DRILL_ALL_RESULTS_ALL_STAGES_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					inputs.put("releaseName", projName);
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_NEW_DEF_DRILL_ALL_RESULTS_ALL_STAGES
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					inputs.putAllowNull("bugSeverity", severity); // can be
																	// null,
																	// optional
					inputs.put("releaseName", projName);
				}
			} else {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_NEW_DEF_DRILL_ALL_RESULTS_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_NEW_DEF_DRILL_ALL_RESULTS
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
				}
			}
		} else {
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_NEW_DEF_DRILL_RESULTS_ALL_STAGES_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					// can be null, optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					inputs.put("releaseName", projName);
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_NEW_DEF_DRILL_RESULTS_ALL_STAGES
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					// can be null, optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					inputs.put("releaseName", projName);
				}
			} else {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_NEW_DEF_DRILL_RESULTS_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					// can be null, optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_NEW_DEF_DRILL_RESULTS
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_N);
					// can be null, optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {

						NewAndExistingDefectsDrillResultsDTO newDefDrillDetailDTO = null;
						while (results.next()) {
							newDefDrillDetailDTO = new NewAndExistingDefectsDrillResultsDTO();
							newDefDrillDetailDTO.setApplication(results
									.getString("application"));
							newDefDrillDetailDTO.setApplicationGroup(results
									.getString("applicationGroup"));
							newDefDrillDetailDTO
									.setApplicationComponent(results
											.getString("applicationComponent"));
							newDefDrillDetailDTO.setDefectId(results
									.getInt("defectId"));
							newDefDrillDetailDTO.setSeverity(results
									.getString("severity"));
							newDefDrillDetailDTO.setSummary(results
									.getString("summary"));
							newDefDrillDetailDTO.setDetectedByTeam(results
									.getString("detectedByTeam"));
							newDefDrillDetailDTO.setAssignedToTeam(results
									.getString("assignedToTeam"));
							newDefDrillDetailDTO.setAssignedTo(results
									.getString("assignedTo"));
							newDefDrillDetailDTO.setAssignedToName(results
									.getString("assignedToName"));
							newDefDrillDetailDTO.setStatus(results
									.getString("status"));
							newDefDrillDetailDTO.setSubject(Integer
									.toString((results.getInt("subject"))));
							if (results.wasNull("subject")) {
								newDefDrillDetailDTO.setSubject(null);
							}

							newDefDrillDTOasList.add(newDefDrillDetailDTO);
						}
						newDefDrillListDTO
								.setNewAndExistingDefectsDrillDTO(newDefDrillDTOasList);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("returning " + newDefDrillDTOasList.size()
					+ " item(s)");
			logger
					.debug("finish readBugByReleaseAndReleaseCyclesInputList/getNewDefDrillResults");
		}
		return newDefDrillListDTO;
	}

	/**
	 * This method gets the existing drill defect results for the selected
	 * defect.
	 * 
	 * @param drillType
	 * @param projName
	 * @param stageName
	 * @param severity
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static NewAndExistingDefectsDrillResultListDTO getExistingDefDrillResults(
			final String drillType, final String projName,
			final String stageName, final String severity, final String dbName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readBugByReleaseAndReleaseCyclesInputList/getExistingDefDrillResults");
		}

		final NewAndExistingDefectsDrillResultListDTO existingDefDrilllistDTO = new NewAndExistingDefectsDrillResultListDTO();
		final List<NewAndExistingDefectsDrillResultsDTO> existingDefDrillDTOasList = new ArrayList<NewAndExistingDefectsDrillResultsDTO>();

		MapStream inputs = new MapStream(
				"readBugByReleaseAndReleaseCyclesInputList");
		List<Object> inputBugStatusList = null;

		inputs.put("qcrDatabaseName", dbName);
		if (drillType != null
				&& drillType.equalsIgnoreCase(GlobalConstants.TOTAL_DEFECTS)) {
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_EXISTING_DEF_DRILL_ALL_RESULTS_ALL_STAGES_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					inputs.put("releaseName", projName);
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_EXISTING_DEF_DRILL_ALL_RESULTS_ALL_STAGES
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
				}
			} else {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_EXISTING_DEF_DRILL_ALL_RESULTS_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_EXISTING_DEF_DRILL_ALL_RESULTS
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
				}
			}
		} else {
			if (stageName != null
					&& stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES)) {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_EXISTING_DEF_DRILL_RESULTS_ALL_STAGES_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null,optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					inputs.put("releaseName", projName);
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_EXISTING_DEF_DRILL_RESULTS_ALL_STAGES
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					// can be null, optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					inputs.put("releaseName", projName);
				}
			} else {
				if (severity != null
						&& severity.equalsIgnoreCase(GlobalConstants.TOTAL)) {
					// GET_EXISTING_DEF_DRILL_RESULTS_ALL_SEVERITY
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null,optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
					inputs.put("orderByFlag", true); // optional
				} else {
					// GET_EXISTING_DEF_DRILL_RESULTS
					inputBugStatusList = new ArrayList<Object>();
					inputBugStatusList.add(GlobalConstants.CLOSED);
					inputs.put("bugStatusList", inputBugStatusList);
					// can be null
					inputs.putAllowNull("bugUserTemplate27",
							GlobalConstants.BUG_USER_TEMPLATE_27_Y);
					// can be null,optional
					inputs.putAllowNull("bugUserTemplate06", drillType);
					// can be null, optional
					inputs.putAllowNull("bugSeverity", severity);
					inputs.put("releaseName", projName);
					inputs.put("releaseCyclesName", stageName); // optional
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						NewAndExistingDefectsDrillResultsDTO existingDefDrillDetailDTO = null;
						while (results.next()) {

							existingDefDrillDetailDTO = new NewAndExistingDefectsDrillResultsDTO();
							existingDefDrillDetailDTO.setApplication(results
									.getString("application"));
							existingDefDrillDetailDTO
									.setApplicationGroup(results
											.getString("applicationGroup"));
							existingDefDrillDetailDTO
									.setApplicationComponent(results
											.getString("applicationComponent"));
							existingDefDrillDetailDTO.setDefectId(results
									.getInt("defectId"));
							existingDefDrillDetailDTO.setSeverity(results
									.getString("severity"));
							existingDefDrillDetailDTO.setSummary(results
									.getString("summary"));
							existingDefDrillDetailDTO.setDetectedByTeam(results
									.getString("detectedByTeam"));
							existingDefDrillDetailDTO.setAssignedToTeam(results
									.getString("assignedToTeam"));
							existingDefDrillDetailDTO.setAssignedTo(results
									.getString("assignedTo"));
							existingDefDrillDetailDTO.setAssignedToName(results
									.getString("assignedToName"));
							existingDefDrillDetailDTO.setStatus(results
									.getString("status"));
							existingDefDrillDetailDTO.setSubject(Integer
									.toString(results.getInt("subject")));
							if (results.wasNull("subject")) {
								existingDefDrillDetailDTO.setSubject(null);
							}

							existingDefDrillDTOasList
									.add(existingDefDrillDetailDTO);
						}
						existingDefDrilllistDTO
								.setNewAndExistingDefectsDrillDTO(existingDefDrillDTOasList);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("returning " + existingDefDrillDTOasList.size()
					+ " item(s)");
			logger
					.debug("finish readBugByReleaseAndReleaseCyclesInputList/getExistingDefDrillResults");
		}
		return existingDefDrilllistDTO;
	}
}