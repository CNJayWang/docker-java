package com.kpelykh.docker.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cpu {

	@JsonProperty("LoadAverage")
	private double loadAverage;

	@JsonProperty("NumOfCPU")
	private int numOfCPU;

	public double getLoadAverage() {
		return loadAverage;
	}

	public void setLoadAverage(double loadAverage) {
		this.loadAverage = loadAverage;
	}

	public int getNumOfCPU() {
		return numOfCPU;
	}

	public void setNumOfCPU(int numOfCPU) {
		this.numOfCPU = numOfCPU;
	}

	@Override
	public String toString() {
		return "Cpu [loadAverage=" + loadAverage + ", numOfCPU=" + numOfCPU
				+ "]";
	}

}
