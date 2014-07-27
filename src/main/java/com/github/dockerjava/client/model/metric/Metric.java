package com.github.dockerjava.client.model.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric {
	
	@JsonProperty("cpu_stats")
	private CpuStats cpuStats;
	
	@JsonProperty("memory_stats")
	private MemoryStats memoryStats;
	
	@JsonProperty("blkio_stats")
	private BlkioStats blkioStats;
	
	@JsonProperty("freezer_stats")
	private FreezerStats freezerStats;

	public CpuStats getCpuStats() {
		return cpuStats;
	}

	public MemoryStats getMemoryStats() {
		return memoryStats;
	}

	public BlkioStats getBlkioStats() {
		return blkioStats;
	}

	public FreezerStats getFreezerStats() {
		return freezerStats;
	}

	@Override
	public String toString() {
		return "Metric [cpuStats=" + cpuStats + ", memoryStats=" + memoryStats
				+ ", blkioStats=" + blkioStats + ", freezerStats="
				+ freezerStats + "]";
	}
}
