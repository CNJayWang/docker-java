package com.github.dockerjava.core.command;

import java.util.List;

import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.model.Subsystem;
import com.github.dockerjava.api.model.WriteSubsystem;
import com.google.common.base.Preconditions;

public class CgroupContainerCmdImpl extends AbstrDockerCmd<CgroupContainerCmd, List<Subsystem>> implements CgroupContainerCmd {

	private String containerId;
	private List<String> toRead;
	private List<WriteSubsystem> toWrite;
	
	public CgroupContainerCmdImpl(CgroupContainerCmd.Exec exec, String containerId) {
		super(exec);
		withContainerId(containerId);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}
	
	@Override
	public List<String> getReadSubsystem() {
		return toRead;
	}

	@Override
	public List<WriteSubsystem> getWriteSubsystem() {
		return toWrite;
	}
	
	@Override
	public CgroupContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}

	@Override
	public CgroupContainerCmd withReadSubsystem(List<String> toRead) {
		this.toRead = toRead;
		return this;
	}

	@Override
	public CgroupContainerCmd withWriteSubsystem(List<WriteSubsystem> toWrite) {
		this.toWrite = toWrite;
		return this;
	}
	
	@Override
    public String toString() {
        return "cgroup " + containerId;
    }

}
