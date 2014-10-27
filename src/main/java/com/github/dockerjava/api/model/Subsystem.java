package com.github.dockerjava.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subsystem {

	@JsonProperty("Subsystem")
	private String name;

	@JsonProperty("Out")
	private String out;

	@JsonProperty("Err")
	private String err;

	@JsonProperty("Status")
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
