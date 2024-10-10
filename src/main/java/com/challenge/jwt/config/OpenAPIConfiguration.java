package com.challenge.jwt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       List<Server> serverList = new ArrayList<>();

       Server server = new Server();
       server.setUrl("http://localhost:8080");
       server.setDescription("Development - Local");
       serverList.add(server);

       server = new Server();
       server.setUrl("http://localhost:9000");
       server.setDescription("Development - Docker");
       serverList.add(server);

       Contact myContact = new Contact();
       myContact.setName("Naldo");
       myContact.setEmail("jcf.naldo7@gmail.com");

       Info information = new Info()
               .title("JWT Management System API")
               .version("1.0")
               .description("Esta API disponibiliza um endpoint que valida tokens JWT.")
               .contact(myContact);

       return new OpenAPI().info(information).servers(serverList);
   }
}