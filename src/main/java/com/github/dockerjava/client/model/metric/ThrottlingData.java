package com.github.dockerjava.client.model.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThrottlingData {

	@JsonProperty("periods")
	private long periods;
	
	@JsonProperty("throttled_periods")
	private long throttledPeriods;
	
	@JsonProperty("throttled_time")
	private long throttledTime;

	public long getPeriods() {
		return periods;
	}

	public long getThrottledPeriods() {
		return throttledPeriods;
	}

	public long getThrottledTime() {
		return throttledTime;
	}

	@Override
	public String toString() {
		return "ThrottlingData [periods=" + periods + ", throttledPeriods="
				+ throttledPeriods + ", throttledTime=" + throttledTime + "]";
	}
}
