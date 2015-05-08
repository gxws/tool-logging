package com.gxws.tool.logging.plugin.nosql;

import java.io.Closeable;

import org.apache.logging.log4j.core.LogEvent;

import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;

/**
 * nosql 连接对象接口
 * 
 * @author zhuwl120820@gxwsxx.com
 *
 * @param <W>
 */
public interface NoSqlConnection<W extends LoggingEntity> extends Closeable {

	public W createObject(LogEvent event);

	public void insert(W o);

	/**
	 * 关闭连接
	 */
	@Override
	public void close();

	public boolean isClosed();
}
