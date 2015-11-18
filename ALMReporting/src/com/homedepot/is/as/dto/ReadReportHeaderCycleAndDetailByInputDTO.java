package com.homedepot.is.as.dto;

import java.sql.Timestamp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("reportNames")
public class ReadReportHeaderCycleAndDetailByInputDTO {
	 @XStreamAlias("dbName")	 
	 private String createSystemUserId;
	 @XStreamAlias("hdrCrtTimeStamp")
	 private Timestamp createTimestamp;
	 @XStreamAlias("domainId")
	 private int qualityCenterDomainId;
	 @XStreamAlias("projectId")
	 private int qualityCenterProjectId;
	 @XStreamAlias("fmsId")
	 private short qualityCenterFmsProjectNumber;
	 @XStreamAlias("subProjectId")
	 private int qualityCenterReleaseId;
	 @XStreamAlias("targetId")
	 private int qualityCenterReleaseCycleId;
	 @XStreamAlias("projectLocationPath")
	 private String qualityCenterTestResultLocationText;
	 @XStreamAlias("cycDbName")
	 private String cycleCreateSystemUserId;
	 @XStreamAlias("cycCrtTimeStamp")
	 private Timestamp cycleCreateTimestamp;
	 @XStreamAlias("testType")
	 private String qualityCenterTestTypeName;
	 @XStreamAlias("dtlDbName")
	 private String detailCreateSystemUserId;
	 @XStreamAlias("dtlCrtTimeStamp")
	 private Timestamp detailCreateTimestamp;
	 @XStreamAlias("arbPlannedFlag")
	 private String arbRequiredFlag;
	 @XStreamAlias("arbComment")
	 private String commentText;
	 
	public String getCreateSystemUserId() {
		return createSystemUserId;
	}
	public void setCreateSystemUserId(String createSystemUserId) {
		this.createSystemUserId = createSystemUserId;
	}
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public int getQualityCenterDomainId() {
		return qualityCenterDomainId;
	}
	public void setQualityCenterDomainId(int qualityCenterDomainId) {
		this.qualityCenterDomainId = qualityCenterDomainId;
	}
	public int getQualityCenterProjectId() {
		return qualityCenterProjectId;
	}
	public void setQualityCenterProjectId(int qualityCenterProjectId) {
		this.qualityCenterProjectId = qualityCenterProjectId;
	}
	public short getQualityCenterFmsProjectNumber() {
		return qualityCenterFmsProjectNumber;
	}
	public void setQualityCenterFmsProjectNumber(short qualityCenterFmsProjectNumber) {
		this.qualityCenterFmsProjectNumber = qualityCenterFmsProjectNumber;
	}
	public int getQualityCenterReleaseId() {
		return qualityCenterReleaseId;
	}
	public void setQualityCenterReleaseId(int qualityCenterReleaseId) {
		this.qualityCenterReleaseId = qualityCenterReleaseId;
	}
	public int getQualityCenterReleaseCycleId() {
		return qualityCenterReleaseCycleId;
	}
	public void setQualityCenterReleaseCycleId(int qualityCenterReleaseCycleId) {
		this.qualityCenterReleaseCycleId = qualityCenterReleaseCycleId;
	}
	public void setQualityCenterTestResultLocationText(
			String qualityCenterTestResultLocationText) {
		this.qualityCenterTestResultLocationText = qualityCenterTestResultLocationText;
	}
	public String getQualityCenterTestResultLocationText() {
		return qualityCenterTestResultLocationText;
	}
	public String getCycleCreateSystemUserId() {
		return cycleCreateSystemUserId;
	}
	public void setCycleCreateSystemUserId(String cycleCreateSystemUserId) {
		this.cycleCreateSystemUserId = cycleCreateSystemUserId;
	}
	public Timestamp getCycleCreateTimestamp() {
		return cycleCreateTimestamp;
	}
	public void setCycleCreateTimestamp(Timestamp cycleCreateTimestamp) {
		this.cycleCreateTimestamp = cycleCreateTimestamp;
	}
	public String getQualityCenterTestTypeName() {
		return qualityCenterTestTypeName;
	}
	public void setQualityCenterTestTypeName(String qualityCenterTestTypeName) {
		this.qualityCenterTestTypeName = qualityCenterTestTypeName;
	}
	public String getDetailCreateSystemUserId() {
		return detailCreateSystemUserId;
	}
	public void setDetailCreateSystemUserId(String detailCreateSystemUserId) {
		this.detailCreateSystemUserId = detailCreateSystemUserId;
	}
	public Timestamp getDetailCreateTimestamp() {
		return detailCreateTimestamp;
	}
	public void setDetailCreateTimestamp(Timestamp detailCreateTimestamp) {
		this.detailCreateTimestamp = detailCreateTimestamp;
	}
	public String isArbRequiredFlag() {
		return arbRequiredFlag;
	}
	public void setArbRequiredFlag(String arbRequiredFlag) {
		this.arbRequiredFlag = arbRequiredFlag;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	 
}
