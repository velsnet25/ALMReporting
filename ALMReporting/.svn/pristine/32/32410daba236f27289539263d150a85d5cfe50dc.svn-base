package com.homedepot.is.as.dto;

import java.io.Serializable;
import java.util.Comparator;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("TestExecResults")
public class TestExecResultsDTO implements Serializable,Comparator<TestExecResultsDTO>{
	@XStreamAlias("TestType")
	private String testType;
	@XStreamAlias("Planned")
	private int planned;
	@XStreamAlias("Passed")
	private int passed;
	@XStreamAlias("Failed")
	private int failed;
	@XStreamAlias("InProg")
	private int inProgress;
	@XStreamAlias("NoRun")
	private int noRun;
	@XStreamAlias("Blocked")
	private int blocked;
	@XStreamAlias("Descoped")
	private int descoped;
	@XStreamAlias("TotalActive")
	private int totActive;
	@XStreamAlias("TotalOverall")
	private int totOverall;
	@XStreamAlias("DefectDensity")
	private int defectDensity;
	
	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public int getInProgress() {
		return inProgress;
	}

	public void setInProgress(int inProgress) {
		this.inProgress = inProgress;
	}

	public int getNoRun() {
		return noRun;
	}

	public void setNoRun(int noRun) {
		this.noRun = noRun;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public int getDescoped() {
		return descoped;
	}

	public void setDescoped(int descoped) {
		this.descoped = descoped;
	}

	public int getTotActive() {
		return totActive;
	}

	public void setTotActive(int totActive) {
		this.totActive = totActive;
	}

	public int getTotOverall() {
		return totOverall;
	}

	public void setTotOverall(int totOverall) {
		this.totOverall = totOverall;
	}

	public int getDefectDensity() {
		return defectDensity;
	}

	public void setDefectDensity(int defectDensity) {
		this.defectDensity = defectDensity;
	}

	public int getPlanned() {
		return planned;
	}

	public void setPlanned(int planned) {
		this.planned = planned;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	
	@Override
	public int compare(TestExecResultsDTO obj1, TestExecResultsDTO obj2) {
		return obj1.getTestType().compareTo(obj2.getTestType());
	}

}
