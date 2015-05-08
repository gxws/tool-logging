package com.gxws.tool.logging.plugin.nosql;

import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;

/**
 * nosql 连接提供接口
 * 
 * @author zhuwl120820@gxwsxx.com
 *
 * @param <C>
 *            要保存的日志信息类
 */
public interface NoSqlProvider<C extends NoSqlConnection<? extends LoggingEntity>> {

	public C getConnection();

	@Override
	public String toString();
}
