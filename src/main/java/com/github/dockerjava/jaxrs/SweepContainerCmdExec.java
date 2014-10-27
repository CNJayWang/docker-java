package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.SweepContainerCmd;

public class SweepContainerCmdExec extends AbstrDockerCmdExec<SweepContainerCmd, Void> implements SweepContainerCmd.Exec {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SweepContainerCmdExec.class);

	public SweepContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	protected Void execute(SweepContainerCmd command) {
		WebTarget webResource = getBaseResource().path("/containers/{id}/sweep").resolveTemplate("id", command.getContainerId());
		LOGGER.trace("POST: {}", webResource);
		webResource.request().accept(MediaType.APPLICATION_JSON).post(entity(null, MediaType.APPLICATION_JSON));	
		return null;
	}

}
