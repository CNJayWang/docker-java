package com.github.dockerjava.client.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.Subsystem;
import com.github.dockerjava.client.model.WriteSubsystem;
import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class CgroupContainerCmd extends AbstrDockerCmd<CgroupContainerCmd, List<Subsystem>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CgroupContainerCmd.class);
	
	private String containerId;
	private List<String> toRead;
	private List<WriteSubsystem> toWrite;
	
	public CgroupContainerCmd(String containerId) {
		withContainerId(containerId);
	}
	
	public CgroupContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}
	
	public CgroupContainerCmd withReadSubsystem(List<String> toRead) {
		this.toRead = toRead;
		return this;
	}
	
	public CgroupContainerCmd withWriteSubsystem(List<WriteSubsystem> toWrite) {
		this.toWrite = toWrite;
		return this;
	}
	
    @Override
    public String toString() {
        return "inspect " + containerId;
    }
	
	@Override
	List<Subsystem> impl() {
		WebResource webResource = baseResource.path(String.format("/containers/%s/cgroup", containerId));
		Map<String, List> data = new HashMap<String, List>();
		if (toRead != null && toRead.size() > 0) {
			data.put("ReadSubsystem", toRead);
		}
		if (toWrite != null && toWrite.size() > 0) {
			data.put("WriteSubsystem", toWrite);
		}
		List<Subsystem> response = null;
		
		try {
			LOGGER.trace("POST: {}", webResource);
			response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE).post(new GenericType<List<Subsystem>>() {
			}, data);
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
