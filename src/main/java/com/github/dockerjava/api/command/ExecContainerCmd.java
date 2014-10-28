package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;

public interface ExecContainerCmd extends BaseExecCmd<ExecContainerCmd, String> {
	
	@Override
	public String exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<ExecContainerCmd, String> {
	}
	
}
