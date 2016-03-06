package com.toedter.tutorials.webapp.lab8.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.toedter.tutorials.webapp.lab8.user.User;

@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
