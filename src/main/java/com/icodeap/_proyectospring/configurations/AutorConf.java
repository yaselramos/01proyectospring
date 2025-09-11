package com.icodeap._proyectospring.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutorConf {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
