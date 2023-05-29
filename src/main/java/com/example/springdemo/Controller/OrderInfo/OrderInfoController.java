package com.example.springdemo.Controller.OrderInfo;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.springdemo.Service.OrderInfo.OrderInfoService;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 订单主信息表 前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/order-info")
public class OrderInfoController {


    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<OrderInfo>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<OrderInfo> aPage = orderInfoService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderInfo> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(orderInfoService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody OrderInfo params) {
        orderInfoService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        orderInfoService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody OrderInfo params) {
        orderInfoService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
