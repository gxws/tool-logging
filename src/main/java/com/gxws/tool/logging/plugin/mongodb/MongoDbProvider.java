package com.gxws.tool.logging.plugin.mongodb;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;

import com.gxws.tool.common.constant.ProjectConstant;
import com.gxws.tool.logging.plugin.nosql.NoSqlProvider;

/**
 * 将日志信息写入mongodb
 * 
 * @author zhuwl120820@gxwsxx.com 2015年3月13日上午10:35:28
 *
 */
@Plugin(name = "MongoDbProvider", category = "Core", printObject = true)
public class MongoDbProvider implements NoSqlProvider<MongoDbConnection> {

	private static final Logger log = StatusLogger.getLogger();

	private static final String defaultServers = "0.mongodb.gxwsxx.com:14000,1.mongodb.gxwsxx.com:14000,2.mongodb.gxwsxx.com:14000";

	private static final String defaultName = "logging";

	private MongoDbConnection conn;

	/**
	 * 线下环境的构造方法
	 */
	private MongoDbProvider() {
		conn = new MongoDbConnection();
	}

	/**
	 * 线上环境的构造方法
	 * 
	 * @param servers
	 *            服务器地址和端口号
	 * @param databaseName
	 *            数据库名
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	private MongoDbProvider(String servers, String databaseName,
			String username, String password) {
		log.info("创建日志存储链接：" + servers + " " + databaseName + " " + username
				+ " " + password);
		conn = new MongoDbConnection(servers, databaseName, username, password);
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
			@PluginAttribute("password") final String password,
			@PluginAttribute("env") final String env) {
		if (ProjectConstant.onlineEnvSet.contains(env)) {
			return new MongoDbProvider(ifblank(servers, "servers",
					defaultServers), ifblank(databaseName, "databaseName",
					defaultName), ifblank(username, "username", ""), ifblank(
					password, "password", ""));
		} else {
			return new MongoDbProvider();
		}

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
