package com.github.dockerjava.client;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.dockerjava.api.EnhancedDockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Subsystem;
import com.github.dockerjava.api.model.WriteSubsystem;
import com.github.dockerjava.api.model.metric.Metric;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.EnhancedDockerClientBuilder;
import com.github.dockerjava.core.TestDockerCmdExecFactory;

public class EnhancedDockerClientTest {

	private static final String DOCKER_IMAGE = "busybox";
	
	private EnhancedDockerClient docker;
	private TestDockerCmdExecFactory dockerCmdExecFactory = new TestDockerCmdExecFactory(DockerClientBuilder.getDefaultDockerCmdExecFactory());
	private List<String> tmpContainers = new ArrayList<String>();
	
	@Before
	public void before() throws Exception {
		docker = EnhancedDockerClientBuilder.getInstance()
				.withDockerCmdExecFactory(dockerCmdExecFactory)
				.build();
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
		CreateContainerCmd cmd = docker.createContainerCmd(DOCKER_IMAGE);
		buildCommonCreateContainerConfig(cmd);
		cmd.withCpuset("0");
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
		CreateContainerCmd cmd = docker.createContainerCmd(DOCKER_IMAGE);
		buildCommonCreateContainerConfig(cmd);
		String instanceId = cmd.exec().getId();
		tmpContainers.add(instanceId);

		docker.startContainerCmd(instanceId).exec();
		Thread.sleep(2000);
		Metric metric = docker.metricContainerCmd(instanceId).exec();
		System.out.println(metric);
	}
	
    @Test
    public void testCreateContainerWithIp() throws Exception {
    	CreateContainerCmd cmd = docker.createContainerCmd(DOCKER_IMAGE);
		buildCommonCreateContainerConfig(cmd);
		String ip = "192.168.3.100/24@192.168.3.1";
		cmd.withIp(ip);
		CreateContainerResponse ccr = cmd.exec();
        Assert.assertNotNull(ccr.getId());
        InspectContainerResponse cir = docker.inspectContainerCmd(ccr.getId()).exec();
        Assert.assertEquals(ip, cir.getConfig().getIp());
        tmpContainers.add(ccr.getId());
    }
    
//    @Test
//    public void testExecCommandInContainer() throws Exception {
//    	CreateContainerConfig containerConfig = buildCommonContainerConfig();
//        ContainerCreateResponse ccr = docker.createContainerCmd(containerConfig).exec();
//        Assert.assertNotNull(ccr.getId());
//        tmpContainers.add(ccr.getId());
//        
//        docker.startContainerCmd(ccr.getId()).exec();
//		Thread.sleep(2000);
//        String response = docker.execContainerCmd(ccr.getId()).withCommand("echo hello").exec();
//        Assert.assertEquals("hello", response);
//        
//    }
    
    @Test
    public void testSweepContainer() throws Exception {
    	CreateContainerCmd cmd = docker.createContainerCmd(DOCKER_IMAGE);
		buildCommonCreateContainerConfig(cmd);
		String ip = "192.168.3.100/24@192.168.3.1";
		cmd.withIp(ip);
		CreateContainerResponse ccr = cmd.exec();
        Assert.assertNotNull(ccr.getId());
        docker.startContainerCmd(ccr.getId()).exec();
        docker.sweepContainerCmd(ccr.getId()).exec();
        InspectContainerResponse cir = docker.inspectContainerCmd(ccr.getId()).exec();
        Assert.assertEquals("", cir.getConfig().getIp());
        Assert.assertEquals(true, cir.getConfig().isNetworkDisabled());
        Assert.assertEquals("none", cir.getHostConfig().getNetworkMode());
        tmpContainers.add(ccr.getId());
    }
    
    @Test
    public void testRemoveContainerWithCheckDevice() throws Exception {
    	CreateContainerCmd cmd = docker.createContainerCmd(DOCKER_IMAGE);
		buildCommonCreateContainerConfig(cmd);
		CreateContainerResponse ccr = cmd.exec();
        Assert.assertNotNull(ccr.getId());
        docker.startContainerCmd(ccr.getId()).exec();
        docker.sweepContainerCmd(ccr.getId()).exec();
        docker.removeContainerCmd(ccr.getId()).withCheckDevice().exec();
    }
	
	private void buildCommonCreateContainerConfig(CreateContainerCmd cmd) {
		cmd.withAttachStderr(false)
		.withAttachStdin(false)
		.withAttachStdout(false)
		.withCmd(new String[]{"tail", "-f", "/dev/null"});
	}
}
