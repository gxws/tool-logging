package com.gxws.tool.logging.plugin.mongodb;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;

import com.gxws.tool.logging.plugin.nosql.NoSqlProvider;

/**
 * 将日志信息写入mongodb
 * 
 * @author 朱伟亮
 * @create 2015年3月13日上午10:35:28
 *
 */
@Plugin(name = "MongoDbProvider", category = "Core", printObject = true)
public class MongoDbProvider implements NoSqlProvider<MongoDbConnection> {

	private static final Logger log = StatusLogger.getLogger();

	private static String defaultServers = "mongodb.gxwsxx.com:14000";

	private static String defaultName = "logging";

	private MongoDbConnection conn;

	private MongoDbProvider(String servers, String databaseName,
			String collectionName, String username, String password) {
		conn = new MongoDbConnection(servers, databaseName, collectionName,
				username, password);
	}

	@Override
	public MongoDbConnection getConnection() {
		return conn;
	}

	@PluginFactory
	public static MongoDbProvider createLoggingMongoDbProvider(
			@PluginAttribute("collectionName") final String collectionName,
			@PluginAttribute("databaseName") final String databaseName,
			@PluginAttribute("servers") final String servers,
			@PluginAttribute("username") final String username,
			@PluginAttribute("password") final String password) {
		return new MongoDbProvider(ifblank(servers, "servers", defaultServers),
				ifblank(databaseName, "databaseName", defaultName), ifblank(
						collectionName, "collectionName", defaultName),
				ifblank(username, "username", ""), ifblank(password,
						"password", ""));
	}

	private static String ifblank(String value, String name, String dValue) {
		if (null == value || "".equals(value.trim()) || "null".equals(value)) {
			log.warn("mongodb {} use default value:{}", name, dValue);
			return dValue;
		} else {
			return value;
		}
	}

}
