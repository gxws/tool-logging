package com.gxws.tool.logging.plugin.nosql.entity;

import java.io.Serializable;

/**
 * 日志来源信息
 * 
 * @author zhuwl120820@gxwsxx.com
 *  2015年3月16日上午11:15:23
 *
 */
public class SourceEntity implements Serializable {

	private static final long serialVersionUID = 8656167247317646909L;
	private String className;
	private String methodName;
	private String fileName;
	private Integer lineNumber;

	public SourceEntity(StackTraceElement ste) {
		if (null == ste) {
			return;
		}
		setClassName(ste.getClassName());
		setFileName(ste.getFileName());
		setMethodName(ste.getMethodName());
		setLineNumber(ste.getLineNumber());
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
}
