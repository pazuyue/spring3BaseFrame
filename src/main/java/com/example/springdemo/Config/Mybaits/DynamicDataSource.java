package com.example.springdemo.Config.Mybaits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    private DataSource master;
    @Autowired
    private DataSource slave;

    public static ThreadLocal<String> name = new ThreadLocal<>();

    @Override
    public void afterPropertiesSet() {
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put("w",master);
        targetDataSources.put("s",slave);
        super.setTargetDataSources(targetDataSources);
        super.setDefaultTargetDataSource(master);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }
}
