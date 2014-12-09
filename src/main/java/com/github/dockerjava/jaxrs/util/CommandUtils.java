package com.github.dockerjava.jaxrs.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.api.model.Capability;
import com.github.dockerjava.api.model.CreateContainerConfig;
import com.github.dockerjava.api.model.StartContainerConfig;

public class CommandUtils {

	public static void popuateCreateContainerCmd(CreateContainerCmd cmd, CreateContainerConfig config) {
		cmd.withHostName(config.getHostName())
		.withUser(config.getUser())
		.withMemoryLimit(config.getMemoryLimit())
		.withMemorySwap(config.getMemorySwap())
		.withCpuShares(config.getCpuShares())
		.withCpuset(config.getCpuset())
		.withAttachStdin(config.isAttachStdin())
		.withAttachStdout(config.isAttachStdout())
		.withAttachStderr(config.isAttachStderr())
		.withPortSpecs(config.getPortSpecs())
		.withTty(config.isTty())
		.withStdinOpen(config.isStdInOnce())
		.withStdInOnce(config.isStdInOnce())
		.withEnv(config.getEnv().toArray(new String[config.getEnv().size()]))
		.withCmd(config.getCmd())
		.withDns(config.getDns())
		.withImage(config.getImage())
		.withVolumes(config.getVolumes())
		.withVolumesFrom(config.getVolumesFrom())
		.withWorkingDir(config.getWorkingDir())
		.withDisableNetwork(config.isDisableNetwork())
		.withExposedPorts(config.getExposedPorts())
		.withIp(config.getIp());
	}
	
	public static void popuateStartContainerCmd(StartContainerCmd cmd, StartContainerConfig config) {
		ObjectMapper objectMapper = new ObjectMapper();
		cmd.withBinds(config.getBinds())
		.withLinks(config.getLinks())
		.withLxcConf(config.getLxcConf())
		.withPortBindings(config.getPortBindings())
		.withPublishAllPorts(config.isPublishAllPorts())
		.withPrivileged(config.isPrivileged())
		.withDns(config.getDns())
		.withDnsSearch(config.getDnsSearch())
		.withVolumesFrom(config.getVolumesFrom())
		.withNetworkMode(config.getNetworkMode())
		.withDevices(config.getDevices())
		.withRestartPolicy(config.getRestartPolicy());
		try {
			for (int i = 0; i < config.getCapAdd().length; i++) {
				cmd.withCapAdd(objectMapper.readValue(config.getCapAdd()[i], Capability.class));
			}
			for (int i = 0; i < config.getCapDrop().length; i++) {
				cmd.withCapDrop(objectMapper.readValue(config.getCapDrop()[i], Capability.class));
			}
		} catch (IOException e) {
			throw new CapibilityConvertException("");
		}
	}
}
