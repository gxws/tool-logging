package com.gxws.tool.logging.spring.interceptor;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 服务调用方通过dubbo远程调用记录日志信息
 * 
 * @author 朱伟亮
 * @create 2015年3月6日下午2:31:56
 *
 */
public class DubboConsumerLoggingInterceptor implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		System.out.println("DubboConsumerLoggingInterceptor");
		return invoker.invoke(invocation);
	}

}
