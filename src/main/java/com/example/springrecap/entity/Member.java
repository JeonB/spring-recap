package com.example.springrecap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String username;

    @Column(length = 500)
    private String password;

    private String displayName;

    public Member orElseThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
        if (this == null) {
            throw exceptionSupplier.get();
        }
        return this;
    }
}
