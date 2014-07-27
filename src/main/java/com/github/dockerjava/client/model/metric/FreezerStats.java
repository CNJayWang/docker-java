package com.github.dockerjava.client.model.metric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FreezerStats {

	@JsonProperty("parent_state")
	private String parentState;
	
	@JsonProperty("self_state")
	private String selfState;

	public String getParentState() {
		return parentState;
	}

	public String getSelfState() {
		return selfState;
	}

	@Override
	public String toString() {
		return "FreezerStats [parentState=" + parentState + ", selfState="
				+ selfState + "]";
	}
}
