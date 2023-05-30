package com.example.springdemo.Controller.TChannelOrderLog;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 渠道订单流水表(日志表) 前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-05-30
 */
@RestController
@RequestMapping("/t-channel-order-log")
public class TChannelOrderLogController {


    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<TChannelOrderLog>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TChannelOrderLog> aPage = tChannelOrderLogService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TChannelOrderLog> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(tChannelOrderLogService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestParam Integer channel_id,@RequestParam String tid,@RequestParam String content) {
        TChannelOrderLog tChannelOrderLog = new TChannelOrderLog();
        tChannelOrderLog.setChannelId(channel_id);
        tChannelOrderLog.setContent(content);
        tChannelOrderLogService.save(tChannelOrderLog);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        tChannelOrderLogService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody TChannelOrderLog params) {
        tChannelOrderLogService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
