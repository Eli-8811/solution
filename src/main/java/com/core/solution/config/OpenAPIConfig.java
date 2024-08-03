package com.core.solution.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI myOpenAPI() {
	  
      return new OpenAPI()
              .info(new Info()
                      .title("solution-api")
                      .version("1.0.0")
                      .description("solution-api")
                      .description("You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).")
                      .termsOfService("http://swagger.io/terms/")
                      .license(new License().name("Apache 2.0").url("http://springdoc.org"))).components(new Components()
                      .addSecuritySchemes("BearerToken", new SecurityScheme()
                              .type(SecurityScheme.Type.HTTP)
                              .scheme("bearer")
                              .bearerFormat("JWT")))
              .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("BearerToken"));
    
  }
  
}