package com.example.springdemo.Service.Impl.GoodsInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.GoodsInfo.GoodsSkuSnInfo;
import com.example.springdemo.Mapper.GoodsInfo.GoodsSkuSnInfoMapper;
import com.example.springdemo.Service.GoodsInfo.GoodsSkuSnInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品信息表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class GoodsSkuSnInfoServiceImpl extends ServiceImpl<GoodsSkuSnInfoMapper, GoodsSkuSnInfo> implements GoodsSkuSnInfoService {

    @Cacheable(cacheNames = { "getOneByskuSn" }, key ="#skuSn + ':' + #company_code")
    public GoodsSkuSnInfo getOneByskuSn(String skuSn,String company_code){
        QueryWrapper<GoodsSkuSnInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_sn",skuSn);
        queryWrapper.eq("company_code",company_code);
        return this.getOne(queryWrapper);
    }

}
