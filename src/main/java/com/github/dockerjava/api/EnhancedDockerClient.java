package com.github.dockerjava.api;

import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.command.CreateExecCmd;
import com.github.dockerjava.api.command.ExecContainerCmd;
import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.command.StartExecCmd;
import com.github.dockerjava.api.command.SweepContainerCmd;


public interface EnhancedDockerClient extends DockerClient {
	
	public CgroupContainerCmd cgroupContainerCmd(String containerId);
	
	public SweepContainerCmd sweepContainerCmd(String containerId);
	
	public MetricContainerCmd metricContainerCmd(String containerId);
	
	public CreateExecCmd createExecCmd(String containerId);
	
	public StartExecCmd startExecCmd(String execId);
	
	public ExecContainerCmd execContainerCmd(String containerId);
}