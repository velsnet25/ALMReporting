package com.homedepot.is.as.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RoleNames")
public class StakeHoldersDTO {
	@XStreamAlias("ReportId")
	private int reportId;
	@XStreamAlias("FMSId")
	private short fmsId;
	/*@XStreamAlias("SolutionOwner")
	private String solutionOwner;
	@XStreamAlias("ProjectManager")
	private String projectManager;
	@XStreamAlias("QAManager")
	private String qaManager;
	@XStreamAlias("QALead")
	private String qaLead;*/
	@XStreamAlias("DetailTimeStamp")
	Timestamp detailTimeStamp;
	@XStreamAlias("roleTimeStamp")
	Timestamp roleTimeStamp;
		
	@XStreamAlias("RoleName")
	private String roleName;
	@XStreamAlias("RolePersonName")
	private String rolePersonName;
	@XStreamAlias("Date")
	String date;
	
	@XStreamAlias("ProjectPath")
	private String projectPath;
	//List<StakeHoldersDTO> stakeHoldersDTOList = new ArrayList<StakeHoldersDTO>();
	
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public short getFmsId() {
		return fmsId;
	}
	public void setFmsId(short fmsId) {
		this.fmsId = fmsId;
	}
/*	public String getSolutionOwner() {
		return solutionOwner;
	}
	public void setSolutionOwner(String solutionOwner) {
		this.solutionOwner = solutionOwner;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getQaManager() {
		return qaManager;
	}
	public void setQaManager(String qaManager) {
		this.qaManager = qaManager;
	}
	public String getQaLead() {
		return qaLead;
	}
	public void setQaLead(String qaLead) {
		this.qaLead = qaLead;
	}*/
	public Timestamp getDetailTimeStamp() {
		return detailTimeStamp;
	}
	public void setDetailTimeStamp(Timestamp detailTimeStamp) {
		this.detailTimeStamp = detailTimeStamp;
	}
	public Timestamp getRoleTimeStamp() {
		return roleTimeStamp;
	}
	public void setRoleTimeStamp(Timestamp roleTimeStamp) {
		this.roleTimeStamp = roleTimeStamp;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRolePersonName() {
		return rolePersonName;
	}
	public void setRolePersonName(String rolePersonName) {
		this.rolePersonName = rolePersonName;
	}
	/*public List<StakeHoldersDTO> getStakeHoldersDTOList() {
		return stakeHoldersDTOList;
	}
	public void setStakeHoldersDTOList(List<StakeHoldersDTO> stakeHoldersDTO) {
		this.stakeHoldersDTOList = stakeHoldersDTO;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	//Instance variable for projectId
	private int projectId;

	//Instance variable for projectAssociateId
	private String projectAssociateId;

	//Instance variable for projectRoleCode
	private short projectRoleCode;

	//Instance variable for displayRoleCode
	private String displayRoleCode;

	//Instance variable for roleDescription
	private String roleDescription;

	//Instance variable for shortRoleDescription
	private String shortRoleDescription;

	//getter method for projectId
	public int getProjectId() {
		return projectId;
	}

	//setter method for projectId
	public void setProjectId(int aValue) {
		projectId = aValue;
	}

	//getter method for projectAssociateId
	public String getProjectAssociateId() {
		return projectAssociateId;
	}

	//setter method for projectAssociateId
	public void setProjectAssociateId(String aValue) {
		projectAssociateId = aValue;
	}

	//getter method for projectRoleCode
	public short getProjectRoleCode() {
		return projectRoleCode;
	}

	//setter method for projectRoleCode
	public void setProjectRoleCode(short aValue) {
		projectRoleCode = aValue;
	}

	//getter method for displayRoleCode
	public String getDisplayRoleCode() {
		return displayRoleCode;
	}

	//setter method for displayRoleCode
	public void setDisplayRoleCode(String aValue) {
		displayRoleCode = aValue;
	}

	//getter method for roleDescription
	public String getRoleDescription() {
		return roleDescription;
	}

	//setter method for roleDescription
	public void setRoleDescription(String aValue) {
		roleDescription = aValue;
	}

	//getter method for shortRoleDescription
	public String getShortRoleDescription() {
		return shortRoleDescription;
	}

	//setter method for shortRoleDescription
	public void setShortRoleDescription(String aValue) {
		shortRoleDescription = aValue;
	}
	
	public String getProjectPath() {
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
}
