package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;

public interface SweepContainerCmd extends DockerCmd<Void> {

	public String getContainerId();
	
	public SweepContainerCmd withContainerId(String containerId);
	
	/**
	 * @throws NotFoundException No such container
	 */
	public Void exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<SweepContainerCmd, Void> {
	}
}
