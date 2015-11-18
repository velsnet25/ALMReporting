package com.homedepot.is.as.dto;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@SuppressWarnings("serial")
@XStreamAlias("TestExecList")
public class TestExecResultListDTO implements Serializable
{
	@XStreamImplicit(itemFieldName="testExecResults")
	private List<TestExecResultsDTO> testExecDTO;

	public List<TestExecResultsDTO> getTestExecDTO() {
		return testExecDTO;
	}

	public void setTestExecDTO(List<TestExecResultsDTO> testExecDTO) {
		this.testExecDTO = testExecDTO;
	}
}
