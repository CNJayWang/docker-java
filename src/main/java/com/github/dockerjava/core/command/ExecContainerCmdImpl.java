package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.ExecContainerCmd;

public class ExecContainerCmdImpl extends BaseExecCmdImpl<ExecContainerCmd, String> implements ExecContainerCmd {

	public ExecContainerCmdImpl(ExecContainerCmd.Exec exec, String containerId) {
		super(exec);
		withContainerId(containerId);
	}

}
