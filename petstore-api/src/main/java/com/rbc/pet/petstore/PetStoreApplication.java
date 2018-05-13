package com.rbc.pet.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan(basePackages = { "com.rbc.pet.petstore" }, excludeFilters = {
// @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
// @EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, WebMvcAutoConfiguration.class })
public class PetStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }
}
