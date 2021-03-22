package com.example.admin.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * 自定义监控端点
 */
@Component
@Endpoint(id = "myservice")
public class MyEndPoint {

    //端点读操作
    @ReadOperation
    public Map getDockerInfo(){
        return Collections.singletonMap("dockerInfo","docker started...");
    }

    @WriteOperation
    public void stopDocker(){
        System.out.println("docker stopped...");
    }
}
