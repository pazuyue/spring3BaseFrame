package com.example.springdemo.Controller.TChannel;

import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.springdemo.Service.TChannel.TChannelService;
import com.example.springdemo.Entity.TChannel.TChannel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 渠道表 前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/t-channel")
public class TChannelController {



    //private TChannelService tChannelService;
    @Autowired
    private TChannelServiceImpl tChannelService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<TChannel>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TChannel> aPage = tChannelService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TChannel> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(tChannelService.getTChannelByID(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TChannel params) {
        tChannelService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        tChannelService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody TChannel params) {
        tChannelService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
