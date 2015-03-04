package com.gxws.tool.logging.spring.webmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxws.tool.common.constant.LocalConstant;
import com.gxws.tool.common.constant.ProjectConstant;
import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.logging.constants.LogContextMapConstant;
import com.gxws.tool.logging.constants.LogContextStackConstant;
import com.gxws.tool.logging.datamodel.HttpServletRequestDm;

/**
 * @author 朱伟亮
 * @create 2015年2月27日下午2:22:56
 *
 */
public class LoggingInterceptor implements HandlerInterceptor {

	private Logger log = LogManager.getLogger();

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		ThreadContext.put(LogContextMapConstant.HTTP_REQUEST, Uuid.order());
		ThreadContext.put(LogContextMapConstant.HTTP_SESSION, req.getSession()
				.getId());
		ThreadContext.put(LogContextMapConstant.PROJECT_NAME,
				ProjectConstant.NAME);
		ThreadContext.put(LogContextMapConstant.PROJECT_ENV,
				ProjectConstant.ENV);
		ThreadContext.put(LogContextMapConstant.PROJECT_VERSION,
				ProjectConstant.VERSION);
		ThreadContext.put(LogContextMapConstant.LOCAL_ADDR, LocalConstant.IP);
		ThreadContext.push(LogContextStackConstant.LEVEL_SPRINGMVC_INTERCEPTOR);
		log.debug("http request processing accept");
		HttpServletRequestDm dm = new HttpServletRequestDm(req);
		log.info(dm.info());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("http request processing complete");
		ThreadContext.pop();
	}

}
