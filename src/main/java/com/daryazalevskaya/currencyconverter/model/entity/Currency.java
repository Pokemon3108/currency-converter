package com.daryazalevskaya.currencyconverter.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date courseDate;

}
