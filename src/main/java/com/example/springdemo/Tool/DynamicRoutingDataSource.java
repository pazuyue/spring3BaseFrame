package com.example.springdemo.Tool;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.example.springdemo.Entity.Master.ConnectionMapper;
import com.example.springdemo.Service.Impl.Master.ConnectionMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DynamicRoutingDataSource {

    @Autowired
    ConnectionMapperServiceImpl connectionMapperService;

    /**
     * 手动切换的数据源
     * @param companyCode
     */
    public void switchDataSource(String companyCode) {
        //需要注意的是手动切换的数据源，最好自己在合适的位置
        ConnectionMapper connectionMapper= connectionMapperService.getOneConnectionMapper(companyCode);
        if (!ObjectUtils.isEmpty(connectionMapper)){
            //调用DynamicDataSourceContextHolder.clear()清空当前线程的数据源信息。
            DynamicDataSourceContextHolder.clear();
            //切换到对应poolName的数据源
            DynamicDataSourceContextHolder.push(connectionMapper.getConnectionName());
        }
    }

}
