package com.config;

import com.web.BaseFeign;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignConfig {

    @Bean
    BaseFeign baseFeign(){
        return Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
                .target(BaseFeign.class,"http://localhost:9001/label");
    }

}
