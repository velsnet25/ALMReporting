package com.homedepot.is.as.dto;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("DomainList")
public class DomainListDTO 
{
	@XStreamImplicit(itemFieldName="domainNames")
	private List<DomainNamesDTO> domainNamesDTO;

	public List<DomainNamesDTO> getDomainNamesDTO() {
		return domainNamesDTO;
	}

	public void setDomainNamesDTO(List<DomainNamesDTO> domainNamesDTO) {
		this.domainNamesDTO = domainNamesDTO;
	}
}
