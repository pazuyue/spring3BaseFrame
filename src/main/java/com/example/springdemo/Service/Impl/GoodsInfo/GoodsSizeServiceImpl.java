package com.example.springdemo.Service.Impl.GoodsInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.GoodsInfo.GoodsSize;
import com.example.springdemo.Entity.GoodsInfo.GoodsSkuSnInfo;
import com.example.springdemo.Mapper.GoodsInfo.GoodsSizeMapper;
import com.example.springdemo.Service.GoodsInfo.GoodsSizeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品尺码表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-03
 */
@Service
public class GoodsSizeServiceImpl extends ServiceImpl<GoodsSizeMapper, GoodsSize> implements GoodsSizeService {

    @Cacheable(cacheNames = { "getOneBySizeCode" }, key ="#size_code")
    public GoodsSize getOneBySizeCode(int size_code){
        return this.getById(size_code);
    }
}
