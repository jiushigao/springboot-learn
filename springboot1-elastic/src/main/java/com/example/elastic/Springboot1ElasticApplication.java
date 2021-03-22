package com.example.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * elasticsearch：
 *      docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d -p 9200:9200 -p 9300:9300 --name es01 elasticsearch:5.6.9
 *      elasticsearch默认会占用2g的虚拟机内存，可通过-e ES_JAVA_OPTS="-Xms256m -Xmx256m" 设置内存大小
 *
 * SpringBoot默认支持两种技术来和ES交互；
 * 1：jest（默认不生效）
 * 需要导入jest的工具包io.searchbox.client.JestClient
 * 2：SpringData ElasticSearch【ES版本可能不适配】
 *      如果版本不适配：
 *      升级springboot或安装对应版本的ES
 *      1）Client clusterNodes clusterName
 *      2）ElasticsearchTemplate
 *      3）编写一个ElasticsearchRepository的子接口来操作ES
 */
@SpringBootApplication
public class Springboot1ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot1ElasticApplication.class, args);
    }

}
