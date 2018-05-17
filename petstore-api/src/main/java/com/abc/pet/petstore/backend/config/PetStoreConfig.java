package com.abc.pet.petstore.backend.config;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetStoreConfig {

    @Bean
    public DozerBeanMapper dozerBean() {
        List<String> mappingFiles = Arrays.asList("dozer-mapping.xml");

        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}
