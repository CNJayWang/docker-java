package com.kpelykh.docker.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
	
	@JsonProperty("Cpu")
	private Cpu cpu;
	
	@JsonProperty("Memory")
	private Memory memory;

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	@Override
	public String toString() {
		return "Metric [cpu=" + cpu + ", memory=" + memory + "]";
	}
}
