package com.amir.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "users",
            uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
            })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-seq")
    @SequenceGenerator(name = "user-seq", sequenceName = "user-seq", allocationSize = 1)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Integer active;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user-roles",
               joinColumns = @JoinColumn(name = "user-id"),
                inverseJoinColumns = @JoinColumn(name = "role-id"))
    private Set<Role> authorization = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email, Integer active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
    }
}
