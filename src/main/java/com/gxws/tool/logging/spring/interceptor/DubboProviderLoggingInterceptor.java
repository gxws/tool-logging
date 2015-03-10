package com.gxws.tool.logging.spring.interceptor;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 服务提供方通过dubbo远程调用记录日志信息
 * 
 * @author 朱伟亮
 * @create 2015年3月6日下午2:24:40
 *
 */
public class DubboProviderLoggingInterceptor implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		System.out.println("DubboProviderLoggingInterceptor");
		return invoker.invoke(invocation);
	}

}
