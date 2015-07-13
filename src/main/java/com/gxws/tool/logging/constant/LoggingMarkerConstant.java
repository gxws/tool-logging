package com.gxws.tool.logging.constant;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * 日志项目的marker集合
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class LoggingMarkerConstant {

	/**
	 * 日志项目自动记录marker
	 */
	public static final Marker TOOL_LOGGING = MarkerManager
			.getMarker("TOOL_LOGGING");

	/**
	 * 日志项目自动记录http请求marker
	 */
	public static final Marker HTTP_REQUEST_MARKER = MarkerManager.getMarker(
			"HTTP_REQUEST").setParents(TOOL_LOGGING);

	/**
	 * 日志项目自动记录spring bean aop的marker
	 */
	public static final Marker BEAN_ASPECT_MARKER = MarkerManager.getMarker(
			"BEAN_ASPECT").setParents(TOOL_LOGGING);

	/**
	 * 日志项目自动记录dubbo filter接收请求的marker
	 */
	public static final Marker DUBBO_FILTER_MARKER = MarkerManager.getMarker(
			"DUBBO_FILTER").setParents(TOOL_LOGGING);
}
