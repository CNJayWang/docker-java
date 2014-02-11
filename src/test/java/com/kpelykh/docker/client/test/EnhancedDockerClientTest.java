package com.kpelykh.docker.client.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kpelykh.docker.client.EnhancedDockerClient;
import com.kpelykh.docker.client.model.ContainerConfig;
import com.kpelykh.docker.client.model.HostConfig;
import com.kpelykh.docker.client.model.WriteSubsystem;

public class EnhancedDockerClientTest {

	private static final String DOCKER_IMAGE = "docker.diors.it/tomcat";

	@Test
	public void testWriteCgroup() throws Exception {
		EnhancedDockerClient docker = new EnhancedDockerClient("http://localhost:4243");

		ContainerConfig containerConfig = new ContainerConfig();
		containerConfig.setImage(DOCKER_IMAGE);
		String instanceId = docker.createContainer(containerConfig).getId();

		HostConfig hostConfig = new HostConfig();
		docker.startContainer(instanceId, hostConfig);

		List<WriteSubsystem> toWrites = new ArrayList<WriteSubsystem>();
		WriteSubsystem w = new WriteSubsystem("cpuset.cpus", "0");
		toWrites.add(w );
		docker.writeCgroup(instanceId, toWrites);
		
	}

}
