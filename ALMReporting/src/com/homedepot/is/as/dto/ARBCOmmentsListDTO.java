package com.homedepot.is.as.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("ARBCommentsList")
public class ARBCOmmentsListDTO implements Serializable
{
	@XStreamAlias("reportId")
	private String reportId;
	@XStreamAlias("TestType")
	private String testType;
	@XStreamAlias("ARBPlanned")
	private String arbFlag;
	@XStreamAlias("ARBComments")
	private String comments;
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getArbFlag() {
		return arbFlag;
	}
	public void setArbFlag(String arbFlag) {
		this.arbFlag = arbFlag;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}