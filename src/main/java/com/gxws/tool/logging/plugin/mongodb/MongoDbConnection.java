package com.gxws.tool.logging.plugin.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.status.StatusLogger;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gxws.tool.logging.plugin.nosql.NoSqlConnection;
import com.gxws.tool.logging.plugin.nosql.entity.LoggingEntity;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * mongodb 连接
 * 
 * @author zhuwl120820@gxwsxx.com 2015年3月13日下午2:27:12
 *
 */
public class MongoDbConnection implements NoSqlConnection<LoggingEntity> {

	private final Logger log = StatusLogger.getLogger();

	private MongoTemplate mt;

	private Mongo mongo;

	private boolean isOffline;

	/**
	 * 
	 * @param servers
	 *            mongodb服务器地址，","分隔
	 * @param databaseName
	 *            数据库名
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public MongoDbConnection(String servers, String databaseName,
			String username, String password) {
		List<ServerAddress> salist = saList(servers);
		List<MongoCredential> mclist = mcList(username, databaseName, password);
		if ("".equals(username) && "".equals(password)) {
			mongo = new MongoClient(salist);
		} else {
			mongo = new MongoClient(salist, mclist);
		}
		mt = new MongoTemplate(mongo, databaseName);
		isOffline = false;
	}

	public MongoDbConnection() {
		isOffline = true;
	}

	private List<ServerAddress> saList(String servers) {
		List<ServerAddress> salist = new ArrayList<>();
		for (String server : servers.split(",")) {
			String[] s = server.split(":");
			try {
				salist.add(new ServerAddress(s[0], Integer.valueOf(s[1])));
			} catch (NumberFormatException | UnknownHostException e) {
				log.error("init mongodb error:" + server, e);
				continue;
			}
		}
		return salist;
	}

	private List<MongoCredential> mcList(String username, String databaseName,
			String password) {
		List<MongoCredential> mclist = new ArrayList<>();
		mclist.add(MongoCredential.createMongoCRCredential(username,
				databaseName, password.toCharArray()));
		return mclist;
	}

	@Override
	public void insert(LoggingEntity o) {
		if (null != mt) {
			mt.insert(o);
		}
	}

	@Override
	public void close() {
		if (null != mongo && !isOffline) {
			mongo.close();
		}
		if (null != mt && !isOffline) {
			mt = null;
		}
	}

	@Override
	public boolean isClosed() {
		if (null == mt && !isOffline) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LoggingEntity createObject(LogEvent event) {
		return new LoggingEntity(event);
	}
}
