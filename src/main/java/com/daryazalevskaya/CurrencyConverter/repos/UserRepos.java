package com.daryazalevskaya.CurrencyConverter.repos;

import com.daryazalevskaya.CurrencyConverter.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
