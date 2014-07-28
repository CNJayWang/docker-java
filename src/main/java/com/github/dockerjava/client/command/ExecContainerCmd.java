package com.github.dockerjava.client.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.client.DockerException;
import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;


public class ExecContainerCmd extends AbstrDockerCmd<ExecContainerCmd, String> {

private static final Logger LOGGER = LoggerFactory.getLogger(ExecContainerCmd.class);
	
	private String containerId;
	private String command;
	private List<String> args = new ArrayList<String>();
	
	public ExecContainerCmd(String containerId) {
		withContainerId(containerId);
	}
	
	public ExecContainerCmd withContainerId(String containerId) {
		Preconditions.checkNotNull(containerId, "containerId was not specified");
		this.containerId = containerId;
		return this;
	}
	
	public ExecContainerCmd withCommand(String command) {
		Preconditions.checkNotNull(command, "command was not specified");
		String[] commands = command.split("\\s+");
		if (commands.length == 0) {
			throw new IllegalArgumentException("command can not be empty");
		} else {
			this.command = commands[0];
			if (commands.length > 1) {
				for (int i = 1; i < commands.length; i++) {
					args.add(commands[i]);
				}
			}
		}
		return this;
	}
	
	public ExecContainerCmd withArgs(String[] args) {
		Preconditions.checkNotNull(args, "args was not specified");
		this.args = Arrays.asList(args);
		return this;
	}
	
	public ExecContainerCmd addArgs(String[] args) {
		Preconditions.checkNotNull(args, "args was not specified");
		this.args.addAll(Arrays.asList(args));
		return this;
	}
	
	public ExecContainerCmd addArg(String args) {
		Preconditions.checkNotNull(args, "args was not specified");
		this.args.add(args);
		return this;
	}
	
	@Override
	String impl() {
		WebResource webResource = baseResource.path(String.format("/containers/%s/exec", containerId));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("command", this.command);
		data.put("args", this.args);
		String response = null;
		try {
			LOGGER.trace("POST: {}", webResource);
			response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE).post(String.class, data);
			LOGGER.trace("Response: {}", response);
		} catch (UniformInterfaceException exception) {
			if (exception.getResponse().getStatus() == 404) {
				LOGGER.warn(String.format("%s is an unrecognized container.", containerId));
			} else if (exception.getResponse().getStatus() == 500) {
				response = exception.getResponse().getEntity(String.class);
				throw new DockerException("Server error - " + response, exception);
			} else {
				throw new DockerException(exception);
			}
		}
		
		return response;
	}

}
