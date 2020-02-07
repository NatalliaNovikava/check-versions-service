package com.example.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;

@SpringBootApplication
public class WebServicesApplication {

  @Autowired
  private MessageSource messageSource;

  public static void main(String[] args) {
    SpringApplication.run(WebServicesApplication.class, args);
  }
}
