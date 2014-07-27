package com.github.dockerjava.client.command;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.AuthConfig;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 *  Authenticate with the server, useful for checking authentication.
 * 
 */
public class AuthCmd extends AbstrAuthCfgDockerCmd<AuthCmd, Void> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthCmd.class);

	public AuthCmd(AuthConfig authConfig) {
		withAuthConfig(authConfig);
	}
	
	protected Void impl() throws DockerException {
		try {
			WebResource webResource = baseResource.path("/auth");
			LOGGER.trace("POST: {}", webResource);
			webResource.header("Content-Type", MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).post(authConfig);
			return null;
		} catch (UniformInterfaceException e) {
			throw new DockerException(e);
		}
	}
	
	@Override
	public String toString() {
		return "authenticate using " + this.authConfig;
	}
}
