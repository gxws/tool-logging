package com.gxws.tool.logging.plugin.nosql.entity;

import com.gxws.tool.common.constant.ProjectConstant;

/**
 * 收集项目相关信息
 * 
 * @author zhuwl120820@gxwsxx.com 2015年3月18日下午5:57:48
 *
 */
public class ProjectEntity {

	private String env;
	private String name;
	private String version;
	private String ip;
	private String port;

	public ProjectEntity() {
		// setEnv(ProjectConstant.VALUE_PROJECT_ENV);
		// setName(ProjectConstant.VALUE_PROJECT_NAME);
		// setVersion(ProjectConstant.VALUE_PROJECT_VERSION);
		// setIp(ProjectConstant.VALUE_PROJECT_IP);
		// setPort(ProjectConstant.VALUE_PROJECT_PORT);
		ProjectConstant pc = ProjectConstant.instance();
		setEnv(pc.getEnv());
		setName(pc.getName());
		setVersion(pc.getVersion());
		setIp(pc.getIp());
		setPort(pc.getPort());
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
