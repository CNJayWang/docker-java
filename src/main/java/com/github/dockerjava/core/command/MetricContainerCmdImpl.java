package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.MetricContainerCmd;
import com.github.dockerjava.api.model.metric.Metric;
import com.google.common.base.Preconditions;

public class MetricContainerCmdImpl extends AbstrDockerCmd<MetricContainerCmd, Metric> implements MetricContainerCmd {

	private String containerId;
	
	public MetricContainerCmdImpl(MetricContainerCmd.Exec exec, String containerId) {
		super(exec);
		withContainerId(containerId);
	}

	public String getContainerId() {
		return containerId;
	}

	@Override
	public MetricContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}
	
}
