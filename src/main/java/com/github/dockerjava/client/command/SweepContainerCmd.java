package com.github.dockerjava.client.command;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.client.DockerException;
import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Sweep a running container.
 * 
 * @param containerId - Id of the container
 * 
 */
public class SweepContainerCmd extends AbstrDockerCmd<SweepContainerCmd, Void> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepContainerCmd.class);

	private String containerId;
	
	public SweepContainerCmd(String containerId) {
		withContainerId(containerId);
	}
	
	public SweepContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}
	
    @Override
    public String toString() {
        return new StringBuilder("sweep ")
            .append(containerId)
            .toString();
    }   

	protected Void impl() throws DockerException {
		WebResource webResource = baseResource.path(String.format("/containers/%s/sweep", containerId));

		try {
			LOGGER.trace("POST: {}", webResource);
			webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post();
		} catch (UniformInterfaceException exception) {
			if (exception.getResponse().getStatus() == 404) {
				LOGGER.warn("No such container {}", containerId);
			} else if(exception.getResponse().getStatus() == 304) {
				//no error
				LOGGER.warn("Container already stopped {}", containerId);
			} else if (exception.getResponse().getStatus() == 204) {
				//no error
				LOGGER.trace("Successfully stopped container {}", containerId);
			} else if (exception.getResponse().getStatus() == 500) {
				throw new DockerException("Server error", exception);
			} else {
				throw new DockerException(exception);
			}
		}
		
		return null;
	}
}
