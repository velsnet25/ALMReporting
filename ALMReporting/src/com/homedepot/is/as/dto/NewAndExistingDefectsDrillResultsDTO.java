package com.homedepot.is.as.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("NewAndExistingDefectsDrillResults")
public class NewAndExistingDefectsDrillResultsDTO {
	@XStreamAlias("AppGroup")
	private String applicationGroup;
	@XStreamAlias("App")
	private String application;
	@XStreamAlias("AppComponent")
	private String applicationComponent;
	@XStreamAlias("DefId")
	private int defectId;
	@XStreamAlias("Severity")
	private String severity;
	@XStreamAlias("Summary")
	private String summary;
	@XStreamAlias("DetectedTeam")
	private String detectedByTeam;
	@XStreamAlias("AssignedTeam")
	private String assignedToTeam;
	@XStreamAlias("AssignedPerson")
	private String assignedTo;
	@XStreamAlias("AssignedName")
	private String assignedToName;
	@XStreamAlias("Status")
	private String status;
	@XStreamAlias("Subject")
	private String subject;
	
	public String getApplicationGroup() {
		return applicationGroup;
	}
	public void setApplicationGroup(String applicationGroup) {
		this.applicationGroup = applicationGroup;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getApplicationComponent() {
		return applicationComponent;
	}
	public void setApplicationComponent(String applicationComponent) {
		this.applicationComponent = applicationComponent;
	}
	public int getDefectID() {
		return defectId;
	}
	public void setDefectId(int defectId) {
		this.defectId = defectId;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetectedByTeam() {
		return detectedByTeam;
	}
	public void setDetectedByTeam(String detectedByTeam) {
		this.detectedByTeam = detectedByTeam;
	}
	public String getAssignedToTeam() {
		return assignedToTeam;
	}
	public void setAssignedToTeam(String assignedToTeam) {
		this.assignedToTeam = assignedToTeam;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssignedToName() {
		return assignedToName;
	}
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
