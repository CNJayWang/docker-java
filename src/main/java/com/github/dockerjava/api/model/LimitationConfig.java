package com.github.dockerjava.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LimitationConfig {

	@JsonProperty("cpuShares")
	private int cpuShares = 0;

	@JsonProperty("cpuset")
	private String cpuset = "";
	
	@JsonProperty("memory")
	private long memoryLimit = 0;

	private boolean saveChanges = false;

	public int getCpuShares() {
		return cpuShares;
	}

	public void setCpuShares(int cpuShares) {
		this.cpuShares = cpuShares;
	}

	public String getCpuset() {
		return cpuset;
	}

	public void setCpuset(String cpuset) {
		this.cpuset = cpuset;
	}

	public long getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(long memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public boolean isSaveChanges() {
		return saveChanges;
	}

	public void setSaveChanges(boolean saveChanges) {
		this.saveChanges = saveChanges;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
