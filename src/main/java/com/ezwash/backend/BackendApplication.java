package com.ezwash.backend;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
@EnableJpaAuditing
@SpringBootApplication
public class BackendApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("postgres://dxwwkedunljski:4f7cb4c76d0b0fefa1178212c152ae440ddd2d26959ac45746a2428b0eb8f6eb@ec2-34-193-113-223.compute-1.amazonaws.com:5432/db0ao2sva7m71v");
        dataSource.setUsername("dxwwkedunljski");
        dataSource.setPassword("4f7cb4c76d0b0fefa1178212c152ae440ddd2d26959ac45746a2428b0eb8f6eb");
        return dataSource;
    }

}
