package com.homedepot.is.as.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DomainNames")
public class DomainNamesDTO {
		
	@XStreamAlias("DomainId")
	private String domainId;

	@XStreamAlias("DomainName")
	private String domainName;
	
	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
