package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;

public interface StartExecCmd extends BaseExecCmd<StartExecCmd, String> {

	public String getExecId();
	
	public StartExecCmd withExecId(String execId);
	
	@Override
	public String exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<StartExecCmd, String> {
	}
	
}
