package com.homedepot.is.as.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TargetNames")
public class ReadReleaseCyclesAndReleasesDetailsByInputListDTO {
	@XStreamAlias("TargetId")
	private int releaseCyclesId;
	
	@XStreamAlias("TargetName")
	private String releaseCyclesName;

	@XStreamAlias("releaseName")
	private String releaseName;

	//getter method for releaseCyclesId
	public int getReleaseCyclesId() {
		return releaseCyclesId;
	}

	//setter method for releaseCyclesId
	public void setReleaseCyclesId(int aValue) {
		releaseCyclesId = aValue;
	}

	//getter method for releaseCyclesName
	public String getReleaseCyclesName() {
		return releaseCyclesName;
	}

	//setter method for releaseCyclesName
	public void setReleaseCyclesName(String aValue) {
		releaseCyclesName = aValue;
	}

	//getter method for releaseName
	public String getReleaseName() {
		return releaseName;
	}

	//setter method for releaseName
	public void setReleaseName(String aValue) {
		releaseName = aValue;
	}

}
