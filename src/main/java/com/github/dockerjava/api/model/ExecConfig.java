package com.github.dockerjava.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecConfig {

	@JsonProperty("User") private String user = "";
	@JsonProperty("Privileged") private boolean privileged = false;
	@JsonProperty("Tty") private boolean tty = false;
	@JsonProperty("Container") private String containerId = "";
	@JsonProperty("AttachStdin") private boolean attachStdin = false;
	@JsonProperty("AttachStdout") private boolean attachStdout = true;
	@JsonProperty("AttachStderr") private boolean attachStderr = true;
	@JsonProperty("Detach") private boolean detach;
	@JsonProperty("Cmd") private String[] cmd;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public boolean isPrivileged() {
		return privileged;
	}
	
	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}
	
	public boolean isTty() {
		return tty;
	}
	
	public void setTty(boolean tty) {
		this.tty = tty;
	}
	
	public String getContainerId() {
		return containerId;
	}
	
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	
	public boolean isAttachStdin() {
		return attachStdin;
	}
	
	public void setAttachStdin(boolean attachStdin) {
		this.attachStdin = attachStdin;
	}
	
	public boolean isAttachStdout() {
		return attachStdout;
	}
	
	public void setAttachStdout(boolean attachStdout) {
		this.attachStdout = attachStdout;
	}
	
	public boolean isAttachStderr() {
		return attachStderr;
	}
	
	public void setAttachStderr(boolean attachStderr) {
		this.attachStderr = attachStderr;
	}
	
	public boolean isDetach() {
		return detach;
	}
	
	public void setDetach(boolean detach) {
		this.detach = detach;
	}
	
	public String[] getCmd() {
		return cmd;
	}
	
	public void setCmd(String[] cmd) {
		this.cmd = cmd;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
