package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;
import com.github.dockerjava.api.model.LimitationConfig;

public interface LimitContainerCmd extends DockerCmd<Void> {

	public String getContainerId();
	
	public long getMemoryLimit();
	
	public int getCpuShares();
	
	public String getCpuset();
	
	public boolean isSaveChanges();
	
	public LimitationConfig getLimitationConfig();
	
	public LimitContainerCmd withCpuShares(int cpuShares);
	
	public LimitContainerCmd withCpuset(String cpuset);
	
	public LimitContainerCmd withMemoryLimit(long memoryLimit);
	
	public LimitContainerCmd withContainerId(String containerId);
	
	public LimitContainerCmd withSaveChanges(boolean saveChanges);
	
	public LimitContainerCmd withLimitationConfig(LimitationConfig limitationConfig);
	
	public Void exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<LimitContainerCmd, Void> {
	}
}
