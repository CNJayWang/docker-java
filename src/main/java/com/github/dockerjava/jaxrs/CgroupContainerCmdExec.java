package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.model.Subsystem;
import com.github.dockerjava.api.model.WriteSubsystem;

public class CgroupContainerCmdExec extends AbstrDockerCmdExec<CgroupContainerCmd, List<Subsystem>> implements CgroupContainerCmd.Exec {

	private static final Logger LOGGER = LoggerFactory.getLogger(CgroupContainerCmdExec.class);
	
	public CgroupContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	protected List<Subsystem> execute(CgroupContainerCmd command) {
		WebTarget webResource = getBaseResource().path("/containers/{id}/cgroup").resolveTemplate("id", command.getContainerId());
		Map<String, List> data = new HashMap<String, List>();
		List<String> toRead = command.getReadSubsystem();
		List<WriteSubsystem> toWrite = command.getWriteSubsystem();
		if (toRead != null && toRead.size() > 0) {
			data.put("ReadSubsystem", toRead);
		}
		if (toWrite != null && toWrite.size() > 0) {
			data.put("WriteSubsystem", toWrite);
		}
		LOGGER.trace("POST: {}", webResource);
		List<Subsystem> response = webResource.request().accept(MediaType.APPLICATION_JSON).post(entity(data, MediaType.APPLICATION_JSON), new GenericType<List<Subsystem>>() {
        });
		return response;
	}

}
