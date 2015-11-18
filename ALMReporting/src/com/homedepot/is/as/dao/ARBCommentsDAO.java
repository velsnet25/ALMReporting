package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ARBCOmmentsListDTO;
import com.homedepot.is.as.dto.GenericResults;
import com.homedepot.is.as.dto.ReadQCReportHeaderCycleAndDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadQCTestReportDetailByInputDTO;
import com.homedepot.is.as.dto.ReadQCTestReportDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderCycleAndDetailByInputDTO;
import com.homedepot.is.as.dto.TargetListDTO;
import com.homedepot.is.as.util.AppUtil;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.ConnectionHandler;
import com.homedepot.ta.aa.dao.Contract;
import com.homedepot.ta.aa.dao.DAOConnection;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.InsertNotifier;
import com.homedepot.ta.aa.dao.ObjectReader;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.QueryHandler;
import com.homedepot.ta.aa.dao.QueryManager;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.UpdateNotifier;
import com.homedepot.ta.aa.dao.basic.BasicContract;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.basic.BasicInsertNotifier;
import com.homedepot.ta.aa.dao.basic.UniversalConnectionHandler;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class reads, writes and updates QC Reporting DB.
 * 
 * @author SXB8898
 * 
 */
public class ARBCommentsDAO {

	private static final Logger logger = Logger.getLogger(ARBCommentsDAO.class);

	/**
	 * This method inserts dbName or System User Id, domainId, project Id,
	 * fmsId, project release Id
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param subProjectId
	 * @return boolean
	 * @throws QueryException
	 */
	public static boolean createQualityCenterTestReportHeader(String dbName,
			String domainId, String domainProjectId, String fmsId,
			String subProjectId, String stageId, String arbTestType,
			boolean arbFlag, String arbComments) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start createQualityCenterTestReportHeader");
		}
		final GenericResults result = new GenericResults();

		boolean arbCycle = false;
		boolean arbComment = false;
		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		try {
			inputs = new MapStream("createQualityCenterTestReportHeader");
			if (dbName != null && dbName.length() > 0)
				inputs.put("createSystemUserId", dbName);
			if (AppUtil.isInteger(domainId))
				inputs.put("qualityCenterDomainId", Integer.parseInt(domainId));
			if (AppUtil.isInteger(domainProjectId))
				inputs.put("qualityCenterProjectId", Integer
						.parseInt(domainProjectId));
			if (AppUtil.isShort(fmsId))
				inputs.put("qualityCenterFmsProjectNumber", Short
						.valueOf(fmsId));
			if (AppUtil.isInteger(subProjectId))
				inputs.put("qualityCenterReleaseId", Integer
						.parseInt(subProjectId));
			// this identifier is set to true to get the auto-generated report
			// Id
			// used to insert same report Id in cycle (td.qc_test_rpt_cyc) and
			// detail (td.qc_test_rpt_dtl) table as a reference.
			inputs.addQualifier("returnGeneratedKey", true);
			BasicDAO.insertObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new InsertNotifier() {
						public void notifyInsert(Object target,
								boolean success, int count, Query query,
								Inputs inputs) throws QueryException {
							result.setTarget(target);
							result.setSuccess(success);
							result.setCount(count);
						}
					});

			if (result.isSuccess() && result.getTarget() != null) {
				arbCycle = createQualityCenterTestReportCycle(result
						.getTarget(), stageId, dbName);
				if (!arbCycle)
					result.setSuccess(false);
			}

			if (result.isSuccess() && arbCycle && result.getTarget() != null) {
				arbComment = createQualityCenterTestReportDetail(result
						.getTarget(), arbTestType, dbName, arbFlag, arbComments);
				if (!arbComment)
					result.setSuccess(false);
			}

		} catch (Exception e) {
			logger.fatal("Exception in createQualityCenterTestReportHeader", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish createQualityCenterTestReportHeader");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method inserts release cycle Id, dbName or System User Id in
	 * TD.QC_TEST_RPT_CYC table
	 * 
	 * @param domainId
	 * @param stageId
	 * @param dbName
	 * @return boolean
	 * @throws QueryException
	 */
	public static boolean createQualityCenterTestReportCycle(Object reportId,
			String stageId, String dbName) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start createQualityCenterTestReportCycle");
		}
		final GenericResults result = new GenericResults();
		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try {
			inputs = new MapStream("createQualityCenterTestReportCycle");
			if (AppUtil.isInteger(reportId.toString()))
				inputs.put("qualityCenterTestReportId", Integer
						.parseInt(reportId.toString()));
			if (AppUtil.isInteger(stageId))
				inputs.put("qualityCenterReleaseCycleId", Integer
						.parseInt(stageId));
			if (dbName != null && dbName.length() > 0)
				inputs.put("createSystemUserId", dbName);
			BasicDAO.insertObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new InsertNotifier() {
						public void notifyInsert(Object target,
								boolean success, int count, Query query,
								Inputs inputs) throws QueryException {

							result.setTarget(target);
							result.setSuccess(success);
							result.setCount(count);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in createQualityCenterTestReportCycle", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish createQualityCenterTestReportCycle");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method inserts generated report Id, ARB planned test type name,
	 * dbName or System user Id, ARB planned flag, comments in
	 * TD.QC_TEST_RPT_DTL table.
	 * 
	 * @param arbTestType
	 * @param dbName
	 * @param arbFlag
	 * @param comments
	 * @return boolean
	 * @throws QueryException
	 */
	public static boolean createQualityCenterTestReportDetail(Object reportId,
			String arbTestType, String dbName, boolean arbFlag, String comments)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start createQualityCenterTestReportDetail");
		}
		final GenericResults result = new GenericResults();

		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		try {
			inputs = new MapStream("createQualityCenterTestReportDetail");
			if (AppUtil.isInteger(reportId.toString()))
				inputs.put("qualityCenterTestReportId", Integer
						.parseInt(reportId.toString()));
			if (arbTestType != null && arbTestType.length() > 0)
				inputs.put("qualityCenterTestTypeName", arbTestType);
			if (dbName != null && dbName.length() > 0)
				inputs.put("createSystemUserId", dbName);
			inputs.put("arbRequiredFlag", arbFlag);
			if (comments != null && comments.length() > 0)
				inputs.putAllowNull("commentText", comments); // can be null
			BasicDAO.insertObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new InsertNotifier() {
						public void notifyInsert(Object target,
								boolean success, int count, Query query,
								Inputs inputs) throws QueryException {
							result.setTarget(target);
							result.setSuccess(success);
							result.setCount(count);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in createQualityCenterTestReportDetail", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish createQualityCenterTestReportDetail");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method gets the report Id for the provided inputs, if exists in DB
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param stageId
	 * @return qc_report_Id
	 * @throws QueryException
	 */
	public static List<ReadReportHeaderByInputListDTO> readReportHeaderByInputList(
			String dbName, String domainId, String domainProjectId,
			String fmsId, String projectId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportHeaderByInputList");
		}
		final List<ReadReportHeaderByInputListDTO> readReportHeaderByInputListList = new ArrayList<ReadReportHeaderByInputListDTO>();

		MapStream inputs = null;

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try {
			inputs = new MapStream(
					"readQualityCenterTestReportHeaderByInputList");
			if (dbName != null && dbName.length() > 0)
				inputs.put("createSystemUserId", dbName);
			if (AppUtil.isInteger(domainId))
				inputs.put("qualityCenterDomainId", Integer.parseInt(domainId));

			if (AppUtil.isInteger(domainProjectId))
				inputs.put("qualityCenterProjectId", Integer
						.parseInt(domainProjectId));
			if (AppUtil.isShort(fmsId))
				inputs.put("qualityCenterFmsProjectNumber", Short
						.valueOf(fmsId));
			if (AppUtil.isInteger(projectId))
				inputs.put("qualityCenterReleaseId", Integer
						.parseInt(projectId));

			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ReadReportHeaderByInputListDTO readReportHeaderByInputListDTO = null;
							while (results.next()) {
								readReportHeaderByInputListDTO = new ReadReportHeaderByInputListDTO();
								readReportHeaderByInputListDTO
										.setQualityCenterTestReportId(results
												.getInt("qualityCenterTestReportId"));
								readReportHeaderByInputListList
										.add(readReportHeaderByInputListDTO);
							}
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readReportHeaderByInputList", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportHeaderByInputList");
			logger.debug("returning " + readReportHeaderByInputListList.size()
					+ " item(s)");
		}
		return readReportHeaderByInputListList;
	}

	private static final Contract technologyInformationSystemsContract = new BasicContract(
			GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
			GlobalConstants.CONTRACT_VERSION);

	/**
	 * This method inserts records to Quality Center Report application using
	 * batch insert and generates a report Id for future references for Cycle
	 * and Detail tables.
	 * 
	 * Project Name is required to pull target cycleId's from DB, if user
	 * selects All Stages.
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @param projectName
	 * @param stageId
	 * @param arbCommentsList
	 * @return
	 * @throws QueryException
	 */
	public static ARBCOmmentsListDTO createQualityCenterBatchInsert(
			final String dbName, final String domainId,
			final String domainProjectId, final String fmsId,
			final String projectId, final String projectName,
			final String stageId, final String projectPath,
			final List<ARBCOmmentsListDTO> arbCommentsList)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start createQualityCenterBatchInsert");
		}
		// QueryManager objects for each contract
		QueryManager technologyInformationSystemsQueryManager = QueryManager
				.getInstance(technologyInformationSystemsContract);

		// Get the Connection Object to work with the queries
		DAOConnection technologyInformationSystemsDaoConn = technologyInformationSystemsQueryManager
				.getDAOConnection(technologyInformationSystemsContract);

		// Generic results for Object Read, Insert, Update, or Delete
		final GenericResults result = new GenericResults();
		ARBCOmmentsListDTO arbCommentsTO = new ARBCOmmentsListDTO();

		final QueryHandler insertHandler = new BasicInsertNotifier(
				new InsertNotifier() {
					public void notifyInsert(Object target, boolean success,
							int count, Query query, Inputs inputs)
							throws QueryException {
						// this to suit your needs
						result.setTarget(target);
						result.setSuccess(success);
						result.setCount(count);
					}
				});

		// ConnectionHandler will handle getting the connection and closing the
		// connection when this query complete
		ConnectionHandler connHandler = null;
		try {

			connHandler = new UniversalConnectionHandler(true, null,
					technologyInformationSystemsDaoConn) {
				// target object is required to store generated report Id and
				// pass to other tables.
				Object target;
				MapStream inputs = null;
				DAOConnection technologyInformationSystemsDaoConn = null;

				@Override
				public void handleQuery(
						Map<DAOConnection, DAOConnection> daoConnList,
						QueryHandler queryHandler) throws QueryException {
					technologyInformationSystemsDaoConn = daoConnList
							.get(technologyInformationSystemsContract);
					Query technologyInformationSystemsQuery = technologyInformationSystemsDaoConn
							.getQuery();
					// insert records into QC_TEST_RPT_HDR table
					inputs = new MapStream(
							"createQualityCenterTestReportHeader");
					if (dbName != null && dbName.length() > 0)
						inputs.put("createSystemUserId", dbName.trim());
					if (AppUtil.isInteger(domainId))
						inputs.put("qualityCenterDomainId", Integer
								.parseInt(domainId));

					if (AppUtil.isInteger(domainProjectId))
						inputs.put("qualityCenterProjectId", Integer
								.parseInt(domainProjectId));
					if (AppUtil.isShort(fmsId))
						inputs.put("qualityCenterFmsProjectNumber", Short
								.valueOf(fmsId));
					if (AppUtil.isInteger(projectId))
						inputs.put("qualityCenterReleaseId", Integer
								.parseInt(projectId));
					if (projectPath != null && projectPath.length() > 0) {
						inputs.putAllowNull(
								"qualityCenterTestResultLocationText",
								projectPath.trim()); // can be null
					} else {
						inputs.putAllowNull(
								"qualityCenterTestResultLocationText",
								projectPath); // can be null
					}
					// set qualifier to true to get the generated Id
					inputs.addQualifier("returnGeneratedKey", true);
					technologyInformationSystemsQuery.insertObject(
							technologyInformationSystemsDaoConn, insertHandler,
							inputs);

					// batch insert release cycle values in cycle table
					if (result.isSuccess() && result.getTarget() != null) {

						target = result.getTarget();
						inputs = new MapStream(
								"createQualityCenterTestReportCycleBatch");
						List<Object> inputQualityCenterTestReportCycleList = null;
						Map<String, Object> inputLIST = null;

						// get release cycle Id's or stage Id's
						// for selected project,
						// if release cycle or stage name is All Stages
						if ((projectName != null && projectName.length() > 0)
								&& (stageId != null && stageId.isEmpty())) {

							try {
								TargetListDTO targetCycleNamesListDTO = null;
								if (dbName != null && dbName.length() > 0)
									targetCycleNamesListDTO = TargetNameDetailsDAO
											.readReleaseCyclesAndReleasesDetailsByInputList(
													dbName, projectName);
								// insert records into QC_TEST_RPT_CYC table
								if (targetCycleNamesListDTO != null) {
									inputQualityCenterTestReportCycleList = new ArrayList<Object>();
									for (int i = 0; i < targetCycleNamesListDTO
											.getTargetNamesDTO().size(); i++) {
										inputLIST = new HashMap<String, Object>();
										inputLIST.put(
												"qualityCenterTestReportId",
												result.getTarget());
										inputLIST.put(
												"qualityCenterReleaseCycleId",
												targetCycleNamesListDTO
														.getTargetNamesDTO()
														.get(i)
														.getReleaseCyclesId());
										if (dbName != null
												&& dbName.trim().length() > 0)
											inputLIST.put("createSystemUserId",
													dbName.trim());
										inputQualityCenterTestReportCycleList
												.add(inputLIST);
									}
								}
							} catch (Exception e) {
								result.setSuccess(false);
								result.setTarget(null);
								logger
										.fatal(
												"Error processing while fetching release cycle Ids",
												e);
							}
						} else if (stageId != null && stageId.length() > 0) {
							inputQualityCenterTestReportCycleList = new ArrayList<Object>();
							inputLIST = new HashMap<String, Object>();
							inputLIST.put("qualityCenterTestReportId", result
									.getTarget());
							if (AppUtil.isInteger(stageId))
								inputLIST.put("qualityCenterReleaseCycleId",
										Integer.parseInt(stageId));
							if (dbName != null && dbName.trim().length() > 0)
								inputLIST.put("createSystemUserId", dbName
										.trim());
							inputQualityCenterTestReportCycleList
									.add(inputLIST);
						}
						inputs.put("qualityCenterTestReportCycleList",
								inputQualityCenterTestReportCycleList);

						technologyInformationSystemsQuery.insertObject(
								technologyInformationSystemsDaoConn,
								insertHandler, inputs);

						if (result.isSuccess())
							result.setTarget(target);
					}

					// batch insert for detail table (Test type, ARB flag,
					// comments)
					if (result.isSuccess() && result.getTarget() != null) {

						// insert data into QC_TEST_RPT_DTL table
						inputs = new MapStream(
								"createQualityCenterTestReportDetailBatch");
						// by default ARB flag is true
						Boolean arbFlag = false;
						if (arbCommentsList != null
								&& !arbCommentsList.isEmpty()) {
							try {
								List<Object> inputQualityCenterTestReportDetailList = new ArrayList<Object>();
								Map<String, Object> inputLIST = null;

								for (int i = 0; i < arbCommentsList.size(); i++) {
									inputLIST = new HashMap<String, Object>();
									inputLIST.put("qualityCenterTestReportId",
											result.getTarget());
									String testTypeAndExists = arbCommentsList
											.get(i).getTestType();
									if (testTypeAndExists != null) {
										if (testTypeAndExists.contains("and")
												&& !testTypeAndExists
														.equals(GlobalConstants.SECURITY_ROLE_BASED_AND_ACCESS_TEST_TYPE)) {
											String replacedTestType = testTypeAndExists
													.replace("and", "&");
											inputLIST
													.put(
															"qualityCenterTestTypeName",
															replacedTestType);
										} else {
											inputLIST
													.put(
															"qualityCenterTestTypeName",
															testTypeAndExists
																	.trim());
										}
									}
									if (dbName != null
											&& dbName.trim().length() > 0)
										inputLIST.put("createSystemUserId",
												dbName.trim());
									if (arbCommentsList.get(i).getArbFlag()
											.equalsIgnoreCase("Y"))
										arbFlag = true;
									else if (arbCommentsList.get(i)
											.getArbFlag().equalsIgnoreCase("N"))
										arbFlag = false;
									inputLIST.put("arbRequiredFlag", arbFlag);
									if (arbCommentsList.get(i).getComments() != null)
										// comments can be null
										inputLIST.put("commentText",
												arbCommentsList.get(i)
														.getComments().trim());
									else
										inputLIST.put("commentText",
												arbCommentsList.get(i)
														.getComments());
									inputQualityCenterTestReportDetailList
											.add(inputLIST);
								}
								inputs.put("qualityCenterTestReportDetailList",
										inputQualityCenterTestReportDetailList);
								technologyInformationSystemsQuery.insertObject(
										technologyInformationSystemsDaoConn,
										insertHandler, inputs);
							} catch (Exception e) {
								result.setSuccess(false);
								result.setTarget(null);
								logger
										.fatal(
												"Error occured while inserting into QC TEST RPT DTL table",
												e);
							}
						}
						// modify this line to interact with the results
						if (result.isSuccess()) {
							if (result.getTarget() == null) {
								result.setTarget(target);
							}
						}
					}
				}
			};
		} catch (Exception e) {
			logger.fatal("Error occured while batch insert", e);
		}
		// Executes the Query
		if (logger.isDebugEnabled()) {
			logger
					.debug("executing the query in createQualityCenterBatchInsert");
		}
		if (connHandler != null)
			connHandler.execute();

		if (logger.isDebugEnabled()) {
			logger.debug("finish createQualityCenterBatchInsert");
		}
		// return result.isSuccess();
		if (result.getTarget() != null)
			arbCommentsTO.setReportId(result.getTarget().toString());
		return arbCommentsTO;
	}

	/**
	 * This method updates test type, ARB planned flag and ARB comments in
	 * TD.QC_TEST_RPT_DTL table
	 * 
	 * @return
	 * @throws QueryException
	 */
	public static boolean updateQualityCenterTestReportDetailBatch(
			final String dbName, final String reportId,
			final List<ARBCOmmentsListDTO> arbCommentsList)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start updateQualityCenterTestReportDetailBatch");
		}
		final GenericResults result = new GenericResults();
		// ARBCOmmentsListDTO arbCommentsTO = null;
		MapStream inputs = null;

		try {
			inputs = new MapStream("updateQualityCenterTestReportDetailBatch");
			boolean arbFlag = false;
			if (dbName != null && dbName.trim().length() > 0
					&& reportId != null && reportId.trim().length() > 0) {
				if (arbCommentsList != null && !arbCommentsList.isEmpty()) {
					List<Object> inputQualityCenterTestReportDetailList = new ArrayList<Object>();
					Map<String, Object> inputLIST = null;
					for (int i = 0; i < arbCommentsList.size(); i++) {
						inputLIST = new HashMap<String, Object>();
						if (AppUtil.isInteger(reportId))
							inputLIST.put("qualityCenterTestReportId", Integer
									.parseInt(reportId));
						// replace test type, if test type contains 'and' with
						// '&' excluding 'Security Role Based and Access' test
						// type
						String testType = arbCommentsList.get(i).getTestType();
						if (testType != null) {
							if ((testType.contains("and") && !testType
									.trim()
									.equals(
											GlobalConstants.SECURITY_ROLE_BASED_AND_ACCESS_TEST_TYPE))) {
								String replacedTestType = testType.replace(
										"and", "&");
								inputLIST.put("qualityCenterTestTypeName",
										replacedTestType);
								inputLIST.put("setQualityCenterTestTypeName",
										replacedTestType);
							} else {
								inputLIST.put("qualityCenterTestTypeName",
										testType);
								inputLIST.put("setQualityCenterTestTypeName",
										testType);
							}
						}
						inputLIST.put("createSystemUserId", dbName);
						if (arbCommentsList.get(i).getArbFlag()
								.equalsIgnoreCase("Y"))
							arbFlag = true;
						else if (arbCommentsList.get(i).getArbFlag()
								.equalsIgnoreCase("N"))
							arbFlag = false;
						inputLIST.put("arbRequiredFlag", arbFlag);
						if (arbCommentsList.get(i).getComments() != null)
							// comments can be null
							inputLIST.put("commentText", arbCommentsList.get(i)
									.getComments().trim());
						else
							inputLIST.put("commentText", arbCommentsList.get(i)
									.getComments());

						inputQualityCenterTestReportDetailList.add(inputLIST);
					}
					inputs.put("qualityCenterTestReportDetailList",
							inputQualityCenterTestReportDetailList);
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.updateObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new UpdateNotifier() {
						public void notifyUpdate(Object target,
								boolean success, int count, Query query,
								Inputs inputs) throws QueryException {
							// default, you can change
							// this to suit your needs
							result.setTarget(target);
							result.setSuccess(success);
							result.setCount(count);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"error while processing update in QC_TEST_RPT_DTL table",
							e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish updateQualityCenterTestReportDetailBatch");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method updates project path in QC_TEST_RPT_HDR table for given
	 * inputs
	 * 
	 * @return boolean
	 * @throws QueryException
	 */
	public static boolean updateQualityCenterTestReportHeaderByInputList(
			ReadReportHeaderCycleAndDetailByInputDTO hdrDTO,
			final String projectPath) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start updateQualityCenterTestReportHeaderByInputList");
		}
		final GenericResults result = new GenericResults();
		MapStream inputs = null;

		try {
			if (hdrDTO != null) {
				inputs = new MapStream(
						"updateQualityCenterTestReportHeaderByInputList");
				if (projectPath != null && projectPath.trim().length() > 0) {
					inputs.putAllowNull("qualityCenterTestResultLocationText",
							projectPath.trim()); // can be null
				} else {
					inputs.putAllowNull("qualityCenterTestResultLocationText",
							projectPath); // can be null
				}
				inputs.putAllowNull("createSystemUserId", hdrDTO
						.getCreateSystemUserId()); // can be null
				inputs.put("qualityCenterDomainId", hdrDTO
						.getQualityCenterDomainId());
				inputs.put("qualityCenterProjectId", hdrDTO
						.getQualityCenterProjectId());
				inputs.put("qualityCenterFmsProjectNumber", hdrDTO
						.getQualityCenterFmsProjectNumber());
				inputs.put("qualityCenterReleaseId", hdrDTO
						.getQualityCenterReleaseId());

				if (logger.isDebugEnabled()) {
					logger.debug("executing the query");
				}

				BasicDAO.updateObject(GlobalConstants.CONTRACT_NAME,
						GlobalConstants.BUSINESS_USE_ID,
						GlobalConstants.CONTRACT_VERSION, inputs,
						new UpdateNotifier() {
							public void notifyUpdate(Object target,
									boolean success, int count, Query query,
									Inputs inputs) throws QueryException {
								// default, you can change
								// this to suit your needs
								result.setTarget(target);
								result.setSuccess(success);
								result.setCount(count);
							}
						});
			}
		} catch (Exception e) {
			logger
					.fatal(
							"error occured while updating project path in QC_TEST_RPT_HDR table",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger
					.debug("finish updateQualityCenterTestReportHeaderByInputList");
			logger.debug("returning " + result.isSuccess());
		}
		return result.isSuccess();
	}

	/**
	 * This method gets the generated report Id from QC_TEST_RPT_HDR table by
	 * checking the database with given inputs. We can change the behavior of
	 * this method to get the existence of record by returning true or false.
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @return
	 * @throws QueryException
	 */
	public static Object readHeaderForExistenceByInputList(final String dbName,
			final String domainId, final String domainProjectId,
			final String fmsId, final String projectId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readQCReportHeaderForExistenceByInputList");
		}
		final GenericResults result = new GenericResults();

		MapStream inputs = null;
		try {
			inputs = new MapStream(
					"readQualityCenterTestReportHeaderForExistenceByInputList");
			if (dbName != null && dbName.trim().length() > 0)
				inputs.put("createSystemUserId", dbName);
			if (AppUtil.isInteger(domainId))
				inputs.put("qualityCenterDomainId", Integer.parseInt(domainId));
			if (AppUtil.isInteger(domainProjectId))
				inputs.put("qualityCenterProjectId", Integer
						.parseInt(domainProjectId));
			if (AppUtil.isShort(fmsId))
				inputs.put("qualityCenterFmsProjectNumber", Short
						.valueOf(fmsId));
			if (AppUtil.isInteger(projectId))
				inputs.put("qualityCenterReleaseId", Integer
						.parseInt(projectId));

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ObjectReader() {
						public void readObject(Object target, Query query,
								Inputs inputs) throws QueryException {
							// This code returns the target by default, you
							// can change
							// this to suit your needs
							result.setTarget(target);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readQCReportHeaderForExistenceByInputList",
							e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readQCReportHeaderForExistenceByInputList");
			logger.debug("returning " + result.getTarget());
		}
		return result.getTarget();
	}

	/**
	 * This method gets existence of report Id in QC_TEST_RPT_CYC table by
	 * checking the database with given inputs. We can change the behavior of
	 * this method to get the existence of record by returning true or false.
	 * 
	 * @param reportId
	 * @param stageId
	 * @return
	 * @throws QueryException
	 */
	public static Object readReportCycleForExistenceByInputList(
			final String reportId, final String stageId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportCycleForExistenceByInputList");
		}
		final GenericResults result = new GenericResults();

		MapStream inputs = null;
		try {

			inputs = new MapStream(
					"readQualityCenterTestReportCycleForExistenceByInputList");
			if (reportId != null && reportId.trim().length() > 0
					&& !reportId.isEmpty())
				if (AppUtil.isInteger(reportId))
					inputs.put("qualityCenterTestReportId", Integer
							.parseInt(reportId));
			if (stageId != null && stageId.trim().length() > 0
					&& !stageId.isEmpty())
				if (AppUtil.isInteger(stageId))
					inputs.put("qualityCenterReleaseCycleId", Integer
							.parseInt(stageId)); // optional

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ObjectReader() {
						public void readObject(Object target, Query query,
								Inputs inputs) throws QueryException {
							// This code returns the target by default, you can
							// change
							// this to suit your needs
							result.setTarget(target);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readReportCycleForExistenceByInputList",
							e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportCycleForExistenceByInputList");
			logger.debug("returning " + result.getTarget());
		}
		return result.getTarget();
	}

	/**
	 * This method gets the generated release cycle Id from QC_TEST_RPT_CYC
	 * table by checking the database with given inputs. We can change the
	 * behavior of this method to get the existence of record by returning true
	 * or false.
	 * 
	 * @param reportId
	 * @param stageId
	 * @return
	 * @throws QueryException
	 */
	public static Object readReportCycleForExistenceByInputList(
			final String reportId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportCycleForExistenceByInputList");
		}
		final GenericResults result = new GenericResults();

		MapStream inputs = null;
		try {

			inputs = new MapStream(
					"readQualityCenterTestReportCycleForExistenceByInputList");
			if (reportId != null && reportId.trim().length() > 0)
				if (AppUtil.isInteger(reportId))
					inputs.put("qualityCenterTestReportId", Integer
							.parseInt(reportId));
			inputs.addQualifier("returnGeneratedKey", true);

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getObject(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ObjectReader() {
						public void readObject(Object target, Query query,
								Inputs inputs) throws QueryException {
							// This code returns the target by default, you can
							// change
							// this to suit your needs
							result.setTarget(target);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readReportCycleForExistenceByInputList",
							e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportCycleForExistenceByInputList");
			logger.debug("returning " + result.getTarget());
		}
		return result.getTarget();
	}

	/**
	 * This method reads values from QC_TEST_RPT_DTL table and populates DTO
	 * object for the given reportId.
	 * 
	 * @param dbName
	 * @param reportId
	 * @return
	 * @throws QueryException
	 */
	public static ReadQCTestReportDetailByInputListDTO readReportDetailByInputList(
			final String dbName, final String reportId) throws QueryException {
		long startTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportDetailByInputList" + startTime);
		}
		final List<ReadQCTestReportDetailByInputDTO> readQualityCenterTestReportDetailByInputListList = new ArrayList<ReadQCTestReportDetailByInputDTO>();
		final ReadQCTestReportDetailByInputListDTO readQCTestReportDetailsList = new ReadQCTestReportDetailByInputListDTO();
		MapStream inputs = null;

		try {
			inputs = new MapStream(
					"readQualityCenterTestReportDetailByInputList");

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}
			if (dbName != null && reportId != null) {
				inputs.put("createSystemUserId", dbName);
				if (AppUtil.isInteger(reportId))
					inputs.put("qualityCenterTestReportId", Integer
							.parseInt(reportId));
				// contract name, business use id and contract version
				BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
						GlobalConstants.BUSINESS_USE_ID,
						GlobalConstants.CONTRACT_VERSION, inputs,
						new ResultsReader() {
							public void readResults(Results results,
									Query query, Inputs inputs)
									throws QueryException {
								ReadQCTestReportDetailByInputDTO readQualityCenterTestReportDetailByInputDTO = null;
								while (results.next()) {
									readQualityCenterTestReportDetailByInputDTO = new ReadQCTestReportDetailByInputDTO();
									String testType = null;
									if (results
											.getString("qualityCenterTestTypeName") != null) {
										testType = results
												.getString("qualityCenterTestTypeName");
										if (testType.contains("&")) {
											testType = testType.replace("&",
													"and");
											readQualityCenterTestReportDetailByInputDTO
													.setTestTypeName(testType
															.trim());
										} else {
											readQualityCenterTestReportDetailByInputDTO
													.setTestTypeName(testType
															.trim());
										}
									}
									if (results.getBoolean("arbRequiredFlag"))

										readQualityCenterTestReportDetailByInputDTO
												.setArbRequiredFlag(GlobalConstants.ARBFLAG_TRUE);
									else
										readQualityCenterTestReportDetailByInputDTO
												.setArbRequiredFlag(GlobalConstants.ARBFLAG_FALSE);
									if (results.getString("commentText") != null)
										readQualityCenterTestReportDetailByInputDTO
												.setCommentText(results
														.getString(
																"commentText")
														.trim());
									else
										readQualityCenterTestReportDetailByInputDTO
												.setCommentText(results
														.getString("commentText"));
									readQualityCenterTestReportDetailByInputDTO
											.setReportId(Integer
													.parseInt((reportId)));
									readQualityCenterTestReportDetailByInputListList
											.add(readQualityCenterTestReportDetailByInputDTO);
								}
								readQCTestReportDetailsList
										.setQcTestReportDetailList(readQualityCenterTestReportDetailByInputListList);
							}
						});
			}
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readReportDetailByInputList",
							e);
		}
		long endTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportDetailByInputList:"
					+ (endTime - startTime));
			logger.debug("returning "
					+ readQualityCenterTestReportDetailByInputListList.size()
					+ " item(s)");
		}
		return readQCTestReportDetailsList;
	}

	/**
	 * This method gets domainId, domainSubProjId, fmsId, projectId, test type,
	 * ARB Flag, Comments system user or dbName for the given reportId.
	 * 
	 * This method reads QC_TEST_RPT_HDR, and QC_TEST_RPT_DTL tables, gets last
	 * inserted or updated results.
	 * 
	 * @param reportId
	 * @return
	 * @throws Queryexception
	 */
	public static ReadQCReportHeaderCycleAndDetailByInputListDTO readReportHeaderAndDetailByIds(
			final String reportId, final boolean dtlFlag) throws QueryException {
		long startTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportHeaderAndDetailByIds:" + startTime);
		}
		final List<ReadReportHeaderCycleAndDetailByInputDTO> readReportHeaderAndDetailByInputList = new ArrayList<ReadReportHeaderCycleAndDetailByInputDTO>();
		final ReadQCReportHeaderCycleAndDetailByInputListDTO readReportHeaderCycleAndDetailByInputListDTO = new ReadQCReportHeaderCycleAndDetailByInputListDTO();
		MapStream inputs = null;
		try {
			if (reportId != null && reportId.trim().length() > 0) {
				inputs = new MapStream(
						"readQualityCenterTestReportHeaderAndDetailByInputList");
				if (AppUtil.isInteger(reportId))
					inputs.put("qualityCenterTestReportId", Integer
							.parseInt(reportId));

				if (logger.isDebugEnabled()) {
					logger.debug("executing the query");
				}

				BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
						GlobalConstants.BUSINESS_USE_ID,
						GlobalConstants.CONTRACT_VERSION, inputs,
						new ResultsReader() {
							public void readResults(Results results,
									Query query, Inputs inputs)
									throws QueryException {
								ReadReportHeaderCycleAndDetailByInputDTO readQualityCenterTestReportHeaderAndDetailByInputListDTO = null;
								while (results.next()) {
									readQualityCenterTestReportHeaderAndDetailByInputListDTO = new ReadReportHeaderCycleAndDetailByInputDTO();
									if (null != results
											.getString("createSystemUserId")) {
										readQualityCenterTestReportHeaderAndDetailByInputListDTO
												.setCreateSystemUserId(results
														.getString(
																"createSystemUserId")
														.trim());
									} else {
										readQualityCenterTestReportHeaderAndDetailByInputListDTO
												.setCreateSystemUserId(results
														.getString("createSystemUserId"));
									}
									/*
									 * readQualityCenterTestReportHeaderAndDetailByInputListDTO
									 * .setCreateTimestamp(results
									 * .getTimestamp("createTimestamp"));
									 */
									readQualityCenterTestReportHeaderAndDetailByInputListDTO
											.setQualityCenterDomainId(results
													.getInt("qualityCenterDomainId"));
									readQualityCenterTestReportHeaderAndDetailByInputListDTO
											.setQualityCenterProjectId(results
													.getInt("qualityCenterProjectId"));
									readQualityCenterTestReportHeaderAndDetailByInputListDTO
											.setQualityCenterFmsProjectNumber(results
													.getShort("qualityCenterFmsProjectNumber"));
									readQualityCenterTestReportHeaderAndDetailByInputListDTO
											.setQualityCenterReleaseId(results
													.getInt("qualityCenterReleaseId"));
									readQualityCenterTestReportHeaderAndDetailByInputListDTO
											.setQualityCenterTestResultLocationText(results
													.getString("qualityCenterTestResultLocationText"));
									if (dtlFlag) {
										//replacing & with and while reading from DB
										String testType = null;
										if(results
												.getString("qualityCenterTestTypeName") != null){
											testType = results.getString("qualityCenterTestTypeName");
										if (testType.contains("&")) {
											testType = testType.replaceAll("&", "and");
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setQualityCenterTestTypeName(testType.trim());
											}else{
												readQualityCenterTestReportHeaderAndDetailByInputListDTO
												.setQualityCenterTestTypeName(testType.trim());
											}
										} else {
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setQualityCenterTestTypeName(results
															.getString("qualityCenterTestTypeName"));
										}
										readQualityCenterTestReportHeaderAndDetailByInputListDTO
												.setDetailCreateSystemUserId(results
														.getString("detailCreateSystemUserId"));

										readQualityCenterTestReportHeaderAndDetailByInputListDTO
												.setDetailCreateTimestamp(results
														.getTimestamp("detailCreateTimestamp"));

										if (results
												.getBoolean("arbRequiredFlag")) {
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setArbRequiredFlag(GlobalConstants.ARBFLAG_TRUE);
										} else {
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setArbRequiredFlag(GlobalConstants.ARBFLAG_FALSE);
										}
										if (results.getString("commentText") != null) {
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setCommentText(results
															.getString(
																	"commentText")
															.trim());
										} else {
											readQualityCenterTestReportHeaderAndDetailByInputListDTO
													.setCommentText(results
															.getString("commentText"));
										}
									}
									readReportHeaderAndDetailByInputList
											.add(readQualityCenterTestReportHeaderAndDetailByInputListDTO);
								}
								readReportHeaderCycleAndDetailByInputListDTO
										.setQcReportDetailList(readReportHeaderAndDetailByInputList);
							}
						});
			}
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readReportHeaderAndDetailByIds",
							e);
		}

		long endTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportHeaderAndDetailByIds:"
					+ (endTime - startTime) + " milliseconds");
			logger.debug("returning "
					+ readReportHeaderAndDetailByInputList.size() + " item(s)");
		}
		return readReportHeaderCycleAndDetailByInputListDTO;
	}

	/**
	 * This method returns reportId and stageId or release cycle Id for given
	 * inputs if exists in DB
	 * 
	 * @param dbName
	 * @param domainId
	 * @param domianProjectId
	 * @param fmsId
	 * @param projectId
	 * @param stageId
	 * @return
	 * @throws QueryException
	 */
	public static List<ReadReportHeaderByInputListDTO> readHeaderAndCycleDetailsByInputList(
			final String dbName, final String domainId,
			final String domianProjectId, final String fmsId,
			final String projectId, final String stageId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readHeaderAndCycleDetailsByInputList");
		}
		final List<ReadReportHeaderByInputListDTO> readReportHeaderByInputListList = new ArrayList<ReadReportHeaderByInputListDTO>();
		MapStream inputs = null;
		try {
			inputs = new MapStream(
					"readQualityCenterTestReportHeaderAndCycleDetailsByInputList");
			if (dbName != null && dbName.trim().length() > 0)
				inputs.put("createSystemUserId", dbName);
			if (domainId != null && domainId.length() > 0)
				if (AppUtil.isInteger(domainId))
					inputs.put("qualityCenterDomainId", Integer
							.parseInt(domainId));
			if (domianProjectId != null && domianProjectId.length() > 0)
				if (AppUtil.isInteger(domianProjectId))
					inputs.put("qualityCenterProjectId", Integer
							.parseInt(domianProjectId));
			if (fmsId != null && fmsId.length() > 0)
				if (AppUtil.isInteger(fmsId))
					inputs.put("qualityCenterFmsProjectNumber", Short
							.valueOf(fmsId));
			if (projectId != null && projectId.length() > 0)
				if (AppUtil.isInteger(projectId))
					inputs.put("qualityCenterReleaseId", Integer
							.parseInt(projectId));
			if (stageId != null && stageId.length() > 0)
				if (AppUtil.isInteger(projectId))
					inputs.put("qualityCenterReleaseCycleId", Integer
							.parseInt(stageId)); // optional

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ReadReportHeaderByInputListDTO readReportHeaderByInputListDTO = null;
							while (results.next()) {
								readReportHeaderByInputListDTO = new ReadReportHeaderByInputListDTO();
								readReportHeaderByInputListDTO
										.setQualityCenterTestReportId(results
												.getInt("qualityCenterTestReportId"));
								readReportHeaderByInputListDTO
										.setQualityCenterReleaseCycleId(results
												.getInt("qualityCenterReleaseCycleId"));
								readReportHeaderByInputListList
										.add(readReportHeaderByInputListDTO);
							}
						}
					});
		} catch (Exception e) {
			logger
					.fatal("Error occured while reading readHeaderAndCycleDetailsByInputList"
							+ e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish readHeaderAndCycleDetailsByInputList");
			logger.debug("returning " + readReportHeaderByInputListList.size()
					+ " item(s)");
		}
		return readReportHeaderByInputListList;
	}

	/**
	 * This method gets domainId, domainSubProjId, fmsId, projectId, targetId or
	 * release cycle Id, test type, ARB Flag, Comments system user or dbName to
	 * the given inputs.
	 * 
	 * @param reportId
	 * @param stageId
	 * @return
	 * @throws QueryException
	 */
	public static ReadQCReportHeaderCycleAndDetailByInputListDTO readReportHeaderCycleAndDetailByIds(
			final String reportId, final String stageId) throws QueryException {
		long startTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("start readReportHeaderCycleAndDetailByIds:"
					+ startTime);
		}
		final List<ReadReportHeaderCycleAndDetailByInputDTO> readHeaderCycleAndDetailByInputList = new ArrayList<ReadReportHeaderCycleAndDetailByInputDTO>();
		final ReadQCReportHeaderCycleAndDetailByInputListDTO readReportHeaderCycleAndDetailByInputListDTO = new ReadQCReportHeaderCycleAndDetailByInputListDTO();
		MapStream inputs = null;

		try {
			inputs = new MapStream(
					"readQualityCenterTestReportHeaderCycleAndDetailByInputList");
			if (reportId != null
					&& !reportId.equalsIgnoreCase(GlobalConstants.UNDEFINED)
					&& AppUtil.isInteger(reportId))
				inputs.put("qualityCenterTestReportId", Integer
						.parseInt(reportId));
			if (stageId != null && stageId.length() > 0
					&& AppUtil.isInteger(stageId))
				inputs.put("qualityCenterReleaseCycleId", Integer
						.parseInt(stageId)); // optional

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			// contract name, business use id and version
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ReadReportHeaderCycleAndDetailByInputDTO readHeaderCycleAndDetailByInputListDTO = null;
							while (results.next()) {
								readHeaderCycleAndDetailByInputListDTO = new ReadReportHeaderCycleAndDetailByInputDTO();

								readHeaderCycleAndDetailByInputListDTO
										.setCreateSystemUserId(results
												.getString("headerCreateSystemUserId"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterDomainId(results
												.getInt("qualityCenterDomainId"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterProjectId(results
												.getInt("qualityCenterProjectId"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterFmsProjectNumber(results
												.getShort("qualityCenterFmsProjectNumber"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterReleaseId(results
												.getInt("qualityCenterReleaseId"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterReleaseCycleId(results
												.getInt("qualityCenterReleaseCycleId"));
								readHeaderCycleAndDetailByInputListDTO
										.setQualityCenterTestResultLocationText(results
												.getString("qualityCenterTestResultLocationText"));
								if (results
										.getString("qualityCenterTestTypeName") != null) {
									String testType = null;
									testType = results.getString("qualityCenterTestTypeName");
									if(testType.contains("&")){
										testType = testType.replace("&", "and");
										readHeaderCycleAndDetailByInputListDTO
												.setQualityCenterTestTypeName(testType.trim());
										}else{
											readHeaderCycleAndDetailByInputListDTO
											.setQualityCenterTestTypeName(testType.trim());
										}
								} else {
									readHeaderCycleAndDetailByInputListDTO
											.setQualityCenterTestTypeName(results
													.getString("qualityCenterTestTypeName"));
								}
								if (results.getBoolean("arbRequiredFlag")) {
									readHeaderCycleAndDetailByInputListDTO
											.setArbRequiredFlag(GlobalConstants.ARBFLAG_TRUE);
								} else {
									readHeaderCycleAndDetailByInputListDTO
											.setArbRequiredFlag(GlobalConstants.ARBFLAG_FALSE);
								}
								if (results.getString("commentText") != null) {
									readHeaderCycleAndDetailByInputListDTO
											.setCommentText(results.getString(
													"commentText").trim());
								} else {
									readHeaderCycleAndDetailByInputListDTO
											.setCommentText(results
													.getString("commentText"));
								}
								readHeaderCycleAndDetailByInputList
										.add(readHeaderCycleAndDetailByInputListDTO);

							}
							readReportHeaderCycleAndDetailByInputListDTO
									.setQcReportDetailList(readHeaderCycleAndDetailByInputList);
						}
					});
		} catch (Exception e) {
			logger
					.fatal(
							"Error occured while processing readReportHeaderCycleAndDetailByIds",
							e);
		}

		long endTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("finish readReportHeaderCycleAndDetailByIds:"
					+ (endTime - startTime) + " milliseconds");
			logger.debug("returning "
					+ readHeaderCycleAndDetailByInputList.size() + " item(s)");
		}
		return readReportHeaderCycleAndDetailByInputListDTO;
	}
}