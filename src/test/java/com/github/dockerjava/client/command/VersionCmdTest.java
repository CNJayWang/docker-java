package com.github.dockerjava.client.command;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.client.AbstractDockerClientTest;
import com.github.dockerjava.client.DockerException;
import com.github.dockerjava.client.model.Version;

public class VersionCmdTest extends AbstractDockerClientTest {

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
	public void version() throws DockerException {
		Version version = dockerClient.versionCmd().exec();
		LOG.info(version.toString());

		assertTrue(version.getGoVersion().length() > 0);
		assertTrue(version.getVersion().length() > 0);

		assertEquals(StringUtils.split(version.getVersion(), ".").length, 3);

	}


}
