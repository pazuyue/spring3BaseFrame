package com.example.springdemo.Service.Impl.OrderInfo;

import com.example.springdemo.Entity.OrderInfo.OrderInfoQuestion;
import com.example.springdemo.Mapper.OrderInfo.OrderInfoQuestionMapper;
import com.example.springdemo.Service.OrderInfo.OrderInfoQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单问题类型表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderInfoQuestionServiceImpl extends ServiceImpl<OrderInfoQuestionMapper, OrderInfoQuestion> implements OrderInfoQuestionService {

}
