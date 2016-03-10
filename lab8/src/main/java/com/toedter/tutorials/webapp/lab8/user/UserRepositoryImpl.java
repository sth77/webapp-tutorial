package com.toedter.tutorials.webapp.lab8.user;

import static com.toedter.tutorials.webapp.lab8.user.QUser.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.JPQLQuery;


/**
 * Sample implementation of a custom finder method using querydsl.
 *
 * @author Stefan Heinzer <stefan.heinzer@kwsoft.ch>
 */
public class UserRepositoryImpl extends QueryDslRepositorySupport implements UserRepositoryExt {

	public UserRepositoryImpl() {
		super(User.class);
	}

	@Override
	public Page<User> findByCoolLogic(String magicString, Pageable pageable) {
        JPQLQuery query = from(user)
                .where(user.fullName.contains(magicString)
                		.or(user.email.contains(magicString)));
        List<User> entities = query.distinct().list(user);
        int limit = Math.min(entities.size(), pageable.getPageSize());
        return new PageImpl<User>(entities.subList(0, limit-1), pageable, entities.size());
	}
}
