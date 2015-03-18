package com.gxws.tool.logging.plugin.nosql;

import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;

public interface NoSqlProvider<C extends NoSqlConnection<? extends LoggingEntity>> {

	public C getConnection();

	@Override
	public String toString();
}
