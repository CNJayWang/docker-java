package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;

public interface CreateExecCmd extends BaseExecCmd<CreateExecCmd, CreateExecResponse> {
	
	@Override
	public CreateExecResponse exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<CreateExecCmd, CreateExecResponse> {
	}
}
