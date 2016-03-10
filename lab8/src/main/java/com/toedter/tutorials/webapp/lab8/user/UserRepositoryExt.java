package com.toedter.tutorials.webapp.lab8.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryExt {

	Page<User> findByCoolLogic(String magicString, Pageable pagable);
}
