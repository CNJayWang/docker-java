package com.github.dockerjava.api.command;

import com.github.dockerjava.api.NotFoundException;

/**
 * Remove a container.
 * 
 * @param removeVolumes - true or false, Remove the volumes associated to the container. Defaults to false
 * @param force - true or false, Removes the container even if it was running. Defaults to false
 */
public interface RemoveContainerCmd extends DockerCmd<Void> {

	public String getContainerId();

	public boolean hasRemoveVolumesEnabled();

	public boolean hasForceEnabled();
	
	public boolean hasCheckDevice();

	public RemoveContainerCmd withContainerId(String containerId);

	public RemoveContainerCmd withRemoveVolumes(boolean removeVolumes);

	public RemoveContainerCmd withForce();

	public RemoveContainerCmd withForce(boolean force);
	
	public RemoveContainerCmd withCheckDevice();

	public RemoveContainerCmd withCheckDevice(boolean checkDevice);

	/**
	 * @throws NotFoundException No such container
	 */
	public Void exec() throws NotFoundException;
	
	public static interface Exec extends DockerCmdExec<RemoveContainerCmd, Void> {
	}

}