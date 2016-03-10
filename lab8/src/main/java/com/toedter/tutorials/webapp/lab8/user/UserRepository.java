package com.toedter.tutorials.webapp.lab8.user;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, String>, UserRepositoryExt {

	/**
	 * Example of a method which is automatically exported the the REST API.
	 * @param email the email to search for
	 * @return the matching users (usually only one)
	 */
	List<User> findByEmail(String email);

}
