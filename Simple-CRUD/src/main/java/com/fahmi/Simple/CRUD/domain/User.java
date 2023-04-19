package com.fahmi.Simple.CRUD.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "tbl_user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_user_seq")
    private Integer userid;

    private String namalengkap;

    @Column(unique = true)
    private String username;

    private String password;

    private char status;
}
