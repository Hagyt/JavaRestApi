package com.apijavacourse.apijavacourse.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "u_id")
    private Long id;

    @Getter @Setter @Column(name = "u_name")
    private String name;

    @Getter @Setter @Column(name = "u_lastname")
    private String lastname;

    @Getter @Setter @Column(name = "u_email")
    private String email;

    @Getter @Setter @Column(name = "u_phone")
    private String phone;

    @Getter @Setter @Column(name = "u_password")
    private String password;

}
