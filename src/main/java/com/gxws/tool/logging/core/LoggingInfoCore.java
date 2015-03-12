package com.gxws.tool.logging.core;

import org.apache.logging.log4j.ThreadContext;

import com.gxws.tool.common.constant.ProjectConstant;

/**
 * 日志信息操作集合
 * 
 * @author 朱伟亮
 * @create 2015年3月11日上午9:01:57
 *
 */
@Deprecated
public class LoggingInfoCore {

	/**
	 * 设置全局信息至日志context
	 * 
	 * @author 朱伟亮
	 * @create 2015年3月11日上午9:07:43
	 *
	 */
	public void initInfo() {
		for (String k : ProjectConstant.getAll().keySet()) {
			ThreadContext.put(k, ProjectConstant.get(k));
		}
	}
}
