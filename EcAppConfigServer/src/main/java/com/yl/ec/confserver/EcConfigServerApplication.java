package com.yl.ec.confserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

//https://www.baeldung.com/spring-cloud-bus
// config server should work with bus-refresh but didin't
// see this article to fix stuff: https://www.appsdeveloperblog.com/spring-cloud-bus-refreshing-config-changes/
@SpringBootApplication
@EnableConfigServer
public class EcConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcConfigServerApplication.class, args);
	}

}
