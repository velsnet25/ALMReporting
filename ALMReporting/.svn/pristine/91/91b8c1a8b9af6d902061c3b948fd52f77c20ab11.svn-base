package com.homedepot.is.as.restService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.homedepot.is.as.bl.RestFulSvcBusinessManager;
import com.homedepot.is.as.dto.ARBCOmmentsListDTO;
import com.homedepot.is.as.dto.ActiveDefectsListDTO;
import com.homedepot.is.as.dto.DomainListDTO;
import com.homedepot.is.as.dto.FMSIdListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsDrillResultListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsListDTO;
import com.homedepot.is.as.dto.ProjectListDTO;
import com.homedepot.is.as.dto.ReadQCReportHeaderCycleAndDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadQCTestReportDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderByInputListDTO;
import com.homedepot.is.as.dto.StakeHoldersDTO;
import com.homedepot.is.as.dto.SubProjectListDTO;
import com.homedepot.is.as.dto.TargetListDTO;
import com.homedepot.is.as.dto.TestExecDrillResultListDTO;
import com.homedepot.is.as.dto.TestExecResultListDTO;
import com.homedepot.is.as.dto.TestTotActDrillResultListDTO;
import com.homedepot.is.as.dto.TestTypesDTO;
import com.homedepot.is.as.restService.formatters.ExceptionXMLFormatter;
import com.homedepot.is.as.restService.messages.RestFulServiceMessage;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.is.as.util.XMLGenerator;

/**
 * Restful services URL generator class. This class is a start point to call
 * services.
 * 
 * @author SXB8898
 * 
 */
@Path("/QCReport")
public class QCReport {
	private static final Logger logger = Logger.getLogger(QCReport.class);

	@GET
	@Produces("application/xml")
	@Path("/getDomainNames")
	public String getDomainNames(
			@DefaultValue("1") @QueryParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getDomainNames()");
		}

		String result = null;
		DomainListDTO domainDetailsResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			restFulSvcBusinessManager = new RestFulSvcBusinessManager();
			domainDetailsResp = restFulSvcBusinessManager.fetchDomainNames();
			if (domainDetailsResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						domainDetailsResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getDomainNames()");
		}

		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getDomainNameById")
	public String getDomainNameById(@QueryParam("domainId") String domainId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getDomainNameById()");
		}
		String result = null;
		DomainListDTO domainNameResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (domainId != null && domainId.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				domainNameResp = restFulSvcBusinessManager
						.fetchDomainNameById(domainId);
			}
			if (domainNameResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						domainNameResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getDomainNames()");
		}

		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getProjectNames")
	public String getProjectNames(@QueryParam("domainName") String domainName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getProjectNames()");
		}

		String xmlresult = null;
		ProjectListDTO projectDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (domainName != null && domainName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				projectDetailsResponse = restFulSvcBusinessManager
						.fetchProjectNames(domainName);
			}
			if (projectDetailsResponse != null)
				xmlresult = XMLGenerator.getInstance().serializingToXML(
						projectDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			xmlresult = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("xmlresult: '" + xmlresult + "'");
			logger.debug("End getProjectNames()");
		}
		return xmlresult;
	}

	@GET
	@Produces("application/xml")
	@Path("/getProjectNameById")
	public String getProjectNameById(@QueryParam("projectId") String projectId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getProjectNameById()");
		}
		String xmlresult = null;
		ProjectListDTO projectDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (projectId != null && projectId.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				projectDetailsResponse = restFulSvcBusinessManager
						.fetchProjectNameById(projectId);
			}
			if (projectDetailsResponse != null)
				xmlresult = XMLGenerator.getInstance().serializingToXML(
						projectDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			xmlresult = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("xmlresult: '" + xmlresult + "'");
			logger.debug("End getProjectNameById()");
		}
		return xmlresult;
	}

	@GET
	@Produces("application/xml")
	@Path("/getFMSIdList")
	public String getFMSIdList(@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getFMSIdList()");
		}
		String result = null;
		FMSIdListDTO fmsDetailsResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				fmsDetailsResp = restFulSvcBusinessManager.fetchFMSIds(dbName);
			}
			if (fmsDetailsResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						fmsDetailsResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getFMSIdList()");
		}

		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getSubProjectNames")
	public String getSubProjectNames(@QueryParam("dbName") String dbName,
			@QueryParam("fmsID") String fmsId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSubProjectNames()");
		}
		String result = null;
		SubProjectListDTO subProjDetailsResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.length() > 0 && fmsId != null
					&& fmsId.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				subProjDetailsResp = restFulSvcBusinessManager
						.fetchSubProjectNames(dbName, fmsId);
			}
			if (subProjDetailsResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						subProjDetailsResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getSubProjectNames()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getSubProjectNameById")
	public String getSubProjectNameById(@QueryParam("dbName") String dbName,
			@QueryParam("subProjectId") String subProjectId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSubProjectNameById()");
		}
		String result = null;
		SubProjectListDTO subProjDetailsResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (subProjectId != null && subProjectId.length() > 0
					&& dbName != null && dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				subProjDetailsResp = restFulSvcBusinessManager
						.fetchSubProjectNameById(subProjectId, dbName);
			}
			if (subProjDetailsResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						subProjDetailsResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getSubProjectNameById()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTargetNames")
	public String getTargetNames(@QueryParam("subProj") String subProjectName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTargetNames()");
		}
		String result = null;
		TargetListDTO targetReleaseCycleResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (subProjectName != null && subProjectName.length() > 0
					&& dbName != null && dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				targetReleaseCycleResp = restFulSvcBusinessManager
						.fetchTargetNames(subProjectName, dbName);
			}
			if (targetReleaseCycleResp != null) {
				result = XMLGenerator.getInstance().serializingToXML(
						targetReleaseCycleResp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTargetNames()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTargetNameById")
	public String getTargetNameById(@QueryParam("stageId") String stageId,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTargetNameById()");
		}
		String result = null;
		TargetListDTO targetReleaseCycleResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (stageId != null && stageId.length() > 0 && dbName != null
					&& dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				targetReleaseCycleResp = restFulSvcBusinessManager
						.fetchTargetNameById(stageId, dbName);
			}
			if (targetReleaseCycleResp != null) {
				result = XMLGenerator.getInstance().serializingToXML(
						targetReleaseCycleResp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTargetNameById()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTestTypes")
	public String getTestTypes(@QueryParam("projectName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTestTypes()");
		}
		String result = null;
		List<TestTypesDTO> testTypesResp = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			// stage name can be null
			if (projectName != null && projectName.length() > 0
					&& dbName != null && dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				testTypesResp = restFulSvcBusinessManager.fetchTestTypes(
						projectName, stageName, dbName);
			}
			if (testTypesResp != null)
				result = XMLGenerator.getInstance().serializingToXML(
						testTypesResp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTestTypes()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTestExecResults")
	public String getTestExecResults(
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTestExecResults()");
		}
		String result = null;
		TestExecResultListDTO testExecDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			// stage name can be null
			if (projectName != null && projectName.length() > 0
					&& dbName != null && dbName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				testExecDetailsResponse = restFulSvcBusinessManager
						.fetchTestExecResults(dbName, projectName, stageName);
			}
			if (testExecDetailsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						testExecDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTestExecResults()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTestExecDrillDown")
	public String getTestExecDrillDown(
			@QueryParam("drillStat") String drillStat,
			@QueryParam("drillType") String drillType,
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("stat1") String stat1,
			@QueryParam("stat2") String stat2,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTestExecDrillDown()");
		}

		String result = null;
		TestExecDrillResultListDTO testExecDrillDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0
					&& drillType != null && drillType.length() > 0
					&& drillStat != null && drillStat.length() > 0
					&& stat1 != null && stat1.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				testExecDrillDetailsResponse = restFulSvcBusinessManager
						.fetchTestExecDrillResults(drillStat, drillType,
								projectName, stageName, stat1, stat2, dbName);
			}
			if (testExecDrillDetailsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						testExecDrillDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTestExecDrillDown()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getTestTotActiveDrillDown")
	public String getTestTotActiveDrillDown(
			@QueryParam("drillStat") String drillStat,
			@QueryParam("drillType") String drillType,
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTestTotActiveDrillDown()");
		}

		String result = null;
		TestTotActDrillResultListDTO testTotActDrillDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0
					&& drillType != null && drillType.length() > 0
					&& drillStat != null && drillStat.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				testTotActDrillDetailsResponse = restFulSvcBusinessManager
						.fetchTestTotActDrillResults(drillStat, drillType,
								projectName, stageName, dbName);
			}
			if (testTotActDrillDetailsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						testTotActDrillDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getTestTotActiveDrillDown()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getNewDefects")
	public String getNewDefects(@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getNewDefects()");
		}

		String result = null;
		NewAndExistingDefectsListDTO newDefectsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				newDefectsResponse = restFulSvcBusinessManager.fetchNewDefects(
						projectName, stageName, dbName);
			}
			if (newDefectsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						newDefectsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getNewDefects()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getNewDefectDrill")
	public String getNewDefectDrill(@QueryParam("drillType") String drillType,
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("severity") String severity,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getNewDefectDrill()");
		}

		String result = null;
		NewAndExistingDefectsDrillResultListDTO newDefectDrillDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0
					&& severity != null && severity.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				newDefectDrillDetailsResponse = restFulSvcBusinessManager
						.fetchNewDefDrillResults(drillType, projectName,
								stageName, severity, dbName);
			}
			if (newDefectDrillDetailsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						newDefectDrillDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getNewDefectDrill()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getExistingDefects")
	public String getExistingDefects(
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getExistingDefects()");
		}

		String result = null;
		NewAndExistingDefectsListDTO existingDefectsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				existingDefectsResponse = restFulSvcBusinessManager
						.fetchExistingDefects(projectName, stageName, dbName);
			}
			if (existingDefectsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						existingDefectsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getExistingDefects()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getExistingDefectDrill")
	public String getExistingDefectDrill(
			@QueryParam("drillType") String drillType,
			@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("severity") String severity,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getExistingDefectDrill()");
		}

		String result = null;
		NewAndExistingDefectsDrillResultListDTO existingDefectDrillDetailsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& projectName != null && projectName.length() > 0
					&& drillType != null && drillType.length() > 0
					&& severity != null && severity.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				existingDefectDrillDetailsResponse = restFulSvcBusinessManager
						.fetchExistingDefDrillResults(drillType, projectName,
								stageName, severity, dbName);
			}
			if (existingDefectDrillDetailsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						existingDefectDrillDetailsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getExistingDefectDrill()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getActiveDefects")
	public String getActiveDefects(@QueryParam("projName") String projectName,
			@QueryParam("stageName") String stageName,
			@QueryParam("dbName") String dbName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getActiveDefects()");
		}

		String result = null;
		ActiveDefectsListDTO activeDefectsResponse = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.length() > 0 && projectName != null
					&& projectName.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				activeDefectsResponse = restFulSvcBusinessManager
						.fetchActiveDefects(projectName, stageName, dbName);
			}
			if (activeDefectsResponse != null)
				result = XMLGenerator.getInstance().serializingToXML(
						activeDefectsResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getActiveDefects()");
		}
		return result;
	}

	/**
	 * This method adds selected preferences to generate link for future access
	 * for users.
	 * 
	 * @param domainId
	 * @param domainProjectId
	 * @param fmsId
	 * @param projectId
	 * @param stageId
	 * @param dbName
	 * @return
	 */
	@POST
	@Path("/createARBReport")
	@Produces("application/xml")
	public String createARBReport(@FormParam("domainId") String domainId,
			@FormParam("domainProjId") String domainProjectId,
			@FormParam("fmsId") String fmsId,
			@FormParam("projId") String projectId,
			@FormParam("stageName") String projectName,
			@FormParam("stageId") String stageId,
			@FormParam("dbName") String dbName,
			@FormParam("arbComment") String arbRequestXML,
			@FormParam("solutionOwner") String solutionOwner,
			@FormParam("projectManager") String projectManager,
			@FormParam("qaManager") String qaManager,
			@FormParam("qaLead") String qaLead,
			@FormParam("projectPath") String projectPath) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start createQCTestARBReport()");
		}
		String result = null;
		ARBCOmmentsListDTO arbComments = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		List<String> roleList = null;
		try {
			boolean nullCheck = (domainId != null && domainProjectId != null
					&& fmsId != null && projectId != null
					&& projectName != null && stageId != null && dbName != null
					&& arbRequestXML != null && solutionOwner != null
					&& projectManager != null && qaManager != null && qaLead != null);

			if (nullCheck && dbName.trim().length() > 0
					&& arbRequestXML.trim().length() > 0
					&& solutionOwner.trim().length() > 0
					&& projectManager.trim().length() > 0
					&& qaManager.trim().length() > 0
					&& qaLead.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				roleList = new ArrayList<String>();
				roleList.add(GlobalConstants.SOLUTION_OWNER + ":"
						+ solutionOwner);
				roleList.add(GlobalConstants.PROJECT_MANAGER + ":"
						+ projectManager);
				roleList.add(GlobalConstants.QA_MANAGER + ":" + qaManager);
				roleList.add(GlobalConstants.QA_LEAD + ":" + qaLead);
				arbComments = restFulSvcBusinessManager.createQCTestARBReport(
						domainId, domainProjectId, fmsId, projectId,
						projectName, stageId, dbName, projectPath, arbRequestXML, roleList);
			}
			if (arbComments != null && arbComments.getReportId() != null
					&& arbComments.getReportId().length() > 0)
				result = arbComments.getReportId();
			/*
			 * result = XMLGenerator.getInstance().serializingToXML(
			 * arbComments);
			 */

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End createQCTestARBReport()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getHeaderExistence")
	public String getHeaderForExistenceByIds(
			@QueryParam("dbName") String dbName,
			@QueryParam("domainId") String domainId,
			@QueryParam("domainProjectId") String domainProjectId,
			@QueryParam("fmsId") String fmsId,
			@QueryParam("projectId") String projectId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getHeaderForExistenceByIds()");
		}
		String result = null;
		boolean exists = false;
		boolean nullCheck = false;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			nullCheck = dbName != null && domainId != null
					&& domainProjectId != null && fmsId != null
					&& projectId != null;
			if (nullCheck) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				exists = restFulSvcBusinessManager.readHeaderForExistence(
						dbName, domainId, domainProjectId, fmsId, projectId);
			}
			// if (exists)
			result = XMLGenerator.getInstance().serializingToXML(exists);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getHeaderForExistenceByIds()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getReleaseCycleExistence")
	public String getCycleForExistenceByIds(
			@QueryParam("reportId") String reportId,
			@QueryParam("stageId") String stageId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getCycleForExistenceByIds()");
		}
		String result = null;
		boolean exists = false;
		boolean valueExists = false;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			valueExists = reportId != null && !reportId.isEmpty()
					&& !reportId.equalsIgnoreCase(GlobalConstants.UNDEFINED)
					&& stageId != null && !stageId.isEmpty()
					&& !stageId.equalsIgnoreCase(GlobalConstants.UNDEFINED);
			if (valueExists
					&& (reportId.equalsIgnoreCase(GlobalConstants.NULL_VALUE) || stageId
							.equalsIgnoreCase(GlobalConstants.NULL_VALUE)))
				valueExists = false;
			if (valueExists && reportId.trim().length() > 0
					&& stageId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				exists = restFulSvcBusinessManager.readCycleForExistence(
						reportId, stageId);
			}
			// if (exists)
			result = XMLGenerator.getInstance().serializingToXML(exists);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getCycleForExistenceByIds()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getReleaseCycleId")
	public String readReleaseCycleId(@QueryParam("reportId") String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getReleaseCycleId()");
		}
		String result = null;
		Object releaseCycleId = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (reportId != null && reportId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				releaseCycleId = restFulSvcBusinessManager
						.readReleaseCycle(reportId);
			}
			if (releaseCycleId != null
					&& releaseCycleId.toString().length() > 0)
				result = XMLGenerator.getInstance().serializingToXML(
						releaseCycleId.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}
		if (result != null)
			result = releaseCycleId.toString();

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getReleaseCycleId()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getHeaderByInputIds")
	public String readHeaderByInputList(@QueryParam("dbName") String dbName,
			@QueryParam("domainId") String domainId,
			@QueryParam("domainProjectId") String domainProjectId,
			@QueryParam("fmsId") String fmsId,
			@QueryParam("projectId") String projectId) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start readHeaderByInputList()");
		}
		String result = null;
		List<ReadReportHeaderByInputListDTO> readHeaderList = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& domainId != null && domainId.length() > 0
					&& domainProjectId != null && domainProjectId.length() > 0
					&& fmsId != null && fmsId.length() > 0 && projectId != null
					&& projectId.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				readHeaderList = restFulSvcBusinessManager
						.fetchReportHeaderByInputList(dbName, domainId,
								domainProjectId, fmsId, projectId);
			}
			if (readHeaderList != null)
				result = XMLGenerator.getInstance().serializingToXML(
						readHeaderList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End readHeaderByInputList()");
		}

		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getHeaderAndCycleByInputIds")
	public String readHeaderAndCycleByInputList(
			@QueryParam("dbName") String dbName,
			@QueryParam("domainId") String domainId,
			@QueryParam("domainProjectId") String domainProjectId,
			@QueryParam("fmsId") String fmsId,
			@QueryParam("projectId") String projectId,
			@QueryParam("stageId") String stageId) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start readHeaderAndCycleByInputList()");
		}
		String result = null;
		List<ReadReportHeaderByInputListDTO> readHeaderList = null;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		try {
			if (dbName != null && dbName.trim().length() > 0
					&& domainId != null && domainId.length() > 0
					&& domainProjectId != null && domainProjectId.length() > 0
					&& fmsId != null && fmsId.length() > 0 && projectId != null
					&& projectId.length() > 0 && stageId != null
					&& stageId.length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				readHeaderList = restFulSvcBusinessManager
						.fetchHeaderAndCycleByInputList(dbName, domainId,
								domainProjectId, fmsId, projectId, stageId);
			}
			if (readHeaderList != null)
				result = XMLGenerator.getInstance().serializingToXML(
						readHeaderList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End readHeaderAndCycleByInputList()");
		}

		return result;
	}

	/**
	 * This method updates table QC_TEST_RPT_DTL for provided reportId.
	 * 
	 * @param reportId
	 * @param dbName
	 * @param arbUpdateXML
	 * @return
	 */
	@POST
	@Produces("application/xml")
	@Path("/updateARBComments")
	public String updateARBCommentsReport(
			@FormParam("reportId") String reportId,
			@FormParam("dbName") String dbName,
			@FormParam("arbComment") String arbUpdateXML,
			@FormParam("solutionOwner") String solutionOwner,
			@FormParam("projectManager") String projectManager,
			@FormParam("qaManager") String qaManager,
			@FormParam("qaLead") String qaLead,
			@FormParam("projectPath") String projectPath) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start updateARBReport()");
		}
		String result = null;
		boolean response = false;
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		List<String> roleList = null;
		try {
			boolean nullCheck = reportId != null && dbName != null
					&& arbUpdateXML != null && solutionOwner != null
					&& projectManager != null && qaManager != null
					&& qaLead != null;
			if (nullCheck && reportId.trim().length() > 0
					&& dbName.trim().length() > 0
					&& arbUpdateXML.trim().length() > 0
					&& solutionOwner.trim().length() > 0
					&& projectManager.trim().length() > 0
					&& qaManager.trim().length() > 0
					&& qaLead.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				roleList = new ArrayList<String>();
				roleList.add(GlobalConstants.SOLUTION_OWNER + ":"
						+ solutionOwner);
				roleList.add(GlobalConstants.PROJECT_MANAGER + ":"
						+ projectManager);
				roleList.add(GlobalConstants.QA_MANAGER + ":" + qaManager);
				roleList.add(GlobalConstants.QA_LEAD + ":" + qaLead);
				response = restFulSvcBusinessManager.updateARBQCTestReport(
						reportId, dbName, arbUpdateXML, roleList, projectPath);
			}
			/*if (response)
				result = XMLGenerator.getInstance().serializingToXML(
					 response);*/
			if (response) {
				result = Boolean.toString(response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End updateARBReport()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getARBDetailResults")
	public String getARBDetailReportByInputList(
			@QueryParam("dbName") String dbName,
			@QueryParam("reportId") String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getARBDetailReportByInputList()");
		}
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		ReadQCTestReportDetailByInputListDTO qcTestReportTo = null;
		String result = null;
		try {
			if (dbName != null && dbName.length() > 0 && reportId != null
					&& reportId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				qcTestReportTo = restFulSvcBusinessManager.fetchReportDetails(
						dbName, reportId);
			}
			if (qcTestReportTo != null)
				result = XMLGenerator.getInstance().serializingToXML(
						qcTestReportTo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getARBDetailReportByInputList()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getARBResultsById")
	public String getARBDetailReportLink(@QueryParam("reportId") String reportId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getARBDetailReportLink()");
		}
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		ReadQCReportHeaderCycleAndDetailByInputListDTO hdrDetailId = null;
		String result = null;
		try {
			if (reportId != null && reportId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				hdrDetailId = restFulSvcBusinessManager
						.getARBHeaderAndDetailsById(reportId);
			}
			if (hdrDetailId != null)
				result = XMLGenerator.getInstance().serializingToXML(
						hdrDetailId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getARBDetailReportLink()");
		}
		return result;

	}

	@GET
	@Produces("application/xml")
	@Path("/getARBResultsByIdWithStageId")
	public String getARBDetailReportLinkStage(
			@QueryParam("reportId") String reportId,
			@QueryParam("stageId") String stageId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getARBDetailReportLinkStage()");
		}
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		ReadQCReportHeaderCycleAndDetailByInputListDTO hdrCycAndDetailId = null;
		String result = null;
		try {
			if (reportId != null && reportId.trim().length() > 0
					&& stageId != null && stageId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				hdrCycAndDetailId = restFulSvcBusinessManager
						.getARBHeaderCycleAndDetailsById(reportId, stageId);
			}
			if (hdrCycAndDetailId != null)
				result = XMLGenerator.getInstance().serializingToXML(
						hdrCycAndDetailId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getARBDetailReportLinkStage()");
		}
		return result;
	}

	@GET
	@Produces("application/xml")
	@Path("/getStakeHolders")
	public String getStakeHolders(
			@QueryParam("reportId") String reportId
			) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStakeHolders()");
		}
		RestFulSvcBusinessManager restFulSvcBusinessManager = null;
		String result = null;
		try {
			List<StakeHoldersDTO> stakeHoldersDTOList = null;
			if (reportId != null && reportId.trim().length() > 0) {
				restFulSvcBusinessManager = new RestFulSvcBusinessManager();
				stakeHoldersDTOList = restFulSvcBusinessManager.getStakeHolders(
						reportId);
			}
			if (null != stakeHoldersDTOList && !stakeHoldersDTOList.isEmpty())
				result = XMLGenerator.getInstance().serializingToXML(
						stakeHoldersDTOList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(new RestFulServiceMessage(e.getMessage()), e);
			result = ExceptionXMLFormatter.formatMessage(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result: '" + result + "'");
			logger.debug("End getStakeHolders()");
		}
		return result;
	}
}
