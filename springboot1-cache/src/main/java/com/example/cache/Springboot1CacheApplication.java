package com.example.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * docker常用命令：
 * yum install docker:安装docker
 * systemctl start docker:启动docker
 * systemctl enable docker:开机启动docker
 * docker search 镜像名:查找镜像
 * docker pull 镜像名[:tag] :下载镜像
 * docker images:查看已下载的镜像
 * docker rmi 镜像名:删除镜像
 * docker run -d -p 主机端口:容器端口 --name 自定义容器名 镜像名/镜像id :启动镜像生成容器  -d:后台运行  -p：将主机端口映射到容器端口
 * docker ps [-a]:查看当前运行的容器  -a 查看所有的容器包括停止的容器
 * docker stop 容器id:关闭容器
 * docker start 容器id:启动容器
 * docker rm 容器id:删除容器
 *
 *
 */
@SpringBootApplication
@EnableCaching//开启基于注解的缓存
public class Springboot1CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot1CacheApplication.class, args);
    }

}
