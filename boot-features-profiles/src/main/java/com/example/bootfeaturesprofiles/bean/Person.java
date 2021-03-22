package com.example.bootfeaturesprofiles.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

public interface Person {

    public String getName();

    public Integer getAge();

}
