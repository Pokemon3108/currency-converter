package com.daryazalevskaya.Currency.repos;

import com.daryazalevskaya.Currency.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
