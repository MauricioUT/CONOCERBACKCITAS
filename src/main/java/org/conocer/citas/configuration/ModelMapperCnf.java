package org.conocer.citas.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperCnf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
