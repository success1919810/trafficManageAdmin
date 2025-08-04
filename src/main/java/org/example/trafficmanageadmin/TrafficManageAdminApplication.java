package org.example.trafficmanageadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.trafficmanageadmin.mapper")
public class TrafficManageAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficManageAdminApplication.class, args);
    }

}
