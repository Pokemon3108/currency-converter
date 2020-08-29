package com.daryazalevskaya.CurrencyConverter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Conversion {

    @Getter
    @Setter
    private String originalCurrency;

    @Getter
    @Setter
    private int originalMoneyAmount;

    @Getter
    @Setter
    private String receivedCurrency;

    @Getter
    @Setter
    private int receivedMoneyAmount;

    @Getter
    @Setter
    private Date date;

}
