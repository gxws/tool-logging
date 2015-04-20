tool-logging
============

日志工具，用于收集系统处理过程中产生的操作记录。

功能点
---
### 1、收集，收集信息包括：
http访问请求请求信息、参数名和值<br>

	com.gxws.tool.logging.spring.interceptor.WebLoggingInterceptor
SOA接收参数和返回结果<br>

	com.gxws.tool.logging.spring.interceptor.DubboConsumerLoggingInterceptor
	com.gxws.tool.logging.spring.interceptor.DubboProviderLoggingInterceptor
spring bean的public方法接收的参数和返回结果<br>

	com.gxws.tool.logging.spring.aspect.BeanLoggingAspect

### 2、发送，将收集数据发送到nosql数据库存储及存储格式：
数据发送到nosql数据库：

	com.gxws.tool.logging.plugin.nosql
数据在nosql数据库的存储格式：

	com.gxws.tool.logging.plugin.nosql.entity

依赖关系
---

### 1、服务依赖
#### mongodb
默认连接mongodb replica set 集群。<br>
默认连接地址为:

	0.mongodb.gxwsxx.com:14000
	1.mongodb.gxwsxx.com:14000
	2.mongodb.gxwsxx.com:14000
	
默认连接数据库(database)、默认使用的集合(collection)的值为：

	logging
	
默认的数据库连接用户名和密码为空<br>
	
如不能按照默认地址配置的环境，可以在项目log4j2.xml中修改配置：

	<NoSqlAppender name="nosqlAppender">
		<MongoDbProvider servers="你提供的mongodb地址:你提供的mongodb端口号" 
		databaseName="你提供的数据库名" collectionName="你提供的集合名" 
		username="你提供的连接用户名" password="你提供的连接密码" />
	</NoSqlAppender>
	
默认设置等效为：

	<NoSqlAppender name="nosqlAppender">
		<MongoDbProvider servers="0.mongodb.gxwsxx.com:14000,1.mongodb.gxwsxx.com:14000,2.mongodb.gxwsxx.com:14000"
		databaseName="logging" collectionName="logging"
		username="" password="" />
	</NoSqlAppender>
	
### 2、组件依赖
spring framework web模块，版本为4.1以上。<br>
apache log4j，版本为2.2以上。<br>
slf4j版本1.7以上。<br>
mongo-java-driver版本2.13以上。
fastjson版本1.2以上。<br>
其他依赖参考pom.xml。

使用方式
---

### 1、引入maven配置
在pom.xml文件中加入

	<dependency>
		<groupId>com.gxws</groupId>
		<artifactId>tool-logging</artifactId>
		<version>${version.gxws.tool.logging}</version>
	</dependency>

### 2、引入spring配置
在spring.xml文件中加入

	<import resource="spring-logging.xml" />
	

	