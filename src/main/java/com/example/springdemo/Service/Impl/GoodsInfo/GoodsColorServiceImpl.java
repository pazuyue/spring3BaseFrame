package com.example.springdemo.Service.Impl.GoodsInfo;

import com.example.springdemo.Entity.GoodsInfo.GoodsColor;
import com.example.springdemo.Entity.GoodsInfo.GoodsSize;
import com.example.springdemo.Mapper.GoodsInfo.GoodsColorMapper;
import com.example.springdemo.Service.GoodsInfo.GoodsColorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品颜色表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-03
 */
@Service
public class GoodsColorServiceImpl extends ServiceImpl<GoodsColorMapper, GoodsColor> implements GoodsColorService {
    @Cacheable(cacheNames = { "getOneByColorCode" }, key ="#color_code")
    public GoodsColor getOneByColorCode(int color_code){
        return this.getById(color_code);
    }
}
