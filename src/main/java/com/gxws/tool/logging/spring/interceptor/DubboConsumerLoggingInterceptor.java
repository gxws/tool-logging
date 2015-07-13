package com.gxws.tool.logging.spring.interceptor;

import java.util.HashMap;
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
import com.gxws.tool.logging.constant.LoggingMarkerConstant;

/**
 * 服务调用方通过dubbo远程调用记录日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class DubboConsumerLoggingInterceptor implements Filter {

	private Logger log = LogManager.getLogger();

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		Object[] os = invocation.getArguments();
		Map<String, String> map = new HashMap<>();
		map.putAll(ThreadContext.getContext());
		for (Object o : os) {
			if (o instanceof BaseDto) {
				((BaseDto) o).setRequestMap(map);
			}
		}
		log.debug(LoggingMarkerConstant.DUBBO_FILTER_MARKER, "发送dubbo rpc请求");
		return invoker.invoke(invocation);
	}

}
