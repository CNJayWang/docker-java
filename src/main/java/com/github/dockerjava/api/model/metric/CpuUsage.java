package com.github.dockerjava.api.model.metric;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuUsage {

	@JsonProperty("current_usage")
	private double currentUsage;
	
	@JsonProperty("total_usage")
	private long totalUsage;
	
	@JsonProperty("percpu_usage")
	private long[] percpuUsage;
	
	@JsonProperty("usage_in_kernelmode")
	private long usageInKernelMode;
	
	@JsonProperty("usage_in_usermode")
	private long usageInUserMode;

	public double getCurrentUsage() {
		return currentUsage;
	}

	public long getTotalUsage() {
		return totalUsage;
	}

	public long[] getPercpuUsage() {
		return percpuUsage;
	}

	public long getUsageInKernelMode() {
		return usageInKernelMode;
	}

	public long getUsageInUserMode() {
		return usageInUserMode;
	}

	@Override
	public String toString() {
		return "CpuUsage [currentUsage="
				+ currentUsage + ", totalUsage=" + totalUsage
				+ ", percpuUsage=" + Arrays.toString(percpuUsage)
				+ ", usageInKernelMode=" + usageInKernelMode
				+ ", usageInUserMode=" + usageInUserMode + "]";
	}
}
