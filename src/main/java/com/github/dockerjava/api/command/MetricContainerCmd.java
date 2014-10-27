package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;
import com.github.dockerjava.api.model.metric.Metric;

public interface MetricContainerCmd extends DockerCmd<Metric> {

	public String getContainerId();
	
	public MetricContainerCmd withContainerId(String containerId);
	
	public Metric exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<MetricContainerCmd, Metric> {
	}
}
