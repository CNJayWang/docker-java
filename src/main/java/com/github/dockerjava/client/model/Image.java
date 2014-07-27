package com.github.dockerjava.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 *
 * @author Konstantin Pelykh (kpelykh@gmail.com)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

	@JsonProperty("Created")
	private long created;

	@JsonProperty("Id")
    private String id;

	@JsonProperty("ParentId")
	private String parentId;

	@JsonProperty("RepoTags")
    private String[] repoTags;

    @JsonProperty("Size")
    private long size;

    @JsonProperty("VirtualSize")
    private long virtualSize;

    public String getId() {
        return id;
    }

    public String[] getRepoTags() {
        return repoTags;
    }

    public String getParentId() {
        return parentId;
    }

    public long getCreated() {
        return created;
    }

    public long getSize() {
        return size;
    }

    public long getVirtualSize() {
        return virtualSize;
    }

    @Override
    public String toString() {
        return "Image{" +
                "virtualSize=" + virtualSize +
                ", id='" + id + '\'' +
                ", repoTags=" + Arrays.toString(repoTags) +
                ", parentId='" + parentId + '\'' +
                ", created=" + created +
                ", size=" + size +
                '}';
    }
}
