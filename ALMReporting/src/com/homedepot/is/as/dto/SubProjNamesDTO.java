package com.homedepot.is.as.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SubProjNames")
public class SubProjNamesDTO {

	@XStreamAlias("SubProjId")
	private int subProjId;

	
	@XStreamAlias("SubProjName")
	private String subProjName;

	public int getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(int subProjId) {
		this.subProjId = subProjId;
	}

	public String getSubProjName() {
		return subProjName;
	}

	public void setSubProjName(String subProjName) {
		this.subProjName = subProjName;
	}
}
