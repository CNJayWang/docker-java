package com.kpelykh.docker.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

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
