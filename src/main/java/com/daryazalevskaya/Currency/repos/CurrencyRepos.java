package com.daryazalevskaya.Currency.repos;

import com.daryazalevskaya.Currency.model.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CurrencyRepos extends CrudRepository<Currency, Long> {
    boolean existsByCourseDate(Date courseDate);

    List<Currency> getAllByCourseDate(Date courseDate);
}
