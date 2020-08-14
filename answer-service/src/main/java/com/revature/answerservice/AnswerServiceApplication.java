package com.revature.answerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@SpringBootApplication
public class AnswerServiceApplication {

  public static void main(String[] args) {
    //
      SpringApplication.run(AnswerServiceApplication.class, args);
  }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*");
            }
        };
    }

    @Bean
    public WebMvcConfigurer configureContent() {
        return new WebMvcConfigurer() {
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
            }
        };
    }
}
