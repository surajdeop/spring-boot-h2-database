package com.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootH2DatabaseApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SpringBootH2DatabaseApplication.class, args );
    }

}
