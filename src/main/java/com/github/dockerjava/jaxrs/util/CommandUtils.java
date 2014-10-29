package com.github.dockerjava.jaxrs.util;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
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
		.withRestartPolicy(config.getRestartPolicy())
		.withCapAdd(config.getCapAdd())
		.withCapDrop(config.getCapDrop());
	}
}
