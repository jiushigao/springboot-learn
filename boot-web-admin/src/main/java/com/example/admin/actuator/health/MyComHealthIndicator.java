package com.example.admin.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义health监控端点属性
 */
@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(1==1){
//            builder.up();
            builder.status(Status.UP);
            map.put("ms",100);
            map.put("msg","一切正常！");
        }else{
//            builder.down();
            builder.status(Status.OUT_OF_SERVICE);
            map.put("ms",3000);
            map.put("msg","指标异常！");
        }
        builder.withDetail("type","自定义端点")
                .withDetails(map);
    }
}
