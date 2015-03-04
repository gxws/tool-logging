package com.gxws.tool.logging.storage.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.common.constant.ProjectConstant;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * @author 朱伟亮
 * @create 2015年2月28日下午5:02:46
 *
 */
public class Factory {

	private static Logger log = LoggerFactory.getLogger(Factory.class);

	private final static int port = 14000;

	private final static String username = ProjectConstant.NAME;

	private final static char[] password = ProjectConstant.NAME.toCharArray();

	private final static String database = "logging";

	public static MongoClient mongoClient() {
		MongoClient mongoClient;
		List<ServerAddress> addrs = new ArrayList<>();
		String host;
		for (int i = 0; i < 3; i++) {
			host = "mongodb" + i + ".gxwsxx.com";
			try {
				addrs.add(new ServerAddress(host, port));
			} catch (UnknownHostException e) {
				log.error("unknow host " + host + ":" + port, e);
				return null;
			}
		}
		List<MongoCredential> credentials = new ArrayList<>();
		credentials.add(MongoCredential.createMongoCRCredential(username,
				database, password));
		mongoClient = new MongoClient(addrs, credentials);
		return mongoClient;
	}

	public static MongoClient mongoClientTemp() {
		MongoClient mongoClient;
		List<MongoCredential> credentials = new ArrayList<>();
		credentials.add(MongoCredential.createMongoCRCredential(username,
				database, password));
		ServerAddress addr;
		try {
			addr = new ServerAddress("localhost", 27017);
		} catch (UnknownHostException e) {
			log.error("unknow host localhost 27017", e);
			return null;
		}
		mongoClient = new MongoClient(addr, credentials);
		return mongoClient;

	}
}
