package com.kpelykh.docker.client.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Konstantin Pelykh (kpelykh@gmail.com)
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {

	@JsonProperty("Id")
	private String id;

	@JsonProperty("Command")
	private String command;

	@JsonProperty("Image")
	private String image;

	@JsonProperty("Created")
	private long created;

	@JsonProperty("Status")
	private String status;

	/*
	 * Example: "Ports": { "22/tcp": [ { "HostIp": "0.0.0.0", "HostPort": "8022" } ] }
	 */

	@JsonProperty("Ports")
	public List<Container.Port> ports;

	@JsonProperty("SizeRw")
	private int size;

	@JsonProperty("SizeRootFs")
	private int sizeRootFs;

	public String getId() {
		return id;
	}

	public String getCommand() {
		return command;
	}

	public String getImage() {
		return image;
	}

	public long getCreated() {
		return created;
	}

	public String getStatus() {
		return status;
	}

	public List<Container.Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Container.Port> ports) {
		this.ports = ports;
	}

	public int getSize() {
		return size;
	}

	public int getSizeRootFs() {
		return sizeRootFs;
	}

	@Override
	public String toString() {
		return "Container{" + "id='" + id + '\'' + ", command='" + command + '\'' + ", image='" + image + '\''
		      + ", created=" + created + ", status='" + status + '\'' + ", ports=" + ports + ", size=" + size
		      + ", sizeRootFs=" + sizeRootFs + '}';
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Port {
		@JsonProperty("PrivatePort")
		private int privatePort;

		@JsonProperty("IP")
		private String ip;

		@JsonProperty("PublicPort")
		private int publicPort;

		@JsonProperty("Type")
		private String type;

		public void setPrivatePort(int privatePort) {
			this.privatePort = privatePort;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public void setPublicPort(int publicPort) {
			this.publicPort = publicPort;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getPrivatePort() {
			return privatePort;
		}

		public String getIp() {
			return ip;
		}

		public int getPublicPort() {
			return publicPort;
		}

		public String getType() {
			return type;
		}

	}
}
