package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.BaseExecCmd;
import com.github.dockerjava.api.command.DockerCmd;
import com.github.dockerjava.api.command.DockerCmdExec;
import com.github.dockerjava.api.model.ExecConfig;

public abstract class BaseExecCmdImpl<CMD_T extends DockerCmd<RES_T>, RES_T> extends AbstrDockerCmd<CMD_T, RES_T> implements BaseExecCmd<CMD_T, RES_T> {

	protected ExecConfig execConfig;
	
	public BaseExecCmdImpl(DockerCmdExec<CMD_T, RES_T> execution) {
		super(execution);
		this.execConfig = new ExecConfig();
	}

	@Override
	public String getContainerId() {
		return execConfig.getContainerId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withContainerId(String containerId) {
		execConfig.setContainerId(containerId);
		return (CMD_T) this;
	}

	@Override
	public boolean isTty() {
		return execConfig.isTty();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withTty(boolean tty) {
		execConfig.setTty(tty);
		return (CMD_T) this;
	}

	@Override
	public boolean isAttachStdin() {
		return execConfig.isAttachStdin();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withAttachStdin(boolean attachStdin) {
		execConfig.setAttachStdin(attachStdin);
		return (CMD_T) this;
	}

	@Override
	public boolean isAttachStdout() {
		return execConfig.isAttachStdout();
	}

	@Override
	public boolean isAttachStderr() {
		return execConfig.isAttachStderr();
	}

	@Override
	public boolean isDetach() {
		return execConfig.isDetach();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withDetach(boolean detach) {
		execConfig.setDetach(detach);
		execConfig.setAttachStdout(!detach);
		execConfig.setAttachStderr(!detach);
		return (CMD_T) this;
	}

	@Override
	public String[] getCmd() {
		return execConfig.getCmd();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withCmd(String... cmd) {
		execConfig.setCmd(cmd);
		return (CMD_T) this;
	}

	@Override
	public ExecConfig getExecConfig() {
		return execConfig;
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withExecConfig(ExecConfig execConfig) {
		this.execConfig = execConfig;
		return (CMD_T) this;
	}

	@Override
	public boolean isPrivileged() {
		return execConfig.isPrivileged();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CMD_T withPrivileged(boolean privileged) {
		this.execConfig.setPrivileged(privileged);
		return (CMD_T) this;
	}
}
