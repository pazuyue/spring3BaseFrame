package com.example.springdemo.Controller.PullOrders;

import com.example.springdemo.Interface.ControllerWebLog;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.springdemo.Service.PullOrders.JdpTbTradeService;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/jdp-tb-trade")
public class JdpTbTradeController {


    @Autowired
    private JdpTbTradeService jdpTbTradeService;

    @GetMapping(value = "/")
    @ControllerWebLog(name = "查询", intoDb = true)
    public ResponseEntity<Page<JdpTbTrade>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<JdpTbTrade> aPage = jdpTbTradeService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JdpTbTrade> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(jdpTbTradeService.getById(id), HttpStatus.OK);
    }



}
