package com.github.dockerjava.client.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.client.AbstractDockerClientTest;
import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.ContainerCreateResponse;
import com.github.dockerjava.client.model.ContainerInspectResponse;

public class RestartContainerCmdTest extends AbstractDockerClientTest {

	@BeforeTest
	public void beforeTest() throws DockerException {
		super.beforeTest();
	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		super.beforeMethod(method);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		super.afterMethod(result);
	}

	@Test
	public void restartContainer() throws DockerException {

		ContainerCreateResponse container = dockerClient
				.createContainerCmd("busybox").withCmd("sleep", "9999").exec();
		LOG.info("Created container: {}", container.toString());
		assertThat(container.getId(), not(isEmptyString()));
		dockerClient.startContainerCmd(container.getId()).exec();
		tmpContainers.add(container.getId());

		ContainerInspectResponse containerInspectResponse = dockerClient
				.inspectContainerCmd(container.getId()).exec();
		LOG.info("Container Inspect: {}", containerInspectResponse.toString());

		String startTime = containerInspectResponse.getState().getStartedAt();

		dockerClient.restartContainerCmd(container.getId()).withtTimeout(2).exec();

		ContainerInspectResponse containerInspectResponse2 = dockerClient
				.inspectContainerCmd(container.getId()).exec();
		LOG.info("Container Inspect After Restart: {}",
				containerInspectResponse2.toString());

		String startTime2 = containerInspectResponse2.getState().getStartedAt();

		assertThat(startTime, not(equalTo(startTime2)));

		assertThat(containerInspectResponse.getState().isRunning(),
				is(equalTo(true)));

		dockerClient.killContainerCmd(container.getId()).exec();
	}


}
