package com.github.dockerjava.core.command;

import java.io.InputStream;

import com.github.dockerjava.api.NotFoundException;
import com.github.dockerjava.api.command.PushImageCmd;

import com.google.common.base.Preconditions;

/**
 * Push the latest image to the repository.
 *
 * @param name The name, e.g. "alexec/busybox" or just "busybox" if you want to default. Not null.
 */
public class PushImageCmdImpl extends AbstrAuthCfgDockerCmd<PushImageCmd, InputStream> implements PushImageCmd  {

    private String name;
    private String tag;

    public PushImageCmdImpl(PushImageCmd.Exec exec, String name) {
    	super(exec);
    	withName(name);
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
    public String getTag() {
        return tag;
    }

    /**
	 * @param name The name, e.g. "alexec/busybox" or just "busybox" if you want to default. Not null.
	 */
	@Override
	public PushImageCmd withName(String name) {
		Preconditions.checkNotNull(name, "name was not specified");
		this.name = name;
		return this;
	}

    /**
     * @param tag The image's tag. Can be null or empty.
     */
    @Override
    public PushImageCmd withTag(String tag) {
        Preconditions.checkNotNull(tag, "tag was not specified");
        this.tag = tag;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder("push ")
            .append(name)
            .toString();
    }
   
    /**
     * @throws NotFoundException No such image
     */
    @Override
    public InputStream exec() throws NotFoundException {
    	return super.exec();
    }
}
