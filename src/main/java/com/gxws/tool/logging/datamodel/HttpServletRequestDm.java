package com.gxws.tool.logging.datamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 收集http servlet request请求的信息
 * 
 * @author zhuwl120820@gxwsxx.com
 *  2015年2月27日下午4:06:15
 *
 */
public class HttpServletRequestDm {

	@JSONField(ordinal = 0)
	private String url;

	@JSONField(ordinal = 2)
	private String referer;

	@JSONField(ordinal = 3)
	private String userAgent;

	@JSONField(ordinal = 4)
	private String remoteAddr;

	@JSONField(ordinal = 5)
	private String remoteHost;

	@JSONField(ordinal = 6)
	private String remotePort;

	@JSONField(ordinal = 3)
	private String localPort;

	@JSONField(ordinal = 7)
	private String localName;

	@JSONField(ordinal = 7)
	private String localAddr;

	@JSONField(ordinal = 1)
	private Map<String, String[]> parameters;

	@JSONField(ordinal = 100)
	private Map<String, Set<String>> cookies;

	public HttpServletRequestDm(HttpServletRequest req) {
		url = req.getRequestURL().toString();
		referer = req.getHeader("Referer");
		userAgent = req.getHeader("User-Agent");
		remoteAddr = req.getRemoteAddr();
		remoteHost = req.getRemoteHost();
		remotePort = String.valueOf(req.getRemotePort());
		localPort = String.valueOf(req.getLocalPort());
		localName = req.getLocalName();
		localAddr = req.getLocalAddr();
		handleParameter(req);
		handleCookie(req);
	}

	private void handleParameter(HttpServletRequest req) {
		parameters = req.getParameterMap();
	}

	private void handleCookie(HttpServletRequest req) {
		cookies = new HashMap<>();
		String key;
		Set<String> valueSet;
		Cookie[] cs = req.getCookies();
		if (null == cs) {
			return;
		}
		for (Cookie c : cs) {
			key = c.getName();
			valueSet = cookies.get(key);
			if (null == valueSet) {
				valueSet = new HashSet<>();
			}
			valueSet.add(c.getValue());
			cookies.put(key, valueSet);
		}
	}

	public String info() {
		return JSON.toJSONString(this);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Map<String, Set<String>> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, Set<String>> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public String getLocalPort() {
		return localPort;
	}

	public String getLocalName() {
		return localName;
	}

	public String getLocalAddr() {
		return localAddr;
	}

}
