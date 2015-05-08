package com.gxws.tool.logging.plugin.nosql.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.core.LogEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 *  2015年3月16日上午11:07:38
 *
 */
@Document(collection = "logging")
public class LoggingEntity implements Serializable {

	@Id
	private String id;
	private String level;
	private String loggerName;
	private String message;
	private String threadName;
	private Long millis;
	private Date date;
	private SourceEntity source;
	private MarkerEntity marker;
	private ThrownEntity thrown;
	private ProjectEntity project;
	private Map<String, String> contextMap;
	private Object[] contextStack;

	public LoggingEntity(LogEvent event) {
		if (null == event) {
			return;
		}
		setLevel(event.getLevel().name());
		setLoggerName(event.getLoggerName());
		setMessage(event.getMessage() == null ? null : event.getMessage()
				.getFormattedMessage());
		setThreadName(event.getThreadName());
		setMillis(event.getTimeMillis());
		setDate(new Date(event.getTimeMillis()));
		setSource(new SourceEntity(event.getSource()));
		setMarker(new MarkerEntity(event.getMarker()));
		setThrown(new ThrownEntity(event.getThrown()));
		setContextMap(event.getContextMap());
		if (null != event.getContextStack()) {
			setContextStack(event.getContextStack().asList().toArray());
		}
		setProject(new ProjectEntity());
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public Long getMillis() {
		return millis;
	}

	public void setMillis(Long millis) {
		this.millis = millis;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SourceEntity getSource() {
		return source;
	}

	public void setSource(SourceEntity source) {
		this.source = source;
	}

	public MarkerEntity getMarker() {
		return marker;
	}

	public void setMarker(MarkerEntity marker) {
		this.marker = marker;
	}

	public ThrownEntity getThrown() {
		return thrown;
	}

	public void setThrown(ThrownEntity thrown) {
		this.thrown = thrown;
	}

	public Map<String, String> getContextMap() {
		return contextMap;
	}

	public void setContextMap(Map<String, String> contextMap) {
		this.contextMap = contextMap;
	}

	public Object[] getContextStack() {
		return contextStack;
	}

	public void setContextStack(Object[] contextStack) {
		this.contextStack = contextStack;
	}

	private static final long serialVersionUID = -4251601488716921388L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
}
