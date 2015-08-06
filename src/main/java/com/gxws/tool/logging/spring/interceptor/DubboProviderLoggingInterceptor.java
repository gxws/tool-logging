package com.gxws.tool.logging.spring.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.gxws.tool.common.data.dto.BaseDto;
import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.logging.constant.LoggingContextMapConstant;
import com.gxws.tool.logging.constant.LoggingMarkerConstant;

/**
 * 服务提供方通过dubbo远程调用记录日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class DubboProviderLoggingInterceptor implements Filter {

	private Logger log = LogManager.getLogger();

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Object[] os = invocation.getArguments();
		Map<String, String> reqMap = null;
		ThreadContext.put(LoggingContextMapConstant.SERVICE_REQUEST, Uuid.order());
		for (Object o : os) {
			if (o instanceof BaseDto) {
				reqMap = ((BaseDto) o).getRequestMap();
				((BaseDto) o).setResponseMap(ThreadContext.getContext());
				if (null != reqMap && reqMap.isEmpty()) {
					break;
				}
			}
		}
		if (null != reqMap && 0 != reqMap.size()) {
			for (String k : reqMap.keySet()) {
				ThreadContext.put(k, reqMap.get(k));
			}
		}
		log.debug(LoggingMarkerConstant.DUBBO_FILTER_MARKER, "接收dubbo rpc请求");
		return invoker.invoke(invocation);
	}
}
