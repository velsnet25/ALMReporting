package com.homedepot.is.as.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ActiveDefectsDTO;
import com.homedepot.is.as.dto.ActiveDefectsListDTO;
import com.homedepot.is.as.util.AppUtil;
import com.homedepot.is.as.util.GlobalConstants;
import com.homedepot.ta.aa.dao.Inputs;
import com.homedepot.ta.aa.dao.Query;
import com.homedepot.ta.aa.dao.Results;
import com.homedepot.ta.aa.dao.ResultsReader;
import com.homedepot.ta.aa.dao.basic.BasicDAO;
import com.homedepot.ta.aa.dao.exceptions.QueryException;
import com.homedepot.ta.aa.dao.stream.MapStream;

/**
 * This class gets active defect details.
 * 
 * @author SXB8898
 *
 */
public class ActiveDefectDetailsDAO {

	private static final Logger logger = Logger
			.getLogger(ActiveDefectDetailsDAO.class);
	
	/**
	 * This method gets active defects for given inputs with release cycle(s)
	 * Release cycle as All Stages and for selected cycle or stage. 
	 * @param projName
	 * @param stageName
	 * @param dbName
	 * @return
	 * @throws QueryException
	 */
	public static ActiveDefectsListDTO getActiveDefects(final String projName,
			final String stageName, final String dbName) throws QueryException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("start readBugDefectDetailsByInputList/getActiveDefects");
		}
		final ActiveDefectsListDTO activeDeflistDTO = new ActiveDefectsListDTO();
		final List<ActiveDefectsDTO> activeDefectDTOasList = new ArrayList<ActiveDefectsDTO>();
		
		MapStream inputs = new MapStream("readBugDefectDetailsByInputList");
		List<Object> inputBugStatusList = null;
		
		inputs.put("qcrDatabaseName", dbName);
		
		if(stageName != null && stageName.equalsIgnoreCase(GlobalConstants.ALL_STAGES))
		{
			// GET_ACTIVE_DEFECTS
			inputBugStatusList = new ArrayList<Object>();
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputs.put("bugStatusList", inputBugStatusList);
			inputs.put("releaseName", projName);
		}else{
			// GET_ACTIVE_DEFECTS_STAGE
			inputBugStatusList = new ArrayList<Object>();
			inputBugStatusList.add(GlobalConstants.CLOSED);
			inputs.put("bugStatusList", inputBugStatusList);
			inputs.put("releaseName", projName);
			inputs.put("releaseCyclesName", stageName); // optional
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("executing the query");
		}

		BasicDAO.getResult(GlobalConstants.CONTRACT_NAME, GlobalConstants.BUSINESS_USE_ID,
				GlobalConstants.CONTRACT_VERSION, inputs, new ResultsReader() {
					public void readResults(Results results, Query query,
							Inputs inputs) throws QueryException {
						ActiveDefectsDTO activeDefectDetailDTO = null;
						while (results.next()) {
							activeDefectDetailDTO = new ActiveDefectsDTO();
							String businessImpact = null;
							activeDefectDetailDTO
									.setSerialNumber(results
											.getInt("serialNumber"));
							activeDefectDetailDTO
									.setDefectNumber(results
											.getInt("defectNumber"));
							activeDefectDetailDTO
									.setSeverity(results.getString("severity"));
							activeDefectDetailDTO
									.setDefectDescription(results
											.getString("defectDescription"));
							activeDefectDetailDTO
									.setStatus(results.getString("status"));
							if(results.getString("businessImpact") != null){
								businessImpact = results.getString("businessImpact");
								if(businessImpact != null && businessImpact.trim().length() > 0){
									businessImpact = AppUtil.clearHTMLTags(businessImpact, -1);
									activeDefectDetailDTO
									.setBusinessImpact(businessImpact.trim());
								}
							}else{
								activeDefectDetailDTO
								.setBusinessImpact("");
							}
							activeDefectDTOasList
									.add(activeDefectDetailDTO);
						}
						activeDeflistDTO.setActiveDefectsDTO(activeDefectDTOasList);
					}
				});

		if (logger.isDebugEnabled()) {
			logger.debug("finish readBugDefectDetailsByInputList");
			logger.debug("returning "
					+ activeDefectDTOasList.size() + " item(s)");
		}
		return activeDeflistDTO;
	}
}