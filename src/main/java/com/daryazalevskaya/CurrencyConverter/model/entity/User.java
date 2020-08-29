package com.daryazalevskaya.CurrencyConverter.model.entity;

import com.daryazalevskaya.CurrencyConverter.model.Conversion;
import com.daryazalevskaya.CurrencyConverter.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    // @NotNull
    @Getter
    @Setter
    private String username;

    // @NotNull
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean isActive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id_role"))
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Set<Role> roles;

    @ElementCollection(targetClass = Conversion.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_history_conversion", joinColumns = @JoinColumn(name = "user_id_conv"))
    @Getter
    @Setter
    private List<Conversion> conversionHistory;

}
