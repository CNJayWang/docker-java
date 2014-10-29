package com.github.dockerjava.core;

import com.github.dockerjava.api.EnhancedDockerClient;
import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateExecCmd;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.command.ExecContainerCmd;
import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.api.command.StartExecCmd;
import com.github.dockerjava.api.command.SweepContainerCmd;
import com.github.dockerjava.api.model.CreateContainerConfig;
import com.github.dockerjava.api.model.StartContainerConfig;
import com.github.dockerjava.core.command.CgroupContainerCmdImpl;
import com.github.dockerjava.core.command.CreateExecCmdImpl;
import com.github.dockerjava.core.command.ExecContainerCmdImpl;
import com.github.dockerjava.core.command.MetricContainerCmdImpl;
import com.github.dockerjava.core.command.StartExecCmdImpl;
import com.github.dockerjava.core.command.SweepContainerCmdImpl;
import com.github.dockerjava.jaxrs.util.CommandUtils;



public class EnhancedDockerClientImpl extends DockerClientImpl implements EnhancedDockerClient {
	
	private EnhancedDockerClientImpl() {
		this(DockerClientConfig.createDefaultConfigBuilder().build());
	}

	private EnhancedDockerClientImpl(String serverUrl) {
		this(configWithServerUrl(serverUrl));
	}

    private EnhancedDockerClientImpl(DockerClientConfig dockerClientConfig) {
    	super(dockerClientConfig);
    }
    
    private static DockerClientConfig configWithServerUrl(String serverUrl) {
		return DockerClientConfig.createDefaultConfigBuilder()
                .withUri(serverUrl)
                .build();
	}
    
    public EnhancedDockerClientImpl withDockerCmdExecFactory(DockerCmdExecFactory dockerCmdExecFactory) {
	    super.withDockerCmdExecFactory(dockerCmdExecFactory);
	    return this;
	}
    
    public static EnhancedDockerClientImpl getInstance() {
    	return new EnhancedDockerClientImpl();
    }
    
    public static EnhancedDockerClientImpl getInstance(DockerClientConfig dockerClientConfig) {
    	return new EnhancedDockerClientImpl(dockerClientConfig);
    }
    
    public static EnhancedDockerClientImpl getInstance(String serverUrl) {
    	return new EnhancedDockerClientImpl(serverUrl);
    }

	@Override
	public CgroupContainerCmd cgroupContainerCmd(String containerId) {
		return new CgroupContainerCmdImpl(getDockerCmdExecFactory().createCgroupContainerCmdExec(), containerId);
	}

	@Override
	public SweepContainerCmd sweepContainerCmd(String containerId) {
		return new SweepContainerCmdImpl(getDockerCmdExecFactory().createSweepContainerCmdExec(), containerId);
	}
	
	@Override
	public MetricContainerCmd metricContainerCmd(String containerId) {
		return new MetricContainerCmdImpl(getDockerCmdExecFactory().createMetricContainerCmdExec(), containerId);
	}

	@Override
	public CreateExecCmd createExecCmd(String containerId) {
		return new CreateExecCmdImpl(getDockerCmdExecFactory().createCreateExecCmdExec(), containerId);
	}

	@Override
	public StartExecCmd startExecCmd(String execId) {
		return new StartExecCmdImpl(getDockerCmdExecFactory().createStartExecCmdExec(), execId);
	}
	
	public ExecContainerCmd execContainerCmd(String containerId) {
		CreateExecCmd createExecCmd = createExecCmd(containerId);
		StartExecCmd startExecCmd = startExecCmd(containerId);
		return new ExecContainerCmdImpl(getDockerCmdExecFactory().createExecContainerCmdExec(createExecCmd, startExecCmd), containerId);
	}

	@Override
	public CreateContainerCmd createContainerCmd(CreateContainerConfig createContainerConfig) {
		CreateContainerCmd cmd = createContainerCmd(createContainerConfig.getImage());
		CommandUtils.popuateCreateContainerCmd(cmd, createContainerConfig);
		return cmd;
	}

	@Override
	public StartContainerCmd startContainerCmd(String containerId, StartContainerConfig startContainerConfig) {
		StartContainerCmd cmd = startContainerCmd(containerId);
		CommandUtils.popuateStartContainerCmd(cmd, startContainerConfig);
		return cmd;
	}
}
