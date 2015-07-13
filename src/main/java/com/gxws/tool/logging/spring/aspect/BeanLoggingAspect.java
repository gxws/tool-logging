package com.gxws.tool.logging.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;

import com.alibaba.fastjson.JSON;
import com.gxws.tool.logging.constant.LoggingMarkerConstant;

/**
 * spring bean aop记录日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class BeanLoggingAspect {

	private Logger log = LogManager.getLogger();

	public void before(JoinPoint jp) {
		String location = jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName();
		ThreadContext.push(location);
		try {
			log.info(LoggingMarkerConstant.BEAN_ASPECT_MARKER,
					JSON.toJSON(jp.getArgs()));
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("{");
			for (Object o : jp.getArgs()) {
				if (null != o) {
					sb.append(o.toString() + ",");
				}
			}
			sb.append("}");
			log.info(LoggingMarkerConstant.BEAN_ASPECT_MARKER, sb.toString());
		}
	}

	public void after(JoinPoint jp) {
		String location = jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName();
		if (location.endsWith(ThreadContext.peek())) {
			ThreadContext.pop();
		}
	}
}
