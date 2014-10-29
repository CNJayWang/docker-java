package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.LimitContainerCmd;

public class LimitContainerCmdExec extends AbstrDockerCmdExec<LimitContainerCmd, Void> implements LimitContainerCmd.Exec {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LimitContainerCmdExec.class);

	public LimitContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	protected Void execute(LimitContainerCmd command) {
		WebTarget webResource = getBaseResource().path("/containers/{id}/limit")
				.resolveTemplate("id", command.getContainerId())
				.queryParam("saveChanges", command.isSaveChanges() ? "1" : "0");
		LOGGER.trace("POST: {}", webResource);
		webResource.request().accept(MediaType.APPLICATION_JSON).post(entity(command.getLimitationConfig(), MediaType.APPLICATION_JSON));	
		return null;
	}
}
