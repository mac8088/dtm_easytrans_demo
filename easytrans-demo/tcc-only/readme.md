# English
## tcc-only
this demo show an simple usage with TCC

more usage you can refer to other demos or the UT case in easytrans-starter

to run this demo, you will need zookeeper and mysql,change the configuration in applicaiton.yml,you can start the services


# 中文
## tcc-only
本demo只演示了在本框架中如何使用tcc以给大家一个如何在业务中使用本框架的整体认知。

更多的更复杂的用法 请参考easytrans-demos里其他目录（demo陆续加入中） 及 easytrans-starter里面的测试案例（用法最全，最复杂）

本demo运行起来需要zk以及关系数据库，修改applicaiton.yml文件里相关zk及数据库配置后，即可启动。


## How to run the Easytransaction tcc-only demo.

### 1. create mysql DB

	execute the scripts in 
	tcc-only\tcconly-order-service\src\main\resources\createDatabase.sql
	tcc-only\tcconly-wallet-service\src\main\resources\createDatabase.sql

note: if there is error message when run the tcc-only demo like:Data source rejected establishment of connection, message from server: "Too many connections"
please modify the max_connections value in my.ini of mysql file.

### 2. start zookeeper

download zookeeper: https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/zookeeper-3.5.6/
unzip and modify dataDir dataLogDir clientPort in apache-zookeeper-3.5.6\conf\zoo_sample.cfg
rename zoo_sample.cfg to zoo.cfg

	run \apache-zookeeper-3.5.6\bin\zkServer.cmd
	run zkCli.cmd -server 127.0.0.1:2281

### 3. modify application.yml set the database and port related information.

### 4. run the order and wallet service.
