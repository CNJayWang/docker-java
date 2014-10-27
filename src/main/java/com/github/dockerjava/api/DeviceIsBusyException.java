package com.github.dockerjava.api;

public class DeviceIsBusyException extends DockerException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8301793561056058849L;

	public DeviceIsBusyException(String message, Throwable cause) {
        super(message, 599, cause);
    }
	
	public DeviceIsBusyException(String message) {
        this(message, null);
    }
	
	public DeviceIsBusyException(Throwable cause) {
        this(cause.getMessage(), cause);
    }
}
