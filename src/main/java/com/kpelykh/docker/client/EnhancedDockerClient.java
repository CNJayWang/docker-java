package com.kpelykh.docker.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.kpelykh.docker.client.model.Subsystem;
import com.kpelykh.docker.client.model.WriteSubsystem;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class EnhancedDockerClient extends DockerClient {

	public EnhancedDockerClient(String serverUrl) {
		super(serverUrl);
	}

	public List<Subsystem> writeCgroup(String containerId, List<WriteSubsystem> toWrite) throws DockerException {
		Preconditions.checkState(!StringUtils.isEmpty(containerId), "Container ID can't be empty");

		WebResource webResource = client.resource(restEndpointUrl + "/containers/" + containerId + "/cgroup");

		List<Subsystem> response = null;
		try {
			LOGGER.trace("POST: {}", webResource);
			response = webResource.accept(MediaType.APPLICATION_JSON).post(new GenericType<List<Subsystem>>() {
			}, toWrite);
			LOGGER.trace("Response: {}", response);
		} catch (UniformInterfaceException exception) {
			if (exception.getResponse().getStatus() == 404) {
				LOGGER.warn(String.format("%s is an unrecognized container.", containerId));
			} else if (exception.getResponse().getStatus() == 500) {
				throw new DockerException("Server error", exception);
			} else {
				throw new DockerException(exception);
			}
		}
		
		return response;
	}

}
