package com.github.dockerjava.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StartContainerConfig {

    @JsonProperty("Binds")
    private Binds binds = new Binds();
    
    @JsonProperty("Links")
    private Links links = new Links();

    @JsonProperty("LxcConf")
    private LxcConf[] lxcConf = new LxcConf[] {};
    
    @JsonProperty("PortBindings")
    private Ports portBindings = new Ports();
    
    @JsonProperty("PublishAllPorts")
    private boolean publishAllPorts;
    
    @JsonProperty("Privileged")
    private boolean privileged;
    
    @JsonProperty("Dns")
    private String[] dns = new String[] {};
    
	@JsonProperty("DnsSearch")
	private String[] dnsSearch = new String[] {};
    
    @JsonProperty("VolumesFrom")
    private String[] volumesFrom = new String[] {};
    
    @JsonProperty("NetworkMode")          
    private String networkMode = "bridge";
	
	@JsonProperty("Devices")
	private Device[] devices = new Device[] {};
	
	@JsonProperty("RestartPolicy")
	private RestartPolicy restartPolicy = new RestartPolicy();
	
	@JsonProperty("CapAdd")
	private String[] capAdd = new String[] {};
	
	@JsonProperty("CapDrop")
	private String[] capDrop = new String[] {};
    
    @JsonIgnore
    public Bind[] getBinds() {
		return binds.getBinds();
	}

    @JsonIgnore
	public void setBinds(Bind[] binds) {
		this.binds = new Binds(binds);
	}

    @JsonIgnore
    public Link[] getLinks() {
		return links.getLinks();
	}

    @JsonIgnore
	public void setLinks(Link[] links) {
		this.links = new Links(links);
	}

	public LxcConf[] getLxcConf() {
		return lxcConf;
	}

	public void setLxcConf(LxcConf[] lxcConf) {
		this.lxcConf = lxcConf;
	}

	public Ports getPortBindings() {
		return portBindings;
	}

	public void setPortBindings(Ports portBindings) {
		this.portBindings = portBindings;
	}

	public boolean isPublishAllPorts() {
		return publishAllPorts;
	}

	public void setPublishAllPorts(boolean publishAllPorts) {
		this.publishAllPorts = publishAllPorts;
	}

	public boolean isPrivileged() {
		return privileged;
	}

	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}

	public String[] getDns() {
		return dns;
	}

	public void setDns(String[] dns) {
		this.dns = dns;
	}

	public String[] getVolumesFrom() {
		return volumesFrom;
	}

	public void setVolumesFrom(String[] volumesFrom) {
		this.volumesFrom = volumesFrom;
	}

	public String getNetworkMode() {
		return networkMode;
	}

	public void setNetworkMode(String networkMode) {
		this.networkMode = networkMode;
	}

	public Device[] getDevices() {
		return devices;
	}

	public void setDevices(Device[] devices) {
		this.devices = devices;
	}

	public RestartPolicy getRestartPolicy() {
		return restartPolicy;
	}

	public void setRestartPolicy(RestartPolicy restartPolicy) {
		this.restartPolicy = restartPolicy;
	}

	public String[] getCapAdd() {
		return capAdd;
	}

	public void setCapAdd(String[] capAdd) {
		this.capAdd = capAdd;
	}

	public String[] getCapDrop() {
		return capDrop;
	}

	public void setCapDrop(String[] capDrop) {
		this.capDrop = capDrop;
	}

	public void setBinds(Binds binds) {
		this.binds = binds;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String[] getDnsSearch() {
		return dnsSearch;
	}

	public void setDnsSearch(String[] dnsSearch) {
		this.dnsSearch = dnsSearch;
	}

	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }
}