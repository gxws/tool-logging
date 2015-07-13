package com.gxws.tool.logging.constant;

/**
 * 日志属性
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class LoggingContextMapConstant {

	/**
	 * 每次接收http request时创建
	 */
	public final static String HTTP_REQUEST = "httpRequestId";

	/**
	 * 每次创建http session时创建
	 */
	public final static String HTTP_SESSION = "httpSessionId";

	/**
	 * 每次rpc调用时创建
	 */
	public final static String SERVICE_REQUEST = "serviceRequestId";

}
