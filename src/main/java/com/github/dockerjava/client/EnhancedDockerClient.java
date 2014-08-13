package com.github.dockerjava.client;

import java.util.List;

import com.github.dockerjava.client.command.CgroupContainerCmd;
import com.github.dockerjava.client.command.CreateContainerCmd;
import com.github.dockerjava.client.command.ExecContainerCmd;
import com.github.dockerjava.client.command.MetricContainerCmd;
import com.github.dockerjava.client.command.PauseContainerCmd;
import com.github.dockerjava.client.command.SweepContainerCmd;
import com.github.dockerjava.client.command.UnpauseContainerCmd;
import com.github.dockerjava.client.model.CreateContainerConfig;
import com.github.dockerjava.client.model.Subsystem;
import com.github.dockerjava.client.model.WriteSubsystem;

public class EnhancedDockerClient extends DockerClient {
	
	public EnhancedDockerClient () {
		super();
	}
	
	public EnhancedDockerClient (String serverUrl) {
		super(serverUrl);
	}
	
	public CreateContainerCmd createContainerCmd(CreateContainerConfig config) {
		return new CreateContainerCmd(config).withBaseResource(baseResource);
	}

	public CgroupContainerCmd cgroupContainerCmd(String containerId) {
		return new CgroupContainerCmd(containerId).withBaseResource(baseResource);
	}
	
	public List<Subsystem> readCgroup(String containerId, List<String> toRead) {
		return cgroupContainerCmd(containerId).withReadSubsystem(toRead).exec();
	}
	
	public List<Subsystem> writeCgroup(String containerId, List<WriteSubsystem> toWrite) {
		return cgroupContainerCmd(containerId).withWriteSubsystem(toWrite).exec();
	}
	
	public MetricContainerCmd metricContainerCmd(String containerId) {
		return new MetricContainerCmd(containerId).withBaseResource(baseResource);
	}
	
	public PauseContainerCmd pauseContainerCmd(String containerId) {
		return new PauseContainerCmd(containerId).withBaseResource(baseResource);
	}
	
	public UnpauseContainerCmd unpauseContainerCmd(String containerId) {
		return new UnpauseContainerCmd(containerId).withBaseResource(baseResource);
	}
	
	public ExecContainerCmd execContainerCmd(String containerId) {
		return new ExecContainerCmd(containerId).withBaseResource(baseResource);
	}
	
	public SweepContainerCmd sweepContainerCmd(String containerId) {
		return new SweepContainerCmd(containerId).withBaseResource(baseResource);
	}
}
