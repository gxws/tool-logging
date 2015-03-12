package com.gxws.tool.logging.constants;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * @author 朱伟亮
 * @create 2015年3月11日下午5:42:17
 *
 */
public class LoggingMarkerConstant {

	public static final Marker HTTP_REQUEST_MARKER = MarkerManager
			.getMarker("springmvc web http logging interceptor");

	public static final Marker BEAN_ASPECT_MARKER = MarkerManager
			.getMarker("spring bean logging aspect");
}
