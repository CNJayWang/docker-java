package com.github.dockerjava.jaxrs;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.model.metric.Metric;

public class MetricContainerCmdExec extends AbstrDockerCmdExec<MetricContainerCmd, Metric> implements MetricContainerCmd.Exec {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetricContainerCmdExec.class);
	
	public MetricContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	protected Metric execute(MetricContainerCmd command) {
		WebTarget webResource = getBaseResource().path("/containers/{id}/metric").resolveTemplate("id", command.getContainerId());
		LOGGER.trace("GET: {}", webResource);
		Metric response = webResource.request().accept(MediaType.APPLICATION_JSON).get(Metric.class);
		return response;
	}

}
