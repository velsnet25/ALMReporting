package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ProjectListDTO;
import com.homedepot.is.as.dto.ProjectNamesDTO;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets list of project names and projectId's.
 * @author SXB8898
 *
 */
public class ProjectNameDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(ProjectNameDetailsDAO.class);

	/**
	 * This method returns list of subDomainProject names for given domaain name.
	 * 
	 * @param domainName
	 * @return
	 * @throws QueryException
	 */
	public static ProjectListDTO readProjectsByDomainName(String domainName)
			throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readProjectsByDomainName");
		}
		final List<ProjectNamesDTO> readProjectsByDomainNameList = new ArrayList<ProjectNamesDTO>();
		final ProjectListDTO projectListDTO = new ProjectListDTO();

		MapStream inputs = new MapStream("readProjectsByDomainName");
		inputs.put("domainName", domainName);

		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}
		try {
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID, GlobalConstants.CONTRACT_VERSION,
					inputs, new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ProjectNamesDTO readProjectsByDomainNameDTO = null;
							while (results.next()) {
								readProjectsByDomainNameDTO = new ProjectNamesDTO();
								readProjectsByDomainNameDTO
										.setProjectId(results
												.getInt("projectId"));
								readProjectsByDomainNameDTO
										.setProjectName(results
												.getString("projectName"));
								readProjectsByDomainNameDTO
										.setDatabaseName(results
												.getString("databaseName"));
								readProjectsByDomainNameList
										.add(readProjectsByDomainNameDTO);
							}
							projectListDTO
									.setProjectNamesDTO(readProjectsByDomainNameList);
						}
					});
		} catch (Exception e) {
			logger.fatal("Exception in readProjectsByDomainName", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finish readProjectsByDomainName");
			logger.debug("returning " + readProjectsByDomainNameList.size()
					+ " item(s)");
		}
		return projectListDTO;
	}

	/**
	 * This method gives project name for given projectId.
	 * 
	 * @param projectId
	 * @return
	 * @throws QueryException
	 */
	public static ProjectListDTO readProjectsByProjectId(
			final String projectId) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger.debug("start readProjectByDomainNameById");
		}
		final List<ProjectNamesDTO> readProjectsByProjectIdList = new ArrayList<ProjectNamesDTO>();
		final ProjectListDTO projectListDTO = new ProjectListDTO();
		
		MapStream inputs = null;

		try {
			inputs = new MapStream("readProjectsByProjectId");
			if(projectId != null && projectId.trim().length() > 0)
			inputs.put("projectId", Integer.parseInt(projectId));

			if (logger.isDebugEnabled()) {
				logger.debug("executing the query");
			}

			
			BasicDAO.getResult(GlobalConstants.CONTRACT_NAME,
					GlobalConstants.BUSINESS_USE_ID,
					GlobalConstants.CONTRACT_VERSION, inputs,
					new ResultsReader() {
						public void readResults(Results results, Query query,
								Inputs inputs) throws QueryException {
							ProjectNamesDTO readProjectsByProjectIdDTO = null;
							while (results.next()) {
								readProjectsByProjectIdDTO = new ProjectNamesDTO();
								readProjectsByProjectIdDTO.setProjectId(results.getInt("projectId"));
								readProjectsByProjectIdDTO.setProjectName(results
										.getString("projectName"));
								readProjectsByProjectIdDTO.setDatabaseName(results
										.getString("databaseName"));
								readProjectsByProjectIdList
										.add(readProjectsByProjectIdDTO);
							}
							projectListDTO.setProjectNamesDTO(readProjectsByProjectIdList);
						}
						
					});
		} catch (Exception e) {
			logger.fatal("Exception in readProjectsByProjectId", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("finish readProjectsByProjectId");
			logger.debug("returning " + readProjectsByProjectIdList.size()
					+ " item(s)");
		}
		return projectListDTO;
	}
}
