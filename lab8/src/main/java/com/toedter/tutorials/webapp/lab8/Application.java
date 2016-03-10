package com.toedter.tutorials.webapp.lab8;

import com.toedter.tutorials.webapp.lab8.Application;
import com.toedter.tutorials.webapp.lab8.user.UserTestDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class Application {

    @Bean(initMethod = "loadData")
    UserTestDataLoader RepositoryTestData() {
        return new UserTestDataLoader();
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
