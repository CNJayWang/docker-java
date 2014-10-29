package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.LimitContainerCmd;
import com.github.dockerjava.api.model.LimitationConfig;
import com.google.common.base.Preconditions;

public class LimitContainerCmdImpl extends AbstrDockerCmd<LimitContainerCmd, Void> implements LimitContainerCmd {

	private String containerId;
	private LimitationConfig limitationConfig;
	
	public LimitContainerCmdImpl(LimitContainerCmd.Exec exec, LimitationConfig limitationConfig, String containerId) {
		super(exec);
		withContainerId(containerId);
		Preconditions.checkNotNull(limitationConfig, "limitation config was not specified");
		withLimitationConfig(limitationConfig);
	}

	@Override
	public String getContainerId() {
		return containerId;
	}

	@Override
	public long getMemoryLimit() {
		return limitationConfig.getMemoryLimit();
	}

	@Override
	public int getCpuShares() {
		return limitationConfig.getCpuShares();
	}

	@Override
	public String getCpuset() {
		return limitationConfig.getCpuset();
	}

	@Override
	public LimitationConfig getLimitationConfig() {
		return limitationConfig;
	}

	@Override
	public LimitContainerCmd withCpuShares(int cpuShares) {
		limitationConfig.setCpuShares(cpuShares);
		return this;
	}

	@Override
	public LimitContainerCmd withCpuset(String cpuset) {
		limitationConfig.setCpuset(cpuset);
		return this;
	}

	@Override
	public LimitContainerCmd withMemoryLimit(long memoryLimit) {
		limitationConfig.setMemoryLimit(memoryLimit);
		return this;
	}

	@Override
	public LimitContainerCmd withContainerId(String containerId) {
		this.containerId = containerId;
		return this;
	}

	@Override
	public LimitContainerCmd withLimitationConfig(
			LimitationConfig limitationConfig) {
		this.limitationConfig = limitationConfig;
		return this;
	}

	@Override
	public boolean isSaveChanges() {
		return limitationConfig.isSaveChanges();
	}

	@Override
	public LimitContainerCmd withSaveChanges(boolean saveChanges) {
		this.limitationConfig.setSaveChanges(saveChanges);
		return this;
	}

}
