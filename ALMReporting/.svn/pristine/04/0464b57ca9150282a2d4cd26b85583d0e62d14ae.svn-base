package com.homedepot.is.as.dto;

import java.io.Serializable;
import java.util.Comparator;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("NewandExistingDefect")
public class NewAndExistingDefectsDTO implements Serializable,Comparator<NewAndExistingDefectsDTO>{
	@XStreamAlias("TestType")
	private String testType;
	@XStreamAlias("SEV1")
	private int sumSev1;
	@XStreamAlias("SEV2")
	private int sumSev2;
	@XStreamAlias("SEV3")
	private int sumSev3;
	@XStreamAlias("SEV4")
	private int sumSev4;
	@XStreamAlias("SEV5")
	private int sumSev5;
	@XStreamAlias("Total")
	private int total;
	
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public int getSumSev1() {
		return sumSev1;
	}
	public void setSumSev1(int sumSev1) {
		this.sumSev1 = sumSev1;
	}
	public int getSumSev2() {
		return sumSev2;
	}
	public void setSumSev2(int sumSev2) {
		this.sumSev2 = sumSev2;
	}
	public int getSumSev3() {
		return sumSev3;
	}
	public void setSumSev3(int sumSev3) {
		this.sumSev3 = sumSev3;
	}
	public int getSumSev4() {
		return sumSev4;
	}
	public void setSumSev4(int sumSev4) {
		this.sumSev4 = sumSev4;
	}
	public int getSumSev5() {
		return sumSev5;
	}
	public void setSumSev5(int sumSev5) {
		this.sumSev5 = sumSev5;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int compare(NewAndExistingDefectsDTO obj1, NewAndExistingDefectsDTO obj2) {
		return obj1.getTestType().compareTo(obj2.getTestType());
	}
	
}
