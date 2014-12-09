package com.github.dockerjava.jaxrs.util;


public class CapibilityConvertException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3254011786073494802L;

	public CapibilityConvertException(String cap) {
		super("Unknown capibility: " + cap);
	}
}
