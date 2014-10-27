package com.github.dockerjava.api.model.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuStats {
	
	@JsonProperty("cpu_usage")
	private CpuUsage cpuUsage;
	
	@JsonProperty("throlling_data")
	private ThrottlingData throttlingData;

	public CpuUsage getCpuUsage() {
		return cpuUsage;
	}

	public ThrottlingData getThrottlingData() {
		return throttlingData;
	}

	@Override
	public String toString() {
		return "CpuStats [cpuUsage=" + cpuUsage + ", throttlingData="
				+ throttlingData + "]";
	}
}
