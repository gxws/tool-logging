package com.gxws.tool.logging.plugin.nosql;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager;
import org.apache.logging.log4j.core.util.Closer;

import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;

/**
 * nosql 连接管理
 * 
 * @author zhuwl120820@gxwsxx.com
 *
 * @param <W>
 */
public class NoSqlManager<W extends LoggingEntity> extends
		AbstractDatabaseManager {

	private static final NoSQLManagerFactory FACTORY = new NoSQLManagerFactory();

	private NoSqlProvider<NoSqlConnection<W>> provider;

	private NoSqlConnection<W> connection;

	private NoSqlManager(final String name, final int bufferSize,
			final NoSqlProvider<NoSqlConnection<W>> provider) {
		super(name, bufferSize);
		this.provider = provider;
	}

	@Override
	protected void startupInternal() {
	}

	@Override
	protected void shutdownInternal() {
		Closer.closeSilently(this.connection);
	}

	@Override
	protected void connectAndStart() {
		try {
			this.connection = this.provider.getConnection();
		} catch (final Exception e) {
			throw new AppenderLoggingException(
					"Failed to get connection from NoSQL connection provider.",
					e);
		}
	}

	@Override
	protected void writeInternal(final LogEvent event) {
		if (!this.isRunning() || this.connection == null
				|| this.connection.isClosed()) {
			throw new AppenderLoggingException(
					"Cannot write logging event; NoSQL manager not connected to the database.");
		}
		W o = connection.createObject(event);
		this.connection.insert(o);
	}

	@Override
	protected void commitAndClose() {

	}

	public static NoSqlManager<?> getNoSqlManager(final String name,
			final int bufferSize, final NoSqlProvider<?> provider) {
		// return new NoSqlManager(name, bufferSize, provider);
		return AbstractDatabaseManager.getManager(name, new FactoryData(
				bufferSize, provider), FACTORY);
	}

	private static final class FactoryData extends
			AbstractDatabaseManager.AbstractFactoryData {
		private final NoSqlProvider<?> provider;

		protected FactoryData(final int bufferSize,
				final NoSqlProvider<?> provider) {
			super(bufferSize);
			this.provider = provider;
		}
	}

	private static final class NoSQLManagerFactory implements
			ManagerFactory<NoSqlManager<?>, FactoryData> {
		@Override
		public NoSqlManager<?> createManager(final String name,
				final FactoryData data) {
			return new NoSqlManager(name, data.getBufferSize(), data.provider);
		}
	}

}
