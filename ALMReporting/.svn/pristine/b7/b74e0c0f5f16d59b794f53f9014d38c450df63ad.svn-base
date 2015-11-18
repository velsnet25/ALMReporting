package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.GenericResults;
import com.homedepot.is.as.dto.StakeHoldersDTO;
import com.homedepot.is.as.util.AppUtil;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.InsertNotifier;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.UpdateNotifier;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

public class StakeHoldersDAO {
	private static final Logger logger = Logger
			.getLogger(StakeHoldersDAO.class);

	public static int createStakeHoldersRole(String reportId, String dbName,
			List<String> roleList) throws QueryException {

		if (logger.isDebugEnabled()) {
			logger.debug("start createStakeHoldersRole");
		}
		final GenericResults result = new GenericResults();
		int returnId = 0;
		MapStream inputs = null;
		List<Object> inputRoleList = null;
		Map<String, Object> inputLIST = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		try {
			if (reportId != null && dbName != null
					&& dbName.trim().length() > 0 && roleList != null
					&& !roleList.isEmpty()) {
				inputs = new MapStream("createQualityCenterTestReportRoleBatch");
				inputRoleList = new ArrayList<Object>();
				if(null != roleList && !roleList.isEmpty()) {
					for (int i = 0; i < roleList.size(); i++) {
						inputLIST = new HashMap<String, Object>();
						if (roleList.get(i).contains(":")) {
							inputLIST.put("qualityCenterTestReportId", Integer
									.parseInt(reportId));
							inputLIST.put("createSystemUserId", dbName);
							inputLIST.put("qualityCenterReportRoleName",
									roleList.get(i).substring(0,
											roleList.get(i).indexOf(":")));
							inputLIST.put("qualityCenterReportPersonName",
									roleList.get(i).substring(
											roleList.get(i).indexOf(":") + 1,
											roleList.get(i).length()));
							inputRoleList.add(inputLIST);
						}
					}
				}
				inputs.put("createQualityCenterTestReportRoleList",
						inputRoleList);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.insertObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new InsertNotifier() {
						public void notifyInsert(Object target,
								boolean success, int count, Query query,
								Inputs inputs) throws QueryException {
							// TODO: This code returns the boolean success by
							// default, you can change
							// this to suit your needs
							result.setTarget(target);
							result.setSuccess(success);
							result.setCount(count);
						}
					});

		} catch (Exception e) {
			logger.fatal("Exception in createStakeHoldersRole", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish createStakeHoldersRole");
			logger.debug("returning " + result.isSuccess());
		}
		if (result.isSuccess())
			returnId = Integer.parseInt(reportId);
		return returnId;
	}

	public static boolean updateStakeHoldersRole(String reportId,
			List<String> roleList) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start updateStakeHoldersRole");
		}
		final GenericResults result = new GenericResults();
		List<Object> inputRoleList = null;
		Map<String, Object> inputLIST = null;
		MapStream inputs = null;
		try {
			if (null != reportId && null != roleList && !roleList.isEmpty()) {
				inputs = new MapStream("updateQualityCenterTestReportRoleBatch");
				inputRoleList = new ArrayList<Object>();
				for (int i = 0; i < roleList.size(); i++) {
					if (roleList.get(i).contains(GlobalConstants.COLON)) {
						inputLIST = new HashMap<String, Object>();
						inputLIST.put("qualityCenterTestReportId", Integer
								.parseInt(reportId));
						inputLIST.put("qualityCenterReportRoleName", roleList
								.get(i).substring(
										0,
										roleList.get(i).indexOf(
												GlobalConstants.COLON)));
						inputLIST.put("setQualityCenterReportPersonName",
								roleList.get(i).substring(
										roleList.get(i).indexOf(
												GlobalConstants.COLON) + 1,
										roleList.get(i).length()));
						inputRoleList.add(inputLIST);
					}
				}
				inputs.put("qualityCenterTestReportRoleList", inputRoleList);
			}

		} catch (Exception e) {
			logger.fatal(
					"Error occured while processing updateStakeHoldersRole", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		BasicDAO.updateObject(GlobalConstants.CONTRACT_NAME,
				GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new UpdateNotifier() {
					public void notifyUpdate(Object target, boolean success,
							int count, Query query, Inputs inputs)
							throws QueryException {
						// TODO: This code returns the boolean success by
						// default, you can change
						// this to suit your needs
						result.setTarget(target);
						result.setSuccess(success);
						result.setCount(count);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("finish updateStakeHoldersRole");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method returns Stake Holder Role and Role name,
	 * including project path 
	 * 
	 * @param reportId
	 * @return
	 * @throws QueryException
	 */
	public static List<StakeHoldersDTO> readStakeHoldersRole(String reportId)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readStakeHoldersRole");
		}
		final List<StakeHoldersDTO> stakeHoldersList = new ArrayList<StakeHoldersDTO>();
		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try {

			if (reportId != null && reportId.length() > 0){
				inputs = new MapStream(
						"readQualityCenterTestReportHeaderAndRoleByInputList");
				if (AppUtil.isInteger(reportId))
					inputs.put("qualityCenterTestReportId", Integer
							.parseInt(reportId));
			}

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}
			
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							StakeHoldersDTO stakeHoldersDTO = null;
							while (results.next()) {
								stakeHoldersDTO = new StakeHoldersDTO();
								stakeHoldersDTO
										.setFmsId(results
												.getShort("qualityCenterFmsProjectNumber"));
								stakeHoldersDTO.setDetailTimeStamp(results
										.getTimestamp("detailCreateTimestamp"));
								if (results
										.getTimestamp("detailCreateTimestamp") != null)
									stakeHoldersDTO
											.setDate(AppUtil
													.parseDate(results
															.getTimestamp("detailCreateTimestamp")));
								stakeHoldersDTO
										.setReportId(results
												.getInt("qualityCenterTestReportIdRole"));
								stakeHoldersDTO
										.setRoleName(results
												.getString("qualityCenterReportRoleName"));
								stakeHoldersDTO
										.setRolePersonName(results
												.getString("qualityCenterReportPersonName"));
								stakeHoldersDTO.setRoleTimeStamp(results
										.getTimestamp("roleCreateTimestamp"));
								//adding project path to stakeHoldersList
								stakeHoldersDTO.setProjectPath(results.getString("qualityCenterTestResultLocationText"));
								stakeHoldersList.add(stakeHoldersDTO);
							}
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception occured in readStakeHoldersRole", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readStakeHoldersRole");
			logger.debug("returning " + stakeHoldersList.size() + " item(s)");
		}
		return stakeHoldersList;
	}
}
