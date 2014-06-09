package com.kpelykh.docker.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Memory {
	
	@JsonProperty("Rss")
	private long rss;

	public long getRss() {
		return rss;
	}

	public void setRss(long rss) {
		this.rss = rss;
	}

	@Override
	public String toString() {
		return "Memory [rss=" + rss + "]";
	}
	
}
