package com.github.dockerjava.api.command;

import com.github.dockerjava.api.model.ExecConfig;

public interface BaseExecCmd<CMD_T extends DockerCmd<RES_T>, RES_T> extends DockerCmd<RES_T> {

	public String getContainerId();

	public boolean isTty();

	public boolean isAttachStdin();

	public boolean isAttachStdout();

	public boolean isAttachStderr();

	public boolean isDetach();

	public String[] getCmd();

	public ExecConfig getExecConfig();

	public CMD_T withContainerId(String containerId);

	public CMD_T withTty(boolean tty);

	public CMD_T withAttachStdin(boolean attachStdin);

	public CMD_T withDetach(boolean detach);

	public CMD_T withCmd(String... cmd);

	public CMD_T withExecConfig(ExecConfig execConfig);
}
