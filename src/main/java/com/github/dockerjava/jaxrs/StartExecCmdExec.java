package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.StartExecCmd;

public class StartExecCmdExec extends AbstrDockerCmdExec<StartExecCmd, String> implements StartExecCmd.Exec {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartExecCmdExec.class);
	
	public StartExecCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	protected String execute(StartExecCmd command) {
		WebTarget webResource = getBaseResource().path("/exec/{id}/start").resolveTemplate("id", command.getExecId());
		
		LOGGER.trace("POST: {} ", webResource);
		String response = webResource.request().accept(MediaType.APPLICATION_JSON)
				.post(entity(command.getExecConfig(), MediaType.APPLICATION_JSON), String.class);
		
		// TODO find a better way to handle it
		// The starting eight characters are always 1,0,0,0,0,0,0,0,(length of content), remove it
		if (response != null && response.length() > 8) {
			response = response.substring(8);
		}
		return response;
	}

}
