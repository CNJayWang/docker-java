package com.github.dockerjava.client;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.dockerjava.client.command.CreateContainerCmd;
import com.github.dockerjava.client.model.ContainerCreateResponse;
import com.github.dockerjava.client.model.ContainerInspectResponse;
import com.github.dockerjava.client.model.CreateContainerConfig;
import com.github.dockerjava.client.model.Subsystem;
import com.github.dockerjava.client.model.WriteSubsystem;
import com.github.dockerjava.client.model.metric.Metric;

public class EnhancedDockerClientTest {

	private static final String DOCKER_IMAGE = "busybox";
	
	private EnhancedDockerClient docker;
	private List<String> tmpContainers = new ArrayList<String>();
	
	@Before
	public void before() throws Exception {
		docker = new EnhancedDockerClient();
	}
	
	@After
	public void after() throws Exception {
		for (String id : tmpContainers) {
			docker.killContainerCmd(id).exec();
			docker.removeContainerCmd(id).exec();
		}
	}
	
	@Test
	public void testChangeCgroup() throws Exception {
		CreateContainerConfig containerConfig = buildCommonContainerConfig();
		containerConfig.withCpuset("0");
		CreateContainerCmd cmd = docker.createContainerCmd(containerConfig);
		String instanceId = cmd.exec().getId();
		tmpContainers.add(instanceId);

		docker.startContainerCmd(instanceId).exec();

		List<String> toRead = new ArrayList<String>();
		toRead.add("cpu.shares");
		List<WriteSubsystem> toWrite = new ArrayList<WriteSubsystem>();
		WriteSubsystem w = new WriteSubsystem("cpuset.cpus", "1");
		toWrite.add(w);
		List<Subsystem> subsystems = docker.cgroupContainerCmd(instanceId)
				.withReadSubsystem(toRead).withWriteSubsystem(toWrite).exec();
		Assert.assertEquals(2, subsystems.size());
		Assert.assertEquals(0, subsystems.get(0).getStatus());
		Assert.assertEquals(0, subsystems.get(1).getStatus());
		
	}
	
	@Test
	public void testGetMetric() throws Exception {
		CreateContainerConfig containerConfig = buildCommonContainerConfig();
		String instanceId = docker.createContainerCmd(containerConfig).exec().getId();
		tmpContainers.add(instanceId);

		docker.startContainerCmd(instanceId).exec();
		Thread.sleep(2000);
		Metric metric = docker.metricContainerCmd(instanceId).exec();
		System.out.println(metric);
	}
	
    @Test
    public void testCreateContainerWithIp() throws Exception {
    	CreateContainerConfig containerConfig = buildCommonContainerConfig();
		String ip = "192.168.10.10/24@192.168.10.1";
		containerConfig.withIp(ip);
        ContainerCreateResponse ccr = docker.createContainerCmd(containerConfig).exec();
        Assert.assertNotNull(ccr.getId());
        ContainerInspectResponse cir = docker.inspectContainerCmd(ccr.getId()).exec();
        Assert.assertEquals(ip, cir.getConfig().getIp());
        tmpContainers.add(ccr.getId());
    }
	
	private CreateContainerConfig buildCommonContainerConfig() {
		CreateContainerConfig config = new CreateContainerConfig();
		config.withAttachStderr(false)
		.withAttachStdin(false)
		.withAttachStdout(false)
		.withCmd(new String[]{"tail", "-f", "/dev/null"})
		.withImage(DOCKER_IMAGE);
		return config;
	}
}
