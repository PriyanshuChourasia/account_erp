package com.codymitra.codymitra_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.codymitra")
@EnableJpaRepositories("com.codymitra")
@ComponentScan("com.codymitra")
@Slf4j
public class CodymitraServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodymitraServiceApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("heyy");
    }
}
