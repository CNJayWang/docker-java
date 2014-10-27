package com.github.dockerjava.core.command;

import com.github.dockerjava.api.NotFoundException;
import com.github.dockerjava.api.command.SweepContainerCmd;
import com.google.common.base.Preconditions;

public class SweepContainerCmdImpl extends AbstrDockerCmd<SweepContainerCmd, Void> implements SweepContainerCmd {
	
	private String containerId;

	public SweepContainerCmdImpl(SweepContainerCmd.Exec exec, String containerId) {
		super(exec);
		withContainerId(containerId);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public SweepContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}
	
	@Override
    public String toString() {
        return "sweep " + containerId;
    }
    
    /**
     * @throws NotFoundException No such container
     */
	@Override
    public Void exec() throws NotFoundException {
    	return super.exec();
    }

}
