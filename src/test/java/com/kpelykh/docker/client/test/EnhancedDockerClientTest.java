package com.kpelykh.docker.client.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kpelykh.docker.client.EnhancedDockerClient;
import com.kpelykh.docker.client.model.ContainerConfig;
import com.kpelykh.docker.client.model.ContainerCreateResponse;
import com.kpelykh.docker.client.model.ContainerInspectResponse;
import com.kpelykh.docker.client.model.HostConfig;
import com.kpelykh.docker.client.model.Subsystem;
import com.kpelykh.docker.client.model.WriteSubsystem;

public class EnhancedDockerClientTest {

	private static final String DOCKER_IMAGE = "busybox";
	
	private EnhancedDockerClient docker;
	private List<String> tmpContainers = new ArrayList<String>();
	
	@Before
	public void before() throws Exception {
		String url = System.getProperty("docker.url", "http://localhost:4243");
		docker = new EnhancedDockerClient(url);
	}
	
	@After
	public void after() throws Exception {
		for (String id : tmpContainers) {
			docker.kill(id);
			docker.removeContainer(id);
		}
	}
	
	@Test
	public void testChangeCgroup() throws Exception {
		ContainerConfig containerConfig = buildCommonContainerConfig();
		String instanceId = docker.createContainer(containerConfig).getId();
		tmpContainers.add(instanceId);

		HostConfig hostConfig = new HostConfig();
		docker.startContainer(instanceId, hostConfig);

		List<String> toRead = new ArrayList<String>();
		toRead.add("cpu.shares");
		List<WriteSubsystem> toWrite = new ArrayList<WriteSubsystem>();
		WriteSubsystem w = new WriteSubsystem("cpuset.cpus", "0");
		toWrite.add(w);
		List<Subsystem> subsystems = docker.changeCgroup(instanceId, toRead, toWrite);
		Assert.assertEquals(2, subsystems.size());
		Assert.assertEquals(0, subsystems.get(0).getStatus());
		Assert.assertEquals(0, subsystems.get(1).getStatus());
		
	}
	
	@Test
	public void testGetMetric() throws Exception {
		ContainerConfig containerConfig = buildCommonContainerConfig();
		String instanceId = docker.createContainer(containerConfig).getId();
		tmpContainers.add(instanceId);

		HostConfig hostConfig = new HostConfig();
		docker.startContainer(instanceId, hostConfig);
		Thread.sleep(2000);
		docker.getMetric(instanceId);
	}

	@Test
	public void testSuspendContainer() throws Exception {
		ContainerConfig containerConfig = buildCommonContainerConfig();
		String instanceId = docker.createContainer(containerConfig).getId();
		tmpContainers.add(instanceId);

		HostConfig hostConfig = new HostConfig();
		docker.startContainer(instanceId, hostConfig);
		Thread.sleep(100);
		docker.suspend(instanceId);
		ContainerInspectResponse response = docker.inspectContainer(instanceId);
		Assert.assertEquals(true, response.getState().suspend);
	}
	
	@Test
	public void testResumeContainer() throws Exception {
		ContainerInspectResponse response = null;
		ContainerConfig containerConfig = buildCommonContainerConfig();
		String instanceId = docker.createContainer(containerConfig).getId();
		tmpContainers.add(instanceId);

		HostConfig hostConfig = new HostConfig();
		docker.startContainer(instanceId, hostConfig);
		Thread.sleep(100);
		docker.suspend(instanceId);
		response = docker.inspectContainer(instanceId);
		Assert.assertEquals(true, response.getState().suspend);
		docker.resume(instanceId);
		response = docker.inspectContainer(instanceId);
		Assert.assertEquals(false, response.getState().suspend);
		Assert.assertEquals(true, response.getState().running);
	}
	
    @Test
    public void testCreateContainerWithIp() throws Exception {
		ContainerConfig containerConfig = buildCommonContainerConfig();
		String ip = "192.168.10.10/24@192.168.10.1";
		containerConfig.setIp(ip);
        ContainerCreateResponse ccr = docker.createContainer(containerConfig);
        Assert.assertNotNull(ccr.getId());
        ContainerInspectResponse cir = docker.inspectContainer(ccr.getId());
        Assert.assertEquals(ip, cir.getConfig().getIp());
        tmpContainers.add(ccr.getId());
    }
	
	private ContainerConfig buildCommonContainerConfig() {
		ContainerConfig containerConfig = new ContainerConfig();
		containerConfig.setAttachStderr(false);
		containerConfig.setAttachStdin(false);
		containerConfig.setAttachStdout(false);
		containerConfig.setCmd(new String[]{"tail", "-f", "/dev/null"});
		containerConfig.setImage(DOCKER_IMAGE);
		return containerConfig;
	}
}
