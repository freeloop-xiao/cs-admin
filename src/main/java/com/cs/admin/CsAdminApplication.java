package com.cs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 启动控制器
 * </p>
 * 开始
 * @author free loop
 * @since 2020-12-31
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class CsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsAdminApplication.class, args);
    }

}
