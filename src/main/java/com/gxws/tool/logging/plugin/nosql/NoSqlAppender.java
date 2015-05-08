package com.gxws.tool.logging.plugin.nosql;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.util.Booleans;

/**
 * nosql appender
 * 
 * @author zhuwl120820@gxwsxx.com
 *
 */
@Plugin(name = "NoSqlAppender", category = "Core", elementType = "appender", printObject = true)
public final class NoSqlAppender extends
		AbstractDatabaseAppender<NoSqlManager<?>> {

	private static final long serialVersionUID = -5332950611163930578L;

	private final String description;

	private NoSqlAppender(final String name, final Filter filter,
			final boolean ignoreExceptions, final NoSqlManager<?> manager) {
		super(name, filter, ignoreExceptions, manager);
		this.description = this.getName() + "{ manager=" + this.getManager()
				+ " }";
	}

	@Override
	public String toString() {
		return this.description;
	}

	@PluginFactory
	public static NoSqlAppender createAppender(
			@PluginAttribute("name") final String name,
			@PluginAttribute("ignoreExceptions") final String ignore,
			@PluginElement("Filter") final Filter filter,
			@PluginAttribute("bufferSize") final String bufferSize,
			@PluginElement("NoSqlProvider") final NoSqlProvider<?> provider) {
		if (provider == null) {
			LOGGER.error("NoSQL provider not specified for appender [{}].",
					name);
			return null;
		}

		final int bufferSizeInt = AbstractAppender.parseInt(bufferSize, 0);
		final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);

		final String managerName = "noSqlManager{ description=" + name
				+ ", bufferSize=" + bufferSizeInt + ", provider=" + provider
				+ " }";

		final NoSqlManager<?> manager = NoSqlManager.getNoSqlManager(
				managerName, bufferSizeInt, provider);
		if (manager == null) {
			return null;
		}

		return new NoSqlAppender(name, filter, ignoreExceptions, manager);
	}
}
