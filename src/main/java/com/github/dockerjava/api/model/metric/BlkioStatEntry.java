package com.github.dockerjava.api.model.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlkioStatEntry {

	@JsonProperty("major")
	private long major;
	
	@JsonProperty("minor")
	private long minor;
	
	@JsonProperty("op")
	private String op;
	
	@JsonProperty("value")
	private long value;

	public long getMajor() {
		return major;
	}

	public long getMinor() {
		return minor;
	}

	public String getOp() {
		return op;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "BlkioStatEntry [major=" + major + ", minor=" + minor + ", op="
				+ op + ", value=" + value + "]";
	}
}
