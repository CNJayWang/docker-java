package com.github.dockerjava.core;

import com.github.dockerjava.api.EnhancedDockerClient;
import com.github.dockerjava.api.command.CgroupContainerCmd;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.command.SweepContainerCmd;
import com.github.dockerjava.core.command.CgroupContainerCmdImpl;
import com.github.dockerjava.core.command.MetricContainerCmdImpl;
import com.github.dockerjava.core.command.SweepContainerCmdImpl;



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
}
