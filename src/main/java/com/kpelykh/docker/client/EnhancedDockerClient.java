package com.kpelykh.docker.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.kpelykh.docker.client.model.Metric;
import com.kpelykh.docker.client.model.Subsystem;
import com.kpelykh.docker.client.model.WriteSubsystem;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class EnhancedDockerClient extends DockerClient {

	public EnhancedDockerClient(String serverUrl) throws DockerException {
		super(serverUrl);
	}
	
	public EnhancedDockerClient(String serverUrl, String version) throws DockerException {
		super(serverUrl + version);
	}

	public List<Subsystem> readCgroup(String containerId, List<String> toRead) throws DockerException {
		return changeCgroup(containerId, toRead, null);
	}
	
	public List<Subsystem> writeCgroup(String containerId, List<WriteSubsystem> toWrite) throws DockerException {
		return changeCgroup(containerId, null, toWrite);
	}
	
	public List<Subsystem> changeCgroup(String containerId, List<String> toRead, List<WriteSubsystem> toWrite) throws DockerException {
		Preconditions.checkState(!StringUtils.isEmpty(containerId), "Container ID can't be empty");

		WebResource webResource = client.resource(restEndpointUrl + "/containers/" + containerId + "/cgroup");

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
	
	public Metric getMetric(String containerId) throws DockerException {
		WebResource webResource = client.resource(restEndpointUrl + "/containers/" + containerId + "/metric");
		
		Metric response = null;
		try {
			LOGGER.trace("GET: {}", webResource);
			response = webResource.accept(MediaType.APPLICATION_JSON).get(new GenericType<Metric>() {});
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

    public void pause(String containerId) throws DockerException {
        WebResource webResource = client.resource(restEndpointUrl + String.format("/containers/%s/pause", containerId));

        try {
            LOGGER.trace("POST: {}", webResource);
            webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post();
        } catch (UniformInterfaceException exception) {
            if (exception.getResponse().getStatus() == 404) {
                LOGGER.warn("No such container {}", containerId);
            } else if (exception.getResponse().getStatus() == 204) {
                //no error
                LOGGER.trace("Successfully killed container {}", containerId);
            } else if (exception.getResponse().getStatus() == 500) {
                throw new DockerException("Server error", exception);
            } else {
                throw new DockerException(exception);
            }
        }
    }
    
    public void unPause(String containerId) throws DockerException {
        WebResource webResource = client.resource(restEndpointUrl + String.format("/containers/%s/unpause", containerId));

        try {
            LOGGER.trace("POST: {}", webResource);
            webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post();
        } catch (UniformInterfaceException exception) {
            if (exception.getResponse().getStatus() == 404) {
                LOGGER.warn("No such container {}", containerId);
            } else if (exception.getResponse().getStatus() == 204) {
                //no error
                LOGGER.trace("Successfully killed container {}", containerId);
            } else if (exception.getResponse().getStatus() == 500) {
                throw new DockerException("Server error", exception);
            } else {
                throw new DockerException(exception);
            }
        }
    }
}
