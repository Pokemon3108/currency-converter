package com.daryazalevskaya.Currency.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private int numCode;

    @Column(length = 3)
    @Getter
    @Setter
    private String charCode;

    @Getter
    @Setter
    private int nominal;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double value;

    @Getter
    @Setter
    private String currencyId;


}
