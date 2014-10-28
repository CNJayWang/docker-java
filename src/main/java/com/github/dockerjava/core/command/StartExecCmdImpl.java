package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.StartExecCmd;

public class StartExecCmdImpl extends BaseExecCmdImpl<StartExecCmd, String> implements StartExecCmd {

	private String execId;
	
	public StartExecCmdImpl(StartExecCmd.Exec exec, String execId) {
		super(exec);
		withExecId(execId);
	}

	@Override
	public String getExecId() {
		return this.execId;
	}

	@Override
	public StartExecCmd withExecId(String execId) {
		this.execId = execId;
		return this;
	}

}
