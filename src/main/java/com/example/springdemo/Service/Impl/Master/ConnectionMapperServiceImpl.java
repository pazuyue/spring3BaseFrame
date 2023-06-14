package com.example.springdemo.Service.Impl.Master;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.Master.ConnectionMapper;
import com.example.springdemo.Mapper.Master.ConnectionMapperMapper;
import com.example.springdemo.Service.Master.ConnectionMapperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 连接映射表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-14
 */
@Service
public class ConnectionMapperServiceImpl extends ServiceImpl<ConnectionMapperMapper, ConnectionMapper> implements ConnectionMapperService {

    @Cacheable(cacheNames = { "getOneConnectionMapper" }, key ="#companyCode")
    public ConnectionMapper getOneConnectionMapper(String companyCode)
    {
        QueryWrapper<ConnectionMapper> connectionMapperQueryWrapper = new QueryWrapper<>();
        connectionMapperQueryWrapper.eq("company_code",companyCode);
        return this.getOne(connectionMapperQueryWrapper);
    }
}
