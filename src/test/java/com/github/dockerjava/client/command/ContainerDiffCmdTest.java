package com.github.dockerjava.client.command;

import static ch.lambdaj.Lambda.selectUnique;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.testinfected.hamcrest.jpa.HasFieldWithValue.hasField;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.client.AbstractDockerClientTest;
import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.ChangeLog;
import com.github.dockerjava.client.model.ContainerCreateResponse;

public class ContainerDiffCmdTest extends AbstractDockerClientTest {

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
	public void testDiff() throws DockerException {
		ContainerCreateResponse container = dockerClient
				.createContainerCmd("busybox").withCmd("touch", "/test" ).exec();
		LOG.info("Created container: {}", container.toString());
		assertThat(container.getId(), not(isEmptyString()));
		dockerClient.startContainerCmd(container.getId()).exec();
		boolean add = tmpContainers.add(container.getId());
		int exitCode = dockerClient.waitContainerCmd(container.getId()).exec();
		assertThat(exitCode, equalTo(0));

		List filesystemDiff = dockerClient.containerDiffCmd(container.getId()).exec();
		LOG.info("Container DIFF: {}", filesystemDiff.toString());

		assertThat(filesystemDiff.size(), equalTo(1));
		ChangeLog testChangeLog = selectUnique(filesystemDiff,
				hasField("path", equalTo("/test")));

		assertThat(testChangeLog, hasField("path", equalTo("/test")));
		assertThat(testChangeLog, hasField("kind", equalTo(1)));
	}


}
