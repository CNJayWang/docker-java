package com.github.dockerjava.client.command;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.metric.Metric;
import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class MetricContainerCmd extends
		AbstrDockerCmd<MetricContainerCmd, Metric> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MetricContainerCmd.class);

	private String	containerId;

	public MetricContainerCmd(String containerId) {
		withContainerId(containerId);
	}

	public MetricContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}

	@Override
	Metric impl() {
		WebResource webResource = baseResource.path(String.format(
				"/containers/%s/metric", containerId));
		Metric response = null;
		try {
			LOGGER.trace("GET: {}", webResource);
			response = webResource.accept(MediaType.APPLICATION_JSON).get(Metric.class);
			LOGGER.trace("Response: {}", response);
		} catch (UniformInterfaceException exception) {
			if (exception.getResponse().getStatus() == 404) {
				LOGGER.warn(String.format("%s is an unrecognized container.",
						containerId));
			} else if (exception.getResponse().getStatus() == 500) {
				throw new DockerException("Server error", exception);
			} else {
				throw new DockerException(exception);
			}
		}
		return response;
	}

}
