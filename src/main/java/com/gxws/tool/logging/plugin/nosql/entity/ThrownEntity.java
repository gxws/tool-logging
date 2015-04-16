package com.gxws.tool.logging.plugin.nosql.entity;

import java.io.Serializable;

/**
 * @author zhuwl120820@gxwsxx.com
 *  2015年3月16日上午11:28:15
 *
 */
public class ThrownEntity implements Serializable {

	private String type;

	private String message;

	private SourceEntity[] stackTrace;

	private ThrownEntity cause;

	public ThrownEntity(Throwable t) {
		if (null == t) {
			return;
		}
		Throwable thrown = t;
		setType(thrown.getClass().getName());
		setMessage(thrown.getMessage());
		StackTraceElement[] stes = thrown.getStackTrace();
		SourceEntity[] ses = new SourceEntity[stes.length];
		for (int i = 0; i < stes.length; i++) {
			ses[i] = new SourceEntity(stes[i]);
		}
		setStackTrace(ses);
		if (null != thrown.getCause()) {
			setCause(new ThrownEntity(thrown.getCause()));
		}

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SourceEntity[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(SourceEntity[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	public ThrownEntity getCause() {
		return cause;
	}

	public void setCause(ThrownEntity cause) {
		this.cause = cause;
	}

	private static final long serialVersionUID = 4953714833965454376L;

}
