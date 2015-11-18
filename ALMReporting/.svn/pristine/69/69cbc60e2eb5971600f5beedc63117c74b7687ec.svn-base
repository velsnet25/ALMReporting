package com.homedepot.is.as.dto;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("SubProjectList")
public class SubProjectListDTO 
{
	@XStreamImplicit(itemFieldName="SubProjNames")
	private List<SubProjNamesDTO> subProjNamesDTO;

	@XStreamImplicit(itemFieldName="MPLNames")
	private List<StakeHoldersDTO> stakeHoldersDTO;
	
	public List<SubProjNamesDTO> getSubProjNamesDTO() {
		return subProjNamesDTO;
	}

	public void setSubProjNamesDTO(List<SubProjNamesDTO> subProjNamesDTO) {
		this.subProjNamesDTO = subProjNamesDTO;
	}
	
	public void setStakeHoldersDTO(List<StakeHoldersDTO> stakeHoldersDTO) {
		this.stakeHoldersDTO = stakeHoldersDTO;
	}

	public List<StakeHoldersDTO> getStakeHoldersDTO() {
		return stakeHoldersDTO;
	}
}
