package com.homedepot.is.as.dto;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@SuppressWarnings("serial")
@XStreamAlias("NewAndExistingDefectList")
public class NewAndExistingDefectsListDTO implements Serializable {
	@XStreamImplicit(itemFieldName = "NewAndExistingDefects")
	private List<NewAndExistingDefectsDTO> newAndExistingDefectsDTO;

	public List<NewAndExistingDefectsDTO> getNewAndExistingDefectsDTO() {
		return newAndExistingDefectsDTO;
	}

	public void setNewAndExistingDefectsDTO(
			List<NewAndExistingDefectsDTO> newAndExistingDefectsDTO) {
		this.newAndExistingDefectsDTO = newAndExistingDefectsDTO;
	}
}