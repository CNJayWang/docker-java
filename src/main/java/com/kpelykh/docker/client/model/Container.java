package com.kpelykh.docker.client.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonProperty("Names")
	private String[] names;

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
	
	public String[] getNames() {
        return names;
    }
	
	public void setId(String id) {
        this.id = id;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSizeRootFs(int sizeRootFs) {
        this.sizeRootFs = sizeRootFs;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

	@Override
	public String toString() {
		return "Container{" + "id='" + id + '\'' + ", command='" + command + '\'' + ", image='" + image + '\''
		      + ", created=" + created + ", status='" + status + '\'' + ", ports=" + ports + ", size=" + size
		      + ", sizeRootFs=" + sizeRootFs + ", names=" + Arrays.toString(names) + '}';
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
