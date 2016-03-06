package com.toedter.tutorials.webapp.lab8.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.toedter.tutorials.webapp.lab8.user.User;

@Configuration
public class UserConfiguration extends RepositoryRestConfigurerAdapter {
 
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
    }
}