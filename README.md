tool-logging
============

mail list:朱伟亮 \<zhuwl120820@gxwsxx.com>

日志工具，用于收集系统处理过程中产生的操作记录。

版本变更说明
---
### 1.0.3
修改将配置文件log4j2.xml和spring-logging.xml从项目目录转移至tool-logging的包目录。<br>
修改非线上环境不启用远程日志存储功能，即env属性不属于[{ "dev", "test", "real" }]的不启用nosql存储功能。<br>
<br>
添加完善了说明文档README.md。<br>

### 1.1.0
去除nosql依赖，将nosql部分的功能划分到可选功能。<br>
<br>
修改重新调整配置了项目maven依赖，不再继承自parent。<br>
修改配置文件spring-logging.xml文件名修改为spring-logging-default.xml。<br>
修改默认配置方式，从配置文件的方式修改为编程方式。<br>
<br>
添加将dubbo filter配置加入spring-logging.xml中。<br>
添加排除logging输出，实现org.springframework.web.servlet.HandlerInterceptor接口的类。<br>
添加排除logging输出，参数只包含org.aspectj.lang.JoinPoint类的对象。<br>
修改marker的内容。<br>

### 1.2.0
添加WebLoggingInterceptor拦截器拦截到异常后的log。<br>

功能点
---
### 1.收集，收集信息包括：
http访问请求请求信息、参数名和值<br>

	com.gxws.tool.logging.spring.interceptor.WebLoggingInterceptor
SOA接收参数和返回结果<br>

	com.gxws.tool.logging.spring.interceptor.DubboConsumerLoggingInterceptor
	com.gxws.tool.logging.spring.interceptor.DubboProviderLoggingInterceptor
spring bean的public方法接收的参数和返回结果<br>

	com.gxws.tool.logging.spring.aspect.BeanLoggingAspect

### 2.发送，将收集数据发送到nosql数据库存储及存储格式：
数据发送到nosql数据库：

	com.gxws.tool.logging.plugin.nosql
数据在nosql数据库的存储格式：

	com.gxws.tool.logging.plugin.nosql.entity

依赖关系
---

### 1.组件依赖
org.springframework spring-web 4.1<br>
org.apache.logging.log4j log4j-core 2.2<br>
org.slf4j slf4j-api 1.7<br>
com.alibaba fastjson 1.2<br>
其他依赖参考pom.xml。

使用方式
---

### 引入maven配置
在pom.xml文件中加入

	<dependency>
		<groupId>com.gxws</groupId>
		<artifactId>tool-logging</artifactId>
		<version>最新版本号</version>
	</dependency>

### 引入spring配置
在spring.xml文件中加入

	<import resource="spring-logging-default.xml" />
	
使用组件
---
### MongoDB
将日志信息发送到MongoDB。<br>
默认连接mongodb replica set 集群。<br>

#### 引入maven包
	<dependency>
		<groupId>org.mongodb</groupId>
		<artifactId>mongo-java-driver</artifactId>
		<version>2.13.0</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-mongodb</artifactId>
		<version>1.6.2.RELEASE</version>
	</dependency>

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
	
	