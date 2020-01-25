package com.sdgt.medcare.master;

import com.sdgt.medcare.master.util.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterApplication.class, args);
	}
	@Bean
	public AbstractJpaDao getGeneDAO(){
		return new AbstractJpaDao();
	}
	@Autowired
	private AbstractJpaDao dao;

	}


