package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.CreateExecCmd;
import com.github.dockerjava.api.command.CreateExecResponse;

public class CreateExecCmdImpl extends BaseExecCmdImpl<CreateExecCmd, CreateExecResponse> implements CreateExecCmd {

	public CreateExecCmdImpl(CreateExecCmd.Exec exec, String containerId) {
		super(exec);
		withContainerId(containerId);
	}
}
