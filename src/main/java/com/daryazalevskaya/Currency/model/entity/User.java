package com.daryazalevskaya.Currency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="usr")
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

    @ElementCollection(targetClass = Role.class, fetch=FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Set<Role> roles;

}
