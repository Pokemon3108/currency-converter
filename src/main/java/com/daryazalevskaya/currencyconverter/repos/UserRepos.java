package com.daryazalevskaya.currencyconverter.repos;

import com.daryazalevskaya.currencyconverter.model.entity.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepos extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
