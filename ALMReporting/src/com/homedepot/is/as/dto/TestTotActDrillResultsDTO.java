package com.homedepot.is.as.dto;

import java.sql.Timestamp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TestTotActDrillResults")
public class TestTotActDrillResultsDTO {
	@XStreamAlias("DefectId")
	private String defectId;
	@XStreamAlias("DetectDt")
	private Timestamp detectDt;
	@XStreamAlias("AssignedTeam")
	private String assignedTeam;
	@XStreamAlias("AssignedTo")
	private String assignedTo;
	@XStreamAlias("Summary")
	private String summary;
	
	//added below properties to full fill DAO requirement 
	//for readBugByInputList and readBugDetailsByInputList selectors.
	private String assignedToName;
	private String bugStatus;
	private String bugUserTemplate06;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAssignedToName() {
		return assignedToName;
	}
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	public String getBugStatus() {
		return bugStatus;
	}
	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}
	public String getBugUserTemplate06() {
		return bugUserTemplate06;
	}
	public void setBugUserTemplate06(String bugUserTemplate06) {
		this.bugUserTemplate06 = bugUserTemplate06;
	}
	public String getDefectId() {
		return defectId;
	}
	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}
	public Timestamp getDetectDt() {
		return detectDt;
	}
	public void setDetectDt(Timestamp detectDt) {
		this.detectDt = detectDt;
	}
	public String getAssignedTeam() {
		return assignedTeam;
	}
	public void setAssignedTeam(String assignedTeam) {
		this.assignedTeam = assignedTeam;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
}
