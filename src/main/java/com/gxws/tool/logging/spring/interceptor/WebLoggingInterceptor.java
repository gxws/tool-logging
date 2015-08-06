package com.gxws.tool.logging.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.logging.constant.LoggingContextMapConstant;
import com.gxws.tool.logging.constant.LoggingMarkerConstant;
import com.gxws.tool.logging.datamodel.HttpServletRequestDm;

/**
 * spring mvc controller接收http请求前interceptor记录日志
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class WebLoggingInterceptor implements HandlerInterceptor {

	private Logger log = LogManager.getLogger();

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		ThreadContext.put(LoggingContextMapConstant.HTTP_REQUEST, Uuid.order());
		ThreadContext.put(LoggingContextMapConstant.HTTP_SESSION, req.getSession().getId());
		log.debug(LoggingMarkerConstant.HTTP_REQUEST_MARKER, "接收http request请求");
		HttpServletRequestDm dm = new HttpServletRequestDm(req);
		log.info(LoggingMarkerConstant.HTTP_REQUEST_MARKER, dm.info());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (null != ex) {
			log.error(ex.getMessage(), ex);
		}
		log.debug(LoggingMarkerConstant.HTTP_REQUEST_MARKER, "处理http request请求完成");
	}

}
