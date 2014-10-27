package com.github.dockerjava.api;

import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.command.SweepContainerCmd;


public interface EnhancedDockerClient extends DockerClient {
	
	public CgroupContainerCmd cgroupContainerCmd(String containerId);
	
	public SweepContainerCmd sweepContainerCmd(String containerId);
	
	public MetricContainerCmd metricContainerCmd(String containerId);
}