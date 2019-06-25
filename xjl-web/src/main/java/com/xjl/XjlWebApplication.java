package com.xjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.xjl.dao")
@EnableAutoConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = {"com.xjl"})
public class XjlWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(XjlWebApplication.class, args);
	}

}
