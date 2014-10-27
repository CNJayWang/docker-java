package com.github.dockerjava.api.model.metric;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MemoryStats {

	@JsonProperty("usage")
	private long usage;
	
	@JsonProperty("max_usage")
	private long maxUsage;
	
	@JsonProperty("stats")
	private Map<String, Long> stats;
	
	@JsonProperty("failcnt")
	private long failcnt;

	public long getUsage() {
		return usage;
	}

	public long getMaxUsage() {
		return maxUsage;
	}

	public Map<String, Long> getStats() {
		return stats;
	}

	public long getFailcnt() {
		return failcnt;
	}

	@Override
	public String toString() {
		return "MemoryStats [usage=" + usage + ", maxUsage=" + maxUsage
				+ ", stats=" + stats + ", failcnt=" + failcnt + "]";
	}
}
