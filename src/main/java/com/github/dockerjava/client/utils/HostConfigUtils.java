package com.github.dockerjava.client.utils;

import com.github.dockerjava.client.model.Bind;
import com.github.dockerjava.client.model.ContainerInspectResponse.HostConfig;
import com.github.dockerjava.client.model.Link;
import com.github.dockerjava.client.model.StartContainerConfig;

public class HostConfigUtils {

	public static StartContainerConfig convertToStartContainerConfig(HostConfig hostConfig) {
		if (hostConfig == null) {
			return null;
		}
		StartContainerConfig startContainerConfig = new StartContainerConfig();
		startContainerConfig.setDns(hostConfig.getDns());
		startContainerConfig.setLxcConf(hostConfig.getLxcConf());
		startContainerConfig.setPortBindings(hostConfig.getPortBindings());
		startContainerConfig.setPrivileged(hostConfig.isPrivileged());
		startContainerConfig.setPublishAllPorts(hostConfig.isPublishAllPorts());
		startContainerConfig.setVolumesFrom(hostConfig.getVolumesFrom());
		
		if (hostConfig.getBinds() != null) {
			Bind[] binds = new Bind[hostConfig.getBinds().length];
			for (int i = 0; i < hostConfig.getBinds().length; i++) {
				binds[i] = Bind.parse(hostConfig.getBinds()[i]);
			}
			startContainerConfig.setBinds(binds);
		}
		
		if (hostConfig.getLinks() != null) {
			Link[] links = new Link[hostConfig.getLinks().length];
			for (int i = 0; i < hostConfig.getLinks().length; i++) {
				links[i] = Link.parse(hostConfig.getLinks()[i]);
			}
			startContainerConfig.setLinks(links);	
		}
		
		return startContainerConfig;
	}
}
