package com.airwallex.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RpcApplication {

	public static void main(String[] args) {
		//SpringApplication.run(RpcApplication.class, args);
		new SpringApplicationBuilder()
				.sources(RpcApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

	}



}
