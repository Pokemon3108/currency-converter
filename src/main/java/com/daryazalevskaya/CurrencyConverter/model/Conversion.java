package com.daryazalevskaya.CurrencyConverter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@NoArgsConstructor
public class Conversion {

    @Getter
    @Setter
    private String originalCurrency;

    @Getter
    @Setter
    private double originalMoneyAmount;

    @Getter
    @Setter
    private String receivedCurrency;

    @Getter
    @Setter
    private double receivedMoneyAmount;

    @Getter
    @Setter
    private Date date;

}
