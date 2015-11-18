package com.homedepot.is.as.dto;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("reportDetails")
public class ReadQCReportHeaderCycleAndDetailByInputListDTO{
	
	@XStreamImplicit(itemFieldName="reportNames")
	private List<ReadReportHeaderCycleAndDetailByInputDTO> reportDetailList = null;
	
	@XStreamImplicit(itemFieldName="reportIds")
	private List<ReadReportIdsDTO> reportIds = null;
		
	public List<ReadReportHeaderCycleAndDetailByInputDTO> getQcReportDetailList() {
		return reportDetailList;
	}

	public void setQcReportDetailList(
			List<ReadReportHeaderCycleAndDetailByInputDTO> qcReportDetailList) {
		this.reportDetailList = qcReportDetailList;
	}

	public List<ReadReportHeaderCycleAndDetailByInputDTO> getReportDetailList() {
		return reportDetailList;
	}

	public void setReportDetailList(
			List<ReadReportHeaderCycleAndDetailByInputDTO> reportDetailList) {
		this.reportDetailList = reportDetailList;
	}

	public List<ReadReportIdsDTO> getReportIds() {
		return reportIds;
	}

	public void setReportIds(List<ReadReportIdsDTO> reportIds) {
		this.reportIds = reportIds;
	}

}
