package com.example.admin.service;

import com.example.admin.bean.City;
import com.example.admin.mapper.CityMapper;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    public CityMapper cityMapper;

    private Counter counter; //添加metrics监控指标，每调用一次getCity方法统计一次

    CityService(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("cityService.getCity.count");
    }

    public City getCity(Long id){
        counter.increment();
        return cityMapper.getCity(id);
    }

    public void insertCity(City city){
        cityMapper.insertCity(city);
    }
}
