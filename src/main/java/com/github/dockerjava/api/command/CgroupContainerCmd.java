package com.github.dockerjava.api.command;

import java.util.List;

import com.github.dockerjava.api.model.Subsystem;
import com.github.dockerjava.api.model.WriteSubsystem;

public interface CgroupContainerCmd extends DockerCmd<List<Subsystem>>{
	
	public String getContainerId();
	
	public List<String> getReadSubsystem();
	
	public List<WriteSubsystem> getWriteSubsystem();
	
	public CgroupContainerCmd withContainerId(String containerId);
	
	public CgroupContainerCmd withReadSubsystem(List<String> toRead);
	
	public CgroupContainerCmd withWriteSubsystem(List<WriteSubsystem> toWrite);
	
	public static interface Exec extends DockerCmdExec<CgroupContainerCmd, List<Subsystem>> {
	}

}
