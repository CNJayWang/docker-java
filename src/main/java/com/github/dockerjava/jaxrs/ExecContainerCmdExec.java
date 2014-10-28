package com.github.dockerjava.jaxrs;

import javax.ws.rs.client.WebTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.CreateExecCmd;
import com.github.dockerjava.api.command.CreateExecResponse;
import com.github.dockerjava.api.command.ExecContainerCmd;
import com.github.dockerjava.api.command.StartExecCmd;
import com.github.dockerjava.api.model.ExecConfig;
import com.google.common.base.Preconditions;

public class ExecContainerCmdExec extends AbstrDockerCmdExec<ExecContainerCmd, String> implements ExecContainerCmd.Exec {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecContainerCmdExec.class);
	
	private CreateExecCmd createExecCmd;
	private StartExecCmd startExecCmd;
	
	public ExecContainerCmdExec(CreateExecCmd createExecCmd, StartExecCmd startExecCmd, WebTarget baseResource) {
		super(baseResource);
		Preconditions.checkNotNull(createExecCmd, "execution was not specified");
		Preconditions.checkNotNull(startExecCmd, "execution was not specified");
		this.createExecCmd = createExecCmd;
		this.startExecCmd = startExecCmd;
	}

	@Override
	protected String execute(ExecContainerCmd command) {
		LOGGER.trace("ExecContainerCmdExec");
		ExecConfig execConfig = command.getExecConfig();
		CreateExecResponse cer = createExecCmd.withExecConfig(execConfig).withContainerId(command.getContainerId()).exec();
		String execId = cer.getId();
		return startExecCmd.withExecConfig(execConfig).withExecId(execId).exec();
	}

}
