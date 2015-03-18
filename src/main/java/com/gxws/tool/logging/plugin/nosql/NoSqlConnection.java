package com.gxws.tool.logging.plugin.nosql;

import java.io.Closeable;

import org.apache.logging.log4j.core.LogEvent;

import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;

public interface NoSqlConnection<W extends LoggingEntity> extends Closeable {

	public W createObject(LogEvent event);

	public void insert(W o);

	@Override
	public void close();

	public boolean isClosed();
}
