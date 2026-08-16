package com.codymitra.codymitra_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.codymitra")
@EnableJpaRepositories("com.codymitra")
@ComponentScan("com.codymitra")
@ConfigurationPropertiesScan
@Slf4j
public class CodymitraServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodymitraServiceApplication.class, args);
	}

    @Value("spring.localUrl")
    private String springUrl;

    @Value("server.servlet.context-path")
    private String path;

    @Value("server.port")
    private String port;

    @Value("spring.application.name")
    private String applicationName;

    @Override
    public void run(String... args) throws Exception {
        log.info("Service Name : {}",applicationName);
        log.info("{}:{}/{}",springUrl,port,path);
    }
}
