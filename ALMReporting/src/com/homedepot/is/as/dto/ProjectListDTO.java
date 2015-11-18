package com.homedepot.is.as.dto;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ProjectList")
public class ProjectListDTO 
{
	@XStreamImplicit(itemFieldName="projectNames")
	private List<ProjectNamesDTO> projectNamesDTO;

	public List<ProjectNamesDTO> getProjectNamesDTO() {
		return projectNamesDTO;
	}

	public void setProjectNamesDTO(List<ProjectNamesDTO> projectNamesDTO) {
		this.projectNamesDTO = projectNamesDTO;
	}
}
