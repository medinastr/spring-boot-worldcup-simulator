package com.medinastr.worldcup.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class WorldCupConfig {

    @Bean
    public OpenAPI wolrdCupOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("World Cup project")
                        .description("Project to practice my REST API skills!")
                        .termsOfService("https://medinastr.com.br/worldcup")
                        .license(new License()
                                .url("https://medinastr.com.br/worldcup")));
    }
}