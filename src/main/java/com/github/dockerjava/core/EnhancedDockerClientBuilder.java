package com.github.dockerjava.core;

import java.util.ServiceLoader;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.EnhancedDockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DockerClientConfig.DockerClientConfigBuilder;

public class EnhancedDockerClientBuilder {
	
	private static ServiceLoader<DockerCmdExecFactory> serviceLoader = ServiceLoader.load(DockerCmdExecFactory.class);

	private EnhancedDockerClientImpl dockerClient = null;

	private EnhancedDockerClientBuilder(EnhancedDockerClientImpl dockerClient) {
		this.dockerClient = dockerClient;
	}

	public static EnhancedDockerClientBuilder getInstance() {
		return new EnhancedDockerClientBuilder(withDefaultDockerCmdExecFactory(EnhancedDockerClientImpl.getInstance()));
	}
	
	public static EnhancedDockerClientBuilder getInstance(DockerClientConfigBuilder dockerClientConfigBuilder) {
		return getInstance(dockerClientConfigBuilder.build());
	}

	public static EnhancedDockerClientBuilder getInstance(DockerClientConfig dockerClientConfig) {
		return new EnhancedDockerClientBuilder(withDefaultDockerCmdExecFactory(EnhancedDockerClientImpl
				.getInstance(dockerClientConfig)));
	}

	public static EnhancedDockerClientBuilder getInstance(String serverUrl) {
		return new EnhancedDockerClientBuilder(withDefaultDockerCmdExecFactory(EnhancedDockerClientImpl
				.getInstance(serverUrl)));
	}

	private static EnhancedDockerClientImpl withDefaultDockerCmdExecFactory(
			EnhancedDockerClientImpl dockerClient) {
		
		DockerCmdExecFactory dockerCmdExecFactory = getDefaultDockerCmdExecFactory();
		
		return dockerClient
				.withDockerCmdExecFactory(dockerCmdExecFactory);
	}

	public static DockerCmdExecFactory getDefaultDockerCmdExecFactory() {
		if(!serviceLoader.iterator().hasNext()) {
			throw new RuntimeException("Fatal: Can't find any implementation of '" + DockerCmdExecFactory.class.getName() +  "' in the current classpath.");
		}
		
		return serviceLoader.iterator().next();
	}

	public EnhancedDockerClientBuilder withDockerCmdExecFactory(
			DockerCmdExecFactory dockerCmdExecFactory) {
		dockerClient = dockerClient
				.withDockerCmdExecFactory(dockerCmdExecFactory);
		return this;
	}

	public EnhancedDockerClient build() {
		return dockerClient;
	}
}
