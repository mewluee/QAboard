package com.example.qaboard.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

}
