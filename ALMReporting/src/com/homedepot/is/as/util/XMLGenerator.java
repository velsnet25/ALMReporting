/**
 *
 * This program is proprietary to The Home Depot and is not to be
 * reproduced, used, or disclosed without permission of:
 *    
 *  The Home Depot
 *  2455 Paces Ferry Road, N.W.
 *  Atlanta, GA 30339-4053
 *

 */

package com.homedepot.is.as.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import com.homedepot.is.as.dto.ARBCOmmentsListDTO;
import com.homedepot.is.as.dto.ActiveDefectsListDTO;
import com.homedepot.is.as.dto.DomainListDTO;
import com.homedepot.is.as.dto.FMSIdListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsDrillResultListDTO;
import com.homedepot.is.as.dto.NewAndExistingDefectsListDTO;
import com.homedepot.is.as.dto.ProjectListDTO;
import com.homedepot.is.as.dto.ReadQCReportHeaderCycleAndDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadQCTestReportDetailByInputListDTO;
import com.homedepot.is.as.dto.ReadReleaseCyclesAndReleasesDetailsByInputListDTO;
import com.homedepot.is.as.dto.ReadReportHeaderByInputListDTO;
import com.homedepot.is.as.dto.StakeHoldersDTO;
import com.homedepot.is.as.dto.SubProjectListDTO;
import com.homedepot.is.as.dto.TargetListDTO;
import com.homedepot.is.as.dto.TestExecDrillResultListDTO;
import com.homedepot.is.as.dto.TestExecResultListDTO;
import com.homedepot.is.as.dto.TestTotActDrillResultListDTO;
import com.homedepot.is.as.dto.TestTypesDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class generates XML structures.
 * 
 * @author SXB8898
 *
 */
public class XMLGenerator {

	private static final Logger logger = Logger.getLogger(XMLGenerator.class);
	
	private static XMLGenerator transformerObj = null;
	
	private XMLGenerator() {
	}

	public synchronized static XMLGenerator getInstance() {
		if (transformerObj == null) {
			transformerObj = new XMLGenerator();
		}
		return transformerObj;
	}

	public String serializingToXML(Object obj) throws Exception {
		String xml = null;
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PrintWriter out = new PrintWriter(new OutputStreamWriter(stream,
					"UTF-8"));

			xstream.toXML(obj, out);
			xml = ((ByteArrayOutputStream) stream).toString("UTF-8");
			out.flush();
			out.close();
			stream.flush();
			stream.close();
		} catch (UnsupportedEncodingException e) {
			logger.fatal("Exception occured while serializing to XML",e);
		}
		return xml;
	}

	// @SuppressWarnings("unchecked")
	private static XStream xstream = new XStream(new DomDriver()) {
		{
			processAnnotations(DomainListDTO.class);
			processAnnotations(ProjectListDTO.class);
			processAnnotations(FMSIdListDTO.class);
			processAnnotations(SubProjectListDTO.class);
			processAnnotations(TargetListDTO.class);
			processAnnotations(ReadReleaseCyclesAndReleasesDetailsByInputListDTO.class);
			processAnnotations(TestExecResultListDTO.class);
			processAnnotations(TestExecDrillResultListDTO.class);
			processAnnotations(TestTotActDrillResultListDTO.class);
			processAnnotations(NewAndExistingDefectsListDTO.class);
			processAnnotations(ActiveDefectsListDTO.class);
			processAnnotations(NewAndExistingDefectsDrillResultListDTO.class);
			processAnnotations(TestTypesDTO.class);
			processAnnotations(ReadReportHeaderByInputListDTO.class);
			processAnnotations(ARBCOmmentsListDTO.class);
			processAnnotations(ReadQCTestReportDetailByInputListDTO.class);
			processAnnotations(ReadQCReportHeaderCycleAndDetailByInputListDTO.class);
			processAnnotations(StakeHoldersDTO.class);
		}
	};

	// XML Reader for ARB Test Type, Flag and Comments
	@SuppressWarnings("unchecked")
	public static List<ARBCOmmentsListDTO> transformXmlToARBCOmmentsListDTO(
			String input) {
		xstream.alias("data", String.class);
		xstream.alias("List", List.class);
		xstream.alias("ARBCommentsList", ARBCOmmentsListDTO.class);
		return (List<ARBCOmmentsListDTO>) xstream.fromXML(input);
	}

	// XML Reader for ARB Test Type, Flag and Comments
	@SuppressWarnings("unchecked")
	public static List<ReadQCTestReportDetailByInputListDTO> transformXmlToQCTestReportListDTO(
			String input) {
		xstream.alias("data", String.class);
		xstream.alias("List", List.class);
		xstream.alias("ReadQCTestReportDetailByInputList",
				ReadQCTestReportDetailByInputListDTO.class);
		return (List<ReadQCTestReportDetailByInputListDTO>) xstream
				.fromXML(input);
	}
}
