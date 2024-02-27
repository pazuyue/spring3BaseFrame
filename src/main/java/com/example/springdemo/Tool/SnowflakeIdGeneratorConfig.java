package com.example.springdemo.Tool;

import com.example.commonadvice.tool.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SnowflakeIdGeneratorConfig {

    @Value("${datacenter.datacenterId}")
    private Long datacenterId;

    @Value("${datacenter.machineId}")
    private Long machineId;

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        log.info("snowflakeIdGenerator-datacenterId:"+datacenterId);
        log.info("snowflakeIdGenerator-datacenterId:"+machineId);
        return new SnowflakeIdGenerator(this.datacenterId,this.machineId);
    }
}


